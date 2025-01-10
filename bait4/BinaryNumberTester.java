public class BinaryNumberTester {

    public static void main(String[] args) {
        // testBasicOperations();
        // testNegativeNumbers();
        testMultiplication();
        testDivision();
        // testEdgeCases();
        // testConversions();
    }

    private static void testBasicOperations() {
        System.out.println("=== Testing Basic Operations ===");
        BinaryNumber a = new BinaryNumber("25");
        BinaryNumber b = new BinaryNumber("100");

        System.out.println("Number: 25");
        System.out.println("Binary: " + a.toString());
        System.out.println("Decimal: " + a.toInt());

        System.out.println("\nNumber: 100");
        System.out.println("Binary: " + b.toString());
        System.out.println("Decimal: " + b.toInt());
    }

    private static void testNegativeNumbers() {
        System.out.println("\n=== Testing Negative Numbers ===");
        BinaryNumber c = new BinaryNumber("-25");
        BinaryNumber d = new BinaryNumber("-100");

        System.out.println("Number: -25");
        System.out.println("Binary: " + c.toString());
        System.out.println("Decimal: " + c.toInt());

        System.out.println("\nNumber: -100");
        System.out.println("Binary: " + d.toString());
        System.out.println("Decimal: " + d.toInt());
    }

    private static void testMultiplication() {
        System.out.println("\n=== Testing Multiplication ===");

        // Test regular multiplication
        BinaryNumber e = new BinaryNumber("100");
        BinaryNumber f = new BinaryNumber("100");
        BinaryNumber result = e.multiply(f);
        System.out.println("Operation: 100 * 100");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt());

        // Test multiplication with negative numbers
        BinaryNumber g = new BinaryNumber("-50");
        BinaryNumber h = new BinaryNumber("3");
        result = g.multiply(h);
        System.out.println("\nOperation: -50 * 3");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt());

        // Test multiplication by zero cases
        System.out.println("\nTesting Multiplication by Zero Cases:");

        // Case 1: Positive * Zero
        BinaryNumber num = new BinaryNumber("42");
        BinaryNumber zero = new BinaryNumber("0");
        result = num.multiply(zero);
        System.out.println("Operation: 42 * 0");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt() + " (Expected: 0)");

        // Case 2: Zero * Positive
        result = zero.multiply(num);
        System.out.println("\nOperation: 0 * 42");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt() + " (Expected: 0)");

        // Case 3: Negative * Zero
        BinaryNumber negNum = new BinaryNumber("-42");
        result = negNum.multiply(zero);
        System.out.println("\nOperation: -42 * 0");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt() + " (Expected: 0)");

        // Case 4: Zero * Zero
        result = zero.multiply(zero);
        System.out.println("\nOperation: 0 * 0");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt() + " (Expected: 0)");
    }

    private static void testDivision() {
        System.out.println("\n=== Testing Division ===");

        // Test regular division
        BinaryNumber i = new BinaryNumber("100");
        BinaryNumber j = new BinaryNumber("10");
        BinaryNumber result = i.divide(j);
        System.out.println("Operation: 100 / 10");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt());

        // Test division with negative numbers
        BinaryNumber k = new BinaryNumber("-100");
        BinaryNumber l = new BinaryNumber("25");
        result = k.divide(l);
        System.out.println("\nOperation: -100 / 25");
        System.out.println("Binary Result: " + result.toString());
        System.out.println("Decimal Result: " + result.toInt());

        // Test division by zero cases
        System.out.println("\nTesting Division by Zero Cases:");

        // Case 1: Positive number divided by zero
        try {
            BinaryNumber num = new BinaryNumber("42");
            BinaryNumber zero = new BinaryNumber("0");
            System.out.println("Attempting: 42 / 0");
            result = num.divide(zero);
            System.out.println("ERROR: Division by zero didn't throw exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully caught division by zero (positive): " + e.getMessage());
        }

        // Case 2: Negative number divided by zero
        try {
            BinaryNumber negNum = new BinaryNumber("-42");
            BinaryNumber zero = new BinaryNumber("0");
            System.out.println("\nAttempting: -42 / 0");
            result = negNum.divide(zero);
            System.out.println("ERROR: Division by zero didn't throw exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully caught division by zero (negative): " + e.getMessage());
        }

        // Case 3: Zero divided by zero
        try {
            BinaryNumber zero1 = new BinaryNumber("0");
            BinaryNumber zero2 = new BinaryNumber("0");
            System.out.println("\nAttempting: 0 / 0");
            result = zero1.divide(zero2);
            System.out.println("ERROR: Division by zero didn't throw exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("Successfully caught division by zero (0/0): " + e.getMessage());
        }

        // Test division edge cases
        System.out.println("\nTesting Division Edge Cases:");

        // Case 4: Division by 1
        try {
            BinaryNumber num = new BinaryNumber("42");
            BinaryNumber one = new BinaryNumber("1");
            result = num.divide(one);
            System.out.println("42 / 1 = " + result.toInt() + " (Expected: 42)");
        } catch (Exception e) {
            System.out.println("ERROR: Division by 1 failed: " + e.getMessage());
        }

        // Case 5: Division by -1
        try {
            BinaryNumber num = new BinaryNumber("42");
            BinaryNumber negOne = new BinaryNumber("-1");
            result = num.divide(negOne);
            System.out.println("42 / -1 = " + result.toInt() + " (Expected: -42)");
        } catch (Exception e) {
            System.out.println("ERROR: Division by -1 failed: " + e.getMessage());
        }

        // Case 6: MAX_VALUE division cases
        try {
            BinaryNumber max = new BinaryNumber(Integer.toString(Integer.MAX_VALUE));
            BinaryNumber two = new BinaryNumber("2");
            result = max.divide(two);
            System.out.println("MAX_VALUE / 2 = " + result.toInt());
        } catch (Exception e) {
            System.out.println("ERROR: MAX_VALUE division failed: " + e.getMessage());
        }
    }

    private static void testEdgeCases() {
        System.out.println("\n=== Testing Edge Cases ===");

        // Test division by zero
        try {
            BinaryNumber zero = new BinaryNumber("0");
            BinaryNumber num = new BinaryNumber("10");
            System.out.println("Attempting division by zero...");
            num.divide(zero);
        } catch (ArithmeticException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Test large number handling
        BinaryNumber large = new BinaryNumber("1000000");
        System.out.println("\nTesting large number: 1000000");
        System.out.println("Binary: " + large.toString());
        System.out.println("Decimal: " + large.toInt());
    }

    private static void testConversions() {
        System.out.println("\n=== Testing Conversions ===");

        // Test standard positive numbers
        testNumber("42");
        testNumber("127");
        testNumber("1024");

        // Test negative numbers
        testNumber("-42");
        testNumber("-127");
        testNumber("-1024");

        // Test boundary cases
        testNumber("0");
        testNumber("1");
        testNumber("-1");

        // Test Integer boundary values
        testNumber(String.valueOf(Integer.MAX_VALUE));
        testNumber(String.valueOf(Integer.MIN_VALUE));
        testNumber(String.valueOf(Integer.MAX_VALUE - 1));
        testNumber(String.valueOf(Integer.MIN_VALUE + 1));

        // Test numbers near power-of-2 boundaries
        testNumber("2147483646"); // MAX_VALUE - 1
        testNumber("2147483647"); // MAX_VALUE
        testNumber("-2147483648"); // MIN_VALUE
        testNumber("-2147483647"); // MIN_VALUE + 1

        // Test numbers that should cause overflow
        testNumber("2147483648"); // MAX_VALUE + 1
        testNumber("4294967295"); // 2^32 - 1
        testNumber("-2147483649"); // MIN_VALUE - 1
        testNumber("-4294967295"); // -(2^32 - 1)

        // Test powers of 2
        testNumber("2");
        testNumber("4");
        testNumber("8");
        testNumber("16");
        testNumber("32");
        testNumber("64");
        testNumber("128");
        testNumber("256");
        testNumber("512");
        testNumber("1024");
        testNumber("2048");
        testNumber("4096");
        testNumber("8192");
        testNumber("16384");
        testNumber("32768");
        testNumber("65536");

        // Test numbers with interesting binary patterns
        testNumber("1431655765"); // 01010101...01
        testNumber("-1431655766"); // 10101010...10
        testNumber("1010101010");
        testNumber("-1010101010");

        // Test very large numbers (beyond int)
        testNumber("9999999999");
        testNumber("-9999999999");
        testNumber("123456789123456789");
        testNumber("-123456789123456789");
    }

    private static void testNumber(String input) {
        try {
            System.out.println("\nTesting number: " + input);
            BinaryNumber num = new BinaryNumber(input);

            // Test binary representation
            System.out.println("Binary representation: " + num.toString());

            // Test string conversion
            String intString = num.toIntString();
            System.out.println("toIntString() result: " + intString);

            // Only test toInt() for numbers within int range
            try {
                long longValue = Long.parseLong(input);
                if (longValue >= Integer.MIN_VALUE && longValue <= Integer.MAX_VALUE) {
                    // Test toInt() only for valid int range
                    int intValue = num.toInt();
                    System.out.println("toInt() result: " + intValue);

                    // Verify int conversion accuracy
                    if (intValue != longValue) {
                        System.out.println("WARNING: toInt conversion mismatch!");
                        System.out.println("Expected: " + longValue);
                        System.out.println("Got: " + intValue);
                    } else {
                        System.out.println("toInt conversion: OK");
                    }
                } else {
                    System.out.println("Skipping toInt() test - number outside int range");
                }
            } catch (NumberFormatException e) {
                System.out.println("Skipping toInt() test - number too large for long");
            }

            // Verify string conversion accuracy
            if (!intString.equals(input)) {
                System.out.println("WARNING: toIntString mismatch!");
                System.out.println("Original input: " + input);
                System.out.println("toIntString result: " + intString);
            } else {
                System.out.println("toIntString match: OK");
            }
        } catch (Exception e) {
            System.out.println("Error testing " + input + ": " + e.getMessage());
        }
    }
}
