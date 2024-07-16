package textBook.PriorityQueue;
//queue that can store HeapNode 
public class Queue extends QueueArray{
	
	//Breadth-First Traverse to find the first node having available position.
	public HeapNode findNodeToAdd(HeapNode n) throws Exception {
		if (n == null) {
			return null;
		}
		Queue thisLevel = new Queue();
		Queue nextLevel = new Queue();
		
		thisLevel.insertLast(n);
		
		while(!thisLevel.isEmpty() || !nextLevel.isEmpty()) {
			
			while(!thisLevel.isEmpty()) {	
				HeapNode current = thisLevel.removeFirst();
					
				//check if we found a position to insert.
				if (current.left == null || current.right == null) {
					return current;
				}
				nextLevel.insertLast(current.left);
				nextLevel.insertLast(current.right);
			}
			while(!nextLevel.isEmpty()) {
				thisLevel.insertLast(nextLevel.removeFirst());
			}
		}
		return null;
	}
	//Breadth-First Traverse to find the last node.
	public HeapNode findLastNode(HeapNode n) throws Exception {
		if(n == null) {
			return null;
		}
		Queue thisLevel = new Queue();
		Queue nextLevel = new Queue();
		
		thisLevel.insertLast(n);
		
		while(!thisLevel.isEmpty() || !nextLevel.isEmpty()) {
			
			while(!thisLevel.isEmpty()) {	
				
				HeapNode current = thisLevel.removeFirst();
				if(current.left != null) {
					nextLevel.insertLast(current.left);
				}
				if(current.right != null) {
					nextLevel.insertLast(current.right);
				}
				if(thisLevel.isEmpty() && nextLevel.isEmpty()) {//last node;
					return current;
				}
			}
			while(!nextLevel.isEmpty()) {
				thisLevel.insertLast(nextLevel.removeFirst());
			}
		}
		return null;
	}
	
	//Breadth-First Traverse which prints node data level by level.
	public static void printHeap(HeapNode n) throws EmptyQueueException, Exception {
		if (n == null) {
			return;
		}
		Queue thisLevel = new Queue();
		Queue nextLevel = new Queue();
		
		thisLevel.insertLast(n);
		
		while(!thisLevel.isEmpty() || !nextLevel.isEmpty()) {
			
			while(!thisLevel.isEmpty()) {	
				
				HeapNode current = thisLevel.removeFirst();
				
				System.out.print(current.data + " ");
				
				if(current.left != null) {
					nextLevel.insertLast(current.left);
				}
				if(current.right != null) {
					nextLevel.insertLast(current.right);
				}
				
			}
			
			System.out.println("\n");
			
			while(!nextLevel.isEmpty()) {
				thisLevel.insertLast(nextLevel.removeFirst());
			}
		}
		
	}
	
}
