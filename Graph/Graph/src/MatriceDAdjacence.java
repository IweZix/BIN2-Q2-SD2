import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatriceDAdjacence extends Graph{

    private Map<Integer, Airport>  correspondanceIndiceAirport;
    private Map<Airport, Integer>  correspondanceAirportIndice;
    private Flight[][] matrice= new Flight[0][0];
    private int nbAirport=0;

    public MatriceDAdjacence() {
        super();
        correspondanceAirportIndice= new HashMap<Airport,Integer>();
        correspondanceIndiceAirport= new HashMap<Integer,Airport>();
    }

    @Override
    // Complexit�: ?
    protected void ajouterSommet(Airport a) {
        correspondanceAirportIndice.put(a,nbAirport);
        correspondanceIndiceAirport.put(nbAirport,a);
        nbAirport++;
        matrice = new Flight[nbAirport][nbAirport];
    }

    @Override
    // Complexit�: ?
    protected void ajouterArc(Flight f) {
        int d = correspondanceAirportIndice.get(f.getSource());
        int a = correspondanceAirportIndice.get(f.getDestination());
        matrice[d][a] = f;
    }

    @Override
    // Complexit�: o(n)
    public Set<Flight> arcsSortants(Airport a) {
        HashSet<Flight> result = new HashSet<Flight>();
        int airport = correspondanceAirportIndice.get(a);
        for (int i = 0; i < nbAirport; i++) {
            if (matrice[airport][i] != null) {
                result.add(matrice[airport][i]);
            }
        }
        return result;
    }

    @Override
    // Complexit�: ?
    public boolean sontAdjacents(Airport a1, Airport a2) {
        int airport1 = correspondanceAirportIndice.get(a1);
        int airport2 = correspondanceAirportIndice.get(a2);
        if (matrice[airport1][airport2] != null || matrice[airport2][airport1] != null) {
            return true;
        }
        return false;
    }
}
