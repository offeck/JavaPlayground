import java.util.Arrays;
import java.util.Comparator;

/**
 * A dynamic array implementation that alternates between two types.
 * Even indices must contain elements of type T1, and odd indices must contain
 * elements of type T2.
 * The array grows and shrinks automatically as elements are added or removed.
 *
 * @param <T1> The type for elements at even indices
 * @param <T2> The type for elements at odd indices
 */
public class MixedTypeArray<T1, T2> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int GROWTH_FACTOR = 2;

    private Object[] array;
    private int size;
    private final Class<T1> t1Class;
    private final Class<T2> t2Class;

    /**
     * Constructs a new MixedTypeArray with the specified types.
     *
     * @param t1Class The Class object for type T1 (even indices)
     * @param t2Class The Class object for type T2 (odd indices)
     */
    public MixedTypeArray(Class<T1> t1Class, Class<T2> t2Class) {
        this.t1Class = t1Class;
        this.t2Class = t2Class;
        this.array = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Retrieves an element from the specified index.
     * Returns T1 for even indices and T2 for odd indices.
     *
     * @param index The index to retrieve from
     * @return The element at the specified index (T1 for even indices, T2 for odd
     *         indices)
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public Object get(int index) {
        validateIndex(index);
        if (index % 2 == 0) {
            return (T1) array[index];
        } else {
            return (T2) array[index];
        }
    }

    /**
     * Replaces the element at the specified index.
     *
     * @param index   The index at which to replace the element
     * @param element The element to insert
     * @throws IndexOutOfBoundsException if the index is out of range
     * @throws IllegalArgumentException  if the element type doesn't match the index
     *                                   position
     */
    public void set(int index, Object element) {
        validateIndex(index);
        validateType(index, element);
        array[index] = element;
    }

    /**
     * Adds an element to the end of the array.
     *
     * @param element The element to add
     * @throws IllegalArgumentException if the element type doesn't match the next
     *                                  available position
     */
    public void add(Object element) {
        validateIndexForAdd(size);
        validateType(size, element);
        ensureCapacity();
        array[size++] = element;
    }

    /**
     * Removes and returns the last element in the array.
     *
     * @return The removed element
     * @throws IndexOutOfBoundsException if the array is empty
     */
    public Object removeLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Cannot remove from empty array");
        }

        Object removed = array[size - 1];
        array[--size] = null;
        shrinkIfNeeded();
        return removed;
    }

    /**
     * Returns the number of elements in the array.
     *
     * @return The current size of the array
     */
    public int size() {
        return size;
    }

    /**
     * Validates an index for element access.
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Validates an index for element insertion.
     */
    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Validates that the element type matches the index position.
     */
    private void validateType(int index, Object element) {
        if (index % 2 == 0 && !t1Class.isInstance(element)) {
            throw new IllegalArgumentException("Expected " + t1Class.getSimpleName() + " at even index " + index);
        }
        if (index % 2 != 0 && !t2Class.isInstance(element)) {
            throw new IllegalArgumentException("Expected " + t2Class.getSimpleName() + " at odd index " + index);
        }
    }

    /**
     * Ensures the array has enough capacity, growing if necessary.
     */
    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * GROWTH_FACTOR);
        }
    }

    /**
     * Shrinks the array if it's significantly under-utilized.
     */
    private void shrinkIfNeeded() {
        if (size > 0 && size < array.length / 4) {
            array = Arrays.copyOf(array, array.length / 2);
        }
    }

    /**
     * Returns a string representation of the array.
     *
     * @return A string containing all elements in order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    /**
     * Main method with test cases for MixedTypeArray functionality.
     */
    public static void main(String[] args) {
        System.out.println("=== Testing MixedTypeArray ===\n");

        // Create a mixed array of String and Integer
        MixedTypeArray<String, Integer> array = new MixedTypeArray<>(String.class, Integer.class);

        // Test 1: Adding elements
        System.out.println("Test 1: Adding elements");
        try {
            array.add("First"); // index 0
            array.add(1); // index 1
            array.add("Third"); // index 2
            array.add(3); // index 3
            System.out.println("Array after adding: " + array);
            System.out.println("Size: " + array.size());
            System.out.println("Test 1: Passed\n");
        } catch (Exception e) {
            System.out.println("Test 1 failed: " + e.getMessage() + "\n");
        }

        // Test 2: Replacing elements
        System.out.println("Test 2: Replacing elements");
        try {
            array.set(2, "New");
            System.out.println("Array after replacing index 2: " + array);
            System.out.println("Test 2: Passed\n");
        } catch (Exception e) {
            System.out.println("Test 2 failed: " + e.getMessage() + "\n");
        }

        // Test 3: Type validation
        System.out.println("Test 3: Type validation");
        try {
            array.set(0, 123); // Should throw exception - wrong type for even index
            System.out.println("Test 3 failed: Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
            System.out.println("Test 3: Passed\n");
        }

        // Test 4: Getting elements
        System.out.println("Test 4: Getting elements");
        try {
            String str = (String) array.get(0);
            Integer num = (Integer) array.get(1);
            System.out.println("Element at index 0 (String): " + str);
            System.out.println("Element at index 1 (Integer): " + num);
            System.out.println("Test 4: Passed\n");
        } catch (Exception e) {
            System.out.println("Test 4 failed: " + e.getMessage() + "\n");
        }

        // Test 5: Removing elements
        System.out.println("Test 5: Removing last element");
        try {
            Object removed = array.removeLast();
            System.out.println("Removed last element: " + removed);
            System.out.println("Array after removal: " + array);
            System.out.println("New size: " + array.size());
            System.out.println("Test 5: Passed\n");
        } catch (Exception e) {
            System.out.println("Test 5 failed: " + e.getMessage() + "\n");
        }

        // Test 6: Array growth
        System.out.println("Test 6: Testing array growth");
        try {
            array.add(0);
            for (int i = 0; i < 20; i++) {
                if (i % 2 == 0) {
                    array.add("String" + i);
                } else {
                    array.add(i);
                }
            }
            System.out.println("Successfully added 20 elements");
            System.out.println("Final array: " + array);
            System.out.println("Final size: " + array.size());
            System.out.println("Test 6: Passed\n");
        } catch (Exception e) {
            System.out.println("Test 6 failed: " + e.getMessage() + "\n");
        }

        // Test 7: Array shrinking
        System.out.println("Test 7: Testing array shrinking");
        try {
            while (array.size() > 5) {
                array.removeLast();
            }
            System.out.println("Array after shrinking: " + array);
            System.out.println("Size after shrinking: " + array.size());
            System.out.println("Test 7: Passed\n");
        } catch (Exception e) {
            System.out.println("Test 7 failed: " + e.getMessage() + "\n");
        }
        for (int i = 0; i < array.size(); i++) {
            Object element = array.get(i);

        }
    }
}
