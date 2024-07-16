

public class ListQuickSort {
 
	CDLinkedList theList;
	
	public void quickSort(DListNode start, DListNode end) {
		if(start == end || start.previousNode == end) {
			return;
		}
		DListNode pivot = findPivot(start, end);
		DListNode sortedPosition = partition(pivot, start, end);
		quickSort(start,sortedPosition.previousNode);
		quickSort(sortedPosition.nextNode, end);
		
	}
	
	public DListNode findPivot(DListNode start, DListNode end) {
		DListNode center = findCenter(start, end);
		if ((start.data >= center.data && start.data <= end.data) || 
				(start.data >= end.data && start.data <= center.data)) {
			return start;
		}else if((center.data >= start.data && center.data <= end.data) 
				|| (center.data >= end.data && center.data <= start.data)) {
			return center;
		}else {
			return end;
		}
	}
	
	private DListNode findCenter(DListNode start, DListNode end) {
		while(start != end && start.previousNode != end) {
			start = start.nextNode;
			end = end.previousNode;
		}
		return start;
	}
	
	public void swap(DListNode n1, DListNode n2) {
		int temp = n1.data;
		n1.data = n2.data;
		n2.data = temp;
	}
	
	public DListNode partition( DListNode pivot, DListNode start, DListNode end) {
		int pValue = pivot.data;
		swap(pivot, end);
		DListNode left = start;
		DListNode right = end.previousNode;
		
		while(true) {
			while(left.data < pValue && left.previousNode != right && left != right) {
				left = left.nextNode;
			}
			while(right.data > pValue && left.previousNode != right && left != right) {
				right = right.previousNode;
			}
			if (left.previousNode != right && left != right) {
				swap(left, right);
				left = left.nextNode;
				right = right.previousNode;
			}else {
				break;
			}
		}
		swap(left, end);
		
		return left;//pivot position after swap;
	}
	
	public static void main(String[] args) throws Exception {
		CDLinkedList cd1 = new CDLinkedList();
		ListQuickSort test = new ListQuickSort();
		test.theList = cd1;
		cd1.insert(8, new DListIterator(cd1.header.previousNode));
		cd1.insert(8, new DListIterator(cd1.header.previousNode));
		cd1.insert(8, new DListIterator(cd1.header.previousNode));
		cd1.insert(8, new DListIterator(cd1.header.previousNode));
		cd1.insert(8, new DListIterator(cd1.header.previousNode));
		cd1.insert(2, new DListIterator(cd1.header.previousNode));
		
		
		System.out.println(cd1.printList());
		test.quickSort(cd1.header.nextNode, cd1.header.previousNode);
		System.out.println(cd1.printList());
	}
}
