

public class ListQuickSort {
 
	CDLinkedList theList;
	
	public DListIterator listIterator(int i) {//O(n)
		if(i<0 || i>= theList.size) {
			return null;
		}
		DListNode current = theList.header.nextNode;
		
		for (int j = 0; j == i; j++) {
			current = current.nextNode;
		}
		return new DListIterator(current);
	}
	
	public DListIterator findPivot() throws Exception {
		if (theList.size < 3) {
			throw new Exception();
		}
		DListNode first = theList.header.nextNode;
		DListNode last = theList.header.previousNode;
		DListNode middle = findMiddleNode(first, last);//O(n/2) = O(n)
		
		if((first.data >= middle.data && first.data <= last.data) 
				|| (first.data >= last.data && first.data <= middle.data)) {
			return new DListIterator(first);
		}else if ((middle.data >= first.data &&  middle.data <= last.data)
				|| (middle.data >= last.data && middle.data <= first.data)){
			return new DListIterator(middle);
		}else {
			return new DListIterator(last);
		}
		
	}
	
	private DListNode findMiddleNode(DListNode start, DListNode end) {//O(n)
		while (start != end && start.previousNode != end) {
			start = start.nextNode;
			end = end.previousNode;
		}
		return start;
	}
	
	public void swap(DListIterator i, DListIterator j) {//O(1)
		if (i == null || j == null) {
			return;
		}
		int temp = i.currentNode.data;
		i.currentNode.data = j.currentNode.data;
		j.currentNode.data = temp;
	}
	
	public void partition(DListIterator pivot, int start, int end) throws Exception {
		if(pivot == null) {
			return;
		}
		int pivotV = pivot.currentNode.data;
		
		DListIterator left = listIterator(start);
		DListIterator last = listIterator(end);
		DListIterator right = new DListIterator(last.currentNode.previousNode);
		swap(pivot, last);
	
		
		while(true) {
			while(left.currentNode.data < pivotV && left.currentNode.previousNode != right.currentNode) {
				left.next();
			}
			while(right.currentNode.data > pivotV && left.currentNode.previousNode != right.currentNode) {
				right.previous();
			}
			if(left.currentNode != right.currentNode && left.currentNode.previousNode != right.currentNode) {
				swap(left, right);
			}
			else {
				break;
			}
		}
		swap(left, last);
	}
	
	
	
//	public void quickSort(int start, int end) {
//		if (start >= end) {
//			return;
//		}
//		DListIterator pivot = findPivot();
//		
//	}
}
