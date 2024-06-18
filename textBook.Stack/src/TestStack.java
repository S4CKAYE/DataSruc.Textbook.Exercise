
public class TestStack {

	MyStack s;
	//remove any duplicate values, out put does not preserve original order (set)
	public void removeDup() throws Exception {
		if(s.isEmpty() || s.size() == 1) {
			return;
		}
		
		MyStack temp = new StackLinkedList();
		MyStack checked = new StackLinkedList();
		int stackSize = s.size();
		int checkValue = s.top();
		//s.pop();
		
		for (int i = 0; i<stackSize; i++) {
			if(s.isEmpty() && temp.isEmpty()) {
				break;
			}
			checkValue = s.top();
			s.pop();
			while(!s.isEmpty()) {
			if(s.top() != checkValue) {
				temp.push(s.top());
				s.pop();
			}else {
				s.pop();
				}
			}
			checked.push(checkValue);
			
			if(s.isEmpty() && temp.isEmpty()) {
				break;
			}

			checkValue = temp.top();
			temp.pop();
			
			while(!temp.isEmpty()) {
				if(temp.top() != checkValue) {
					s.push(temp.top());
					temp.pop();
				}else {
					temp.pop();
				}
			}
			checked.push(checkValue);
		}
		s = checked;
	}
	
	public void removeMin() throws Exception {
		if(s.isEmpty()) {
			return;
		}
		int min = this.findMin();
		MyStack noMinStack = new StackLinkedList();
		while(!s.isEmpty()) {
			if(s.top() != min) {
				noMinStack.push(s.top());
			}
			s.pop();
		}
		
		while(!noMinStack.isEmpty()) {
			s.push(noMinStack.top());
			noMinStack.pop();
		}
		
	}
	public int findMin() throws Exception {
		int min = Integer.MAX_VALUE;
		MyStack temp = new StackLinkedList();
		while(!s.isEmpty()) {
			int current = s.top();s.pop();
			if(current < min) {
				min = current;
			}
			temp.push(current);	
		}
		while(!temp.isEmpty()) {
			s.push(temp.top());
			temp.pop();
		}
		return min;
	}
	
	
	public void printStack() throws Exception {
		MyStack temp = new StackArray();
		System.out.println("=========");
		while(!s.isEmpty()) {
			System.out.println(s.top() + "\n");
			temp.push(s.top());
			s.pop();
		}
		System.out.println("=========");
		
		while(!temp.isEmpty()) {
			s.push(temp.top());
			temp.pop();
		}
		
	}
	
	public void removeBottom() throws Exception {
		MyStack noBottom = new StackLinkedList();
		if(s.isEmpty()) { 
			throw new MyStackException();
		}
		while(s.size() > 1) {
			noBottom.push(s.top());
			s.pop();
		}
		s.makeEmpty();
		while(!noBottom.isEmpty()) {
			s.push(noBottom.top());
			noBottom.pop();
		}
	}
	
	public static void main(String[] args) throws Exception {
		MyStack s1 = new StackArray();
		
		s1.push(3);
		s1.push(1);
		s1.push(3);
		s1.push(4);
		s1.push(1);
		
		TestStack test = new TestStack();
		test.s = s1;
		
		System.out.println("=====Before: s1 =====");
		test.printStack();
		
		//test.removeDup();
		test.removeBottom();
		
		System.out.println("=====After: s1 =====");
		test.printStack();
		
	
		
	}
	
}
