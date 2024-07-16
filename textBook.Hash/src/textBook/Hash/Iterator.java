package textBook.Hash;

public interface Iterator {

	public boolean hasNext();

	public boolean hasPrevious();

	// Move iterator to the next position,
	// then returns the value at that position.
	public Object next() throws Exception; 
	                    
	// Return the value at current position,
	// then move the iterator back one position.
	public Object previous() throws Exception;
	                        
	public void set(Object value);

}
