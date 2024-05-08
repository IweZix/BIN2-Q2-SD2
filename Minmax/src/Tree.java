// Cette classe représente l'arbre du jeu.
public class Tree {

  // L'état du jeu correspondant à un noeud de l'arbre.
  private State state;

  // La valeur Minimax dans cet état.
  private Triplet minimaxValue;

  // null si le noeud courant est une feuille, le fils de gauche sinon.
  private Tree leftChild;

  // null si le noeud courant est une feuille, le fils de droite sinon.
  private Tree rightChild;

  /**
   * Construit un arbre à partir d'un état.
   * @param state l'état initial de l'arbre.
   */
  public Tree(State state) {
    this.state = state;

    if (state.getLeftBar() != state.getRightBar()) {
      leftChild = new Tree(state.playLeft());
      rightChild = new Tree(state.playRight());
    }
  }

  /**
   * Renvoie la valeur Minimax du joueur bleu en fonction des valeurs Minimax de ses fils.
   * @param leftRes valeur fils gauche
   * @param rightRes valeur fils gauche
   * @return un triplet contenant la valeur Minimax du joueur bleu.
   */
  private static Triplet minBlue(Triplet leftRes, Triplet rightRes) {
    if (leftRes.getMinBlue() < rightRes.getMinBlue()) {
      return leftRes;
    } else if (leftRes.getMinBlue() > rightRes.getMinBlue()) {
      return rightRes;
    }
    return new Triplet(leftRes.isLeftMove(), leftRes.getMinBlue(), rightRes.getMinOrange());
  }

  /**
   * Renvoie la valeur Minmax du joueur orange en fonction des valeurs Minmax de ses fils
   * @param leftRes valeur fils gauche
   * @param rightRes valeur fils gauche
   * @return un triplet contenant la valeur Minmax du joueur orange.
   */
  private static Triplet minOrange(Triplet leftRes, Triplet rightRes) {
    if (leftRes.getMinOrange() < rightRes.getMinOrange()) {
      return leftRes;
    } else if (leftRes.getMinOrange() > rightRes.getMinOrange()) {
      return rightRes;
    }
    return new Triplet(rightRes.isLeftMove(), leftRes.getMinBlue(), rightRes.getMinOrange());
  }

  // Calcule les valeurs Minimax de tout l'arbre.
  // En pratique, cette méthode calcule pour chaque noeud de l'arbre un nouveau
  // Triplet représentant les valeurs Minimax de chaque noeud.
  public void computeMinimaxValues() {
    if (this.isLeaf()) {
      minimaxValue = new Triplet(false, state.getBluePoints(), state.getOrangePoints());
    } else {
      leftChild.computeMinimaxValues();
      rightChild.computeMinimaxValues();
      if (state.isBlueToPlay()) {
        minimaxValue = minBlue(leftChild.getMinimaxValue(), rightChild.getMinimaxValue());
      } else {
        minimaxValue = minOrange(leftChild.getMinimaxValue(), rightChild.getMinimaxValue());
      }
    }
  }

  // Renvoie true si le noeud est une feuille, false sinon.
  public boolean isLeaf() {
    return leftChild == null;
  }
  
  // Getter de la valeur Minimax
  public Triplet getMinimaxValue() {
    return minimaxValue;
  }

  // Getter de l'état courant
  public State getState() {
    return state;
  }

  // Getter du fils de gauche
  public Tree getLeftChild() {
    return leftChild;
  }

  // Getter du fils de droite
  public Tree getRightChild() {
    return rightChild;
  }

  public int nbrNode() {
    int res = 1;
    if (!isLeaf()) {
      res += leftChild.nbrNode() + rightChild.nbrNode();
    }
    return res;
  }
}
