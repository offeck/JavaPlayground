
import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        // ---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int mod = 2 % k;
        int modSum = 1;

        for (int i = 0; i < n; i++) {
            modSum *= mod;
            modSum %= k;
        }

        ans = modSum;

        // ---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
        scanner.close();
    }
}