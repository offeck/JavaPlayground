import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> extends BinaryTree<T>{

	protected Comparator<T> treeComparator;

	// This constructor builds an empty tree
	public BinarySearchTree(Comparator<T> myComparator){
		super();
		this.treeComparator = myComparator;
	}

	public void insert(T toInsert) {
		if(toInsert == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			root = new BinarySearchNode<T>(toInsert, treeComparator);
		}
		else {
			root.insert(toInsert);
		}
	} 
	
	public T findData(T element) {
        if (isEmpty())
            return null;
        return ((BinarySearchNode<T>) root).findData(element);
    }
	
	public T findMin(){
		if (isEmpty())
            return null;
        return ((BinarySearchNode<T>) root).findMin();
	}

	public Comparator<T> getComparator() {
		return treeComparator;
	}

	public void remove(T toRemove) {
		if(toRemove == null) {
			throw new IllegalArgumentException();
		}
		if(!isEmpty()) {
			root = root.remove(toRemove);
		}
	}


	public boolean equals(Object other){
		boolean isEqual = true;
		if(!(other instanceof BinarySearchTree<?>)) {
			isEqual = false;
		}
		else {
			Iterator<T> thisIter  = this.iterator();
			Iterator<?> otherIter = ((BinarySearchTree<?>)other).iterator();
			while(thisIter.hasNext() && otherIter.hasNext() && isEqual) {
				if(!thisIter.next().equals(otherIter.next())) {
					isEqual = false;
				}
			}
			if(isEqual) {
				isEqual = thisIter.hasNext() == otherIter.hasNext();
			}
		}
		return isEqual;
	}
}

