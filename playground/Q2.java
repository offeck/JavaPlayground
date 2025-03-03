public class Q2 {
    public static void main(String[] args) {
        printAll(8);
        // String it = "123";
        // System.out.println(it.substring(0, 3));
    }

    public static void printAll(int sum) {
        printAll(sum, "", 0);
    }

    public static void printAll(int sum, String acc, int prev) {
        if (sum == 0) {
            System.out.println(acc);
            return;
        }
        if (sum <= prev)
            return;
        for (int i = prev+1; i <= sum; i++) {
            printAll(sum - i, acc + " " + i, i);
        }
    }
}
