public interface Filter<T> {
	// Returns true if element passes the condition of the filter
	public boolean accept(T element);
}
