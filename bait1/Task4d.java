
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        // ---------------write your code BELOW this line only!--------------

        int n = scanner.nextInt();
        // int tries = 0;
        while (true) {
            ans++;
            int a = ((int) (Math.random() * (n - 2))) + 1;

            int mod = a % n;
            int modSum = 1;

            for (int i = 0; i < n - 1; i++) {
                modSum *= mod;
                modSum %= n;
            }

            if (modSum != 1) {
                break;
            }
        }

        // ---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
        scanner.close();
    }

    // public static boolean fermats(int n, int a) {
    // int mod = a % n;
    // int modSum = 1;

    // for (int i = 0; i < n - 1; i++) {
    // modSum *= mod;
    // modSum %= n;
    // }

    // if (modSum != 1) {
    // return false;
    // }
    // return true;
    // }
}