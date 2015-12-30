package student_classes;

import java.util.ArrayList;
import java.util.Iterator;

import student_classes.BinaryTree;
import student_classes.BinaryTree.BTNode;

/**
 * <P>This version of the classic Binary Search Tree is similar
 * to the commonly found textbook implementations. My purpose
 * in creating the lab is to have you practice implementing
 * many commonly-referenced "binary tree" and "binary search tree" algorithms.
 * Note the distinction: not every Binary Tree is a Binary Search Tree. 
 * Hence. some operations belong to <b>all</b> trees (i.e., are shared
 * by all directed di-graphs), such as counting the number of nodes contained
 * in the tree, obtaining a left or right subtree, determining if a tree is a
 * leaf, etc. Other operations, however, only make sense in the setting 
 * of a Binary Search Tree, such as insertion, deletion, and searching because
 * in the case of BSTs, some ordering relation has been specified and this guides
 * the structure of the tree itself.
 *</P>
 *<br>
 *<h1>Differences/Exceptional Requirements</h1>
 *<br>
 * You will first implement a generic <code>BinaryTree</code> class that is
 * parameterized on general types (objects). This class must provide a handful
 * of "structural" operations, such as retrieving left and right branches, retrieving
 * values from the root (topmost node of a tree), and a handful of predicates.
 * <br>
 * Your BST must <em>extend</em> the <code>BinaryTree</code> class, and
 * must, in addition, restrict the values that may reside within the BST to
 * homogeneous Comparable types. implementation must accept any homogeneous, 
 * <code>Comparable</code> objects as values. The
 * construction algorithm should treat place values
 * that are less than or equal to the root to the
 * left, and those greater to the right. Duplications
 * will be permitted.
 * <h2>Additional Static Method required</h2>
 * In addition to the "standard" dynamic methods (and those inherited from the
 * <code>BinaryTree</code> class), you must implement a <code>static</code> method
 * called <code>isBST(BinaryTree tree)</code> that returns <code>true</code> iff
 * <code>tree</code> satisfies all of the requirements for a Binary Search Tree (as
 * we've defined it):
 * <ul>
 * <li> It is empty;</li>
 * <li> It is a leaf node (contains no left or right children); or,</li>
 * <li> It is a binary tree whose left elements are less than or equal to its root,
 * and its right elements are greater than its root value.</li>
 * </ul>
 * 
 * <P>
 * Finally, your implementation should provide an
 * <code>Iterator</code> that allows users to iterate
 * through the nodes of a BST <em>in sort order</em>, i.e., 
 * the order in which nodes values' should be presented
 * by the Iterator must correspond to their <em>natural ordering</em>
 * as determined by the <code>compareTo</code> method.
 * @author UMD CS Department
 *
 * @param <T>
 */


public class BST <T extends Comparable<T>> extends BinaryTree<T> implements Iterable<T>  {
	private ArrayList<T> list;

	public BST(){
		//empty
	}

	public void add(T value){
		if (this.root == null) {
			this.setValue(value);
			return;
		} else if (this.root.value.compareTo(value) < 0) {
			if (!hasRight()) {
				this.root.right = new BTNode(value);
				return;
			}
			getRight().add(value);

		} else {
			if (!hasLeft()) {
				this.root.left = new BTNode(value);
				return;
			}
			getLeft().add(value);
		}
	}

	public boolean contains(T ele){
		if (root == null)
			return false;
		if (root.value == ele)
			return true;
		BST<T> right = new BST<T>();
		right.root = this.root.right;
		BST<T> left = new BST<T>();
		left.root = this.root.left;
		if ((hasRight() && right.contains(ele)) || (hasLeft() && left.contains(ele))) {
			return true;
		}
		return false;
	}

	public BST<T> getLeft(){
		BST<T> l = new BST<T>();
		l.root = this.root.left;
		return l;
	}

	public BST<T> getRight(){
		BST<T> r = new BST<T>();
		r.root = this.root.right;
		return r;
	}

	public static <T extends Comparable<T>> boolean isBST(BinaryTree<T> tree){
		if (tree.isEmpty() || tree.isLeaf()) {
			return true;
		}
		if (tree.root.value.compareTo(tree.getRight().getValue()) >= 0) {
			return false;
		} else if (tree.root.value.compareTo(tree.getLeft().getValue()) < 0) {
			return false;
		}
		
		if (isBST(tree.getRight()) && isBST(tree.getLeft())) {
			return true;
		} else {
			return false;
		}
	}

	private void printInOrder(ArrayList<T> arraylist){
		if (this.isEmpty()) {
			return;
		}
		if (this.hasLeft()) {
			BST<T> leftSub = new BST<T>();
			leftSub.root = this.root.left;
			leftSub.printInOrder(arraylist);
		}
		arraylist.add(getValue());
		if (this.hasRight()) {
			BST<T> rightSub = new BST<T>();
			rightSub.root = this.root.right;
			rightSub.printInOrder(arraylist);
		}

	}

	public Iterator<T> iterator() {
		list = new ArrayList<T>();
		printInOrder(list);
		return list.iterator();
	}

	public class MyIterator<T> implements Iterator<Object>{
		private int pos = 0;
		public boolean hasNext(){
			return pos<list.size();
		}
		public Object next() {
			return list.get(pos++);
		}
	}
}
