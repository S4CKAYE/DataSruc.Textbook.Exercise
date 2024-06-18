
public class Q {
MyQueue q;
DeQ dq;

	public void removeOddIndex() throws Exception {
		int size = q.size();
		for(int i = 0; i<size; i++) {
			if(i%2 == 1) {//odd index
				q.removeFirst();
			}else {
				q.insertLast(q.removeFirst());
			}
		}
	}
	
	public void printQ() throws Exception {
		int size = q.size();
		
		for(int i = 0; i<size; i++) {
			int v = q.removeFirst();
			System.out.print(v + " ");
			q.insertLast(v);
		}
		
	}
	
	public void printDeQ() throws Exception {
		int size = dq.size();
		
		for(int i = 0; i<size; i++) {
			int v = dq.removeFirst();
			System.out.print(v + " ");
			dq.insertLast(v);
		}
		
	}
	
	public void moveBackToFront() throws Exception {
		int size = q.size();
		
		for(int i = 0; i<size-1; i++) {
			q.insertLast(q.removeFirst());
		}
	}
	
	public void moveToFront(int x) throws Exception {
		int size = q.size();
		MyQueue temp = new QueueLinkedList();
		for(int i = 0; i<size; i++) {
			int v = q.removeFirst();
			if (v == x) {
				continue;
			}else {
				temp.insertLast(v);
			}
		}
		q.insertLast(x);
		while(!temp.isEmpty()) {
			q.insertLast(temp.removeFirst());
		}
	}
	
	public void reverseQueue() throws Exception {
		StackArray stack = new StackArray();
		while(!q.isEmpty()) {
			stack.push(q.removeFirst());
		}
		while(!stack.isEmpty()) {
			q.insertLast(stack.top());
			stack.pop();
		}
	}
	
	public Q merge(MyQueue q1, MyQueue q2) throws Exception {
		Q mergeQ = new Q();
		mergeQ.q = new QueueLinkedList();
		while(!q1.isEmpty() && !q2.isEmpty()) {
			int v1 = q1.front();
			int v2 = q2.front();
			if(v1 <= v2) {
				mergeQ.q.insertLast(q1.removeFirst());
			}else {
				mergeQ.q.insertLast(q2.removeFirst());
			}
		}
		if(!q1.isEmpty()) {
			while(!q1.isEmpty()) {
				mergeQ.q.insertLast(q1.removeFirst());
			}
		}else {
			while(!q2.isEmpty()) {
				mergeQ.q.insertLast(q2.removeFirst());
			}
		}
		return mergeQ;
	}
	
	public MyQueue sortQueue() throws Exception {
		if (q.size() == 0 || q.size() == 1) {
			return q;
		}
		
		MyQueue firstHalf = new QueueLinkedList();
		MyQueue secondHalf = new QueueLinkedList();
		this.splitHalfQueue(q, firstHalf, secondHalf);
		
		Q firstQ = new Q(); firstQ.q = firstHalf;
		Q secondQ = new Q(); secondQ.q = secondHalf;
		
		firstHalf  = firstQ.sortQueue();
		secondHalf = secondQ.sortQueue();
		
		return merge(firstHalf, secondHalf).q;
		
	}
	public void splitHalfQueue(MyQueue q, MyQueue firstHalf, MyQueue secondHalf) throws Exception {
		int mid = q.size()/2;
		
		for(int i = 0; i < mid; i++) {
			firstHalf.insertLast(q.removeFirst());
		}
		while(!q.isEmpty()) {
			secondHalf.insertLast(q.removeFirst());
		}
	}
	//put new data x at position i, the data from position get shifted by 1 position.
	public void jumpQueue(int x, int i) throws Exception {
		int originalSize = q.size();
		if(i >= originalSize || i<0) {
			throw new Exception();
		}
		for(int j = 0; j< i; j++) {
			q.insertLast(q.removeFirst());
		}
		q.insertLast(x);
		for(int j = 0; j< originalSize - i; j++) {
			q.insertLast(q.removeFirst());
		}
	}
	// this method works only with DeQ type**
	public void swap(int p1, int p2) throws Exception {
		int size = dq.size();
		if(p1 >= size || p2 >= size || p1 < 0 || p2 <0) {
			throw new Exception();
		}
		int v1 = -1;
		int v2 = -1;
		//find the value to swap
		for(int i = 0; i < size; i++) {
			if(i == p1) {
				v1 = dq.front();
			}else if(i == p2) {
				v2 = dq.front();
			}
			dq.insertLast(dq.removeFirst());
		}
		//swap the value according to the position
		for(int i = 0; i < size; i++) {
			if(i == p1) {
				dq.removeFirst();
				dq.insertFirst(v2);
			}else if(i == p2) {
				dq.removeFirst();
				dq.insertFirst(v1);
			}
			dq.insertLast(dq.removeFirst());
		}
	}

	
	
	public static void main(String[] args) throws Exception {
		MyQueue q1 = new QueueLinkedList();
		q1.insertLast(0);
		q1.insertLast(2);
		q1.insertLast(4);
		q1.insertLast(6);
		q1.insertLast(8);
		q1.insertLast(10);
		
		MyQueue q2 = new QueueLinkedList();
		q2.insertLast(1);
		q2.insertLast(11);
		q2.insertLast(5);
		q2.insertLast(7);
		q2.insertLast(9);
		q2.insertLast(13);
		
		DeQ dq1 = new DeQArray();
		
		dq1.insertLast(1);
		dq1.insertLast(11);
		dq1.insertLast(5);
		dq1.insertLast(7);
		dq1.insertLast(9);
		dq1.insertLast(13);
		
		Q qTest = new Q();
		qTest.dq = dq1;
		System.out.println("\n======Before: dq1=====\n");
		qTest.printDeQ();
		qTest.swap(1, 3);
		System.out.println("\n======After: dq1=====\n");
		qTest.printDeQ();
		//Q qTest2 = new Q();
		//qTest2.q = q2;
		
		
//		qTest.q = q1;
		//System.out.println("\n======Before: q2=====\n");
		//qTest2.printQ();
		//qTest2.q = qTest.sortQueue();
		//qTest2.jumpQueue(77, 3);
		//System.out.println("\n\n======After: q2=====\n");
		//qTest.removeOddIndex();
		//qTest.moveBackToFront();
		//qTest.moveToFront(3);
		//qTest.reverseQueue();
		//qTest2.printQ();
		
		
	}
	
}
