public class TestDecimalNumber {
    public static void main(String[] args) {
        // Existing tests...

        // Adding new tests based on JUnit output

        // Task1a - Testing legal numeric string
        testLegalNumericString("72849", 2, false);

        // Task1b - Testing decimal increment
        DecimalNumber incTest1 = new DecimalNumber("99999");
        testIncrementMethod(incTest1, "000001");
        DecimalNumber incTest2 = new DecimalNumber("4321");
        testIncrementMethod(incTest2, "5321");
        DecimalNumber incTest3 = new DecimalNumber("0");
        testIncrementMethod(incTest3, "1");
        DecimalNumber incTest4 = new DecimalNumber("199");
        testIncrementMethod(incTest4, "299");
        DecimalNumber incTest5 = new DecimalNumber("999");
        testIncrementMethod(incTest5, "0001");
        testIncrementMethodWrapper(1000);

        // Task1c - Testing decimal double
        DecimalNumber doubleTest1 = new DecimalNumber("5");
        testMultiplyByTwoMethod(doubleTest1, "01");
        DecimalNumber doubleTest2 = new DecimalNumber("4321");
        testMultiplyByTwoMethod(doubleTest2, "8642");
        DecimalNumber doubleTest3 = new DecimalNumber("0");
        testMultiplyByTwoMethod(doubleTest3, "0");
        DecimalNumber doubleTest4 = new DecimalNumber("9999");
        testMultiplyByTwoMethod(doubleTest4, "89991");
        DecimalNumber doubleTest5 = new DecimalNumber("0004");
        testMultiplyByTwoMethod(doubleTest5, "0008");
        testMultiplyByTwoMethodWrapper(1000);

        // Task1d - Testing binary to decimal conversion
        testConstructorWithBaseString("0", 2, "0");
        testConstructorWithBaseString("11111111", 2, "552");
        testConstructorWithBaseString("011111111", 2, "015");

        // Task1e - Testing octal to decimal conversion
        testConstructorWithBaseString("0", 8, "0");
        testConstructorWithBaseString("1234567", 8, "3534502");
        testConstructorWithBaseString("07777", 8, "06723");

        // Task1f - Testing invalid decimal constructor
        try {
            new DecimalNumber("invalid");
            System.out.println("Failed: Should throw exception for invalid decimal");
        } catch (IllegalArgumentException e) {
            System.out.println("Success: Caught expected exception for invalid decimal input");
        }

        // Task1g - Testing invalid base constructor
        try {
            new DecimalNumber("invalid", 2);
            System.out.println("Failed: Should throw exception for invalid binary");
        } catch (IllegalArgumentException e) {
            System.out.println("Success: Caught expected exception for invalid binary input");
        }

        // Task1h - Testing equals and toString
        DecimalNumber eq1 = new DecimalNumber("12345");
        DecimalNumber eq2 = new DecimalNumber("12345");
        DecimalNumber eq3 = new DecimalNumber("54321");
        testEqualsMethod(eq1, eq2, true);
        testEqualsMethod(eq1, eq3, false);
        System.out.println("toString() test: " + eq1.toString().equals("12345"));

        // Test equals with non-DecimalNumber object
        System.out.println("equals() with String test: " + !eq1.equals("12345"));
    }

    // Add helper method for testing legal numeric string
    public static void testLegalNumericString(String input, int base, boolean expected) {
        String result;
        try {
            new DecimalNumber(input, base);
            result = expected ? "Success" : "Failed";
        } catch (IllegalArgumentException e) {
            result = !expected ? "Success" : "Failed";
        }
        System.out.println("Testing legal numeric string: input = " + input +
                " in base " + base + "; Expected = " + expected + "; --> " + result);
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
    public static boolean testIncrementMethod(DecimalNumber num, String expectedOutput) {
        String result;
        String originalValue = num.toString(); // Store the original value before increment
        num.increment();
        String actualOutput = num.toString();

        if (actualOutput.equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
            System.out.println("Testing increment() method:");
            System.out.println("  Input value    = " + originalValue);
            System.out.println("  Actual output  = " + actualOutput);
            System.out.println("  Expected output= " + expectedOutput);
            System.out.println("  Result         = " + result);
            System.out.println();
            return false;
        }
        return true;
    }

    // Test multiplyByTwo() method
    public static boolean testMultiplyByTwoMethod(DecimalNumber num, String expectedOutput) {
        String result;
        String originalValue = num.toString(); // Store the original value before multiplication
        num.multiplyByTwo();
        String actualOutput = num.toString();

        if (actualOutput.equals(expectedOutput)) {
            result = "Success";
        } else {
            result = "Failed";
            System.out.println("Testing multiplyByTwo() method:");
            System.out.println("  Input value    = " + originalValue);
            System.out.println("  Actual output  = " + actualOutput);
            System.out.println("  Expected output= " + expectedOutput);
            System.out.println("  Result         = " + result);
            System.out.println();
            return false;
        }
        return true;
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

    public static void testMultiplyByTwoMethodWrapper(int numberOfTests) {
        int successCount = 0;
        for (int i = 0; i < numberOfTests; i++) {
            // Generate a random number between 0 and 999 for testing
            int randomNum = (int) (Math.random() * 1000);
            int randomNumResult = randomNum * 2;

            String randomNumString = new StringBuilder(String.valueOf(randomNum)).reverse().toString();
            String randomNumResultString = new StringBuilder(String.valueOf(randomNumResult)).reverse().toString();

            DecimalNumber num = new DecimalNumber(randomNumString);
            if (testMultiplyByTwoMethod(num, randomNumResultString)) {
                successCount++;
            }
        }
        System.out.println("Success rate for multiply by two: " +
                (successCount * 100.0 / numberOfTests) + "%");
    }

    public static void testIncrementMethodWrapper(int numberOfTests) {
        int successCount = 0;
        for (int i = 0; i < numberOfTests; i++) {
            // Generate a random number between 0 and 9999 for testing
            int randomNum = (int) (Math.random() * 10000);
            int randomNumResult = randomNum + 1;
            String randomNumString = new StringBuilder(String.valueOf(randomNum)).reverse().toString();
            String randomNumResultString = new StringBuilder(String.valueOf(randomNumResult)).reverse().toString();
            DecimalNumber num = new DecimalNumber(randomNumString);
            if (testIncrementMethod(num, randomNumResultString)) {
                successCount++;
            }
        }
        System.out.println("Success rate for increment: " +
                (successCount * 100.0 / numberOfTests) + "%");
    }
}
