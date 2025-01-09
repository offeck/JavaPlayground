public class BinaryNumberTester {

    public static void main(String[] args) {
        testBasicOperations();
        testNegativeNumbers();
        testMultiplication();
        testDivision();
        testEdgeCases();
        testConversions();
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
    }

    private static void testDivision() {
        System.out.println("\n=== Testing Division ===");
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

            // Test various representations
            System.out.println("Binary representation: " + num.toString());

            // Test decimal conversions
            // int intValue = num.toInt();
            // System.out.println("toInt() result: " + intValue);

            String intString = num.toIntString();
            System.out.println("toIntString() result: " + intString);

            // Verify conversion accuracy
            // String originalInput = String.valueOf(intValue);
            // if (!originalInput.equals(input)) {
            // System.out.println("WARNING: toInt round-trip conversion mismatch!");
            // System.out.println("Original input: " + input);
            // System.out.println("After toInt conversion: " + originalInput);
            // } else {
            // System.out.println("toInt round-trip conversion: OK");
            // }

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
