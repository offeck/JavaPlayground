import java.util.Set;

public class List_Tests {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = i;
        }
        System.out.println("Original array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d%n", i, arr[i]);
        }

        System.out.println("\nAdding value 10 at index 3...");
        add(arr, 3, 10);

        System.out.println("\nModified array:");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d%n", i, arr[i]);
        }
    }

    public static void add(Integer[] arr, int index, int value) {
        if (index < 0 || index >= arr.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        // Store the value at index position
        Integer temp = arr[index];
        // Insert the new value
        arr[index] = value;

        // Shift remaining elements right by one position
        for (int i = index + 1; i < arr.length; i++) {
            Integer next = arr[i];
            arr[i] = temp;
            temp = next;
        }
    }
}
