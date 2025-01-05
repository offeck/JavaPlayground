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
                rep.addFirst(Bit.ZERO);
            } else {
                rep.addFirst(Bit.ONE);
            }
            num = num / 2;
        }
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
        throw new UnsupportedOperationException("Delete this line and implement the method.");
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
        BinaryNumber other2 = new BinaryNumber(other);
        this.rep.padding(maxLength);
        other2.rep.padding(maxLength);
        Iterator<Bit> thisIterator = this.rep.iterator();
        Iterator<Bit> otherIterator = other2.rep.iterator();
        BinaryRepresentation res = new BinaryRepresentation();

        Bit carry = Bit.ZERO;
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            Bit bit1 = thisIterator.next();
            Bit bit2 = otherIterator.next();
            Bit sum = Bit.fullAdderSum(bit1, bit2, carry);
            carry = Bit.fullAdderCarry(bit1, bit2, carry);
            res.addLast(sum);
        }

        if (carry == Bit.ONE) {
            if (other2.rep.getLast() == this.rep.getLast())
                res.addLast(Bit.ONE);
        }
        res.reduce();
        return new BinaryNumber(res.toString());
    }

    // Task 2.5
    // Returns a new BinaryNumber that represents the Additive Inverse of this, that
    // is, if this equals X, the return value is -X
    public BinaryNumber negate() {
        BinaryRepresentation res = new BinaryRepresentation(this.rep);
        res.complement();
        BinaryNumber res2 = new BinaryNumber(res.toString());
        res2.add(new BinaryNumber(1));
        return res2;
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
        throw new UnsupportedOperationException("Delete this line and implement the method.");
    }

    // Task 2.10
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the multiplication of
    // other and this (i.e. this * other)
    public BinaryNumber multiply(BinaryNumber other) {
        throw new UnsupportedOperationException("Delete this line and implement the method.");
    }

    // Task 2.11
    // Assumes other is non-null BinaryNumber
    // Returns a new BinaryNumber containing the result of the integer-division of
    // other from this (i.e. this / other)
    public BinaryNumber divide(BinaryNumber other) {
        throw new UnsupportedOperationException("Delete this line and implement the method.");
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
        temp.subtract(other);
        return temp.signum();
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
                if (power > 30 || result > Integer.MAX_VALUE - (1 << power)) {
                    throw new RuntimeException("Number too large to be represented as int");
                }
                result += (1 << power);
            }
            power++;
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

        throw new UnsupportedOperationException("Delete this line and implement the method.");
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
}
