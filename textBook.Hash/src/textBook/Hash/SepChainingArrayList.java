package textBook.Hash;

import java.util.ArrayList;

public class SepChainingArrayList {
	private static int DEFAULT_SIZE = 101;
	private static int MAXLOAD = 2;
	private ArrayList<Object>[] lists;
	private int currentSize = 0;
	
	public SepChainingArrayList() {
		this(DEFAULT_SIZE);
	}
	
	
	public SepChainingArrayList(int size) {
		int nextPrimeSize = nextPrime(size);
		lists = new ArrayList[nextPrimeSize];
		for (int i = 0; i<lists.length; i++) {
			lists[i] = new ArrayList<Object>();
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
	
	public void add(Object data) {
		int pos = hash(data);
		ArrayList<Object> theArray = lists[pos];
		if (!theArray.contains(data)) {
			theArray.add(data);
			currentSize++;
		}
		if (currentSize/lists.length >= MAXLOAD) {
			rehash();
		}
	}
	
	public void rehash() {
		ArrayList<Object>[] oldLists = lists;
		int newLength = nextPrime(lists.length * 2);
		lists = new ArrayList[newLength];
		
		for (int i = 0; i<lists.length; i++) {
			lists[i] = new ArrayList<Object>();
		}
		for (int i = 0; i<oldLists.length; i++) {
			while(!oldLists[i].isEmpty()) {
				add(oldLists[i].remove(0));
			}
		}
		
	}
	
	public void remove(Object data) {
		int pos = hash(data);
		ArrayList<Object> theArray = lists[pos];
		if (theArray.contains(data)) {
			theArray.remove(data);
			currentSize--;
		}
	}
	
	public static void main(String[] args) {
		SepChainingArrayList hash = new SepChainingArrayList();
		hash.add(1);
		hash.add(2);
		hash.add(5);
		hash.remove(2);
		
	}
}
