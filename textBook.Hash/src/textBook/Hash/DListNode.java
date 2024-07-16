package textBook.Hash;


public class DListNode {
	Object data;
	DListNode nextNode, previousNode;

	DListNode(Object data) {
		this(data, null, null);
	}

	DListNode(Object theElement, DListNode n, DListNode p) {
		data = theElement;
		nextNode = n;
		previousNode = p;
	}

}
