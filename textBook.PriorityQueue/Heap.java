package textBook.PriorityQueue;

public class Heap implements PriorityQ  {
	Object[] mData;
	int size = 0;

	public Heap() {
		final int DEFAULT_CAPACITY = 11;
		mData = new Comparable[DEFAULT_CAPACITY];
	} // default constructor

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Object element) {
		if (++size == mData.length) {
			Object[] newHeap = new Object[2 * mData.length];
			System.arraycopy(mData, 0, newHeap, 0, size);
			mData = newHeap;
		}
		mData[size - 1] = element;
		percolateUp();
		
	}

	protected void percolateUp() {
		int parent;
		int child = size - 1;
		Comparable temp;
		while (child > 0) {
			parent = (child - 1) / 2;
			if (((Comparable)mData[parent]).compareTo(mData[child]) <= 0)
				break;
			temp = (Comparable)mData[parent];
			mData[parent] = mData[child];
			mData[child] = temp;
			child = parent;
		}
	}

	public Object top() throws Exception {
		if (size == 0)
			throw new Exception("Empty");
		return mData[0];
	}

	public Object pop() throws Exception {
		if (size == 0)
			throw new Exception("Priority queue empty.");
		Object minElem = mData[0];
		mData[0] = mData[size - 1];
		size--;
		percolateDown(0);
		return minElem;
	}

	protected void percolateDown(int start) {
		int parent = start;
		int child = 2 * parent + 1;
		Object temp;
		while (child < size) {
			Comparable lVal = (Comparable) mData[child];
			if (child < size - 1) {
				Comparable rVal = (Comparable) mData[child+1];
				if (lVal.compareTo(rVal) > 0){
					child++;
				}
			}
			Comparable pVal = (Comparable) mData[parent];
			if(pVal.compareTo(mData[child]) <= 0) {
				break;
			}
			temp = mData[child];
			mData[child] = mData[parent];
			mData[parent] = temp;
			parent = child;
			child = 2*parent +1;
		}
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	
	
	//========================Implemented methods============================
	
	//removes specified value stored
	//after removal, properties of heap still holds.
	public void removeValue(Object value) {
		for(int i = 0; i<size; i++) {
			Object current = mData[i];
			if(current == value) {
				mData[i] = mData[size-1];
				size--;
				percolateDown(i);
				add(value);
			}
		}
	}
	//removes the second most important value
	//assume Heap contains at least 3 elements
	//smallest value is the most important value
	public void removeSecond() {
		if(size <3) {
			return;
		}else {
			int toRemove = 1;
			//check if the right child is more important than the left child.
			if (((Comparable) mData[1]).compareTo(mData[2]) > 0) {
				toRemove++;
			}
			
			mData[toRemove] = mData[size-1];
			size--;
			percolateDown(toRemove);
		}
	}
	//returns the index of the maximum value in our array.
	public int calculateMaxIndex() {
		int maxIndex = size-1;
		int i = size-1;//start search from the last element
		
		//if leftchild does not exist, then does the right child => leaf
		int leftChild = 2*i +1;
		
		while(leftChild >= size) {//search all the leaves
			if(((Comparable) mData[i]).compareTo(mData[maxIndex]) > 0){
				maxIndex = i;
			}
			i--;
			leftChild = 2*i +1;
		}
		return maxIndex;
	}
	
	//============================implemented for min-max heap structure=====================//
	
	
	//min-max heap of integer 
	//even level: small value is more important (min heap)
	//odd level; greater value is more important (max heap)
	
	public void addForMinMaxHeap(int newnumber) {
		if (++size == mData.length) {
			Object[] newHeap = new Object[2 * mData.length];
			System.arraycopy(mData, 0, newHeap, 0, size);
			mData = newHeap;
		}
		mData[size - 1] = newnumber;
		int initialLevel = findInitialLevel(size);
		percolateUpForMinMax(initialLevel);
	}
	
	
	protected void percolateUpForMinMax(int currentLevel) {
		int parent;
		int child = size-1;
		Object temp;
		while(child>0) {
			parent = (child-1)/2;
			if (currentLevel%2 == 0) {//even level
				if (((Comparable)mData[parent]).compareTo(mData[child]) >= 0)
					break;	
			}else {//currentLevel%2 == 1 //odd level
				if (((Comparable)mData[parent]).compareTo(mData[child]) <= 0)
					break;		
			}
			temp = (Comparable)mData[parent];
			mData[parent] = mData[child];
			mData[child] = temp;
			child = parent;
			currentLevel--;
			
		}
	}
	
	private static int findInitialLevel(int size) {
		int level = 0;
		while(size/2 > 0) {
			level++;
			size/=2;
		}
		return level;
	}
	//================================================================
	
	
	public static void main(String[] args) throws Exception {
		Heap heap = new Heap();
		
		HuffmanNode a = new HuffmanNode("a", 370);
		HuffmanNode b = new HuffmanNode("b", 80);
		HuffmanNode c = new HuffmanNode("c", 60);
		HuffmanNode d = new HuffmanNode("d", 150);
		HuffmanNode e = new HuffmanNode("e", 30);
		
		heap.add(a);
		heap.add(b);
		heap.add(c);
		heap.add(d);
		heap.add(e);
		
		HuffmanNode root = HuffmanNode.makeHuffmanTree(heap);
//		
//		HuffmanNode.breadthFirstSearch(root);
//		String bitString = HuffmanNode.findBitString(root, "");
//		System.out.println(bitString);
		
		//create integer heap, least is most important
//		Heap iHeap = new Heap();
//		iHeap.add(1);
//		iHeap.add(3);
//		iHeap.add(8);
//		iHeap.add(4);
//		iHeap.add(9);
//		iHeap.add(11);
//		iHeap.add(10);
//		iHeap.add(20);
//		iHeap.add(17);
//		int maxIndex  = iHeap.calculateMaxIndex();
//		System.out.println(iHeap.mData[maxIndex]);
		
		
		
//		System.out.println("\n===========test: min-max heap===============\n");
//		
//		Heap minMaxHeap = new Heap();
//		
//		minMaxHeap.addForMinMaxHeap(1);
//		minMaxHeap.addForMinMaxHeap(30);
//		minMaxHeap.addForMinMaxHeap(80);
//		minMaxHeap.addForMinMaxHeap(4);
//		minMaxHeap.addForMinMaxHeap(9);
//		minMaxHeap.addForMinMaxHeap(5);
//		minMaxHeap.addForMinMaxHeap(8);
//		minMaxHeap.addForMinMaxHeap(85);
//		minMaxHeap.addForMinMaxHeap(10);
//	
//	int currentLevel = 0;
//	for(int i = 1; i<= minMaxHeap.size; i++) {
//		if(findInitialLevel(i) > currentLevel) {
//			currentLevel = findInitialLevel(i);
//			System.out.println();
//		}
//		System.out.print(minMaxHeap.mData[i-1]+ " ");
//		
//	}
//	
//	minMaxHeap.addForMinMaxHeap(3);
//	
//	System.out.println();
//	
//	currentLevel = 0;
//	
//	for(int i = 1; i<= minMaxHeap.size; i++) {
//		if(findInitialLevel(i) > currentLevel) {
//			currentLevel = findInitialLevel(i);
//			System.out.println();
//		}
//		System.out.print(minMaxHeap.mData[i-1]+ " ");
//		
//	}
//	
		
	}
}
