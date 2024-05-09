import com.sun.security.jgss.GSSUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogue {
	
	//Vous pouvez ajouter des attributs et/ou des constructeurs
	Map<Integer, List<Film>> catalogue = new HashMap<>();

	//ajoute le film au catalogue
	public void ajouterFilm(Film f) {
		int year = f.getAnnee();
		if (!catalogue.containsKey(year)) {
			catalogue.put(year, new ArrayList<>());
		}
		catalogue.get(year).add(f);
	}
	
	// affiche tous les films de l'année en paramètre par ordre alphabétique
	// utilise le toString() de Film
	public void afficherFilmParOrdreAlphabetique (int annee) {
		if (catalogue.get(annee) == null) {
			System.out.println("Pas de film en " + annee);
		} else {
			List<Film> films = catalogue.get(annee);
			films.sort(Comparator.comparing(Film::getNom));
			for (Film film : films) {
				System.out.println(film);
			}
		}
	}
}
