import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Tree implements Iterable<Tree> {

	private int value;

	private Tree parent;

	private Tree[] children;

	// *******************************************************
	// CONSTRUCTEURS
	// *******************************************************
	public Tree(int v, Tree[] chd) {
		value = v;
		children = chd;

		for (Tree child : chd) {
			child.parent = this;
		}
	}

	public Tree(int v) {
		this(v, new Tree[0]);
	}

	// *******************************************************
	// GETTERS
	// *******************************************************
	public int getValue() {
		return value;
	}

	public Tree getParent() {
		return parent;
	}

	public Tree[] getChildren() {
		return children;
	}

	// *******************************************************
	// ITERATEUR
	// *******************************************************
	public Iterator<Tree> iterator() {
		return Arrays.asList(children).iterator();
	}

	public int nbrChildren() {
		return children.length;
	}

	public boolean isLeaf() {
		return children.length == 0;
	}

	// Cette méthode renvoie une copie de this càd une nouvelle instance de Tree
	// ayant les mêmes éléments au même endroit que l'objet courant
	public Tree clone() {
		Tree[] newChildren = new Tree[children.length];
		for (int i = 0; i < children.length; i++) {
			newChildren[i] = children[i].clone();
		}
		return new Tree(value, newChildren);
	}
	
	// Cette méthode imprime à la sortie standard tous les noeuds 
	// suivi de leurs ancêtres jusqu’à la racine.
	public void afficherNoeudsAvecAncetres() {
		if (this.getParent() == null) {
			System.out.println(this.value);
		} else {
			Tree parent = this.getParent();
			System.out.print(this.value + " " + parent.value);
			while (parent.getParent() != null) {
				parent = parent.getParent();
				System.out.print(" " + parent.value);
			}
			System.out.println();
		}
		for (Tree child : children) {
			child.afficherNoeudsAvecAncetres();
		}
	}
	

	public static void main(String[] args) {
		Tree l6 = new Tree(6);
		Tree l1 = new Tree(1);
		Tree t9 = new Tree(9, new Tree[] { l6, l1 });
		Tree l3 = new Tree(3);
		Tree l8 = new Tree(8);
		Tree t8 = new Tree(8, new Tree[] { l3, l8 });
		Tree l4 = new Tree(4);
		Tree t4 = new Tree(4, new Tree[] { t8, t9, l4});
		Tree t4bis= t4.clone();
		t4.afficherNoeudsAvecAncetres();
		System.out.println("-----------");
		t4bis.afficherNoeudsAvecAncetres();
	}
}

