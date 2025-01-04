public class BinaryNumberTester {
    private static void printTestResult(String testName, boolean result) {
        System.out.println("Test: " + testName);
        System.out.println("Result: " + (result ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }

    // Task 2.1 - Testing constructor from int
    public static boolean testConstructorFromInt() {
        System.out.println("\nTesting Constructor from Int:");

        // Test case 1: Zero
        BinaryNumber num = new BinaryNumber(0);
        System.out.println("Testing 0: " + num.toString());
        if (!num.toString().equals("0")) {
            System.out.println("Failed: Expected 0, got " + num.toString());
            return false;
        }
        System.out.println("Zero test passed");

        // Test case 2: Powers of 2
        num = new BinaryNumber(8);
        System.out.println("Testing 8: " + num.toString());
        if (!num.toString().equals("1000")) {
            System.out.println("Failed: Expected 1000, got " + num.toString());
            return false;
        }
        System.out.println("Power of 2 test passed");

        // Test case 3: Regular number
        num = new BinaryNumber(13);
        System.out.println("Testing 13: " + num.toString());
        if (!num.toString().equals("1101")) {
            System.out.println("Failed: Expected 1101, got " + num.toString());
            return false;
        }
        System.out.println("Regular number test passed");

        return true;
    }

    // Task 2.2 - Testing toString
    public static boolean testToString() {
        System.out.println("\nTesting toString:");

        // Test positive numbers
        BinaryNumber num = new BinaryNumber(5);
        System.out.println("Testing 5: " + num.toString());
        if (!num.toString().equals("101")) {
            System.out.println("Failed: Expected 101, got " + num.toString());
            return false;
        }

        // Test zero
        num = new BinaryNumber(0);
        System.out.println("Testing 0: " + num.toString());
        if (!num.toString().equals("0")) {
            System.out.println("Failed: Expected 0, got " + num.toString());
            return false;
        }

        return true;
    }

    // Task 2.3 - Testing equals
    public static boolean testEquals() {
        System.out.println("\nTesting equals:");

        // Test equal numbers
        BinaryNumber num1 = new BinaryNumber(5);
        BinaryNumber num2 = new BinaryNumber(5);
        System.out.println("Testing 5 equals 5");
        if (!num1.equals(num2)) {
            System.out.println("Failed: 5 should equal 5");
            return false;
        }

        // Test unequal numbers
        num2 = new BinaryNumber(6);
        System.out.println("Testing 5 not equals 6");
        if (num1.equals(num2)) {
            System.out.println("Failed: 5 should not equal 6");
            return false;
        }

        // Test with null
        System.out.println("Testing equals with null");
        if (num1.equals(null)) {
            System.out.println("Failed: Number should not equal null");
            return false;
        }

        return true;
    }

    // Task 2.4 - Testing add
    public static boolean testAdd() {
        System.out.println("\nTesting add:");

        // Test simple addition
        BinaryNumber num1 = new BinaryNumber(5);
        BinaryNumber num2 = new BinaryNumber(3);
        BinaryNumber result = num1.add(num2);
        System.out.println("Testing 5 + 3 = 8");
        if (result.toInt() != 8) {
            System.out.println("Failed: Expected 8, got " + result.toInt());
            return false;
        }

        // Test addition with zero
        num2 = new BinaryNumber(0);
        result = num1.add(num2);
        System.out.println("Testing 5 + 0 = 5");
        if (result.toInt() != 5) {
            System.out.println("Failed: Expected 5, got " + result.toInt());
            return false;
        }

        return true;
    }

    // Add more test methods for other tasks...

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Starting Binary Number Tests");
        System.out.println("=================================");

        printTestResult("Constructor from Int (Task 2.1)", testConstructorFromInt());
        printTestResult("ToString (Task 2.2)", testToString());
        printTestResult("Equals (Task 2.3)", testEquals());
        printTestResult("Add (Task 2.4)", testAdd());
        // Add more test calls as you implement them

        System.out.println("=================================");
        System.out.println("All tests completed");
        System.out.println("=================================");
    }
}
