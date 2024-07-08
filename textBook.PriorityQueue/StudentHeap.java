package textBook.PriorityQueue;

public class StudentHeap extends Heap {
	
	
	//this method combines secondHeap of Student with our heap
	//return a new heap will data from both heaps
	//for each data, a new copy of it must be created then added.
	public Heap mergeHeap(Heap secondHeap) {
		Heap mergeH = new StudentHeap();
		
		for(int i = 0; i<this.size; i++) {//O(n1) 
			Student s = (Student) mData[i];
			mergeH.add(new Student(s.name, s.mark));//worst case O(logN) // amortize time O(1);
		}
		for (int j =0; j<secondHeap.size; j++) {//O(n2)
			Student s = (Student) secondHeap.mData[j];
			mergeH.add(s);//worst case O(logN) // amortize time O(1);
		}
		return mergeH;
	}
	//returns true if students with more marks are organized to be popped first.
	//assume the structure is ordered correctly
	public static boolean isAHeap(Heap h) {
		int size = h.size();
		Object[] mData = h.mData;
		int child;
		for (int parent = 0; parent < size; parent++) {
			child = 2*parent +1;
			if(child >= size) {
				break;
			}
			//returns false if parent has less priority than child
			if (((Comparable)mData[parent]).compareTo(mData[child]) > 0) {
				return false;
			}
			if (child+1<size) {
				if (((Comparable)mData[parent]).compareTo(mData[child+1]) > 0) {
					return false;
				}
			}
		}
		return true;
	}
	//change the mark of a student of given name
	//after change, the heap must still hold heap properties.
	public void changemark(String name, int newMark) {
		for (int i = 0; i<size; i++) {//O(n)
			Student currentS = (Student)mData[i];
			if (currentS.name == name) {//found that student
				//change the mark;
				currentS.mark = newMark;
				
				//replace this position with the last data and percolate down.
				mData[i] = mData[size-1];
				size--;
				percolateDown(i);//O(logn) average runtime
				
				//add the modified data back in the tree.
				add(currentS); //O(1)  average runtime
				return;
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		StudentHeap stuH = new StudentHeap();
		stuH.add(new Student("s1", 10));
		stuH.add(new Student("s2", 20));
		stuH.add(new Student("s3", 30));
		stuH.add(new Student("s4", 40));
		stuH.add(new Student("s5", 50));
		
//		System.out.println("\n=============test: isAHeap===========\n");
//		System.out.println(isAHeap(stuH)); //should be true
		
//		System.out.println("\n=============test: mergeHeap===========\n");
//		
//		StudentHeap stuH2 = new StudentHeap();
//		stuH2.add(new Student("s6", 35));
//		stuH2.add(new Student("s7", 25));
//		stuH2.add(new Student("s8", 15));
//		
//		StudentHeap mergeH = (StudentHeap) stuH.mergeHeap(stuH2);
//		System.out.println("size: " + mergeH.size);
//		System.out.println("isHeap: " + isAHeap(mergeH));
//		System.out.println("top(): " + ((Student)mergeH.top()).mark);
		
//		System.out.println("\n=============test: changemark===========\n");
//		stuH.changemark("s1", 80);
//		System.out.println("size: " + stuH.size);
//		System.out.println("isHeap: " + isAHeap(stuH));
//		System.out.println("top(): " + ((Student)stuH.top()).mark);
		
		
	}
	
}
