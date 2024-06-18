
public class StackLinkedList implements MyStack {
	private CDLinkedList theList;
	
	public StackLinkedList(){ // create an empty stack
		theList = new CDLinkedList();
	}
	
	public StackLinkedList(CDLinkedList l) throws Exception {
		super();
		DListIterator iparam = new DListIterator(l.header);
		DListIterator ithis = new DListIterator(theList.header);
		while (iparam.hasNext()) {
			int v = iparam.next();
			if (iparam.currentNode == l.header)
				return;
			theList.insert(v, ithis);
			ithis.next();
		}	
	}
	
	public CDLinkedList getTheList() {
		return theList;
	}

	public void setTheList(CDLinkedList theList) {
		this.theList = theList;
	}

	public boolean isEmpty(){
		return theList.isEmpty();
	}
	
	public boolean isFull(){
		return false;
	}
	
	public void makeEmpty(){
		theList.makeEmpty();
	}
	
	public int top() throws MyStackException{
		if(isEmpty())
			throw new MyStackException();
		return theList.header.nextNode.data;
	}
	
	public void pop() throws MyStackException{
		if(isEmpty())
			throw new MyStackException();
		Iterator itr = new DListIterator(theList.header);
		theList.remove(itr);
	}
	
	public void push(int data) throws Exception{
		Iterator itr = new DListIterator(theList.header);
		theList.insert(data, itr);
	}
	
	private DListIterator findPos(int i) throws Exception {
		DListIterator iPos = new DListIterator(theList.header);
		for(int k =0;k<= i;k++) {
			iPos.next();
		}
		return iPos;
	}
	
	public int size() {
		return theList.size;
	}
	
	//implemented method
	
	public void addNoDuplicate (StackLinkedList s2) throws Exception {
		StackLinkedList temp = new StackLinkedList();
		StackLinkedList duplicate = new StackLinkedList();
		StackLinkedList unique = new StackLinkedList();
		
		while(!s2.isEmpty()) {
			while(!this.isEmpty() && this.top() != s2.top()) {
				temp.push(this.top());
				this.pop();
			}
			if(this.isEmpty()) {//no duplicate
				unique.push(s2.top());
				s2.pop();
			}else if (this.top() == s2.top()) {//duplicate data
				duplicate.push(s2.top());
				s2.pop();
			}
			
			while(!temp.isEmpty()) {
				this.push(temp.top());
				temp.pop();
			}
		}
		
		while(!unique.isEmpty()) {
			this.push(unique.top());
			unique.pop();
		}
		
		while(!duplicate.isEmpty()) {
			s2.push(duplicate.top());
			duplicate.pop();
		}
		
	}
	
	public void printStack() throws Exception {
		StackLinkedList temp = new StackLinkedList();
		System.out.println("=========");
		while(!this.isEmpty()) {
			System.out.println(this.top() + "\n");
			temp.push(this.top());
			this.pop();
		}
		System.out.println("=========");
		
		while(!temp.isEmpty()) {
			this.push(temp.top());
			temp.pop();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		StackLinkedList s1 = new StackLinkedList();
		StackLinkedList s2 = new StackLinkedList();
		s1.push(5);
		s1.push(4);
		s1.push(3);
		s1.push(2);
		s1.push(1);
		
		s2.push(1);
		s2.push(2);
		s2.push(3);
		s2.push(4);
		s2.push(5);
			
		System.out.println("=====Before: s1 =====");
		s1.printStack();
		
		System.out.println("=====Before: s2 =====");
		s2.printStack();
		
		System.out.println(s1.isEmpty()+ "\n");
		System.out.println(s2.isEmpty()+ "\n");
		
		s1.addNoDuplicate(s2);
		
		System.out.println("=====After: s1 =====");
		s1.printStack();
		
		System.out.println("=====After: s2 =====");
		s2.printStack();
		
		
		
	}

	
}
