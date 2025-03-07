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
        BinaryNumber power = new BinaryNumber(1);
        BinaryNumber temp = new BinaryNumber(0);
        for (int i = s.length() - 1; i > -1; i--) {
            if (s.charAt(i) == '-') {
                temp = temp.negate();
                continue;
            }
            int digit = charToInt(s.charAt(i));
            BinaryNumber digitAsBinary = new BinaryNumber(digit);
            BinaryNumber multiplication = power.multiply(digitAsBinary);
            temp = temp.add(multiplication);
            power = power.multiply(new BinaryNumber(10));
        }
        this.rep = temp.rep;
    }

    // Task 2.4
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the addition of other to
    // this (i.e. this + other)
    public BinaryNumber add(BinaryNumber other) {
        // Validate input
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }

        // Create copies and pad to same length for addition
        int maxLength = Math.max(this.length(), other.length());
        BinaryNumber augend = new BinaryNumber(this);
        BinaryNumber addend = new BinaryNumber(other);
        augend.rep.padding(maxLength);
        addend.rep.padding(maxLength);

        // Set up iterators and result
        Iterator<Bit> augendIterator = augend.rep.iterator();
        Iterator<Bit> addendIterator = addend.rep.iterator();
        BinaryNumber result = new BinaryNumber(0);
        result.rep.shiftRight(); // Remove initial 0

        // Perform addition with carry
        Bit carry = Bit.ZERO;
        Bit lastAugendBit = null;
        Bit lastAddendBit = null;

        while (augendIterator.hasNext() && addendIterator.hasNext()) {
            Bit augendBit = augendIterator.next();
            Bit addendBit = addendIterator.next();
            
            // Calculate sum and carry using full adder
            Bit sum = Bit.fullAdderSum(augendBit, addendBit, carry);
            carry = Bit.fullAdderCarry(augendBit, addendBit, carry);
            
            result.rep.addLast(sum);
            lastAugendBit = augendBit;
            lastAddendBit = addendBit;
        }

        // Add final carry bit if both numbers had same sign
        if (lastAugendBit == lastAddendBit) {
            result.rep.addLast(carry);
        }

        result.rep.reduce();
        return result;
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
        if (other.equals(new BinaryNumber(0))) {
            throw new IllegalArgumentException("Division by zero");
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

    // Helper method for division of positive numbers
    // Implements long division algorithm in binary
    private BinaryNumber dividePositive(BinaryNumber denominator) {
        // Initialize result and working numerator
        BinaryNumber numerator = new BinaryNumber(0);
        BinaryNumber result = new BinaryNumber(0);

        // Process bits from most significant to least significant
        Iterator<Bit> thisIterator = this.rep.descendingIterator();

        while (thisIterator.hasNext()) {
            // Get next bit and add it to current numerator
            Bit currentBit = thisIterator.next();
            numerator.rep.addFirst(currentBit);
            numerator.rep.reduce();

            // If numerator >= denominator, subtract and add 1 to result
            if (numerator.compareTo(denominator) >= 0) {
                numerator = numerator.subtract(denominator);
                result.rep.addFirst(Bit.ONE);
            } else {
                // Otherwise, add 0 to result
                result.rep.addFirst(Bit.ZERO);
            }
        }

        // Ensure result is in minimal form
        result.rep.reduce();
        return result;
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
        Iterator<Bit> thisIterator = this.rep.iterator();
        Iterator<Bit> otherIterator = ((BinaryNumber) other).rep.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (thisIterator.next() != otherIterator.next()) {
                return false;
            }
        }
        if (thisIterator.hasNext() || otherIterator.hasNext()) {
            return false;
        }
        return true;
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
                // power > 30 would cause 2^power to overflow
                if (power > 30 || result > Integer.MAX_VALUE - (int) Math.pow(2, power)) {
                    throw new IllegalArgumentException("Number too large to be represented as int");
                }
                // Add 2^power to result when we see a 1 bit
                result += (int) Math.pow(2, power);
            }
            power++; // Track which power of 2 we're at
        }

        // Apply sign
        if (isNegative) {
            if (result == Integer.MIN_VALUE) {
                return result;
            }
            if (result > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Number too small to be represented as int");
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
        BinaryNumber absBinary = (this.signum() == -1) ? this.negate() : this;
        String res = binaryToDecimal(absBinary);
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

    private static String binaryToDecimal(BinaryNumber s) {
        Iterator<Bit> iterator = s.rep.descendingIterator();
        String res = "0";
        while (iterator.hasNext()) {
            res = decimalDouble(res);
            Bit bit = iterator.next();
            if (bit == Bit.ONE) {
                res = decimalIncrement(res);
            }

        }
        return res;
    }

    // Converts a binary string to decimal string
    // private static String binaryToDecimal(BinaryNumber s) {
    // // Base case: single digit
    // if (s.length() == 1) {
    // return s.toString();
    // }

    // // Get all bits except the last one
    // String allButLast = s.substring(0, s.length() - 1);
    // BinaryNumber allButLastBinary = new BinaryNumber(s);

    // // Convert prefix to decimal and double it
    // String result = decimalDouble(binaryToDecimal(allButLast));

    // // Add 1 if last bit is 1
    // if (s.charAt(s.length() - 1) == '1') {
    // result = decimalIncrement(result);
    // }

    // return result;
    // }
}
