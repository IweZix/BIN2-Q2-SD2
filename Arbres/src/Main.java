
public class Main {

  public static void main(String[] args){

    Tree l1 = new Tree(1);
    Tree l3 = new Tree(3);
    Tree l5 = new Tree(5);
    Tree l7 = new Tree(7);
    
    Tree t2 = new Tree(2, new Tree[]{l1, l3});
    Tree t6 = new Tree(6, new Tree[]{l7});
    
    Tree t4 = new Tree(4, new Tree[]{t2, l5, t6});

    System.out.println(Trees.nbrLeaves(t4));
    
    
    Tree[] ls = Trees.flattenLeaves(t4);
    System.out.println("Les " + ls.length + " feuilles");
    int i = 0;
    while(i != ls.length) {
      System.out.println(ls[i].getValue());
      i++;
    }

    System.out.println("Path V1");    
    Trees.pathV1(l7);

    System.out.println("Path V2");    
    Trees.pathV2(l7);

    System.out.println();
    System.out.println("********************");
    System.out.println("*        Exercice 1       *");
    System.out.println("********************");
    System.out.println();

    //                     t4
    //           /          |         \
    //         t2         i5         t6
    //       /     \                    |
    //     l1      l3                 l7

    // 3 noeuds : {t4, t2, t6, l5, l1, l3, l7}
    // 4 feuilles : {l5, l1, l3, l7}

    // 1.1)
    System.out.println("1.1) Recherche du nombre de Noeud de t4 : ");
    System.out.println("Nombre de Noeud attendu : 7");
    System.out.println("Nombre de Noeud trouvé : " + Trees.nbrNode(t4));
    System.out.println();

    // 1.2)
    System.out.println("1.2) Recherche de la valeur minimale de t4 : ");
    System.out.println("Valeur minimale attendue : 1");
    System.out.println("Valeur minimale trouvée : " + Trees.min(t4));
    System.out.println();

    // 1.3)
    System.out.println("1.3) Recherche de la somme de t4 : ");
    System.out.println("Valeur de la somme attendue : 28");
    System.out.println("Valeur de la somme trouvée : " + Trees.sum(t4));
    System.out.println();

    // 1.4)
    System.out.println("1.4) Comparaison de t4 et t4 : ");
    System.out.println("Comparaison attendue : true");
    System.out.println("Comparaison trouvée : " + Trees.equals(t4, t4));
    System.out.println();

    System.out.println("1.4) Comparaison de t4 et t2 : ");
    System.out.println("Comparaison attendue : false");
    System.out.println("Comparaison trouvée : " + Trees.equals(t4, t2));
    System.out.println();

    // 1.5)
    System.out.println("1.5) recherche de la longeur entre l1 et t4");
    System.out.println("Longeur attendue : 2");
    System.out.println("Longeur trouvée : " + Trees.depth(l1));
    System.out.println();

    // 1.6)
    System.out.println("1.6) Vérifie si I3 et t6 appartiennent au même arbre");
    System.out.println("Vérification attendue : true");
    System.out.println("Vérification trouvée : " + Trees.sameOne(l3, t6));
    System.out.println();

    // 1.7)
    System.out.println("1.7) Parcours en profondeur de t4");
    System.out.println("Parcours attendu : 4 2 1 3 5 6 7");
    System.out.print("Parcours trouvé : ");
    Trees.dfsPrint(t4);
    System.out.println("\n");

    // 1.8)
    System.out.println("1.8) Parcours en largeur de t4");
    System.out.println("Parcours attendu : 4 2 6 1 3 5 7");
    System.out.print("Parcours trouvé : ");
    Trees.bfsPrint(t4);
    System.out.println("\n");

    // 2.1)
    System.out.println("2.1) Recherche du chemin de I3 à t4");
    System.out.println("Chemin attendu : 4 2 3");
    System.out.print("Chemin trouvé : ");
    Trees.printPathV1(l3);
    System.out.println("\n");

    // 2.2)
    System.out.println("2.2) Recherche du chemin de I3 à t4");
    System.out.println("Chemin attendu : 4 2 3");
    System.out.print("Chemin trouvé : ");
    Trees.printPathV2(l3);
    System.out.println("\n");

    // 2.3)
    System.out.println("2.3) Recherche du chemin de I3 à t6");
    System.out.println("Chemin attendu : 3 2 4 6");
    System.out.print("Chemin trouvé : ");
    Trees.printPathV3(l3, 6);
    System.out.println("\n");


    // 3.1)
    Tree leaf1 = new Tree(1);
    Tree tree2 = new Tree(2, new Tree[]{leaf1});

    Tree leaf3 = new Tree(3);
    Tree leaf7 = new Tree(7);
    Tree tree6 = new Tree(6, new Tree[]{leaf3, leaf7});

    Tree tree4 = new Tree(4, new Tree[]{tree2, tree6});

    System.out.println("3.1) Récupération du tableau de T4");
    System.out.println("Tableau attendu : ");
    System.out.println("{4 2  1  6  3  7 }");
    System.out.println("{1  2 -1 4 -1 -1}");
    System.out.println("{3 -1 -1 5 -1 -1}");
    int[][] tab = Trees.toArray(tree4);
    System.out.println("Tableau trouvé : ");
    for (int k = 0; k < tab.length; k++) {
      System.out.print("{");
      for (int l = 0; l < tab[k].length; l++) {
        System.out.print(tab[k][l] + " ");
      }
      System.out.println("}");
    }
    System.out.println();
  }
}