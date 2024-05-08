import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ListeDArc extends Graph{

    private ArrayList<Flight> flights;

    public ListeDArc() {
        super();
        flights=new ArrayList<Flight>();
    }

    @Override
    // Complexit�: o(1)
    protected void ajouterSommet(Airport a) {
        return;
    }

    @Override
    // Complexit�: o(1)
    protected void ajouterArc(Flight f) {
        flights.add(f);
    }

    @Override
    // Complexit�: o(n)
    public Set<Flight> arcsSortants(Airport a) {
        HashSet<Flight> result = new HashSet<Flight>();
        for (Flight flight : flights) {
            if (flight.getDestination().equals(a))
                result.add(flight);
        }
        return result;
    }

    @Override
    // Complexit�: o(n)
    public boolean sontAdjacents(Airport a1, Airport a2) {
        for (Flight f : flights) {
            if ((f.getSource().equals(a1) && f.getDestination().equals(a2)) || (f.getSource().equals(a2) && f.getDestination().equals(a1)))
                return true;
        }
        return false;
    }

}
