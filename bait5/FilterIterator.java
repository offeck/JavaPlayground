import java.util.Iterator;
import java.util.NoSuchElementException;


public class FilterIterator<T> implements Iterator<T> {
	Iterator<T> iterator;
	Iterable<Filter<T>> filters;
	T next;
	
	public FilterIterator(Iterable<T> elements, Iterable<Filter<T>> filters) {
	    throw new UnsupportedOperationException("Remove this line");
	}
	
	@Override
	public boolean hasNext() {
	    throw new UnsupportedOperationException("Remove this line");
	}

	@Override
	public T next() {
	    throw new UnsupportedOperationException("Remove this line");
	}
}
