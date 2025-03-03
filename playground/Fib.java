public class Fib {
    public static void main(String[] args) {
        System.out.println(fib(5));
        System.out.println(fibCount2(5, 1));
    }

    public static int fib(int n) {
        if (n < 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static int fibCount1(int n, int k) {
        if (n < k) {
            return 0;
        }
        if (n == k) {
            return 1;
        }
        return fibCount1(n - 1, k) + fibCount1(n - 2, k);
    }

    public static int fibCount2(int n, int k) {
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i < k) {
                arr[i] = 0;
            } else {
                arr[i] = -1;
            }
        }
        fibCount2(n, k, arr);
        return arr[n];
    }

    public static void fibCount2(int n, int k, int[] arr) {
        if (arr[n] != -1) {
            return;
        }
        if (n == k) {
            arr[n] = 1;
            return;
        }
        fibCount2(n - 1, k, arr);
        fibCount2(n - 2, k, arr);
        arr[n] = arr[n - 1] + arr[n - 2];
    }
}
