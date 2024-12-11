public class RecurseTest {
    public static void main(String[] args) {
        // Record o1 = new Record("John", 123);
        // System.out.println(o1.equals(o1));
    }

    public static boolean ss(int[] weights, int sum, int i) {
        boolean ans = false;

        if (sum == 0) {
            ans = true;
        } else if (weights.length != 0 && i < weights.length) {
            ans = ss(weights, sum - weights[i], i + 1) ||
                    ss(weights, sum, i + 1);
        }

        return ans;
    }

    public static void pasq(String s, String acc) {
        if (s.length() == 0) {
            System.out.println(acc);
        } else {
            pasq(s.substring(0, s.length() - 1), acc);
            pasq(s.substring(0, s.length() - 1), s.charAt(s.length() - 1) + " " + acc);
        }
    }

    public static void pas(int n, String acc) {
        if (n == 0) {
            System.out.println(acc);
        } else {
            pas(n - 1, acc);
            pas(n - 1, n + " " + acc);
        }
    }

    public static void fac(int n) {
        if (n > 1) {
            System.out.print(n + "*");
            fac(n - 1);
        } else {
            System.out.print(1);
        }
    }

    public static int factorial(int n) {
        int ans;
        if (n == 1) {
            ans = 1;
            System.out.print("1" + "*");
        } else {
            ans = n * factorial(n - 1);
            System.out.print(n);
        }
        return ans;
    }

    public static String toBinary(int n) {
        String ans = "";
        if (n != 0) {
            if (n % 2 == 1) {
                ans = toBinary(n / 2) + "1";
            } else {
                ans = toBinary(n / 2) + "0";
            }
        }
        return ans;
    }

    // Assumes s is not null
    // Returns true if s is a palindrome and false if s isn't a palindrome
    public static boolean isPalindrome(String s) {
        boolean ans;

        if (s.length() == 0 || s.length() == 1) {
            ans = true;
        } else if (s.charAt(0) != s.charAt(s.length() - 1)) {
            ans = false;
        } else {
            ans = isPalindrome(s.substring(1, s.length() - 1));
        }

        return ans;
    }

    public static String rd(String s) {
        if (s.length() == 0) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        if (s.charAt(0) == s.charAt(1)) {
            return rd(s.substring(1));
        }
        return s.charAt(0) + rd(s.substring(1));
    }

    public static boolean isAlmostPrime(int n, int k) {
        int n2 = n;
        int divider = 2;
        int counter = 0;

        while (n2 != divider) {
            if (n2 % divider == 0) {
                n2 /= divider;
                divider = 2;
                counter++;
            } else {
                divider++;
            }
        }

        counter++; // Count the last prime factor
        return counter == k;
    }

    public static int subsetSum(int[] S, int sum, int i) {
        // Base case: if we've found our target sum
        if (sum == 0) {
            return 1;
        }

        // Base case: if we've gone through all elements or sum becomes negative
        if (i >= S.length || sum < 0) {
            return 0;
        }

        // Recursive case: try including current element (sum - S[i]) and excluding it
        return subsetSum(S, sum - S[i], i + 1) + subsetSum(S, sum, i + 1);
    }
}
