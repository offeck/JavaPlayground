import java.util.Iterator;
import java.util.LinkedList;

public class BinaryRepresentation implements Iterable<Bit> {
    final private LinkedList<Bit> bits;
    private int numOfOnes;

    // Creates an empty BinaryRepresentation
    public BinaryRepresentation() {
        this.bits = new LinkedList<>();
        this.numOfOnes = 0;
    }

    // Task 1.3
    // Assumes other is a non-null BinaryRepresentation
    // Creates a copy of the other
    public BinaryRepresentation(BinaryRepresentation other) {
        this.bits = new LinkedList<>();
        for (Bit bit : other.bits) {
            this.bits.add(bit);
        }
        this.numOfOnes = other.numOfOnes;
    }

    // Task 1.1
    // Assumes bit is a non-null Bit
    // Adds the bit into the first position in this representation
    public void addFirst(Bit bit) {
        if (bit == null) {
            throw new IllegalArgumentException("Bit cannot be null");
        }
        bits.addFirst(bit);
        if (bit == Bit.ONE) {
            numOfOnes++;
        }
    }

    // Task 1.1
    // Assumes bit is a non-null Bit
    // Adds the bit into the last position in this representation
    public void addLast(Bit bit) {
        if (bit == null) {
            throw new IllegalArgumentException("Bit cannot be null");
        }
        bits.addLast(bit);
        if (bit == Bit.ONE) {
            numOfOnes++;
        }
    }

    // Task 1.1
    // Removes the first bit of the representation
    public Bit removeFirst() {
        Bit bit = bits.removeFirst();
        if (bit == Bit.ONE) {
            numOfOnes--;
        }
        return bit;
    }

    // Task 1.1
    // Removes the last bit of the representation
    public Bit removeLast() {
        Bit bit = bits.removeLast();
        if (bit == Bit.ONE) {
            numOfOnes--;
        }
        return bit;
    }

    // Task 1.4
    // Returns true if and only if the representation is a valid number, but not
    // necessarly a minimal representation
    public boolean isLegalNumber() {
        if (bits.size() == 0) {
            return false;
        }
        if (numOfOnes > 1 || bits.getLast() == Bit.ZERO) {
            return true;
        }
        return false;
    }

    // Task 1.5
    // Returns true if and only if the representation is a valid minimal
    // representation of a number
    public boolean isReduced() {
        // Must be a legal number first
        if (!isLegalNumber()) {
            return false;
        }

        // Case 1: Special minimal sequences <0>, <01>, <11>
        if (bits.size() == 1) {
            return true; // <0>
        }
        if (bits.size() == 2) {
            return bits.getFirst() == Bit.ONE;
        }

        // For sequences of length 3 or more
        if (bits.size() >= 3) {
            Bit last = bits.getLast();
            Bit secondLast = bits.get(bits.size() - 2);

            // Case 2: Ends with 01 or 10
            if ((last == Bit.ONE && secondLast == Bit.ZERO) ||
                    (last == Bit.ZERO && secondLast == Bit.ONE)) {
                return true;
            }

            // Case 3: Only two 1s and they are at the end
            if (numOfOnes == 2) {
                // Check if the last two bits are 1s
                return last == Bit.ONE && secondLast == Bit.ONE;
            }
        }

        return false;
    }

    // Task 1.5
    // If the current representation is not a minimal representation of a number,
    // reduces it to the minimal form
    public void reduce() {
        if (!isLegalNumber()) {
            return; // Can't reduce illegal numbers
        }

        // Keep removing bits from the left until we get a minimal representation
        while (!isReduced()) {
            removeLast();
        }
    }

    // Task 1.6
    // Replaces each Bit in the representation with its negative
    public void complement() {
        if (bits.isEmpty()) {
            return;
        }
        numOfOnes = length() - getNumOfOnes();
        int tempLength = length();
        for (int i = 0; i < tempLength; i++) {
            Bit bit = shiftRight();
            bits.addLast(bit.negate());
        }
    }

    // Task 1.7
    // Adds a zero bit in the first position of the representation
    public void shiftLeft() {
        addFirst(Bit.ZERO);
    }

    // Task 1.7
    // Removes and returns the rightmost Bit of the representation if exists, and
    // returns null if the representation is empty
    public Bit shiftRight() {
        if (bits.isEmpty()) {
            return null;
        }
        Bit first = removeFirst();
        return first;
    }

    // Task 1.8
    // Pads this binary representation to the length newLength if it is larger than
    // the current representation length
    // by adding the last Bit of the representation until reaching the desired
    // length
    public void padding(int newLength) {
        while (bits.size() < newLength) {
            Bit last = getLast();
            addLast(last);
        }
    }

    // Task 1.2
    // Returns a string of the bits of this representation surrounded by '<' and '>'
    public String toString() {
        String result = "";
        for (Bit bit : bits) {
            result = (bit.toString()) + result;
        }
        return "<" + result + ">";
    }

    /*
     * =====================================================
     * Do not change the following methods:
     * =====================================================
     */

    // Returns the last bit of this representation without removing it
    public Bit getLast() {
        return this.bits.get(this.bits.size() - 1);
    }

    // Returns the first bit of this representation without removing it
    public Bit getFirst() {
        return this.bits.get(0);
    }

    // Returns the number of ones in the representation
    public int getNumOfOnes() {
        return this.numOfOnes;
    }

    // Returns the number of bits in the representation
    public int length() {
        return this.bits.size();
    }

    // Returns the built-in iterator of java.util.LinkedList<T> for easier iteration
    // over the representation
    public Iterator<Bit> iterator() {
        return this.bits.iterator();
    }
}
