package textBook.PriorityQueue;

//modified to store HeapNode

public class QueueArray  {
	protected HeapNode[] theArray;
	protected int size; // number of currently stored data.
	protected int front; // index of the first data

	static final int DEFAULT_CAPACITY = 10;

	public QueueArray() {
		this(DEFAULT_CAPACITY);
	}

	public QueueArray(int capacity) {
		theArray = new HeapNode[capacity];
		size = 0;
		front = -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == theArray.length;
	}

	public void makeEmpty() {
		size = 0;
		front = -1;
	}

	public int size() {
		return size;
	}

	public HeapNode front() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return theArray[front];
	}

	public HeapNode back() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		return theArray[(front + size - 1) % theArray.length];
	}

	/*
	 * return an item at the front of the queue, delete that item. Throw
	 * exception if the queue is empty.
	 */
	public HeapNode removeFirst() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException();
		size--;

		HeapNode frontItem = theArray[front];
		front = (front + 1) % theArray.length;
		return frontItem;
	}

	public void doubleCapacity() { // resize array to twice the original size
		HeapNode[] temp = new HeapNode[theArray.length * 2];
		
		//cannot use arraycopy because we have to go round the array
		for (int i = 0; i < size; i++) {
			temp[i] = theArray[(front+i)%theArray.length];
		}
		theArray = temp;
		front =0;
	}

	public void insertLast(HeapNode n) throws Exception{
		if (isFull())
			doubleCapacity();
		if(isEmpty())
			front=0;
		theArray[(front + size) % theArray.length] = n;
		size++;
	}
	
	public static void main(String[] args) {
		int d = (0)%5;
		System.out.println(d);
	}

}
