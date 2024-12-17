public class DecimalNumber {

    // ----------------Fields------------------
    private String decimalValue;

    // ----------------Constructors------------------

    // Task 1.6
    // 'value' is a string representing a valid decimal number.
    // Constructs a DecimalNumber with the given decimal string value
    public DecimalNumber(String value) {
        // ---------------write your code BELOW this line only! ------------------
        if (!legalNumericString(value, 10)) {
            throw new IllegalArgumentException("The string is not a valid decimal number");
        }
        this.decimalValue = value;
        // ---------------write your code ABOVE this line only! ------------------
    }

    // Task 1.7
    // 'value' is a string representing a valid number in the given 'base' (2, 8, or
    // 10).
    // Constructs a DecimalNumber by converting the given value from the specified
    // base to decimal.
    public DecimalNumber(String value, int base) {
        // ---------------write your code BELOW this line only! ------------------
        if (!legalNumericString(value, base)) {
            throw new IllegalArgumentException("The string is not a valid number in the given base");
        }
        if (base == 2) {
            this.decimalValue = binaryToDecimal(value);
        } else if (base == 8) {
            this.decimalValue = octalToDecimal(value);
        } else if (base == 10) {
            this.decimalValue = value;
        } else {
            throw new IllegalArgumentException("The base is not valid");
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

    // ----------------Public Methods------------------
    // Increments decimalValue by 1.
    public void increment() {
        this.decimalValue = decimalIncrement(this.decimalValue);
    }

    // Multiplies decimalValue by 2.
    public void multiplyByTwo() {
        this.decimalValue = decimalDouble(this.decimalValue);
    }

    // Task 1.8
    // Returns the decimal number as a string.
    public String toString() {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        ans = this.decimalValue;
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.8
    // No assumptions
    // Compares this DecimalNumber to another for equality based on their decimal
    // values. If other is null, should return false.
    public boolean equals(Object other) {
        boolean equals = false;
        // ---------------write your code BELOW this line only! ------------------
        if (other == null) {
            return false;
        }
        if (other instanceof DecimalNumber) {
            DecimalNumber otherNum = (DecimalNumber) other;
            equals = this.decimalValue.equals(otherNum.decimalValue);
        }
        // ---------------write your code ABOVE this line only! ------------------
        return equals;
    }

    // ----------------Private Static Functions------------------
    // No assumptions
    // Converts the character 'c' to its integer value, returns -1 if 'c' is not a
    // decimal digit.
    private static int toInt(char c) {
        return "0123456789".indexOf(c);
    }

    // Task 1.1
    // No assumptions
    // Checks if 's' is a valid numeric string in the specified 'base'>1.
    private static boolean legalNumericString(String s, int base) {
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------
        if (s == null || s.length() == 0) {
            return false;
        }
        if (base < 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            int num = toInt(s.charAt(i));
            if (num == -1 || num >= base) {
                return false;
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.2
    // 's' is a string representing a valid decimal number.
    // Increments the number represented by 's' by 1 and returns the result as a
    // string.
    private static String decimalIncrement(String s) {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        if (!legalNumericString(s, 10)) {
            throw new IllegalArgumentException("The string is not a valid decimal number");
        }
        int carry = 1;
        ans = decimalIncrement(s, carry);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.2
    // 's' is a string representing a valid decimal number, 0<='carry'<=1.
    // Increments the number represented by 's' by 'carry'.
    private static String decimalIncrement(String s, int carry) {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        if (s.length() == 0) {
            if (carry == 1) {
                ans = "1";
            } else {
                ans = "";
            }
        } else if (toInt(s.charAt(0)) + carry > 9) {
            ans = "0" + decimalIncrement(s.substring(1), 1);
        } else {
            ans = toInt(s.charAt(0)) + carry + decimalIncrement(s.substring(1), 0);
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3
    // 's' is a string representing a valid decimal number.
    // Doubles the decimal number represented by 's' and returns the result as a
    // string.
    private static String decimalDouble(String s) {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        if (!legalNumericString(s, 10)) {
            throw new IllegalArgumentException("The string is not a valid decimal number");
        }
        int carry = 0;
        ans = decimalDouble(s, carry);

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.3
    // 's' is a string representing a valid decimal number, 0<='carry'<=1
    // Doubles the decimal number represented by 's', and adds to it the 'carry'
    private static String decimalDouble(String s, int carry) {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        if (s.length() == 0) {
            if (carry == 1) {
                return "1";
            }
            return "";
        }
        int mul = (toInt(s.charAt(0)) * 2);
        if (mul + carry > 9) {
            ans = ((mul + carry) % 10) + decimalDouble(s.substring(1), 1);
        } else {
            ans = (mul + carry) + decimalDouble(s.substring(1), 0);
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.4
    // 's' is a string representing a valid binary number.
    // Converts a binary string 's' to its decimal string representation.
    private static String binaryToDecimal(String s) {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        if (s.length() == 1) {
            return s;
        }
        ans = decimalDouble(binaryToDecimal(s.substring(1)));
        if (s.charAt(0) == '1') {
            ans = decimalIncrement(ans);
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 1.5
    // 's' is a string representing a valid octal number.
    // Converts an octal string 's' to its decimal string representation.
    private static String octalToDecimal(String s) {
        String ans = "";
        // ---------------write your code BELOW this line only! ------------------
        if (s.length() == 1) {
            return s;
        }
        ans = octalToDecimal(s.substring(1));
        // Each octal digit needs to be multiplied by the appropriate power of 8
        // We can do this by doubling 3 times (2^3 = 8) and then adding the digit value
        ans = decimalDouble(ans); // Multiply by 2
        ans = decimalDouble(ans); // Multiply by 2 again (now 4)
        ans = decimalDouble(ans); // Multiply by 2 again (now 8)
        // Add the current digit value
        int currentDigit = toInt(s.charAt(0));
        while (currentDigit > 0) {
            ans = decimalIncrement(ans);
            currentDigit--;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

}
