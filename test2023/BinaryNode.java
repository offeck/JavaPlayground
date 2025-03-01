public class BinaryNode<T> {

	// Fields
	protected T data;
	protected BinaryNode<T> left;
	protected BinaryNode<T> right;

	// Constructor
	public BinaryNode(T element) {
		if (element == null) {
			throw new IllegalArgumentException();
		}
		this.data = element;
		left = null;
		right = null;
	}

	// Methods
	public void insert(T element) {
		if (Math.random() < 0.5) {
			if (left == null) {
				left = new BinaryNode<T>(element);
			} else {
				left.insert(element);
			}
		} else {
			if (right == null) {
				right = new BinaryNode<T>(element);
			} else {
				right.insert(element);
			}
		}
	}

	public BinaryNode<T> remove(T toRemove) {
		BinaryNode<T> output = this;
		if (data.equals(toRemove)) {
			if (left != null) {
				data = left.data;
				left = left.remove(data);
			} else if (right != null) {
				data = right.data;
				right = right.remove(data);
			} else
				output = null;
		} else {
			if (left != null && left.contains(toRemove)) {
				left = left.remove(toRemove);
			} else if (right != null) {
				right = right.remove(toRemove);
			}
		}
		return output;
	}

	public boolean contains(T element) {
		boolean found = false;
		if (data.equals(element)) {
			found = true;
		} else if (left != null && left.contains(element)) {
			found = true;
		} else if (right != null && right.contains(element)) {
			found = true;
		}
		return found;
	}

	public int height() {
		int leftH = -1, rightH = -1;
		if (left != null) {
			leftH = left.height();
		}
		if (right != null) {
			rightH = right.height();
		}
		return Math.max(leftH, rightH) + 1;
	}

	public int size() {
		int leftS = 0, rightS = 0;
		if (left != null) {
			leftS = left.size();
		}
		if (right != null) {
			rightS = right.size();
		}
		return leftS + rightS + 1;
	}

	public void printInOrder() {
		if (left != null) {
			left.printInOrder();
		}
		System.out.println(data.toString());
		if (right != null) {
			right.printInOrder();
		}
	}

	public void printPreOrder() {
		System.out.println(data.toString());
		if (left != null) {
			left.printPreOrder();
		}
		if (right != null) {
			right.printPreOrder();
		}
	}

	public void printPostOrder() {
		if (left != null) {
			left.printPostOrder();
		}
		if (right != null) {
			right.printPostOrder();
		}
		System.out.println(data.toString());
	}

	public boolean equals(Object other) {
		boolean isEqual = false;
		if (other instanceof BinaryNode<?>) {
			BinaryNode<?> otherNode = (BinaryNode<?>) other;
			isEqual = data.equals(otherNode.data)
					&& ((left == null && otherNode.left == null) ||
							(left != null && left.equals(otherNode.left)))
					&& ((right == null && otherNode.right == null) ||
							(right != null && right.equals(otherNode.right)));
		}
		return isEqual;
	}

	public void mirror() {

		// swap the left and right subtrees
		BinaryNode<T> temp = left;
		left = right;
		right = temp;

		// mirror the left and right subtrees
		if (left != null) {
			left.mirror();
		}
		if (right != null) {
			right.mirror();
		}

	}

	private T findAndRemoveLeft() {
		if (left == null) {
			throw new IllegalStateException("No left child");
		}
		T output;
		BinaryNode<T> curr = this;
		while (curr.left.left != null) {
			curr = curr.left;
		}
		output = curr.left.data;
		curr.left = curr.left.right;
		return output;
	}

	public BinaryNode<T> removeAll(T toRemove) {
		if (data.equals(toRemove)) {
			if (left != null) {
				T value = findAndRemoveLeft();
				data = value;
				return removeAll(toRemove);
			}
			if (right != null) {
				return right.removeAll(toRemove);
			} else {
				return null;
			}
		}
		if (left != null) {
			left = left.removeAll(toRemove);
		}
		if (right != null) {
			right = right.removeAll(toRemove);
		}
		return this;
	}

}