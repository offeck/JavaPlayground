import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber> {
    final private BinaryRepresentation rep;

    // Task 2.1
    // Assumes n is non-negative
    // Initializes a BinaryNumber representing n
    public BinaryNumber(int n) {
        if (n < 0) {
            throw new RuntimeException("Number cannot be negative");
        }

        rep = new BinaryRepresentation();

        if (n == 0) {
            rep.addFirst(Bit.ZERO);
            return;
        }

        // Convert n to binary by repeatedly dividing by 2 and tracking remainders
        int num = n;
        while (num > 0) {
            if (num % 2 == 0) {
                rep.addLast(Bit.ZERO);
            } else {
                rep.addLast(Bit.ONE);
            }
            num = num / 2;
        }
        rep.addLast(Bit.ZERO);
        rep.reduce();
    }

    // Assumes other is a non-null BinaryNumber
    // Initializes a copy of other
    public BinaryNumber(BinaryNumber other) {
        this.rep = new BinaryRepresentation(other.rep);
    }

    // Task 2.12
    // Assumes s is a string representing a valid number, either positive or
    // negative
    // Initializes a BinaryNumber representing the number described in s
    public BinaryNumber(String s) {
        int power = 1;
        // this.rep = (new BinaryNumber(0)).rep;
        BinaryNumber temp = new BinaryNumber(0);
        for (int i = s.length() - 1; i > -1; i--) {
            if (s.charAt(i) == '-') {
                temp = temp.negate();
                continue;
            }
            BinaryNumber powerAsBinary = new BinaryNumber(power);
            int digit = charToInt(s.charAt(i));
            BinaryNumber digitAsBinary = new BinaryNumber(digit);
            BinaryNumber multiplication = powerAsBinary.multiply(digitAsBinary);
            temp = temp.add(multiplication);
            power *= 10;
        }
        this.rep = temp.rep;
    }

    // Task 2.4
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the addition of other to
    // this (i.e. this + other)
    public BinaryNumber add(BinaryNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        int maxLength = Math.max(this.length(), other.length());
        BinaryNumber this2 = new BinaryNumber(this);
        BinaryNumber other2 = new BinaryNumber(other);
        this2.rep.padding(maxLength);
        other2.rep.padding(maxLength);
        Iterator<Bit> thisIterator = this2.rep.iterator();
        Iterator<Bit> otherIterator = other2.rep.iterator();
        BinaryNumber res = new BinaryNumber(0);
        res.rep.shiftRight();

        Bit carry = Bit.ZERO;
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            Bit bit1 = thisIterator.next();
            Bit bit2 = otherIterator.next();
            Bit sum = Bit.fullAdderSum(bit1, bit2, carry);
            carry = Bit.fullAdderCarry(bit1, bit2, carry);
            res.rep.addLast(sum);
        }
        if (other2.rep.getLast() == this2.rep.getLast()) {
            res.rep.addLast(carry);
        }
        res.rep.reduce();
        return res;
    }

    // Task 2.5
    // Returns a new BinaryNumber that represents the Additive Inverse of this, that
    // is, if this equals X, the return value is -X
    public BinaryNumber negate() {
        // BinaryRepresentation res = new BinaryRepresentation(this.rep);
        BinaryNumber res = new BinaryNumber(this);
        res.rep.complement();
        res = res.add(new BinaryNumber(1));
        return res;
    }

    // Task 2.6
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the subtraction of other
    // from this (i.e. this - other)
    public BinaryNumber subtract(BinaryNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        return this.add(other.negate());
    }

    // Task 2.7
    // Returns 1 if the number represented by this object is positive, -1 if it
    // negative and 0 if it equals 0
    public int signum() {
        if (length() == 1) {
            return 0;
        }
        return this.rep.getLast() == Bit.ONE ? -1 : 1;
    }

    // Task 2.10
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the multiplication of
    // other and this (i.e. this * other)
    private BinaryNumber multiplyPositive(BinaryNumber other) {
        Bit first = other.rep.shiftRight();
        if (first == null) {
            return new BinaryNumber(0);
        }
        BinaryNumber res = (first == Bit.ZERO) ? new BinaryNumber(0) : new BinaryNumber(this);
        this.rep.shiftLeft();
        BinaryNumber multiplicationResult = this.multiplyPositive(other);
        BinaryNumber finalRes = res.add(multiplicationResult);
        return finalRes;
    }

    // Task 2.10
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the multiplication of
    // other and this (i.e. this * other)
    public BinaryNumber multiply(BinaryNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        int signum = this.signum() * other.signum();
        BinaryNumber thisPositive = (this.signum() == -1) ? new BinaryNumber(this.negate()) : new BinaryNumber(this);
        BinaryNumber otherPositive = (other.signum() == -1) ? new BinaryNumber(other.negate())
                : new BinaryNumber(other);

        BinaryNumber res = thisPositive.multiplyPositive(otherPositive);

        if (signum == -1) {
            res = res.negate();
        }
        return res;
    }

    // Task 2.11
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the integer-division of
    // other from this (i.e. this / other)
    public BinaryNumber divide(BinaryNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        int signum = this.signum() * other.signum();
        BinaryNumber thisPositive = (this.signum() == -1) ? this.negate() : this;
        BinaryNumber otherPositive = (other.signum() == -1) ? other.negate() : other;
        BinaryNumber res = thisPositive.dividePositive(otherPositive);
        if (signum == -1) {
            res = res.negate();
        }
        return res;
    }

    private BinaryNumber dividePositive(BinaryNumber denominator) {
        BinaryNumber numerator = new BinaryNumber(0);
        BinaryNumber res = new BinaryNumber(0);
        Iterator<Bit> thisIterator = this.rep.descendingIterator();
        while (thisIterator.hasNext()) {
            Bit bit = thisIterator.next();
            numerator.rep.addFirst(bit);
            numerator.rep.reduce();
            if (numerator.compareTo(denominator) >= 0) {
                numerator = numerator.subtract(denominator);
                res.rep.addFirst(Bit.ONE);
            } else {
                res.rep.addFirst(Bit.ZERO);
            }
        }
        res.rep.reduce();
        return res;
    }

    // Task 2.2
    // Assumes this object represents a legal binary number
    // Returns the represention of this BinaryNumber as a binary string, that is,
    // all the chars are either 0 or 1
    public String toString() {
        if (!isLegal()) {
            throw new IllegalArgumentException("Illegal Number");
        }

        return rep.toString().substring(1, rep.toString().length() - 1);
    }

    // Task 2.3
    // Returns true if and only if this and other represent the same number
    public boolean equals(Object other) {
        if (other == null || !(other instanceof BinaryNumber)) {
            return false;
        }
        return this.toString().equals(((BinaryNumber) other).toString());
    }

    // Task 2.8
    // Returns a positive number if this object represents a number bigger than the
    // one other represents,
    // 0 if they are equal, and a negative number if it is smaller.
    public int compareTo(BinaryNumber other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        BinaryNumber temp = new BinaryNumber(this);
        BinaryNumber res = temp.subtract(other);
        return res.signum();
    }

    // Task 2.9
    // Assumes this current number is small enough to be represented by an int
    // Returns the int value of the number represented by this
    public int toInt() {
        if (!isLegal()) {
            throw new IllegalArgumentException("Number is not legal");
        }

        // Handle zero case
        if (length() == 1) {
            return 0;
        }

        // Check if number is negative (ends with 1)
        boolean isNegative = rep.getLast() == Bit.ONE;

        // Calculate absolute value
        int result = 0;
        int power = 0;

        // Create a copy to avoid modifying original
        BinaryNumber temp = new BinaryNumber(this);
        if (isNegative) {
            // For negative numbers, first negate to get positive value
            temp = temp.negate();
        }

        // Convert binary to decimal
        Iterator<Bit> iterator = temp.rep.iterator();
        while (iterator.hasNext()) {
            Bit bit = iterator.next();
            if (bit == Bit.ONE) {
                // Check for overflow before adding
                // power > 30 would cause 1 << power to overflow
                // Also check if adding 2^power would overflow result
                if (power > 30 || result > Integer.MAX_VALUE - (1 << power)) {
                    throw new RuntimeException("Number too large to be represented as int");
                }
                // Add 2^power to result when we see a 1 bit
                // 1 << power is equivalent to 2^power
                result += (1 << power);
            }
            power++; // Track which power of 2 we're at
        }

        // Apply sign
        if (isNegative) {
            if (result == Integer.MIN_VALUE) {
                return result;
            }
            if (result > Integer.MAX_VALUE) {
                throw new RuntimeException("Number too small to be represented as int");
            }
            result = -result;
        }

        return result;
    }

    // Task 2.13
    // Assumes this object represents a legal binary number
    // Returns a decimal string (positive or negative) of the number represented by
    // this
    public String toIntString() {
        if (!isLegal()) {
            throw new IllegalArgumentException("Illegal Number");
        }
        if (this.length() == 1) {
            return "0";
        }
        String absStr = (this.signum() == -1) ? this.negate().toString() : this.toString();
        String absStrWithoutFirstBit = absStr.substring(1, absStr.length());
        String res = binaryToDecimal(absStrWithoutFirstBit);
        if (this.signum() == -1) {
            res = "-" + res;
        }
        return res;
    }

    /*
     * =================================================================
     * Don't change the following functions:
     * =================================================================
     */

    // Returns true if and only if the representation of this BinaryNumber is a
    // legal and minimal representation of a number
    public boolean isLegal() {
        return rep.isLegalNumber() && rep.isReduced();
    }

    // Returns the number of bits currently in the representation of this
    // BinaryNumber
    public int length() {
        return this.rep.length();
    }

    // Returns a new BinaryNumber representing this * 2
    private BinaryNumber multiplyBy2() {
        BinaryNumber res = new BinaryNumber(this);
        res.rep.shiftLeft();
        return res;
    }

    // Returns a new BinaryNumber representing this / 2
    private BinaryNumber divideBy2() {
        BinaryNumber res = new BinaryNumber(this);
        res.rep.shiftRight();
        return res;
    }

    // Helper methods for decimal-binary conversion
    private static int charToInt(char c) {
        return c - '0';
    }

    // Recursively increments a decimal string by 1
    private static String decimalIncrement(String s) {
        return decimalIncrement(s, 1);
    }

    // Helper method for decimal increment with carry
    private static String decimalIncrement(String s, int carry) {
        // Base case: empty string
        if (s.length() == 0) {
            return carry == 1 ? "1" : "";
        }

        // Get last digit
        int lastDigit = charToInt(s.charAt(s.length() - 1));

        // If adding carry creates overflow
        if (lastDigit + carry > 9) {
            // Recursively handle carry and append 0
            return decimalIncrement(s.substring(0, s.length() - 1), 1) + "0";
        } else {
            // No carry, just add and return
            return s.substring(0, s.length() - 1) + (carry + lastDigit);
        }
    }

    // Doubles a decimal string
    private static String decimalDouble(String s) {
        return decimalDouble(s, 0);
    }

    // Helper method for decimal doubling with carry
    private static String decimalDouble(String s, int carry) {
        // Base case: empty string
        if (s.length() == 0) {
            return carry == 1 ? "1" : "";
        }

        // Get last digit and multiply by 2
        int lastDigit = charToInt(s.charAt(s.length() - 1));
        int product = lastDigit * 2 + carry;

        // Handle carry for next recursion
        if (product > 9) {
            return decimalDouble(s.substring(0, s.length() - 1), 1) + (product % 10);
        } else {
            return decimalDouble(s.substring(0, s.length() - 1), 0) + product;
        }
    }

    // Converts a binary string to decimal string
    private static String binaryToDecimal(String s) {
        // Base case: single digit
        if (s.length() == 1) {
            return s;
        }

        // Get all bits except the last one
        String allButLast = s.substring(0, s.length() - 1);

        // Convert prefix to decimal and double it
        String result = decimalDouble(binaryToDecimal(allButLast));

        // Add 1 if last bit is 1
        if (s.charAt(s.length() - 1) == '1') {
            result = decimalIncrement(result);
        }

        return result;
    }
}
