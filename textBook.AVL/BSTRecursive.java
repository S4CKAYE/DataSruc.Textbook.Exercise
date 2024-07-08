import java.util.Arrays;

public class BSTRecursive {

	BSTNode root;
	int size;

	public BSTRecursive(BSTNode root, int size) {
		this.root = root;
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(BSTNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(BSTNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, BSTNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new TreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public BSTNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public BSTNode insert(int v, BSTNode n, BSTNode parent) {
		if (n == null) {
			n = new BSTNode(v, null, null, parent);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		return n;
	}

	public BSTNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public BSTNode remove(int v, BSTNode n, BSTNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n.parent = null; // disconnect from above
				n = null; // disconnect from below
				size--;
			} else if (n.left != null && n.right == null) {
				BSTNode n2 = n.left;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.left = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else if (n.right != null && n.left == null) {
				BSTNode n2 = n.right;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.right = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else {
				TreeIterator i = (TreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		return n;
	}

	private int height(BSTNode n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	//=========================ImplementedMethod===========================================//
	
	//modified from class AVLTree, to be called by moveFurthestDown().
	public BSTNode rotateLeftChild(BSTNode n) {
		BSTNode l = n.left;
		BSTNode lr = n.left.right; // can be null
		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		//AVLNode.updateHeight(n);
		//AVLNode.updateHeight(l);
		return l;
	}
	//modified from class AVLTree, to be called by moveFurthestDown()
	public BSTNode rotateRightChild(BSTNode n) {
		BSTNode r = n.right;
		BSTNode rl = n.right.left; // can be null
		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		//AVLNode.updateHeight(n);
		//AVLNode.updateHeight(r);
		return r;
	}
	
	
	//this method regards n as the root of a subtree
	//rotates data in n down the longest path possible from n until n is a leaf node
	//note that the longest path changes for each rotation.
	//returns a new root of this subtree.
	public BSTNode moveFurthestDown(BSTNode n) {
		if (n.left == null && n.right == null) {
			return n;
		}
		else {
			if (height(n.right) >= height(n.left)) {
				n = rotateRightChild(n);
				n.left = moveFurthestDown(n.left);
			}else {//height(n.right) < height(n.left)
				n = rotateLeftChild(n);
				n.right = moveFurthestDown(n.right);
			}
		}
		return n;
	}
	
	public BSTNode addUp(int num,BSTNode n) {
		n = addUp(num, n, null);
		return n;
		
	}
	
	//this method insert num just like normal insert
	//after num is added, it must be at the root of this subtree
	//use rotation to move newly added number up the tree.
	public BSTNode addUp(int num,BSTNode n, BSTNode parent) {
		if (n == null) {
			n = new BSTNode(num, null, null, parent);
			size++;
		} else if (num < n.data) {
			n.left = addUp(num, n.left, n);
			n = rotateLeftChild(n);
		} else if (num > n.data) {
			n.right = addUp(num, n.right, n);
			n = rotateRightChild(n);
		}
		return n;
	}
	
	
	public static void main(String[] args) {
		BSTNode r1 = new BSTNode(10);
		BSTRecursive t1 = new BSTRecursive(r1,1);
		t1.insert(5);
		t1.insert(15);
//		t1.insert(20);
//		t1.insert(30);
//		t1.insert(35);
		BTreePrinter.printNode(t1.root);
		
		
//		System.out.println("\n===========test: moveFurthestDown()=============");
//		t1.root = t1.moveFurthestDown(t1.root);
//		
//		BTreePrinter.printNode(t1.root);
		
		System.out.println("\n===========test: addUp()=============");
		t1.root = t1.addUp(8, t1.root);
		BTreePrinter.printNode(t1.root);
		
		
		
	}
}
