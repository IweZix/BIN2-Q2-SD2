import java.io.File;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Graph {

	private final Map<Station, Set<Troncon>> mapStationTroncons;
	private final Map<String, Station> mapNomStation;
	private final Map<Integer, Ligne> mapLignes;

	public Graph(File lignes, File troncons) throws Exception {
		mapLignes = new HashMap<>();
		mapStationTroncons = new HashMap<>();
		mapNomStation = new HashMap<>();

		List<String> allLines = Files.readAllLines(lignes.toPath());
		for (String line : allLines) {
			String[] attributes = line.split(",");
			int id = Integer.parseInt(attributes[0]);
			String numero = attributes[1];
			Station depart = getStation(attributes[2]);
			Station destination = getStation(attributes[3]);
			String transport = attributes[4];
			int tempsAttenteMoyen = Integer.parseInt(attributes[5]);
			mapLignes.put(id, new Ligne(id, numero, depart, destination, transport, tempsAttenteMoyen));
		}

		allLines = Files.readAllLines(troncons.toPath());

		for (String line : allLines) {
			String[] attributes = line.split(",");
			Ligne ligne = mapLignes.get(Integer.parseInt(attributes[0]));
			Station depart = getStation(attributes[1]);
			Station arrivee = getStation(attributes[2]);
			int duree = Integer.parseInt(attributes[3]);
			Troncon troncon = new Troncon(ligne, depart, arrivee, duree);
			if (mapStationTroncons.containsKey(depart)) {
				mapStationTroncons.get(depart).add(troncon);
			} else {
				HashSet<Troncon> tronconsSet = new HashSet<>();
				tronconsSet.add(troncon);
				mapStationTroncons.put(depart, tronconsSet);
			}
		}
	}

	public Station getStation(String nomStation) {
		Station station;
		if (!mapNomStation.containsKey(nomStation)) {
			station = new Station(nomStation);
			mapNomStation.put(station.getNom(), station);
		} else {
			station = mapNomStation.get(nomStation);
		}
		return station;
	}
	
	// renvoie l'ensemble des tronçons entrants de la station 
	// càd les tronçons dont l'arrivee est la station en paramètre
	public Set<Troncon> tronconsEntrants(Station s){
		Set<Troncon> set = new HashSet<>();
		for (Entry<Station, Set<Troncon>> stationSetEntry : mapStationTroncons.entrySet()) {
			Set<Troncon> toVerify = stationSetEntry.getValue();
			for (Troncon troncon : toVerify) {
				if (troncon.getArrivee().equals(s))
					set.add(troncon);
			}
		}
		return set;
	}

	// affiche le chemin le plus court (en terme de nombre de troncon) entre la station de départ
	// et la station d'arrivée
	// Pour afficher les troncons, on utilise simplement la méthode toString() de Troncon
	public void calculerCheminMinimisantNombreTroncons(String depart, String arrivee) {
		Queue<Station> file = new ArrayDeque<Station>();
		Set<Station> visites = new HashSet<>();
		Map<Station, Station> previousStation = new HashMap<>();

		Station stationDepart= mapNomStation.get(depart);
		Station stationArrivee= mapNomStation.get(arrivee);

		file.add(stationDepart);
		visites.add(stationDepart);

		while (!file.isEmpty()) {
			Station current = file.remove();
			if (current.equals(stationArrivee))
				break;

			for (Troncon t :  mapStationTroncons.get(current)) {
				Station nexStation = t.getArrivee();
				if (!visites.contains(t.getArrivee())) {
					file.add(nexStation);
					visites.add(nexStation);
					previousStation.put(nexStation, current);
				}
			}
		}

		List<Station> path = new ArrayList<>();
		for (Entry<Station, Station> stationStationEntry : previousStation.entrySet()) {
			Station current = stationStationEntry.getKey();
			path.add(current);
			while (previousStation.containsKey(current)) {
				current = previousStation.get(current);
				path.add(current);
			}
		}

		Collections.reverse(path);

		for (Station station : path) {
			System.out.println(station);
		}
	}
	 



}
