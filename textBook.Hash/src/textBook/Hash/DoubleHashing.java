package textBook.Hash;

public class DoubleHashing extends OpenAddressing{
	private static double MAXFACTOR = 0.75;
	private int occupiedSlots = 0;
	
	public DoubleHashing() {
		this(DEFAULT_SIZE);
	}
	
	public DoubleHashing(int size) {
		super(size);
	}
	
	public int hash(Object data) {
		int hashValue = data.hashCode();
		int abs = Math.abs(hashValue);
		return abs%array.length;
	}
	
	public int hash2(Object data) {
		int abs = Math.abs(data.hashCode());
		return 3-(abs%3);
	}
	
	//returns an slot with our data or an empty slot
	//else return -1;
	public int find(Object data) {
		final int smallNum = 5;
		int h = hash(data);
		int hash2Result = hash2(data);
		for(int i = 0; i<currentSize+smallNum; i++) {
			if (array[h] == null || array[h].equals(data)) {
				return h;
			}
			h = (h+hash2Result)%array.length;
			//h = (h+1)%array.length       linear probing
			//h = (h+2*i-1)%array.length   quadratic probing
			
		}
		return -1;
	}
	
	public void add(Object data) {
		int h = hash(data);
		int hash2Result = hash2(data);
		int emptySlotPosition = -1;
		int i;
		final int smallNum = 5; //a small threshild
		for (i = 0; i<currentSize + smallNum; i++) {
			if(array[h] == null || array[h].equals(data)) {
				break;
			}
			if (array[h] == DELETED && emptySlotPosition == -1) {
				emptySlotPosition = h;
			}
			h = (h + hash2Result) % array.length;
			//h = (h+1)%array.length       linear probing
			//h = (h+2*i-1)%array.length   quadratic probing
		}
		if(i >= currentSize + smallNum) {
			rehash();
			add(data);
		}else {
			if(array[h] == null) {// to add
				if (emptySlotPosition != -1) {// adding in DL position
					array[emptySlotPosition] = data;
				}else {
					array[h] = data;
					occupiedSlots++;
				}
				currentSize++;
				double currentLoadFactor = (double)(occupiedSlots/array.length);
				if (currentLoadFactor > MAXFACTOR) {
					rehash();
				}
			}
		}
	}
	
	public void rehash() {
		Object[] oldArray = array;
		array = new Object[nextPrime(array.length*2)];
		currentSize = 0;
		occupiedSlots = 0;
		for (int i = 0; i< oldArray.length; i++) {
			if (oldArray[i] != null && oldArray[i] != DELETED) {
				add(oldArray[i]);
			}
		}
	}			
	
	public void remove(Object data) {
		int index = find(data); //returns an empty position or position having the data
		if (index != -1 && array[index] != null) {
			array[index] = DELETED;
			currentSize--;
		}
	}
}
