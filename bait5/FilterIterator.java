import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterIterator<T> implements Iterator<T> {
	Iterator<T> iterator;
	Iterable<Filter<T>> filters;
	T next;

	public FilterIterator(Iterable<T> elements, Iterable<Filter<T>> filters) {
		this.iterator = elements.iterator();
		this.filters = filters;
		this.next = findNext();
	}

	@Override
	public boolean hasNext() {
		return this.next != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T result = this.next;
		this.next = findNext();
		return result;
	}

	/**
	 * Finds the next element in the iterator that passes all filters.
	 * Iterates through elements until finding one that is accepted by all filters,
	 * or reaching the end of the iterator.
	 *
	 * @return The next accepted element, or null if no more elements pass the filters
	 */
	private T findNext() {
		while (this.iterator.hasNext()) {
			T element = this.iterator.next();
			if (this.verifyFilters(element)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Verifies if an element passes all filters in the filter collection.
	 * 
	 * @param element The element to check against all filters
	 * @return true if the element is accepted by all filters, false otherwise
	 */
	private boolean verifyFilters(T element) {
		for (Filter<T> filter : this.filters) {
			if (!filter.accept(element)) {
				return false;
			}
		}
		return true;
	}
}
