import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trees {

	// *******************************************************
	// Un premier exemple: le nombre de feuilles d'un arbre
	// *******************************************************
	public static int nbrLeaves(Tree t) {
		int r;
		if (t.isLeaf()) {
			r = 1;
		} else {
			r = 0;
			for (Tree child : t) {
				r += nbrLeaves(child);
			}
		}
		return r;
	}

	// *******************************************************
	// Un deuxième exemple: aplanir un arbre
	// *******************************************************
	public static Tree[] flattenLeaves(Tree t) {
		int nl = nbrLeaves(t);
		Tree[] r = new Tree[nl];
		flattenLeaves(t, r, 0);
		return r;
	}

	private static int flattenLeaves(Tree t, Tree[] a, int idx) {
		int r;
		if (t.isLeaf()) {
			a[idx] = t;
			r = 1;
		} else {
			r = 0;
			for (Tree child : t) {
				r += flattenLeaves(child, a, idx + r);
			}
		}
		return r;
	}

	// *******************************************************
	// Un troisième exemple:
	// tous les algorithmes ne sont pas récursifs
	// *******************************************************
	public static void pathV1(Tree t) {
		System.out.println(t.getValue());
		if (t.getParent() != null) {
			pathV1(t.getParent());
		}
	}

	public static void pathV2(Tree t) {
		while (t != null) {
			System.out.println(t.getValue());
			t = t.getParent();
		}
	}

	// *******************************************************
	// Exercices 1
	// *******************************************************

	// 1.1)
	public static int nbrNode(Tree t) {
			int nodes = 1;
			for (Tree child : t.getChildren()) {
				nodes += nbrNode(child);
			}
			return nodes;
	}

	// 1.2)
	public static int min(Tree t) {
		int value = t.getValue();
		for (Tree child : t.getChildren()) {
			int min = min(child);
			if (min < value) {
				value = min;
			}
		}
    return value;
  }

	// 1.3)
	public static int sum(Tree t) {
		int sum = t.getValue();
		for (Tree child : t.getChildren()) {
			sum += sum(child);
		}
		return sum;
	}

	// 1.4)
	public static boolean equals(Tree t1, Tree t2) {
		if (t1.getValue() != t2.getValue()) {
			return false;
		}
		if (t1.getChildren().length != t2.getChildren().length) {
			return false;
		}
		for (int i = 0; i < t1.getChildren().length; i++) {
			if (!equals(t1.getChildren()[i], t2.getChildren()[i])) {
				return false;
			}
		}
		return true;
	}

	// 1.5)
	public static int depth(Tree n) {
		return n.getParent() != null
				? 1 + depth(n.getParent())
				: 0;
	}

	// 1.6)
	public static boolean sameOne(Tree n1, Tree n2) {
		return findRacine(n1) == findRacine(n2);
	}

	private static Tree findRacine(Tree t) {
		return t.getParent() != null
				? findRacine(t.getParent())
				: t;
	}

	// 1.7)
	public static void dfsPrint(Tree t) {
		System.out.print(t.getValue() +  " ");
		for (Tree child : t.getChildren()) {
			dfsPrint(child);
		}
	}

	// 1.8)
	public static void bfsPrint(Tree t) {
		Queue<Tree> q = new LinkedList<>();
		q.add(t);
		while (!q.isEmpty()) {
			Tree n = q.poll();
			System.out.print(n.getValue() + " ");
			for (Tree child : n.getChildren()) {
				q.add(child);
			}
		}
	}

	// *******************************************************
	// Exercices 2
	// *******************************************************

	// 2.1)
	static void printPathV1(Tree node) {
		System.out.print(node.getValue() + " ");
		if (node.getParent() != null) {
			printPathV1(node.getParent());
		}
	}

	// 2.2)
	static void printPathV2(Tree node) {
		List<Integer> path = new LinkedList<>();
		while (node != null) {
			path.addFirst(node.getValue());
			node = node.getParent();
		}
		for (int value : path) {
			System.out.print(value + " ");
		}
	}

	// 2.3
	static void printPathV3(Tree t, int v) {
		// TODO
	}

	// *******************************************************
	// Exercices 3
	// *******************************************************

	// 3.1
	public static int[][] toArray(Tree t) {
		int[][] table = new int[3][nbrNode(t)];

		List<Tree> values = new ArrayList<>();
		toList(t, values);
		for (int i = 0; i < table[0].length; i++) {
			table[0][i] = values.get(i).getValue();
		}

		int idx = 0;
		for (Tree value : values) {
			Tree[] children = value.getChildren();
			Tree gauche = children.length > 0 ? children[0] : null;
			Tree droite = children.length > 1 ? children[1] : null;

			int emplGauche = -1;
			int emplDroite = -1;

			for (int i = 0; i < values.size(); i++) {
				if (values.get(i) == gauche) {
					emplGauche = i;
				}
				if (values.get(i) == droite) {
					emplDroite = i;
				}
			}

			table[1][idx] = emplGauche;
			table[2][idx] = emplDroite;
			idx++;
		}

		return table;
	}

	private static void toList(Tree t, List<Tree> values) {
		values.add(t);
		for (Tree child : t.getChildren()) {
			toList(child, values);
		}
	}

	// 3.2
	public static Tree toTree(int[][] t) {
		return null;
	}

	// *******************************************************
	// Exercices 4
	// *******************************************************

	public static Tree lca(Tree n1, Tree n2) {
		return null;
	}
}