import java.util.ArrayList;

public class BSTRecursive extends BST {
	public BSTRecursive(BSTNode root, int size) {
		super(root, size);
	}
	public boolean isBST(BSTNode n) {
		if (n == null) {
			return true;
		}else if(n.left != null && n.left.data >= n.data) {
			return false;
		}else if (n.right != null && n.right.data <= n.data) {
			return false;
		}
		return (isBST(n.left) && isBST(n.right));
	}
	
	private int heightUtil(BSTNode n) {
		if(n == null) {
			return 0;
		}
		return 1 + Integer.max(height(n.left), height(n.right));
	}
	
	public int height(BSTNode n) {
		if(isEmpty()) {
			return -1;
		}
		return heightUtil(n) -1;
	}
	//returns the maximum number of nodes the tree can handle based on its height.
	//the maximum is that of perfect binary tree.
	public int maxNumNodes() {
		// if t is a perfect binary tree, (#node+1)/2 = 2^height;
		int height = height(root);
		if(height == -1) {
			return -1;
		}
		int twoPowerHeight = (int) Math.pow(2, height);
		return (twoPowerHeight*2)-1;
	}
	
	public int numNodes() {
		if(isEmpty()) {
			return 0;
		}
		return numNodesUtil(root);
	}
	//helper method for numNodes
	private int numNodesUtil(BSTNode n) {
		if (n == null) {
			return 0;
		}
		return 1 + numNodesUtil(n.left) + numNodesUtil(n.right);
	}
	
	public int numLeaves() {
		if(isEmpty()) {
			return 0;
		}
		return numLeavesUtil(root);
	}
	//helper method for numLeaves.
	private int numLeavesUtil(BSTNode n) {
		if(n.left == null && n.right == null) {// leave
			return 1;
		}else if(n.left != null & n.right == null){
			return numLeavesUtil(n.left);
		}else if(n.left == null && n.right != null) {
			return numLeavesUtil(n.right);
		}else {
			return numLeavesUtil(n.left) + numLeavesUtil(n.right);
		}
	}
	//find parents of a given node(in subtree)
	//without following parent link up from that node.
	//returns null if no parent can be found
	// n is root of subtree, d is the node that we want to find its parent
	//parent is our temporary node that will move down the tree.
	
	public BSTNode findParent(BSTNode n, BSTNode d, BSTNode parent) {
		parent = n; // set parent = root of that (sub) tree
		if (n == null) {
			return null;
		}
		if (parent.left == d || parent.right == d) {
			return parent;
		}
		if(d.data < parent.data) {
			return findParent(parent.left, d, parent);
		}else {//d.data > parent.data
			return findParent(parent.right, d, parent);
		}
		
	}
	//most balanced tree possible has data filled from left to right, top to bottom, except at last level
	public void contructMostBalanced() {
		if(isEmpty()) {
			return;
		}
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		sortedList = makeInOrderList(sortedList, this.root);
		this.root = makeBalancedFromArrayList(sortedList,0, sortedList.size()-1);
	}
	
	private ArrayList<Integer> makeInOrderList(ArrayList<Integer> a,BSTNode n){
		if (n == null) {
			return a;
		}
		makeInOrderList(a, n.left);
		a.add(n.data);
		makeInOrderList(a, n.right);
		return a;
	}
	
	private BSTNode makeBalancedFromArrayList(ArrayList<Integer> a,int start, int end) {
		if (start>end) {
			return null;
		}
		int midIndex = (start + end)/2;
		BSTNode leftNode = makeBalancedFromArrayList(a, start, midIndex-1);
		BSTNode rightNode = makeBalancedFromArrayList(a, midIndex+1, end);
		return new BSTNode(a.get(midIndex), leftNode, rightNode,null);
	}
	
//	public BSTNode createMirror(BSTNode n) {
//		if (isEmpty()) {
//			return null;
//		}
//		BSTRecursive mirrorTree = makeMirrorTree(n);
//		return mirrorTree.root;
//	}
//	//modified from cloneTreeRecursive(BSTNode n) lab8.BST.old
//	private BSTRecursive makeMirrorTree(BSTNode n) {
//		if (n == null) {
//			return new BSTRecursive(null, 0);
//		}
//		BSTRecursive leftSubTree = makeMirrorTree(n.left);
//		BSTRecursive rightSubTree = makeMirrorTree(n.right);
//		BSTNode rootOfMirror = new BSTNode(n.data, rightSubTree.root, leftSubTree.root, null);
//		return new BSTRecursive(rootOfMirror, rightSubTree.size+leftSubTree.size+1);	
//	}
	
	public BSTNode createMirror(BSTNode n) {
		if (n == null) {
			return null;
		}
		//traverse to right of original tree, set it as our left side of mirror tree.
		BSTNode leftNode = createMirror(n.right);
		//traverse to right of original tree, set it as our left side of mirror tree.
		BSTNode rightNode = createMirror(n.left);
		//create the root for the mirror tree and connect left and right child.
		BSTNode rootOfMirror = new BSTNode(n.data, leftNode, rightNode, null);
		//create a new recursive mirror tree
		BSTRecursive mirrorTree = new BSTRecursive(rootOfMirror, this.size);
		//return root of that tree.
		return rootOfMirror;	
	}
	
	public Iterator findMax() {
		return findMax(root);
	}
	
	private Iterator findMax(BSTNode n) {
		if (n == null) {
			return null;
		}
		if(n.right == null) {
			return new TreeIterator(n);
		}
		return findMax(n.right);
	}
	
	
	public static void main(String[] args) {
		// Printing example.
		// You can print how the tree looks to help with debugging.

		BSTNode r = new BSTNode(7);
		BSTRecursive t = new BSTRecursive(r, 1);
		t.insert(3);
		t.insert(11);
		t.insert(2);
		t.insert(5);
		t.insert(8);
		
		BSTNode r2 = new BSTNode(7);
		BSTRecursive notBalancedTree = new BSTRecursive(r2,1);
		notBalancedTree.insert(5);
		notBalancedTree.insert(9);
		notBalancedTree.insert(1);
		notBalancedTree.insert(3);
		notBalancedTree.insert(9);
		notBalancedTree.insert(11);

		//BTreePrinter.printNode(t.root);
		//System.out.println("\n=====Test: isBST=====\n");
		//System.out.println(t.isBST(t.root));
//		System.out.println("\n=====Test: height=====\n");
//		System.out.println(t.height(t.root));
//		System.out.println("\n=====Test: numNodes=====\n");
//		System.out.println(t.numNodes());
//		System.out.println("\n=====Test: numLeaves=====\n");
//		System.out.println(t.numLeaves());
//		System.out.println("\n=====Test: findParent =====\n");
//		TreeIterator itrForTest = (TreeIterator) t.find(5);
//		BSTNode d = itrForTest.currentNode;
//		System.out.println(t.findParent(t.root,d, new BSTNode(999)).data);
//		System.out.println("\n=====Test: constructMostBalanced()=====\n");
//		BTreePrinter.printNode(notBalancedTree.root);
//		notBalancedTree.contructMostBalanced();
//		BTreePrinter.printNode(notBalancedTree.root);
//		System.out.println("\n=====Test: createMirror(BSTNode n)=====\n");
//		BTreePrinter.printNode(notBalancedTree.root);
//		BSTNode mirrorTreeRoot = notBalancedTree.createMirror(notBalancedTree.root);
//		BTreePrinter.printNode(mirrorTreeRoot);
		System.out.println("\n=====Test: createMirror(BSTNode n)=====\n");
		BTreePrinter.printNode(t.root);
		System.out.println(((TreeIterator)t.findMax()).currentNode.data);
	}

	 
}