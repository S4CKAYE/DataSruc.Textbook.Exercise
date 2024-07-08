import java.util.ArrayList;
import java.util.List;

public class Sorting {
	//recursion ver. of insertion sort
	public static void insertionSort(int[] a,int sorted) {
		if (sorted >= a.length) {
			return;
		}
		int temp = a[sorted];
		int current = sorted;
		while(current > 0) {
			if (temp < a[current-1]) {
				a[current] = a[current-1];
			}else {
				break;
			}
			current--;
		}
		a[current] = temp;
		insertionSort(a, sorted+1);
	}
	
	public static void insertionSort(int[] a) {
		insertionSort(a, 1);
	}
	
	//special selection sort
	//for any two odd numbers, they are arranged from small to large.
	//for any two even numbers, they are arranged from small to large.
	//odd numbers always come before even numbers
	public static void specialSelectionSort(int[] a) {
		int maxOddIndex = -99;
		int maxEvenIndex = -99;
		
		for (int sortedIndex = a.length-1; sortedIndex>0; sortedIndex--) {
			for (int left = 0; left <= sortedIndex; left++) {
				if (a[left]%2 == 0) {
					if(maxEvenIndex == -99|| a[left] > a[maxEvenIndex]) {
					maxEvenIndex = left;
					}
				}else if (a[left]%2 == 1) {
					if (maxOddIndex == -99 || a[left] > a[maxOddIndex]) {
					maxOddIndex = left;
					}
				}
			}
			if (maxEvenIndex != -99) {//if there is still even numbers.
				swap(a,maxEvenIndex, sortedIndex);
			}else{
				swap(a,maxOddIndex,sortedIndex);
			}
			maxOddIndex = -99;
			maxEvenIndex = -99;
		}
		
	}
	
	private static void swap(int[] a, int i1, int i2) {
		if(i1 == i2) {
			return;
		}
		int temp = a[i1];
		a[i1] = a[i2];
		a[i2] = temp;
	}
	
	
	public static void printList(int[] a) {
		String output = "\nList: ";
		for (int i = 0; i< a.length; i++) {
			output += (a[i] + " ");
		}
		System.out.println(output);
	}
	
	public static void quickSort(int[] a, int start, int end) {
		if(start>=end) {
			return;
		}
		int pIndex = findPivot(a, start, end);
		int pivot = a[pIndex];
		swap(a, pIndex, end);
		int i = start;
		int j = end-1;
		while(true) {
			while(i<j && a[i] < pivot) {
				i++;
			}
			while(i<j && a[j] > pivot) {
				j--;
			}
			if (i<j) {
				swap(a, i, j);
				i++;
				j--;
			}else {
				break;
			}
		}
		swap(a, i, end);
		quickSort(a, start, i-1);
		quickSort(a, i+1, end);
		
	}
	
	
	
	//finds the pivot index using method of 3.
	private static int findPivot(int[] a, int start, int end) {
		int mid = (start+end)/2;
		if ((a[start] >= a[mid] && a[start] <= a[end]) 
				|| (a[start] >= a[end] && a[start] <= a[mid])){
			return start;
		}else if ((a[mid] <= a[start] && a[mid] <= a[end])
				|| (a[mid] >= a[end] && a[mid] <= a[start])) {
			return mid;
		}else {
			return end;
		}
	}
	
	//Stucked question.
//	If you want to sort an array of positive integers
//	containing n numbers but you do not know the range
//	of the values in the array, explain and write code for
//	method:
//	public static int[] sort(int[] input)
//	This method sorts the array so that the big O is less
//	than 𝑂(𝑛 log 𝑛). Discuss the big O of your code and any
//	limitation your code has. 
	
	//Implemeted by construction RadixSort class using int[] and arrayList
	//assume that the maximum number of digits is much less that the size of the array
	//assume the list only contain positive numbers.
	//O(n)
	public static int[] RadixSort(int[] input) {
		RadixSort rd = new RadixSort(input);
		rd.sort();
		return rd.theArray;
	}
	
	
	static class RadixSort{
		int[] theArray;
		public RadixSort(int[] theArray) {
			this.theArray = theArray;
		}
		
		//return the kth digit of v.
		//least significant digit is 0
		public int getKthDigit(int v, int k) {
			for (int i = 0; i<k; i++) {
				v = v/10;
			}
			return v%10;
		}
		
		//find the number of digits of a value v.
		public int numberOfDigit(int v) {
			int digit = 1;
			while(v/10 > 0) {
				v = v/10;
				digit++;
			}
			return digit;
		}
		
		//get the maximum number of digit exist in the array
		public int maxDigit() {
			int maxDigits = 1;
			for (int i = 0; i < theArray.length; i++) {
				int digits = numberOfDigit(theArray[i]);
				if ( digits > maxDigits) {
					maxDigits = digits;
				}
			}
			return maxDigits;
		}
		
		public void sort() {
			int maxDigit = maxDigit();
			//create a list that will store arrayList for each digit.
			List[] allLists = new List[10];
			
			//initialize all 10 arrayLists
			for (int i = 0; i<10; i++) {
				allLists[i] = new ArrayList<Integer>();
			}
			
			//for each digit
			for(int k = 0; k< maxDigit; k++) {
				// for each data in array
				for (int i = 0; i< theArray.length; i++) {
					int value = theArray[i];
					ArrayList<Integer> a = (ArrayList<Integer>) allLists[getKthDigit(value, k)];
					a.add(value);
				}
				
				//index to put data back into the array after sorted in that particular digit.
				int j = 0;
				
				//for each arrayList
				for (int i = 0; i<10; i++) {
					//empty each queue and output to the theArray.
					while(!allLists[i].isEmpty()) {
						int data = (int) allLists[i].remove(0);
						theArray[j] = data;
						j++;
					}
				}
				
			}	
		}
		
	}
	
	
	
	
	//this method sorts two arrays. When finishes, both arrays must be sorted(from small to large)
	//all numbers in a1 must be smaller than all numbers in a2.
	public static void sortTwoArrays(int[] a1, int[] a2) {
		insertionSort(a1);
		insertionSort(a2);
		
		int index1 = 0;          
		int index2 = 0;
		int[] temp = new int[a1.length + a2.length];
		int tempIndex = 0;
		while(index1<a1.length && index2 < a2.length) {
			if (a1[index1] <= a2[index2]) {
				temp[tempIndex] = a1[index1]; 
				index1++;
			}else {
				temp[tempIndex] = a2[index2];
				index2++;
			}
			tempIndex++;
		}
		if(index1 == a1.length) {
			while(index2 < a2.length) {
				temp[tempIndex] = a2[index2];
				tempIndex++;
				index2++;
			}
		}else {
			while(index1 < a1.length) {
				temp[tempIndex] = a1[index1];
				tempIndex++;
				index1++;
			}
		}
		index1 = 0;
		index2 = 0;
		for(int i = 0; i <temp.length ; i++) {
			if (i<a1.length) {
				a1[index1] = temp[i];
				index1++;
			}else {
				a2[index2] = temp[i];
				index2++;
			}
		}
		
	}
	//this method sorts the first n integer from small to large.
	public static int[] sortFirstN(int[] a, int n) {
		quickSort(a, 0, n-1);
		return a;
	}
	

	
	
	public static void main(String[] args) {
		int[] a = {4,78,3,34,1,45,7,8};
		
		//insertionSort(a,1);
		//printList(a);
//		
//		specialSelectionSort(a);
//		printList(a);
		
//		System.out.println("\n============test: sortTwoArrays===========\n");
//		int[] a1 = {8,4,7,5,2};
//		int[] a2 = {6,10,3,0,11,1};
//		printList(a1);
//		printList(a2);
//		sortTwoArrays(a1,a2);
//		printList(a1);
//		printList(a2);
		
//		System.out.println("\n============test: sortFirstN()===========\n");
//		sortFirstN(a, a.length-1);
//		printList(a);
		
//		System.out.println("\n============test: RadixSort()===========\n");
//		int[] bigA = new int[50000];
//		int size = bigA.length;
//		for (int i = 0 ; i<size; i++) {
//			bigA[i] = size-i;
//		}
//		printList(bigA);
//		RadixSort(bigA);
//		printList(bigA);
	}
}
