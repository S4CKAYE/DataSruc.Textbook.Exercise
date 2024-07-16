package textBook.PriorityQueue;

public class HeapNode {
	int data;
	HeapNode left, right, parent;
	
	public HeapNode(int v) {
		data = v;
		left = null;
		right = null;
		parent = null;
	}
	
	public static void main(String[] args) throws Exception {
		HeapTree h = new HeapTree();
		
//		System.out.println("\n=======test: add()===========\n");
//		h.add(1);
//		h.add(5);
//		h.add(3);
//		h.add(6);
//		h.add(8);
//		h.add(7);
//		h.add(9);
//		h.printHeap();
		
//		System.out.println("\n=======test: pop()===========\n");
//		h.pop();
//		h.pop();
//		h.pop();
//		h.pop();
//		h.pop();
//		h.pop();
//		h.pop();
//		h.printHeap();
	}
}

//A heap of integer (min-heap), smallest number is most important
//implemented using binary tree structure that has parent link.
class HeapTree{
	HeapNode root;
	HeapNode lastNode;
	Queue q = new Queue();
	
	
	public void percolateUp(HeapNode n) {
		//we update last data every time;
		lastNode = n;
		
		HeapNode parent = n.parent;
		HeapNode child = n;
		int temp;
		while(parent != null && parent.data>child.data) {
			temp = child.data;
			child.data = parent.data;
			parent.data = temp;
			child = parent;
			parent = parent.parent;
		}
	}
	
	public void add(int v) throws Exception {
		HeapNode newNode = new HeapNode(v);
		if (root == null) {//empty tree
			root = newNode;
		}else {
			HeapNode toAdd = q.findNodeToAdd(root);
			if (toAdd.left == null) {
				toAdd.left = newNode;
				newNode.parent = toAdd;
			}else {
				toAdd.right = newNode;
				newNode.parent = toAdd;
			}
		}
		percolateUp(newNode);
	}
	
	public int pop() throws Exception {
		if (root == null) {
			throw new Exception();
		}
		int minV = root.data;
		
		if (root.left == null && root.right == null) {//if root is the only node in the tree.
			makeEmpty();
			return minV;
		}
		
		//have root have the data of the last node.
		root.data = lastNode.data;
		
		//disconnect lastNode from the tree
		HeapNode p = lastNode.parent;
		if(p.right == lastNode) {
			p.right = null;
		}else {
			p.left = null;
		}
		//update last node
		lastNode = q.findLastNode(root);
		
		
		percolateDown(root);
		return minV;
	}
	
	public void percolateDown(HeapNode start) {
		if (start == null) {//tree is empty after pop
			return;
		}
		HeapNode parent = start;
		HeapNode child = parent.left;
		
		while(child != null) {//iterate until leaf
			HeapNode rightChild = parent.right;
			if (rightChild != null && rightChild.data < child.data) {
				child = rightChild;
			}
			if (parent.data <= child.data) {
				break;
			}else {
				int temp = parent.data;
				parent.data = child.data;
				child.data = temp;
				parent = child;
				child = parent.left;
				}
		}
	}
	
	public void makeEmpty() {
		root = null;
		lastNode = null;
	}
	
	public boolean isHeap() {
		return isHeap(root);
	}
	
	public boolean isHeap(HeapNode n) {
		if (n == null) {
			return true;
		}else if (n.left != null && n.left.data > n.data) {
			return false;
		}else if(n.right != null && n.right.data > n.data) {
			return false;
		}
		return isHeap(n.left) && isHeap(n.right);
	}
	
	public void printHeap() throws EmptyQueueException, Exception {
		q.printHeap(root);
	}
	
}

