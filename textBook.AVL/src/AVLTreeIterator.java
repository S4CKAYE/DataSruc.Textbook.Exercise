

import java.util.NoSuchElementException;

public class AVLTreeIterator implements Iterator {

	AVLNode currentNode;

	public AVLTreeIterator(AVLNode currentNode) {
		this.currentNode = currentNode;
	}

	@Override
	public boolean hasNext() {
		AVLNode temp = currentNode;

		if (temp.right != null) {
			return true;
		}

		AVLNode p = temp.parent;
		while (p != null && p.right == temp) {
			temp = p;
			p = temp.parent;
		}

		if (p == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean hasPrevious() {
		AVLNode temp = currentNode;

		if (temp.left != null) {
			return true;
		}

		AVLNode p = temp.parent;
		while (p != null && p.left == temp) {
			temp = p;
			p = temp.parent;
		}

		if (p == null)
			return false;
		else
			return true;

	}

	@Override
	public int next() throws Exception {
		// Throw exception if the next data
		// does not exist.
		AVLNode temp = currentNode;

		if (temp.right != null) {
			temp = temp.right;
			while (temp.left != null) {
				temp = temp.left;
			}
		} else {
			AVLNode p = temp.parent;
			while (p != null && p.right == temp) {
				temp = p;
				p = temp.parent;
			}
			temp = p;
		}

		if (temp == null) // hasNext() == false
			throw new NoSuchElementException();
		currentNode = temp;
		return currentNode.data;
	}

	@Override
	public int previous() throws Exception {
		// Throw exception if the previous data
		// does not exist.
		AVLNode temp = currentNode;
		int d = currentNode.data;
		
		if (temp.left != null) {
			temp = temp.left;
			while (temp.right != null) {
				temp = temp.right;
			}
		} else {
			AVLNode p = temp.parent;
			while (p != null && p.left == temp) {
				temp = p;
				p = temp.parent;
			}
			temp = p;
		}

		if (temp == null) // hasPrevious() == false
			throw new NoSuchElementException();
		currentNode = temp;
		return d;

	}

	@Override
	public void set(int value) {
		currentNode.data = value;
	}

}
