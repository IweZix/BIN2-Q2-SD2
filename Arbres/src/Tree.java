import java.util.Arrays;
import java.util.Iterator;

public class Tree implements Iterable<Tree> {

	private int value;

	private Tree parent;

	private Tree[] children;

	// *******************************************************
	// CONSTRUCTEURS
	// *******************************************************

	/**
	 * Constructeur d'un arbre
	 * @param v la valeur de l'arbre
	 * @param chd les enfants de l'arbre
	 */
	public Tree(int v, Tree[] chd) {
		value = v;
		children = chd;

		for (Tree child : chd) {
			child.parent = this;
		}
	}

	/**
	 * Constructeur d'un arbre sans enfant
	 * @param v la valeur de l'arbre
	 */
	public Tree(int v) {
		this(v, new Tree[0]);
	}

	// *******************************************************
	// GETTERS
	// *******************************************************

	/**
	 * Retourne la valeur de l'arbre
	 * @return la valeur de l'arbre
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Retourne le parent de l'arbre
	 * @return le parent de l'arbre
	 */
	public Tree getParent() {
		return parent;
	}

	/**
	 * Retourne les enfants de l'arbre
	 * @return les enfants de l'arbre
	 */
	public Tree[] getChildren() {
		return children;
	}

	// *******************************************************
	// ITERATEUR
	// *******************************************************

	/**
	 * Retourne un itérateur sur les enfants de l'arbre
	 * @return un itérateur sur les enfants de l'arbre
	 */
	public Iterator<Tree> iterator() {
		return Arrays.asList(children).iterator();
	}

	/**
	 * Retourne le nombre d'enfants de l'arbre
	 * @return le nombre d'enfants de l'arbre
	 */
	public int nbrChildren() {
		return children.length;
	}

	/**
	 * Vérifie si l'arbre est une feuille
	 * @return  true si l'arbre est une feuille, false sinon
	 */
	public boolean isLeaf() {
		return children.length == 0;
	}
}
