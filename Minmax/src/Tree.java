public class Tree {
  
  private State state;
  
  private Triplet minimaxValue;
  
  private Tree leftChild;
  
  private Tree rightChild;
  
  public Tree(State state) {
    this.state = state;

    if (state.getLeftBar() != state.getRightBar()) {
      leftChild = new Tree(state.playLeft());
      rightChild = new Tree(state.playRight());
    }
  }
  
  private static Triplet minBlue(Triplet leftRes, Triplet rightRes) {
    if (leftRes.getMinBlue() < rightRes.getMinBlue()) {
      return leftRes;
    } else if (leftRes.getMinBlue() > rightRes.getMinBlue()) {
      return rightRes;
    }
    return new Triplet(leftRes.isLeftMove(), leftRes.getMinBlue(), leftRes.getMinOrange());
  }
  
  private static Triplet minOrange(Triplet leftRes, Triplet rightRes) {
    if (leftRes.getMinOrange() < rightRes.getMinOrange()) {
      return leftRes;
    } else if (leftRes.getMinOrange() > rightRes.getMinOrange()) {
      return rightRes;
    }
    return new Triplet(rightRes.isLeftMove(), rightRes.getMinBlue(), rightRes.getMinOrange());
  }
  
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
  
  public boolean isLeaf() {
    return leftChild == null;
  }
  
  public Triplet getMinimaxValue() {
    return minimaxValue;
  }
  
  public State getState() {
    return state;
  }
  
  public Tree getLeftChild() {
    return leftChild;
  }
  
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
