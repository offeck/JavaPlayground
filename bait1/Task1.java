
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        // ---------------write your code BELOW this line only!--------------
        ans = false;
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int q = scanner.nextInt();
        int r = scanner.nextInt();
        if (r < b && b != 0 && q * b + r == a)
            ans = true;

        // ---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
        scanner.close();
    }
}