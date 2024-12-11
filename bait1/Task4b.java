
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        // ---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                ans++;
            }
        }

        // ---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
        scanner.close();
    }
}