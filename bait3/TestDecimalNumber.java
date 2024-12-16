public class TestDecimalNumber {
    public static void main(String[] args) {
        // Test Constructor with decimal string
        testConstructorWithDecimalString("321", "321");

        // Test increment() method
        DecimalNumber num1 = new DecimalNumber("321");
        testIncrementMethod(num1, "421");
        num1 = new DecimalNumber("0");
        testIncrementMethod(num1, "1");
        num1 = new DecimalNumber("1");
        testIncrementMethod(num1, "2");

        // Test multiplyByTwo() method
        num1 = new DecimalNumber("421");
        testMultiplyByTwoMethod(num1, "842");
        num1 = new DecimalNumber("842");
        testMultiplyByTwoMethod(num1, "694");
        num1 = new DecimalNumber("0");
        testMultiplyByTwoMethod(num1, "0");
        num1 = new DecimalNumber("1");
        testMultiplyByTwoMethod(num1, "2");

        // Test Constructor with binary string
        testConstructorWithBaseString("1011", 2, "31");
        testConstructorWithBaseString("0", 2, "0");
        testConstructorWithBaseString("1", 2, "1");

        // Test Constructor with octal string
        testConstructorWithBaseString("71", 8, "51");
        testConstructorWithBaseString("0", 8, "0");
        testConstructorWithBaseString("1", 8, "1");

        // Test equals() method
        DecimalNumber num2 = new DecimalNumber("842");
        DecimalNumber num3 = new DecimalNumber("842");
        DecimalNumber num4 = new DecimalNumber("942");
        testEqualsMethod(num2, num3, true);
        testEqualsMethod(num2, num4, false);

        // Additional tests
        // Students are encouraged to add more test cases to thoroughly test their
        // implementations
    }

    // Test Constructor with decimal string
    public static void testConstructorWithDecimalString(String input, String expectedOutput) {
        String result;
        DecimalNumber num = new DecimalNumber(input);
        if (num.toString().equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }
        System.out.println("Testing Constructor with decimal string: input = " + input +
                "; Expected output = " + expectedOutput + "; --> " + result);
    }

    // Test increment() method
    public static void testIncrementMethod(DecimalNumber num, String expectedOutput) {
        String result;
        String originalValue = num.toString(); // Store the original value before increment
        num.increment();
        String actualOutput = num.toString();

        if (actualOutput.equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }

        System.out.println("Testing increment() method:");
        System.out.println("  Input value    = " + originalValue);
        System.out.println("  Actual output  = " + actualOutput);
        System.out.println("  Expected output= " + expectedOutput);
        System.out.println("  Result         = " + result);
        System.out.println();
    }

    // Test multiplyByTwo() method
    public static void testMultiplyByTwoMethod(DecimalNumber num, String expectedOutput) {
        String result;
        String originalValue = num.toString(); // Store the original value before multiplication
        num.multiplyByTwo();
        String actualOutput = num.toString();

        if (actualOutput.equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }

        System.out.println("Testing multiplyByTwo() method:");
        System.out.println("  Input value    = " + originalValue);
        System.out.println("  Actual output  = " + actualOutput);
        System.out.println("  Expected output= " + expectedOutput);
        System.out.println("  Result         = " + result);
        System.out.println();
    }

    // Test Constructor with binary or octal string
    public static void testConstructorWithBaseString(String input, int base, String expectedOutput) {
        String result;
        DecimalNumber num = new DecimalNumber(input, base);
        String actualOutput = num.toString();

        if (actualOutput.equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }

        String baseName = (base == 2) ? "binary" : (base == 8) ? "octal" : "unknown";

        System.out.println("Testing Constructor with " + baseName + " string:");
        System.out.println("  Input value    = " + input);
        System.out.println("  Base           = " + base);
        System.out.println("  Actual output  = " + actualOutput);
        System.out.println("  Expected output= " + expectedOutput);
        System.out.println("  Result         = " + result);
        System.out.println();
    }

    // Test equals() method
    public static void testEqualsMethod(DecimalNumber num1, DecimalNumber num2, boolean expectedOutput) {
        String result;
        boolean actualOutput = num1.equals(num2);
        if (actualOutput == expectedOutput) {
            result = "Success";
        } else {
            result = "Failed";
        }
        System.out.println("Testing equals() method: num1 = " + num1 + ", num2 = " + num2 +
                "; Expected output = " + expectedOutput + "; --> " + result);
    }
}
