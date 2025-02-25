public class TestBankAccountFiltering {
    public static void main(String[] args) {
        List<BankAccount> accounts = new LinkedList<>();
        accounts.add(new BankAccount("John Doe", 1, 1000));
        accounts.add(new BankAccount("Jane Doe", 2, 2000));
        accounts.add(new BankAccount("Jim Doe", 3, 3000));
        accounts.add(new BankAccount("Jill Doe", 4, 4000));
        accounts.add(new BankAccount("Jack Doe", 5, 5000));
        List<BankAccount> result = BankAccountFiltering.getAllValidAccounts(accounts, 3000, 2, 4);
        System.out.println(result);
    }
}
