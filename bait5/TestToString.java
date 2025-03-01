public class TestToString {

  public static void main(String[] args) {
    testEmptyTree();
    testSingleNode();
    testLeftChildOnly();
    testRightChildOnly();
    testBothChildren();
    testMultipleLevels();
    testComplexTree();
  }

  private static void printTestResult(String testName, boolean passed) {
    System.out.println("Test: " + testName);
    System.out.println("Result: " + (passed ? "PASSED" : "FAILED"));
    System.out.println("------------------------");
  }

  private static void testEmptyTree() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    String expected = "tree";
    String result = tree.toString();
    printTestResult("Empty Tree", result.equals(expected));
  }

  private static void testSingleNode() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.root = new BinaryNode<>(1);
    String expected = "tree(1)";
    String result = tree.toString();
    printTestResult("Single Node", result.equals(expected));
  }

  private static void testLeftChildOnly() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.root = new BinaryNode<>(1);
    tree.root.left = new BinaryNode<>(2);
    String expected = "tree((2),1)";
    String result = tree.toString();
    printTestResult("Left Child Only", result.equals(expected));
  }

  private static void testRightChildOnly() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.root = new BinaryNode<>(1);
    tree.root.right = new BinaryNode<>(3);
    String expected = "tree(1,(3))";
    String result = tree.toString();
    printTestResult("Right Child Only", result.equals(expected));
  }

  private static void testBothChildren() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.root = new BinaryNode<>(1);
    tree.root.left = new BinaryNode<>(2);
    tree.root.right = new BinaryNode<>(3);
    String expected = "tree((2),1,(3))";
    String result = tree.toString();
    printTestResult("Both Children", result.equals(expected));
  }

  private static void testMultipleLevels() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    tree.root = new BinaryNode<>(1);
    tree.root.left = new BinaryNode<>(2);
    tree.root.right = new BinaryNode<>(3);
    tree.root.left.left = new BinaryNode<>(4);
    tree.root.right.right = new BinaryNode<>(5);
    String expected = "tree(((4),2),1,(3,(5)))";
    String result = tree.toString();
    printTestResult("Multiple Levels", result.equals(expected));
  }

  private static void testComplexTree() {
    BinaryTree<Integer> tree = new BinaryTree<>();
    // Create a complex tree structure
    tree.root = new BinaryNode<>(1);
    tree.root.left = new BinaryNode<>(2);
    tree.root.right = new BinaryNode<>(3);
    tree.root.left.right = new BinaryNode<>(4);
    tree.root.right.left = new BinaryNode<>(5);
    tree.root.right.right = new BinaryNode<>(6);
    tree.root.left.right.left = new BinaryNode<>(7);

    String expected = "tree((2,((7),4)),1,((5),3,(6)))";
    String result = tree.toString();
    printTestResult("Complex Tree", result.equals(expected));
  }
}
