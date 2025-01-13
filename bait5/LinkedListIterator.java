import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T> {
    private Link<T> next;

    public LinkedListIterator(Link<T> first) {
        this.next = first;
    }

    public boolean hasNext() {
        return next != null;
    }

    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        T data = next.getData();
        this.next = next.getNext();
        return data;
    }
}