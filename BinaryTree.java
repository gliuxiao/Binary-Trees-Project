package student_classes;
/**
 * Common "base class" for any and all directed di-graphs (or, what we will
 * be creating in this class). This class is extended by the Binary Search Tree
 * class (qv), and therefore relies upon some of its properties (indirectly) and
 * methods. Note that all of the methods belonging to this class address
 * the <emph>structure</emph> of the tree a di-graph, i.e., independently of what
 * values might be stored at these locations. In other words: the operations defined
 * by this class are common to all directed di-graphs, which means that they are
 * appropriate to the BST (binary search tree) that you will be required to define
 * using this as a base class. 
 * <br>
 * <P>Observe that the signatures for many of the methods on this class return
 * <emph>subtree</emph>s and <b>never</b> <code>BTNode</code>s. This ensures that
 * clients never know anything about the underlying representation of these objects
 * or any of their descendants. </P>
 * @author UMD CS Dept.
 *
 * @param <T>
 */
public class BinaryTree<T> {

	// Binary node inner class
	class BTNode {
		T	value;
		BTNode left, right;

		BTNode( T val, BTNode l, BTNode r ) {
			this.value = val;
			this.left = l;
			this.right = r;
		}

		BTNode( T val ) {
			this( val, null, null );
		}

	}
	/*
	 * You may choose to rename this private and use the public accessors, 
	 * or you may leave this protected and use a mixture of public accessors
	 * and/or direct field references.
	 */
	protected BTNode root = null;

	public BinaryTree(){
		
	}

	public void add(T value){
		if (isEmpty()) {
			root = new BTNode(value);
			return;
		}
		BTNode leftNode = root.left;
		BTNode rightNode = root.right;
		if (leftNode == null) {
			root.left = new BTNode(value);
			return;
		}
		else if (rightNode == null) {
			root.right = new BTNode(value);
			return;
		}
		BinaryTree<T> leftSub = new BinaryTree<T>();
		leftSub.root = leftNode;
		leftSub.add(value);		
	}
	
	public boolean equals(Object other){
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		else if (other.getClass() != this.getClass()) {
			return false;
		}
		BinaryTree<T> casted = (BinaryTree<T>) other;
		if (casted.getSize() != this.getSize()) {
			return false;
		}
		if (!root.equals(casted.root)) {
			return false;
		}
		if ((this.hasRight() && !casted.hasRight()) || (!this.hasRight()&&casted.hasRight())) {
			return false;
		}
		if ((this.hasLeft() && !casted.hasLeft()) || (!this.hasLeft()&&casted.hasRight())) {
			return false;
		}
		if (this.isLeaf() && casted.isLeaf()) {
			return true;
		}
		if (casted.hasRight() && !casted.getRight().equals(this.getRight())) {
			return false;
		}
		if (casted.hasLeft() && !casted.getLeft().equals(this.getLeft())) {
			return false;
		}
		return true;


	}
	public BinaryTree<T> getLeft(){
		if (root == null) {
			throw new RuntimeException();
		} else {
			BinaryTree<T> leftSub = new BinaryTree<T>();
			leftSub.root = root.left;
			return leftSub;
		}
	}
	
	public BinaryTree<T> getRight(){
		if (root == null) {
			throw new RuntimeException();
		} else {
			BinaryTree<T> rightSub=new BinaryTree<T>();
			rightSub.root = root.right;
			return rightSub;
		}
	}
	
	public int getSize(){
		if (root == null) {
			return 0;
		}
		return getRight().getSize() + getLeft().getSize() + 1;
	}
	
	public T getValue(){
		if (root == null) {
			throw new RuntimeException();
		} else {
			return root.value;
		}
	}
	
	protected boolean hasLeft(){
		return root.left != null;
	}
	
	protected boolean hasRight(){
		return root.right != null;
	}
	
	// Recursively finds the height of the tree
	public int height(){
		if(isEmpty())
			return -1;
		int left = getLeft().height();
		int right = getRight().height();
		if(left<right){
			return right+1;
		}
		return left+1;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean isLeaf(){
		return root != null && this.getLeft() == null && this.getRight() == null;
	}
	
	public void setLeftValue(T value){
		if (this.getLeft() == null) {
			this.root.left = new BTNode(value);
		} else {
			this.root.left.value = value;
		}
	}
	
	public void setRightValue(T value){
		if (this.getRight() == null) {
			this.root.right=new BTNode(value);
		} else {
			this.root.left.value=value;
		}
	}
	public void setValue(T newValue){
		if (root == null) {
			root = new BTNode(newValue);
		} else {
			root.value = newValue;
		}
	}	

}
