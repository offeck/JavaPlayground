public class TestDecimalNumber {
    public static void main(String[] args) {
        // Test Constructor with decimal string
        testConstructorWithDecimalString("321", "321");

        // Test increment() method
        DecimalNumber num1 = new DecimalNumber("321");
        testIncrementMethod(num1, "421");

        // Test multiplyByTwo() method
        num1 = new DecimalNumber("421");
        testMultiplyByTwoMethod(num1, "842");

        // Test Constructor with binary string
        testConstructorWithBaseString("1011", 2, "31");

        // Test Constructor with octal string
        testConstructorWithBaseString("71", 8, "51");

        // Test equals() method
        DecimalNumber num2 = new DecimalNumber("842");
        DecimalNumber num3 = new DecimalNumber("842");
        DecimalNumber num4 = new DecimalNumber("942");
        testEqualsMethod(num2, num3, true);
        testEqualsMethod(num2, num4, false);

        // Additional tests
        // Students are encouraged to add more test cases to thoroughly test their implementations
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
        num.increment();
        if (num.toString().equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }
        System.out.println("Testing increment() method: after increment = " + num +
                "; Expected output = " + expectedOutput + "; --> " + result);
    }

    // Test multiplyByTwo() method
    public static void testMultiplyByTwoMethod(DecimalNumber num, String expectedOutput) {
        String result;
        num.multiplyByTwo();
        if (num.toString().equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }
        System.out.println("Testing multiplyByTwo() method: after multiplyByTwo = " + num +
                "; Expected output = " + expectedOutput + "; --> " + result);
    }

    // Test Constructor with binary or octal string
    public static void testConstructorWithBaseString(String input, int base, String expectedOutput) {
        String result;
        DecimalNumber num = new DecimalNumber(input, base);
        if (num.toString().equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
        }

        String baseName = "";
        if (base == 2) {
            baseName = "binary";
        } else if (base == 8) {
            baseName = "octal";
        } else {
            baseName = "unknown";
        }

        System.out.println("Testing Constructor with " + baseName + " string: input = " + input +
                ", base = " + base + "; Expected output = " + expectedOutput + "; --> " + result);
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
