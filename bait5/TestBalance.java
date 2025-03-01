import java.util.Comparator;

public class TestBalance {
    public static void main(String[] args) {
        Comparator<BankAccount> comparator = new AccountComparatorByNumber();
        BankAccountsBinarySearchTree tree = new BankAccountsBinarySearchTree(comparator);
        tree.insert(new BankAccount("John Doe", 1, 0));
        tree.insert(new BankAccount("Jane Doe", 2, 0));
        tree.insert(new BankAccount("Jim Doe", 3, 0));
        tree.insert(new BankAccount("Jill Doe", 4, 0));
        tree.insert(new BankAccount("Jack Doe", 5, 0));
        tree.insert(new BankAccount("Jill Doe", 6, 0));
        tree.insert(new BankAccount("Jill Doe", 7, 0));
        tree.insert(new BankAccount("Jill Doe", 8, 0));
        tree.insert(new BankAccount("Jill Doe", 9, 0));
        tree.insert(new BankAccount("Jill Doe", 10, 0));
        System.out.println(tree.toString());
        System.out.println(tree.height());
        tree.balance();
        System.out.println(tree.toString());
        System.out.println(tree.height());
    }

}
