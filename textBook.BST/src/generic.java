import java.util.ArrayList;
import java.util.NoSuchElementException;

public class generic {
	
	// uses arrayList. 
//	public boolean sameData(BST t1, BST t2) {
//		ArrayList<Integer> a1 = new ArrayList<Integer>();
//		ArrayList<Integer> a2 = new ArrayList<Integer>();
//		
//		inOrderList(a1, t1.root);
//		inOrderList(a2, t2.root);
//		
//		if (a1.size() != a2.size()) {//size must be equal
//			return false;
//		}
//		for (int i = 0; i < a1.size(); i++) {
//			int v = a1.get(i);
//			if (!a2.contains(v)) {
//				return false; //there exist a data that is not present in a2
//			}
//		}
//		
//		return true;
//				
//	}
//	private void inOrderList(ArrayList<Integer> a, BSTNode n){
//		if (n == null) {
//			return;
//		}
//		inOrderList(a, n.left);
//		a.add(n.data);
//		inOrderList(a, n.right);
//	}
//	
	
	//returns true if t1 and t2 have same data, while shape may vary.
	public boolean sameData(BST t1, BST t2) {
		if (t1.size != t2.size) {
			return false;
		}
		TreeIterator minOfBST1 = (TreeIterator)t1.findMin();
		TreeIterator minOfBST2 = (TreeIterator)t2.findMin();
		BSTNode current1 = minOfBST1.currentNode;
		BSTNode current2 = minOfBST2.currentNode;
		
		// we traverse from min value to max value of each tree.
		// to have the same data t1 and t2 must have same sorted data
		while(current1 != null || current2 != null) {
			if(current1.data != current2.data) {
				return false;
			}
			current1 = nextOfNode(current1);
			current2 = nextOfNode(current2);	
		}
		//current1 and current2 should both exit at null if both have same data.
		if (current1 == null && current2 == null) {
			return true;
		}
		return false;
	}
	//return true if t1 and t2 have the same shape and same content.
	public boolean same(BST t1, BST t2) {
		if (t1.size != t2.size) {
			return false;
		}
		TreeIterator minOfBST1 = (TreeIterator)t1.findMin();
		TreeIterator minOfBST2 = (TreeIterator)t2.findMin();
		BSTNode current1 = minOfBST1.currentNode;
		BSTNode current2 = minOfBST2.currentNode;
		
		// we traverse from min value to max value of each tree.
		// to have the same data t1 and t2 must have same sorted data
		while(current1 != null || current2 != null) {
			if(current1.data != current2.data) {
				return false;
			}
			//added from sameDatas
			if(current1.parent != current2.parent) {
				return false;
			}
			if(current1.left != current2.left) {
				return false;
			}
			if(current1.right != current2.right) {
				return false;
			}
			//
			current1 = nextOfNode(current1);
			current2 = nextOfNode(current2);	
		}
		//current1 and current2 should both exit at null if both have same data.
		if (current1 == null && current2 == null) {
			return true;
		}
		return false;
	}
	
	// modified from next() of TreeIterator class.
	//returns node just greater than currentNode 
	// helper method for sameData() and same() and average().
	private BSTNode nextOfNode(BSTNode currentNode){
		// Throw exception if the next data
		// does not exist.
		BSTNode temp = currentNode;

		if (temp.right != null) {
			temp = temp.right;
			while (temp.left != null) {
				temp = temp.left;
			}
		} else {
			BSTNode p = temp.parent;
			while (p != null && p.right == temp) {
				temp = p;
				p = temp.parent;
			}
			temp = p;
		}

		if (temp == null) {// hasNext() == false
			return null;
		}
		
		return temp;
	}
	
//	public int average(BST t) {
//		if (t.isEmpty()) {
//			return 0;
//		}
//		int sum = 0;
//		findSumOfNodes(t.root, sum);
//		System.out.println(sum);
//		return (sum/t.size);
//	}
	
	// need fixing, issue: sum does not add up.
//	private void findSumOfNodes(BSTNode n, int sum) {
//		if ( n == null) {
//			return;
//		}
//		//find sum of left subtree
//		findSumOfNodes(n.left, sum);
//		//find sum of right subtree
//		findSumOfNodes(n.right, sum);
//		sum += n.data;
//	}
		
	public int average(BST t) {
		if (t.isEmpty()) {
			return 0;
		}
		int total = 0;
		TreeIterator minItr = (TreeIterator) t.findMin();
		BSTNode current = minItr.currentNode;
		
		while(current != null) {
			total += current.data;
			current = nextOfNode(current);
		}
		
		return total/t.size;
	}
	
	    public static void main(String[] args) {
	    	BSTNode r = new BSTNode(7);
			BST t = new BST(r, 1);
			t.insert(3);
			t.insert(1);
			t.insert(11);
			t.insert(2);
			t.insert(5);
			t.insert(9);
			t.insert(6);

			t.insert(0);
			t.insert(8);
			t.insert(10);
			t.insert(12);
			t.insert(13);
			t.insert(14);
			t.insert(4);
			//t.insert(100);
			//BTreePrinter.printNode(t.root);
			
			// Create the root node with a different initial value to guarantee a different structure
			BSTNode root2 = new BSTNode(10);
			BST t2 = new BST(root2, 1);

			// Insert elements in a different order than tree t
			t2.insert(5);
			t2.insert(14);
			t2.insert(0);
			t2.insert(3);
			t2.insert(8);
			t2.insert(12);
			t2.insert(11);
			t2.insert(1);
			t2.insert(7);
			t2.insert(2);
			t2.insert(6);
			t2.insert(4);
			t2.insert(13);
			t2.insert(9);
			
			BSTNode r2 = new BSTNode(7);
			BST notBalancedTree = new BST(r2,1);
			notBalancedTree.insert(5);
			notBalancedTree.insert(9);
			notBalancedTree.insert(1);
			notBalancedTree.insert(3);
			notBalancedTree.insert(9);
			notBalancedTree.insert(11);
			
			BTreePrinter.printNode(notBalancedTree.root);
			
			//BTreePrinter.printNode(t2.root);
			generic test = new generic();
//			System.out.println("\n=====Test: sameData(t, t2)=====\n");
//			System.out.println(test.sameData(t, t2));
//			System.out.println("\n=====Test: same(t, t2)=====\n");
//			System.out.println(test.same(t, t2));
			System.out.println("\n=====Test: average(BST t)=====\n");
			System.out.println(test.average(notBalancedTree));

	    }
	

	
}
