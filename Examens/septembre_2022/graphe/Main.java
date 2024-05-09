
public class Main {
	public static void main(String[] args) throws Exception {
		Graph g = new Graph("./graphe/flight.xml");
		g.bfs(g.getAirport("JFK"));
	}
}
