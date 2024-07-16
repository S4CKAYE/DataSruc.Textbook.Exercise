public class AVLTree {
	AVLNode root;
	int size;

	public AVLTree() {
		root = null;
		size = 0;
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

	public Iterator findMin(AVLNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(AVLNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, AVLNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new AVLTreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public AVLNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public AVLNode insert(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		//AVLTreePrinter.printNode(n);
		n = rebalance(n);
		return n;
	}

	public AVLNode insertNoBalance(int v) {
		return insertNoBalance(v, root, null);
	}

	private AVLNode insertNoBalance(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insertNoBalance(v, n.left, n);
		} else if (v > n.data) {
			n.right = insertNoBalance(v, n.right, n);
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public AVLNode remove(int v, AVLNode n, AVLNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n = null;
				size--;
			} else if (n.left != null && n.right == null) {
				n.left.parent = parent;
				n = n.left;
				size--;
			} else if (n.right != null && n.left == null) {
				n.right.parent = parent;
				n = n.right;
				size--;
			} else {
				AVLTreeIterator i = (AVLTreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		//AVLTreePrinter.printNode(n);
		n = rebalance(n);
		return n;
	}

	public AVLNode rebalance(AVLNode n) {
		if (n == null)
			return n;
		int balance = AVLNode.tiltDegree(n);
		if (balance >= 2) {
			if (AVLNode.tiltDegree(n.left) <= -1) // 3rd case
				n.left = rotateRightChild(n.left);
			n = rotateLeftChild(n); // 1st case
		} else if (balance <= -2) {
			if (AVLNode.tiltDegree(n.right) >= 1) // 4th case
				n.right = rotateLeftChild(n.right);
			n = rotateRightChild(n); // 2nd case
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode rotateLeftChild(AVLNode n) {
		AVLNode l = n.left;
		AVLNode lr = n.left.right; // can be null
		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(l);
		return l;
	}

	public AVLNode rotateRightChild(AVLNode n) {
		AVLNode r = n.right;
		AVLNode rl = n.right.left; // can be null
		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(r);
		return r;
	}

	

	public boolean isAVL() {
		// code this method
		return isAVL(this.root);
	}

//	private boolean isAVL(AVLNode n) {
//		if (n == null) {
//			return true;
//		}
//		int balance = AVLNode.tiltDegree(n);
//		if (Math.abs(balance) >=2) {
//			return false;
//		}
//		return (isAVL(n.left) && isAVL(n.right));
//	}

	
	public static boolean same(AVLTree t1, AVLTree t2) {
		//code this method
		return same(t1.root, t2.root);
	}
	
	private static boolean same(AVLNode n1, AVLNode n2) {
		if (n1 == null || n2 == null) {
			return true;
		}
		else if (n1.data != n2.data) {
			return false;
		}
		return (same(n1.left, n2.left))&&(same(n1.right, n2.right));
	}
	
	
	//=======================Implemented method=========================
	
	public boolean isAVL(AVLNode n) {
		if (n == null) {
			return true;
		}
		if (Math.abs(AVLNode.tiltDegree(n)) >= 2) {
			return false;
		}
		return isAVL(n.left) && isAVL(n.right);
	}
	
	//merge a BST to this AVL tree
	//after merge the tree must be AVL.
	public AVLTree merge(BSTRecursive t) {
		merge(t.root);
		return this;
	}
	
	//helper method for merge
	private void merge(BSTNode n) {
		if (n == null) {
			return;
		}
		
		merge(n.left);
		root = insert(n.data);
		//AVLTreePrinter.printNode(root);
		merge(n.right);
		
	}
	
	//this tree is not guranteed to be AVL (i.e. can be BST)
	//this method make this tree to be an AVL tree.
	//idea is to insert again. (implemented inOrder sort)
	public void makeAVL() throws Exception {
		//code this method
		AVLTree tt = new AVLTree();
		root = makeAVL(root, tt);
		
		
	}
	//helper method of makeAVL()
	private AVLNode makeAVL(AVLNode n,AVLTree tt) {
		if (n == null) {
			return n;
		}
		makeAVL(n.left,tt);
		tt.root = tt.insert(n.data);
//		System.out.println("n: " +n.data + "\n");
//		AVLTreePrinter.printNode(tt.root);
		makeAVL(n.right,tt);
		
		
		return tt.root;
	}

	public static void main(String[] args) throws Exception {
		// example: print a tree

		AVLTree t = new AVLTree();

		t.root = t.insertNoBalance(33);
		t.root = t.insertNoBalance(4);
		t.root = t.insertNoBalance(1);
		t.root = t.insertNoBalance(66);
		t.root = t.insertNoBalance(2);
		t.root = t.insertNoBalance(6);

		AVLTreePrinter.printNode(t.root);
//		t.makeAVL();
		
//		
//		AVLTree t2 = new AVLTree();
//		t2.root = t2.insert(10);
//		t2.root = t2.insert(15);
//		t2.root = t2.insert(20);
//		t2.root = t2.insert(18);
//		t2.root = t2.insert(35);
//		t2.root = t2.insert(19);
//		//AVLTreePrinter.printNode(t2.root);
//	
//		BSTRecursive t3 = new BSTRecursive(null, 0);
//
//		t3.root = t3.insert(33);
//		t3.root = t3.insert(4);
//		t3.root = t3.insert(1);
//		t3.root = t3.insert(66);
//		t3.root = t3.insert(2);
//		t3.root = t3.insert(6);
		
		//BTreePrinter.printNode(t3.root);
		
//		
		System.out.println("\n==============test: makeAVL()=====================\n");  
		t.makeAVL();
		AVLTreePrinter.printNode(t.root);
		
//		System.out.println("\n==============test: isAVL()=====================\n");
//		System.out.println(t.isAVL(t.root));
		
//		System.out.println("\n==============test: merge()=====================\n");
//		t2.merge(t3);
//		AVLTreePrinter.printNode(t2.root);
		
		
		
		
		AVLTree t4 = new AVLTree();
		t4.root = t4.insert(10);
		t4.root = t4.insert(15);
		t4.root = t4.insert(20);
		t4.root = t4.insert(18);
		t4.root = t4.insert(35);
		t4.root = t4.insert(19);
		//AVLTreePrinter.printNode(t4.root);
		
		
		
		
	}

}
