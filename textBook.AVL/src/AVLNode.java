public class AVLNode {
	// cannot extend from BSTNode because left, right, parent will then be
	// BSTNode!!
	int data;
	AVLNode left, right, parent;
	int height;

	public AVLNode(int data) {
		this.data = data;
		left = null;
		right = null;
		parent = null;
		height = 0;
	}

	public AVLNode(int data, AVLNode left, AVLNode right, AVLNode parent, int height) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.height = height;
	}

	public static int getHeight(AVLNode n) {
		// Need this to be static method because null does not have height to be
		// checked!
		return (n == null ? -1 : n.height);
	}

	// Assume that left and right child have correct height,
	// we update the height value of n
	public static void updateHeight(AVLNode n) {
		if (n == null)
			return;
		int leftHeight = getHeight(n.left);
		int rightHeight = getHeight(n.right);
		n.height = 1 + (leftHeight < rightHeight ? rightHeight : leftHeight);
	}

	// Assume that left and right subtree have correct height.
	// find which way the tree is more loaded.
	public static int tiltDegree(AVLNode n) {
		if (n == null)
			return 0;
		return getHeight(n.left) - getHeight(n.right);
	}
}
