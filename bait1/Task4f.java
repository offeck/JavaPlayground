import java.util.Scanner;

public class Task4f {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    int ans = 0;

    // ---------------write your code BELOW this line only!--------------

    int x = scanner.nextInt();
    int y = scanner.nextInt();

    while (true) {
      double r = Math.random();
      int n = ((int) (r * (y - x))) + x;
      boolean isPrime = true;
      for (int i = 0; isPrime && i < 5; i++) {
        int a = ((int) (Math.random() * (n - 2))) + 1;
        int mod = a % n;
        int modSum = 1;
        
        for (int j = 0; j < n - 1; j++) {
          modSum *= mod;
          modSum %= n;
        }

        if (modSum != 1) {
          isPrime = false;
        }
      }
      if (isPrime) {
        ans = n;
        break;
      }
    }

    // ---------------write your code ABOVE this line only!--------------

    System.out.println(ans);

    scanner.close();
  }

  // public static boolean isPrimeRnd(int n) {
  // for (int i = 0; i < 5; i++) {
  // int a = ((int) (Math.random() * (n - 2))) + 1;
  // if (!fermats(n, a)) {
  // return false;
  // }
  // }
  // return true;
  // }

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