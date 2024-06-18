public class CDLinkedList {
	DListNode header;
	int size;
	static final int HEADERVALUE = -99;

	public CDLinkedList() {
		header = new DListNode(HEADERVALUE);
		makeEmpty();// necessary, otherwise next/previous node will be null
	}

	public boolean isEmpty() {
		return header.nextNode == header;
	}

	public boolean isFull() {
		return false;
	}

	/** make the list empty. */
	public void makeEmpty() {
		header.nextNode = header;
		header.previousNode = header;
		size = 0;
	}

	// put in new data after the position of p.
	public void insert(int value, Iterator p) throws Exception {
		if (p == null || !(p instanceof DListIterator))
			throw new Exception();
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();

		DListIterator p3 = new DListIterator(p2.currentNode.nextNode);
		DListNode n = new DListNode(value, p3.currentNode, p2.currentNode);
		p2.currentNode.nextNode = n;
		p3.currentNode.previousNode = n;
		size++;
	}

	// return position number of value found in the list.
	// otherwise, return -1.
	public int find(int value) throws Exception {
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				return -1;
			if (v == value)
				return index; // return the position of value.
		}
		return -1;
	}

	// return data stored at kth position.
	public int findKth(int kthPosition) throws Exception {
		if (kthPosition < 0 || kthPosition > size - 1)
			throw new Exception();// exit the method if the position is
		// beyond the first/last possible
		// position, throwing exception in the process.
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				throw new Exception();
			if (index == kthPosition)
				return v;
		}
		throw new Exception();
	}

	// Return iterator at position before the first position that stores value.
	// If the value is not found, return null.
	public Iterator findPrevious(int value) throws Exception {
		if (isEmpty())
			return null;
		Iterator itr1 = new DListIterator(header);
		Iterator itr2 = new DListIterator(header);
		int currentData = itr2.next();
		while (currentData != value) {
			currentData = itr2.next();
			itr1.next();
			if (((DListIterator) itr2).currentNode == header)
				return null;
		}
		return itr1;
	}

	// remove content at position just after the given iterator. Skip header if
	// found.
	public void remove(Iterator p) {
		if (isEmpty())
			return;
		if (p == null || !(p instanceof DListIterator))
			return;
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			return;
		if (p2.currentNode.nextNode == header)
			p2.currentNode = header;
		if (p2.currentNode.nextNode == null)
			return;
		DListIterator p3 = new DListIterator(p2.currentNode.nextNode.nextNode);
		p2.currentNode.nextNode = p3.currentNode;
		p3.currentNode.previousNode = p2.currentNode;
		size--;
	}

	// remove the first instance of the given data.
	public void remove(int value) throws Exception {
		Iterator p = findPrevious(value);
		if (p == null)
			return;
		remove(p);
	}

	// remove data at position p.
	// if p points to header or the list is empty, do nothing.
	public void removeAt(Iterator p) throws Exception {
		if (isEmpty() || p == null || !(p instanceof DListIterator) || ((DListIterator) p).currentNode == null
				|| ((DListIterator) p).currentNode == header)
			return;

		DListIterator p2 = (DListIterator) (findPrevious(p));
		remove(p2);

	}

	// Print each data out into a string, one by one.
	public String printList() throws Exception {
		String ans = "";
		DListIterator itr = new DListIterator(header);
		while (itr.hasNext() && itr.currentNode.nextNode != header) {
			int data = itr.next();

			ans += data +" ";
		}
		ans = ans.trim();
		return ans;
	}

	public int size() {
		return size;
	}

	// return iterator pointing to location before position.
	public Iterator findPrevious(Iterator position) throws Exception {
		if (position == null)
			return null;
		if (!(position instanceof DListIterator))
			return null;
		if (((DListIterator) position).currentNode == null)
			return null;

		DListIterator p = ((DListIterator) position);
		DListIterator p2 = new DListIterator(p.currentNode.previousNode);
		return p2;

	}
	
	
	//=======================Implemented methods==========================
	
	//inserts a new node with value x between header and other nodes.
	public void insertAtFront(int x) {
		DListNode next2Header = header.nextNode;
		DListNode newNode =  new DListNode(x, next2Header,header);
		header.nextNode = newNode;
		next2Header.previousNode = newNode;
		size++;
	}
	
	
	public int removeAtLast() {
		if(this.size == 0) {//list is empty
			return -1;
		}
		int output = header.previousNode.data;
		DListNode prevOfLast = header.previousNode.previousNode;
		prevOfLast.nextNode = header;
		header.previousNode = prevOfLast;
		size--;
		return output;
	}
	//uses helper method swapNodes()
	public void reverseList() throws Exception {
		DListIterator start = new DListIterator(header.nextNode);
		DListIterator end = new DListIterator(header.previousNode);
		while(start.currentNode != end.currentNode) {
			swapNodes(start.currentNode, end.currentNode);
			start.next();
			end.previous();
		}
	}
	//helper methods for reverseList();
	private void swapNodes(DListNode n1, DListNode n2) {
		if (n1 == null || n2 == null) {
			return;
		}
		int v1 = n1.data;
		int v2 = n2.data;
		
		n1.data = v2; n2.data = v1;
	}
	//returns true if x is stored in a node before y
	//if x or y is not in the list, returns false
	public boolean isInFront(int x, int y) {
		try {
			//find index where x and y is stored
			int posX = this.find(x);
			int posY = this.find(y);
			if (posX == -1 || posY == -1) {//x or y is not in the list.
				return false;
			}
			if(posX < posY) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	//remove all duplicated data (5,5,3,5,7) => (5,7,3)
	public void setify() {
		DListNode current1 = header.nextNode;
		try {
		while(current1 != header) {
			int v = current1.data;
			DListNode current2 = current1.nextNode;
			while(current2 != header) {
				if (current2.data == v) {
					current2 = current2.nextNode;
					this.removeAt(new DListIterator(current2.previousNode));
				}else {
					current2 = current2.nextNode;
				}
			}
			current1 = current1.nextNode;
		}
		}catch (Exception e) {
			return;
		}
	}
	
	public void removeBefore(DListIterator p) {
		if (p == null || p.currentNode.previousNode == header || this.isEmpty()) {
			return;
		}
		try {
			this.removeAt(new DListIterator(p.currentNode.previousNode));
		} catch (Exception e) {
			return;
		}
	}
	
	public void removeMin() {
		if(this.isEmpty()) {
			return;
		}
		int min = this.findMin();
		try {
			this.remove(min);
		} catch (Exception e) {
			return;
		}
	}
	public int findMin() {
		DListNode current = header.nextNode;
		int min = Integer.MAX_VALUE;
		while(current != header) {
			if (current.data < min) {
				min = current.data;
			}
			current = current.nextNode;		
		}
		return min;
	}
	// moves content of n to the front of the list.
	//other contents relative ordering remains unchanged
	//if node not in list, or node is header, do nothing
	public void moveToFront(DListNode n) {
		DListNode current = header.nextNode;
		try {
		while(current != header) {
			if (current == n) {
				this.removeAt(new DListIterator(n));
				this.insert(n.data, new DListIterator(header));
				return;
			}
			current = current.nextNode;
		}
		} catch (Exception e) {
			return;
		}
	}
	//removed all data from this list
	//copies all items from list2 to itself
	//changing data in the new list does not affect list2
	public void clone(CDLinkedList list2) {
		this.makeEmpty();
		DListNode current2 = list2.header.nextNode;
		try {
		while(current2 != list2.header) {// for every node in list2
			this.insert(current2.data, new DListIterator(header.previousNode));
			current2 = current2.nextNode;
		}
		} catch (Exception e) {
			return;
		}
	}
		//removes all value greater than value from this list
		//returns a list containing removed values in order from left to right.
		public CDLinkedList partition(int value) {
			DListNode current1 = header.nextNode;
			CDLinkedList greaterThanV = new CDLinkedList();
			try {
			while(current1 != header) {
				if (current1.data <= value) {
					current1 = current1.nextNode;
				}else {//current1.data > value
					current1 = current1.nextNode;
					greaterThanV.insert(current1.previousNode.data, new DListIterator(greaterThanV.header.previousNode));
					this.removeAt(new DListIterator(current1.previousNode));
				}
			}
			return greaterThanV;
			} catch (Exception e) {
				return greaterThanV;
			}
		}
		//swap data of node at index = x with node at index = y
		public void swap(int x, int y) {
			if (x<0 || y<0 || x == y || x >= this.size || y >= this.size || this.size <= 1) {
				return;
			}
			DListNode nodeX = null;
			int dataX = 0;
			DListNode nodeY = null;
			int dataY = 0;
			DListNode current = header.nextNode;
			for (int i = 0; i<this.size; i++) {
				if (i == x) {
					nodeX = current;
					dataX = nodeX.data;
				}else if (i == y) {
					nodeY = current;
					dataY = nodeY.data;
				}
				current = current.nextNode;
			}
			nodeX.data = dataY;
			nodeY.data = dataX;	
		}
		//modify list so that even numbers are in front, odd numbers are in back
		//also preserve order.
		public void evenOdds() {
			DListIterator evenItr = new DListIterator(header);
			DListIterator oddItr = new DListIterator(header.previousNode);
			DListNode current =header.nextNode;
			int size = this.size;
			try {
			for(int i = 0; i<size; i++) {
				if(current.data%2 == 0) {//even 
					this.insert(current.data, evenItr);
					evenItr.next();
					current = current.nextNode;
					this.removeAt(new DListIterator(current.previousNode));
				}else {
					this.insert(current.data, oddItr);
					oddItr.next();
					current = current.nextNode;
					this.removeAt(new DListIterator(current.previousNode));
				}
			}
			} catch(Exception e) {
				return;
			}
		}
		//creates a new list by taking elements from C, specified by their indices in P
		//ex. P contains 5,1 3 => output will have elements of node at index 5 , 1, 3 of C
		//ignores if specified index does not exist
		//uses helper method getKthFromC
		public CDLinkedList specificElements(CDLinkedList C, int[] P) {
			CDLinkedList output = new CDLinkedList();
			if(P.length == 0) {
				return output;	
			}
			try {
			for (int i = 0; i<P.length; i++) {
				int index = P[i];
				DListIterator itrAtK = getKthIteratorFromC(C, index);
				if (itrAtK != null) {
					output.insert(itrAtK.currentNode.data, new DListIterator(output.header.previousNode));
				}			
			}
			return output;
			
			} catch (Exception e) {
				return output;
			}
		}
		
		private DListIterator getKthIteratorFromC(CDLinkedList C, int k) {
			if (k>= C.size || k<0) {
				return null;
			}
			try {
			DListIterator itrC = new DListIterator(C.header.nextNode);
			int i = 0;
			while(i != k) {
				itrC.next();
				i++;
			}
			return itrC;
			} catch (Exception e) {
				return null;
			}
		}
		//creates a new list which is the union of a and b
		//a and b remain unchanged
		//no duplicate data in u
		public CDLinkedList union(CDLinkedList a, CDLinkedList b) {
			CDLinkedList u = new CDLinkedList();
			DListIterator itrA = new DListIterator(a.header.nextNode);
			DListIterator itrB = new DListIterator(b.header.nextNode);
			int v = itrA.currentNode.data;
			try {
			while(itrA.currentNode != a.header) {
				if (u.find(v) == -1) {
					u.insert(v, new DListIterator(u.header.previousNode));
				}
				v = itrA.next();
			}
			v = itrB.currentNode.data;
			while(itrB.currentNode != b.header) {
				if (u.find(v) == -1) {
					u.insert(v, new DListIterator(u.header.previousNode));
				}
				v = itrB.next();
			}
			return u;
			
			} catch (Exception e) {
				return u;
			}
			
		}
		//creates new list which results is intersection of a and b (exist in a and b)
		//no duplicate data added
		public CDLinkedList intersect(CDLinkedList a, CDLinkedList b) {
			CDLinkedList u = new CDLinkedList();
			DListIterator itrA = new DListIterator(a.header.nextNode);
			int v = itrA.currentNode.data;
			try {
			while(itrA.currentNode != a.header) {
				if (b.find(v) != -1 && u.find(v) == -1) {//add if exist in b and not in u
					u.insert(v, new DListIterator(u.header.previousNode));
				}
				v = itrA.next();
			}
			return u;
			
			} catch (Exception e) {
				return u;
			}
			
		}
		//creates new list which has data which are in a but not b.
		//output must have no duplicate data
		public CDLinkedList diff(CDLinkedList a, CDLinkedList b) {
			CDLinkedList u = new CDLinkedList();
			DListIterator itrA = new DListIterator(a.header.nextNode);
			int v = itrA.currentNode.data;
			try {
			while(itrA.currentNode != a.header) {
				if (b.find(v) == -1 && u.find(v) == -1) {//add if not exist in b and not in u
					u.insert(v, new DListIterator(u.header.previousNode));
				}
				v = itrA.next();
			}
			return u;
			
			} catch (Exception e) {
				return u;
			}
			
		}
		//changes this list by moving position start to end(inclusive) to position in front of p
		//assume list is not empty
		//all itr points to node in list, start is always left to end
		//p is not between start and end(inclusive)
		public void swapChunk(DListIterator start, DListIterator end, DListIterator p) {
			DListNode prevP = p.currentNode.previousNode;
			DListNode prevStart = start.currentNode.previousNode;
			DListNode nextEnd = end.currentNode.nextNode;
			
			prevP.nextNode = start.currentNode;
			start.currentNode.previousNode = prevP;
			
			end.currentNode.nextNode = p.currentNode;
			p.currentNode.previousNode = end.currentNode;
			
			prevStart.nextNode = nextEnd;
			nextEnd.previousNode = prevStart;
		}
	
	public static void main(String[] args) throws Exception {
		CDLinkedList cd1 = new CDLinkedList();
		cd1.insert(1, new DListIterator(cd1.header));
		cd1.insert(3, new DListIterator(cd1.header.previousNode));
		cd1.insert(5, new DListIterator(cd1.header.previousNode));
		cd1.insert(6, new DListIterator(cd1.header.previousNode));
		cd1.insert(7, new DListIterator(cd1.header.previousNode));
		System.out.println(cd1.printList());
		
		CDLinkedList cd2 = new CDLinkedList();
		cd2.insert(5, new DListIterator(cd2.header));
		cd2.insert(6, new DListIterator(cd2.header.previousNode));
		cd2.insert(7, new DListIterator(cd2.header.previousNode));
		cd2.insert(9, new DListIterator(cd2.header.previousNode));
		cd2.insert(10, new DListIterator(cd2.header.previousNode));
		System.out.println(cd2.printList());
		
		CDLinkedList cd3 = new CDLinkedList();
		cd3.insert(3, new DListIterator(cd3.header));
		cd3.insert(6, new DListIterator(cd3.header.previousNode));
		cd3.insert(4, new DListIterator(cd3.header.previousNode));
		cd3.insert(7, new DListIterator(cd3.header.previousNode));
		//System.out.println(cd3.printList());
		
		
		
//		System.out.println("\n=======test: insertAtFront(int x)=========\n");
//		cd1.insertAtFront(19);
//		System.out.println(cd1.printList());
//		System.out.print(cd1.size);
//		CDLinkedList cd2 = new CDLinkedList();
//		cd2.insertAtFront(8);
//		System.out.println(cd2.printList());
//		System.out.println("\n=======test: removeAtLast()=========\n");
//		cd1.removeAtLast();
//		System.out.println(cd1.printList());
//		System.out.print(cd1.size);
//		System.out.println("\n=======test: reverseList()=========\n");
//		cd1.reverseList();
//		System.out.println(cd1.printList());
//		System.out.println("\n=======test: isInFront()=========\n");
//		System.out.println(cd1.isInFront(6, 100));
//		System.out.println("\n=======test: setify()=========\n");
//		cd2.setify();
//		System.out.println(cd2.printList());
//		System.out.println("\n=======test: removeBefore()=========\n");
//		//itr that marks 9
//		DListIterator itr9 = (DListIterator) cd2.findPrevious(10);
//		DListIterator itr5 = (DListIterator) cd2.findPrevious(6);
//		cd2.removeBefore(itr9);
//		// 7 should be removed
//		System.out.println(cd2.printList());
//		cd2.removeBefore(itr5);
//		// should return immediately since 5 is the first node
//		System.out.println(cd2.printList());
//		System.out.println("\n=======test: removeMin()=========\n");
//		cd1.removeMin();
//		System.out.println(cd1.printList());
//		System.out.println("\n=======test: moveToFront(DListNode n)=========\n");
//		DListIterator itr6  = (DListIterator)cd1.findPrevious(7);
//		DListNode nodeNotInList = new DListNode(990);
//		cd1.moveToFront(itr6.currentNode.nextNode.nextNode);
//		System.out.println(cd1.printList());
//		System.out.println("\n=======test: clone(CDLinkedList list2)=========\n");
//		cd1.clone(cd2);
//		System.out.println(cd1.printList());
//		DListIterator itr9 = (DListIterator)cd1.findPrevious(10);
//		itr9.currentNode.data = 11;
//		//original list (c2) should not change
//		System.out.println(cd1.printList());
//		System.out.println(cd2.printList());
//		System.out.println("\n=======test: partition(int value)=========\n");
//		CDLinkedList greaterThan5 = cd3.partition(10);
//		System.out.println(cd3.printList());
//		System.out.println(greaterThan5.printList());
//		System.out.println("\n=======test: swap(int x, int y)=========\n");
//		cd3.swap(0, 7);
//		System.out.println(cd3.printList());
//		System.out.println("\n=======test: evenOdds()=========\n");
//		cd2.evenOdds();
//		System.out.println(cd2.printList());
//		System.out.println("\n=======test: specificElements()=========\n");
//		int[] P = new int[2];
//		P[0] = 4; P[1] = 100;
//		CDLinkedList elementsFromC = cd1.specificElements(cd2, P);
//		System.out.println(cd2.printList());
//		System.out.println(elementsFromC.printList());
		System.out.println("\n=======test: union(CDLinkedList a, CDLinkedList b)=========\n");
		CDLinkedList union = cd3.union(cd1, cd2);
		System.out.println(union.printList());
//		System.out.println("\n=======test: intersect(CDLinkedList a, CDLinkedList b)=========\n");
//		CDLinkedList intersect = cd3.intersect(cd1, cd2);
//		System.out.println(intersect.printList());
		
//		DListIterator start = (DListIterator)union.findPrevious(6);//5
//		DListIterator end = (DListIterator)union.findPrevious(9);//7
//		DListIterator p = (DListIterator)union.findPrevious(5);//3
//		System.out.println("\n=======test: swapChunk(CDLinkedList a, CDLinkedList b)=========\n");
//		union.swapChunk(start, end, p);
//		System.out.println(union.printList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}