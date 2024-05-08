import java.io.File;

public class Main {

  public static void main(String[] args) {
    try {
      File lignes = new File("./graphe/lignes.txt");
      File troncons = new File("./graphe/troncons.txt");
      Graph g = new Graph(lignes, troncons);
      g.tronconsEntrants(g.getStation("ALMA")).forEach(System.out::println);
      System.out.println("--------------------------------------");
      g.calculerCheminMinimisantNombreTroncons("BOILEAU", "ALMA");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
