package textBook.PriorityQueue;

import java.util.ArrayList;

public class HuffmanNode implements Comparable<HuffmanNode>{
	String character;
	int freq;
	HuffmanNode left;
	HuffmanNode right;
	
	public HuffmanNode(String character, int freq, HuffmanNode left, HuffmanNode right) {
		this.character = character;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}
	
	public HuffmanNode(String character, int freq) {
		this(character, freq, null, null);
	}
	
	public static HuffmanNode makeHuffmanTree(PriorityQ Heap) throws Exception {
		while(Heap.size() >= 1) {
			if (Heap.size() == 1) {
				break;
			}else {//Heap.size >= 2
				HuffmanNode node1 = (HuffmanNode) Heap.pop();
				HuffmanNode node2 = (HuffmanNode) Heap.pop();
				Heap.add(new HuffmanNode("", node1.freq+node2.freq, node1, node2));
			}
		}
		return (HuffmanNode) Heap.pop();
	}

	@Override
	public int compareTo(HuffmanNode other) {
		// TODO Auto-generated method stub
		if(freq < other.freq) {
			return -1;
		}else if (freq == other.freq) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public String toString() {
		return "\ncharacter: " + character +" frequency: " + freq;
	}
	
	public static void breadthFirstSearch(HuffmanNode root) {
		ArrayList<HuffmanNode> a = new ArrayList<HuffmanNode>();
		a.add(root);
		
		while(!a.isEmpty()) {
			HuffmanNode current = a.remove(0);
			System.out.println(current);
			if (current.left != null) {
				a.add(current.left);
			}
			if(current.right != null) {
				a.add(current.right);
			}
		}
	}
	//returns the bit string of each character in the huffman tree;
	// n is considered the root of that Huffman tree.
	public static String findBitString(HuffmanNode n, String currentPath) {
		if(n == null) {
			return "";
		}if(n.left == null && n.right == null) {
			return "\ncharecter: "+ n.character + " ,bit: "+ currentPath;
		}else {
			return findBitString(n.left, currentPath + "0") + findBitString(n.right, currentPath + "1");
		}
	}
	
	public static String findBitString(HuffmanNode n) {
		return findBitString(n, "");
	}
	
}
