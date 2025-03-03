public class Question3 {

    public static void main(String[] args) {
        BinaryNode<Integer> root = new BinaryNode<Integer>(1);
        root.left = new BinaryNode<Integer>(4);
        root.right = new BinaryNode<Integer>(3);
        root.left.left = new BinaryNode<Integer>(4);
        root.left.right = new BinaryNode<Integer>(4);
        root.right.left = new BinaryNode<Integer>(6);
        root.right.right = new BinaryNode<Integer>(7);
        root.right.right.right = new BinaryNode<Integer>(4);
        root.right.right.right.right = new BinaryNode<Integer>(4);
        root.right.right.right.right.right = new BinaryNode<Integer>(5);
        root.right.right.right.right.right.right = new BinaryNode<Integer>(2);
        root.right.right.right.right.right.right.right = new BinaryNode<Integer>(8);
        root.right.right.right.right.right.right.right.right = new BinaryNode<Integer>(4);
        root.right.right.right.right.right.right.right.right.right = new BinaryNode<Integer>(4);
        

        System.out.println("Original tree:");
        root.printInOrder();
        System.out.println();
        System.out.println("After removing all 4s:");
        BinaryNode<Integer> root2 = root.removeAll(4);
        root2.printInOrder();
    }
}
