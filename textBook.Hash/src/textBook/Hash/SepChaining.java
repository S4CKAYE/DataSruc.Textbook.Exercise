package textBook.Hash;

public class SepChaining {
	private static int DEFAULT_SIZE = 101;
	private static int MAXLOAD = 2;
	private CDLinkedList[] lists;
	private int currentSize = 0;
	
	public SepChaining() {
		this(DEFAULT_SIZE);
	}
	
	public SepChaining(int size) {
		int nextPrimeSize = nextPrime(size);
		lists = new CDLinkedList[nextPrimeSize];
		for (int i = 0; i<lists.length; i++) {
			lists[i] = new CDLinkedList();
		}
	}
	
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	protected static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		for (; !isPrime(n); n += 2) {
		}
		return n;
	}
	
	public int hash(Object data) {
		int hashValue = data.hashCode();
		int abs = Math.abs(hashValue);
		return abs%lists.length;
	}
	
	// return position number of value found in the list.
	// otherwise, return -1.
	public int find(Object data) throws Exception {
		int pos = hash(data);
		CDLinkedList theList = lists[pos];
		return theList.find(data);
	}
	
	public void add(Object data) throws Exception {
		int pos = hash(data);
		CDLinkedList theList = lists[pos];
		if (theList.find(data) == -1) {//not found.
			DListIterator itr = new DListIterator(lists[pos].header);
			lists[pos].insert(data, itr);
			currentSize++;
		}
		if (currentSize/lists.length >= MAXLOAD) {
			rehash();
		}
	}
	
	public void rehash() throws Exception{
		CDLinkedList[] oldLists = lists;
		int newLength = nextPrime(2*lists.length);
		lists = new CDLinkedList[newLength];
		//initialize linkedlists
		for (int i = 0; i<lists.length; i++) {
			lists[i] = new CDLinkedList();
		}
		for (int i = 0; i<oldLists.length; i++) {
			DListIterator itr = new DListIterator(oldLists[i].header);
			while(itr.currentNode.nextNode != oldLists[i].header) {
				add(itr.next());
			}
		}
		
	}
	
	public void remove(Object data) throws Exception{
		int pos = hash(data);
		CDLinkedList theList = lists[pos];
		if (theList.find(data) != -1) {//data found
			theList.remove(data);
			currentSize--;
		}
	}
	
}
