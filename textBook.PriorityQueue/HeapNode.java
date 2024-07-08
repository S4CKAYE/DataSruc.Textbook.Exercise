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
}

class HeapTree{
	HeapNode root;
	
	
	public void percolateUp(HeapNode n) {
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
	//*hard
//	public void add(int v) {
//		
//	}
//	
//	public HeapNode findParentToAdd() {
//		
//	}
	
}
