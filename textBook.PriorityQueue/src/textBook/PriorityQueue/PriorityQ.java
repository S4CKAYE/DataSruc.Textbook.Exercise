package textBook.PriorityQueue;

public interface PriorityQ {
	int size();
	
	boolean isEmpty();
	
	void add(Object element) throws Exception;
	
	public Object top() throws Exception;
	
	public Object pop() throws Exception;
}
