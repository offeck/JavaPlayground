public class test {
    public static void main(String[] args) {
        // for (int i = 1; i <= 7; i++) {
        // compareFunctions(i);
        // }
        compareFunctions(10);
    }

    /**
     * Compares the output of both implementations for a given sum
     * Prints the results from both implementations side by side
     * 
     * @param sum The number to find all possible additive combinations for
     */
    public static void compareFunctions(int sum) {
        System.out.println("\n=== Testing for sum = " + sum + " ===");
        System.out.println("My implementation:");
        printAllNew(sum);
        System.out.println("\nCorrect implementation:");
        printAllCorrect(sum);
        System.out.println("======================\n");
    }

    public static void printAll(int sum) {
        printAll(sum, "", false);
    }

    public static void printAllNew(int sum) {
        printAllNew(sum, "", 0);
    }

    public static void printAllCorrect(int sum) {
        printAllCorrect(sum, "", 0);
    }

    public static void printAllNew(int sum, String str, int max) {
        if (sum != 0) {
            System.out.println(str + sum);
        } else {
            System.out.println(str);
        }
        if (sum == 1 || sum == 2) {
            return;
        }
        int mid = sum / 2;
        if (sum % 2 == 0)
            mid -= 1;
        for (int i = 1; i <= mid; i++) {
            if (i > max) {
                printAllNew(sum - i, str + i + " ", i);
            }
        }
    }

    // My implementation, 1 to 1 copy of my code from the test
    public static void printAll(int sum, String add, boolean equals) {
        if (!equals)
            System.out.println(sum + " " + add);
        if (sum == 1 || sum == 2)
            return;
        for (int i = 1; i < (sum / 2) + 1; i++) {
            boolean eq = i == sum - i;
            printAll(i, (sum - i) + " " + add, eq);
        }
    }

    // Correct implementation, from a friend's test. He got 100% for this question.
    // So I guess it's correct.
    public static void printAllCorrect(int sum, String ans, int num) {
        if (sum == 0) {
            System.out.println(ans.substring(0, ans.length() - 1));
        } else if (sum < 0)
            return;
        else {
            while (num <= sum) {
                num++;
                printAllCorrect(sum - num, ans + num + " ", num);
            }
        }
    }
}
