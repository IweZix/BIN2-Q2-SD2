import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Huffman Algorithm Class.
 */
public class Huffman {

  /**
   * Intern Node Class.
   */
  public static class Node  {
    public char ch;
    public int freq;
    public final Node left, right;

    public Node(char ch, int freq, Node left, Node right) {
      this.ch = ch;
      this.freq = freq;
      this.left = left;
      this.right = right;
    }

    public boolean isLeaf() {
      return left == null && right == null;
    }

  }

  /**
   * Return a map with Character and occurrence of the Character
   * @param s String put in the map
   * @return map with Character and occurrence of the Character
   */
  public static Map<Character, Integer> computeFreq(String s) {
    // Create a map with Character and occurrence of the Character
    Map<Character, Integer> freq = new HashMap<>();
    // For each character in the string
    for (int i = 0; i < s.length(); i++) {
      // Get the character
      Character c = s.charAt(i);
      // If the character is already in the map
      if (freq.containsKey(c)) {
        // Increment the occurrence of the character
        freq.put(c, freq.get(c) + 1);
      } else {
        // Else add the character in the map
        freq.put(c, 1);
      }
    }
    // Finally return the map
    return freq;
  }

  /**
   * Build the tree with the frequency of the character ordered by the frequency
   * @param freq Map with Character and occurrence of the Character
   * @return Node of the tree
   */
  public static Node buildTree(Map<Character, Integer> freq) {
    // Create a priority queue
    PriorityQueue<Node> priorityQueue = new PriorityQueue<>(
        // Size of the map
        freq.size(),
        // Comparator to order the node by the frequency
        Comparator.comparingInt(a -> a.freq)
    );

    // For each entry in the map <Character, Integer>
    for (Entry<Character, Integer> entry : freq.entrySet()) {
      // Add a new node in the priority queue
      priorityQueue.add(new Node(entry.getKey(), entry.getValue(), null, null));
    }

    // While the size of the priority queue is greater than 1
    while (priorityQueue.size() > 1) {
      // Get the first node
      Node first = priorityQueue.poll();
      // Get the second node
      Node second = priorityQueue.poll();
      // Add a new node in the priority queue with the sum of the frequency of the first and the second node
      priorityQueue.add(new Node(' ', (first.freq + second.freq), first, second));
    }

    // Return the first node of the priority queue
    return priorityQueue.poll();
  }

  /**
   * Build the code with the tree
   * @param root Node of the tree
   * @return Map with Character and code of the Character
   */
  public static Map<Character, String> buildCode(Node root) {
    // Create a map with Character and code of the Character
    Map<Character, String> codeMap = new HashMap<>();
    // Build the code
    buildCode(codeMap, root, "");
    // Return the map
    return codeMap;
  }

  /**
   * Build the code with the tree
   * @param codeMap Map to put the code
   * @param node Node of the tree
   * @param s String of the code
   */
  private static void buildCode(Map<Character, String> codeMap, Node node, String s) {
    // if map is a leaf it's the end of the tree
    // so we can add to the code map that has been created bcs we can't add anything else
    if (node.isLeaf()) {
      codeMap.put(node.ch, s);
      // if it's not a leaf
    } else {
      // so  we build the code for the left node by adding 0 to s because we go down to the left
      buildCode(codeMap, node.left, s + '0');
      // so  we build the code for the right node by adding 1 to s because we go down to the right
      buildCode(codeMap, node.right, s + '1');
    }
  }


  /**
   * Compress a string with the code map
   * @param s String to compress
   * @param codeMap Map with Character and code of the Character
   * @return Compressed string
   */
  public static String compress(String s, Map<Character, String> codeMap) {
    // Create a string builder where we will put the compressed string
    StringBuilder sb = new StringBuilder();
    // For each character in the string
    for (char c : s.toCharArray()) {
      // Add the code of the character in the string builder
      sb.append(codeMap.get(c));
    }
    // Return the compressed string
    return sb.toString();
  }


  /**
   * Decompress a string with the tree
   * @param root Node of the tree to get the character
   * @param t String to decompress
   * @return Decompressed string
   */
  public static String expand(Node root, String t) {
    // Create a string builder where we will put the decompressed string
    StringBuilder sb = new StringBuilder();
    // Get the frequency of the root
    int freq = root.freq;
    // Create a counter to stop the decompression
    int compteur = 0;
    // Create a current node to go down in the tree
    Node current = root;
    // For each character in the string
    for (char c : t.toCharArray()) {
      // If the character is 1
      if (c == '1') {
        // Go down to the right
        current = current.right;
      } else {
        // Go down to the left
        current = current.left;
      }
      // If the current node is a leaf
      if (current.isLeaf()) {
        // Add the character in the string builder bcs we have found the character
        sb.append(current.ch);
        // Go back to the root
        current = root;
        // Increment the counter
        compteur++;
      }
      // If the counter is equal to the frequency
      if (compteur == freq) {
        // Stop the decompression
       break;
      }
    }
    return sb.toString();
  }

}
