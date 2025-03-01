public class BinaryRepresentationTester {
    private static void printTestResult(String testName, boolean result) {
        System.out.println("Test: " + testName);
        System.out.println("Result: " + (result ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }

    // Task 1.1 - Testing add/remove operations
    public static boolean testAddRemoveOperations() {
        System.out.println("\nTesting Add/Remove Operations:");
        BinaryRepresentation br = new BinaryRepresentation();

        // Test addFirst
        br.addFirst(Bit.ONE);
        br.addFirst(Bit.ZERO);
        System.out.println("Testing addFirst: Current state = " + br.toString());
        if (!br.toString().equals("<10>") || br.getNumOfOnes() != 1) {
            System.out.println("Failed: Expected <10> with 1 one, got " + br.toString() +
                    " with " + br.getNumOfOnes() + " ones");
            return false;
        }
        System.out.println("addFirst test passed");

        // Test addLast
        br.addLast(Bit.ONE);
        System.out.println("Testing addLast: Current state = " + br.toString());
        if (!br.toString().equals("<110>") || br.getNumOfOnes() != 2) {
            System.out.println("Failed: Expected <110> with 2 ones, got " + br.toString() +
                    " with " + br.getNumOfOnes() + " ones");
            return false;
        }
        System.out.println("addLast test passed");

        // Test removeFirst
        Bit removed = br.removeFirst();
        System.out.println("Testing removeFirst: Removed " + removed + ", Current state = " + br.toString());
        if (removed != Bit.ZERO || !br.toString().equals("<11>") || br.getNumOfOnes() != 2) {
            System.out.println("Failed: Expected <11> with 2 ones after removing 0, got " +
                    br.toString() + " with " + br.getNumOfOnes() + " ones");
            return false;
        }
        System.out.println("removeFirst test passed");

        // Test removeLast
        removed = br.removeLast();
        System.out.println("Testing removeLast: Removed " + removed + ", Current state = " + br.toString());
        if (removed != Bit.ONE || !br.toString().equals("<1>") || br.getNumOfOnes() != 1) {
            System.out.println("Failed: Expected <1> with 1 one after removing 1, got " +
                    br.toString() + " with " + br.getNumOfOnes() + " ones");
            return false;
        }
        System.out.println("removeLast test passed");

        return true;
    }

    // Task 1.2 - Testing toString
    public static boolean testToString() {
        System.out.println("\nTesting ToString Operation:");
        BinaryRepresentation br = new BinaryRepresentation();

        // Test empty representation
        System.out.println("Testing empty representation");
        if (!br.toString().equals("<>")) {
            System.out.println("Failed: Empty representation should be <>, got " + br.toString());
            return false;
        }
        System.out.println("Empty representation test passed");

        // Test sequential addFirst operations
        br = new BinaryRepresentation();
        System.out.println("\nTesting sequential addFirst operations:");
        System.out.println("Initial state: " + br.toString()); // <>

        br.addFirst(Bit.ZERO);
        System.out.println("After first ZERO: " + br.toString()); // <0>

        br.addFirst(Bit.ZERO);
        System.out.println("After second ZERO: " + br.toString()); // <00>

        br.addFirst(Bit.ONE);
        System.out.println("After adding ONE: " + br.toString()); // <001>

        if (!br.toString().equals("<001>")) {
            System.out.println("Failed: Expected <001>, got " + br.toString());
            return false;
        }
        System.out.println("Sequential addFirst test passed");

        // Test with some bits (original test case)
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ONE);
        System.out.println("\nTesting representation with addLast: " + br.toString());
        if (!br.toString().equals("<101>")) {
            System.out.println("Failed: Expected <101>, got " + br.toString());
            return false;
        }
        System.out.println("Non-empty representation test passed");

        return true;
    }

    // Task 1.3 - Testing copy constructor
    public static boolean testCopyConstructor() {
        System.out.println("\nTesting Copy Constructor:");
        BinaryRepresentation original = new BinaryRepresentation();
        original.addLast(Bit.ONE);
        original.addLast(Bit.ZERO);
        original.addLast(Bit.ONE);

        System.out.println("Original: " + original.toString());
        BinaryRepresentation copy = new BinaryRepresentation(original);
        System.out.println("Copy: " + copy.toString());

        // Test if copy is equal but separate
        if (!copy.toString().equals(original.toString())) {
            System.out.println("Failed: Copy not equal to original");
            System.out.println("Original: " + original.toString());
            System.out.println("Copy: " + copy.toString());
            return false;
        }
        System.out.println("Initial copy test passed");

        // Modify original and verify copy remains unchanged
        original.addLast(Bit.ZERO);
        System.out.println("After modifying original:");
        System.out.println("Original: " + original.toString());
        System.out.println("Copy: " + copy.toString());

        if (!copy.toString().equals("<101>")) {
            System.out.println("Failed: Copy changed when original was modified");
            return false;
        }
        System.out.println("Deep copy test passed");

        return true;
    }

    // Task 1.4 - Testing isLegalNumber
    public static boolean testIsLegalNumber() {
        System.out.println("\nTesting isLegalNumber:");
        BinaryRepresentation br = new BinaryRepresentation();

        // Test case 1: Empty representation
        System.out.println("\nTesting empty representation:");
        if (br.isLegalNumber()) {
            System.out.println("Failed: Empty representation should not be legal");
            return false;
        }
        System.out.println("Empty representation test passed");

        // Test case 2: Single bits
        br.addLast(Bit.ONE); // <1>
        System.out.println("\nTesting single 1: " + br.toString());
        if (br.isLegalNumber()) {
            System.out.println("Failed: Single 1 should not be legal");
            return false;
        }
        System.out.println("Single 1 test passed");

        br = new BinaryRepresentation();
        br.addLast(Bit.ZERO); // <0>
        if (!br.isLegalNumber()) {
            System.out.println("Failed: Single 0 should be legal");
            return false;
        }

        // Test case 3: Two bits
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ONE); // <11>
        if (!br.isLegalNumber()) {
            System.out.println("Failed: Multiple 1s should be legal");
            return false;
        }

        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO); // <10>
        if (!br.isLegalNumber()) {
            System.out.println("Failed: Number ending with 0 should be legal");
            return false;
        }

        // Test case 4: Longer sequences
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ZERO); // <100>
        if (!br.isLegalNumber()) {
            System.out.println("Failed: Number ending with 0 should be legal");
            return false;
        }

        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ZERO); // <1100>
        if (!br.isLegalNumber()) {
            System.out.println("Failed: Multiple 1s with trailing zeros should be legal");
            return false;
        }

        // Test case 5: Complex cases
        br = new BinaryRepresentation();
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO); // <010>
        if (!br.isLegalNumber()) {
            System.out.println("Failed: Number with internal 1s ending in 0 should be legal");
            return false;
        }

        return true;
    }

    // Task 1.5 - Testing isReduced and reduce
    public static boolean testReducedOperations() {
        System.out.println("\nTesting Reduced Operations:");
        BinaryRepresentation br = new BinaryRepresentation();

        // Case A: Special minimal sequences
        System.out.println("\nTesting special minimal sequences:");

        // Test <0>
        br.addLast(Bit.ZERO);
        System.out.println("Testing <0>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <0> should be reduced");
            return false;
        }
        System.out.println("<0> test passed");

        // Test <01>
        br = new BinaryRepresentation();
        br.addFirst(Bit.ZERO);
        br.addFirst(Bit.ONE);
        System.out.println("Testing <01>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <01> should be reduced");
            return false;
        }
        System.out.println("<01> test passed");

        // Test <11>
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ONE);
        System.out.println("Testing <11>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <11> should be reduced");
            return false;
        }
        System.out.println("<11> test passed");

        // Case B: 3+ bits ending with 01 or 10
        System.out.println("\nTesting sequences ending with 01/10:");

        // Test <101>
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ONE);
        System.out.println("Testing <101>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <101> should be reduced");
            return false;
        }
        System.out.println("<101> test passed");

        // Test <010>
        br = new BinaryRepresentation();
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        System.out.println("Testing <010>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <010> should be reduced");
            return false;
        }
        System.out.println("<010> test passed");

        // Case C: 3+ bits with exactly two 1s at the end
        System.out.println("\nTesting sequences with two 1s at the end:");

        // Test <110>
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        System.out.println("Testing <110>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <110> should be reduced");
            return false;
        }
        System.out.println("<110> test passed");

        // Test <1100>
        br = new BinaryRepresentation();
        br.addFirst(Bit.ONE);
        br.addFirst(Bit.ONE);
        br.addFirst(Bit.ZERO);
        br.addFirst(Bit.ZERO);
        System.out.println("Testing <1100>: " + br.toString());
        if (!br.isReduced()) {
            System.out.println("Failed: <1100> should be reduced");
            return false;
        }
        System.out.println("<1100> test passed");

        // Test non-reduced cases
        System.out.println("\nTesting non-reduced cases:");

        // Test <00000> -> should reduce to <0>
        br = new BinaryRepresentation();
        for (int i = 0; i < 5; i++) {
            br.addLast(Bit.ZERO);
        }
        System.out.println("Testing reduce <00000>: " + br.toString());
        if (br.isReduced()) {
            System.out.println("Failed: <00000> should not be reduced");
            return false;
        }
        br.reduce();
        if (!br.toString().equals("<0>")) {
            System.out.println("Failed: <00000> should reduce to <0>, got " + br.toString());
            return false;
        }
        System.out.println("Reduce <00000> to <0> test passed");

        // Test <11101> -> should reduce to <101>
        br = new BinaryRepresentation();
        br.addFirst(Bit.ONE);
        br.addFirst(Bit.ONE);
        br.addFirst(Bit.ONE);
        br.addFirst(Bit.ZERO);
        br.addFirst(Bit.ONE);
        System.out.println("Testing reduce <11101>: " + br.toString());
        if (br.isReduced()) {
            System.out.println("Failed: <11101> should not be reduced");
            return false;
        }
        br.reduce();
        if (!br.toString().equals("<101>")) {
            System.out.println("Failed: <11101> should reduce to <101>, got " + br.toString());
            return false;
        }
        System.out.println("Reduce <11101> to <101> test passed");

        return true;
    }

    // Task 1.6 - Testing complement
    public static boolean testComplement() {
        System.out.println("\nTesting Complement Operation:");
        BinaryRepresentation br;

        // Test case 1: Single bit
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE); // <1>
        System.out.println("Testing complement of <1>");
        System.out.println("Before: " + br.toString());
        br.complement();
        System.out.println("After:  " + br.toString());
        if (!br.toString().equals("<0>")) {
            System.out.println("Failed: Expected <0>, got " + br.toString());
            return false;
        }
        System.out.println("Single bit complement test passed");

        // Test case 2: Multiple bits
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ONE); // <101>
        System.out.println("\nTesting complement of <101>");
        System.out.println("Before: " + br.toString());
        br.complement();
        System.out.println("After:  " + br.toString());
        if (!br.toString().equals("<010>")) {
            System.out.println("Failed: Expected <010>, got " + br.toString());
            return false;
        }
        System.out.println("Multiple bits complement test passed");

        // Test case 3: All zeros
        br = new BinaryRepresentation();
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ZERO); // <000>
        System.out.println("\nTesting complement of <000>");
        System.out.println("Before: " + br.toString());
        br.complement();
        System.out.println("After:  " + br.toString());
        if (!br.toString().equals("<111>")) {
            System.out.println("Failed: Expected <111>, got " + br.toString());
            return false;
        }
        System.out.println("All zeros complement test passed");

        // Test case 4: All ones
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ONE);
        br.addLast(Bit.ONE); // <111>
        System.out.println("\nTesting complement of <111>");
        System.out.println("Before: " + br.toString());
        br.complement();
        System.out.println("After:  " + br.toString());
        if (!br.toString().equals("<000>")) {
            System.out.println("Failed: Expected <000>, got " + br.toString());
            return false;
        }
        System.out.println("All ones complement test passed");

        // Test case 5: Double complement should return to original
        br = new BinaryRepresentation();
        br.addLast(Bit.ONE);
        br.addLast(Bit.ZERO);
        br.addLast(Bit.ONE); // <101>
        String original = br.toString();
        System.out.println("\nTesting double complement of <101>");
        System.out.println("Original: " + original);
        br.complement();
        br.complement();
        System.out.println("After double complement: " + br.toString());
        if (!br.toString().equals(original)) {
            System.out.println("Failed: Double complement should return to original");
            return false;
        }
        System.out.println("Double complement test passed");

        // Test case 6: Sequential addition then complement
        br = new BinaryRepresentation();
        br.addFirst(Bit.ZERO); // <0>
        br.addFirst(Bit.ZERO); // <00>
        br.addFirst(Bit.ONE); // <100>
        System.out.println("\nTesting sequential addition then complement:");
        System.out.println("After additions: " + br.toString());
        String beforeComplement = br.toString();
        br.complement();
        System.out.println("After complement: " + br.toString());
        if (!br.toString().equals("<110>")) {
            System.out.println("Failed: Expected <110>, got " + br.toString());
            System.out.println("Original was: " + beforeComplement);
            return false;
        }
        System.out.println("Sequential addition then complement test passed");

        return true;
    }

    // Task 1.7 - Testing shift operations
    public static boolean testShiftOperations() {
        System.out.println("\nTesting Shift Operations:");
        BinaryRepresentation br = new BinaryRepresentation();
        br.addFirst(Bit.ZERO);
        br.addFirst(Bit.ONE);

        // Test shiftLeft
        br.shiftLeft();
        System.out.println("Testing shiftLeft: Current state = " + br.toString());
        if (!br.toString().equals("<010>")) {
            System.out.println("Failed: Expected <010>, got " + br.toString());
            return false;
        }
        System.out.println("shiftLeft test passed");

        // Test shiftRight
        Bit removed = br.shiftRight();
        System.out.println("Testing shiftRight: Removed " + removed + ", Current state = " + br.toString());
        return removed == Bit.ZERO && br.toString().equals("<01>");
    }

    // Task 1.8 - Testing padding
    public static boolean testPadding() {
        System.out.println("\nTesting Padding:");
        BinaryRepresentation br = new BinaryRepresentation();
        br.addFirst(Bit.ZERO);
        br.addFirst(Bit.ONE);

        // Pad with length smaller than current (should not change)
        br.padding(1);
        System.out.println("Testing padding with length 1: Current state = " + br.toString());
        if (!br.toString().equals("<01>")) {
            System.out.println("Failed: Expected <01>, got " + br.toString());
            return false;
        }
        System.out.println("padding with length 1 test passed");

        // Pad with larger length
        br.padding(4);
        System.out.println("Testing padding with length 4: Current state = " + br.toString());
        return br.toString().equals("<0001>");
    }

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Starting Binary Representation Tests");
        System.out.println("=================================");

        printTestResult("Add/Remove Operations (Task 1.1)",
                testAddRemoveOperations());
        printTestResult("ToString (Task 1.2)", testToString());
        printTestResult("Copy Constructor (Task 1.3)", testCopyConstructor());
        printTestResult("Legal Number (Task 1.4)", testIsLegalNumber());
        printTestResult("Reduced Operations (Task 1.5)", testReducedOperations());
        printTestResult("Complement (Task 1.6)", testComplement());
        printTestResult("Shift Operations (Task 1.7)", testShiftOperations());
        printTestResult("Padding (Task 1.8)", testPadding());

        System.out.println("=================================");
        System.out.println("All tests completed");
        System.out.println("=================================");
    }
}
