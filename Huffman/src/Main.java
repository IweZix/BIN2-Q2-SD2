import java.util.Map;


public class Main {

  public static void main(String[] args) {
    String s = "Bonjour! Au revoir!";
    Map<Character, Integer> freq = Huffman.computeFreq(s);
    Huffman.Node root = Huffman.buildTree(freq);
    System.out.println("Nbre de lettre dansla chaine "
        + "de caractère à encoder: "+ root.freq);
    System.out.println("Fréquence des lettres dans le sous-arbre "
        + "de gauche: "+ root.left.freq);
    System.out.println("Fréquence des lettres dans le sous-arbre de "
        + "droite: "+ root.right.freq);

    System.out.println();

    Map<Character, String> code = Huffman.buildCode(root);
    System.out.println(code);

    System.out.println();

    String compressed = Huffman.compress(s, code);
    System.out.println(compressed);

    System.out.println();

    String originalText = Huffman.expand(root, compressed);
    System.out.println(originalText);
  }

}
