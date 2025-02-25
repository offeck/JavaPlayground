public class TestBank {
    public static void main(String[] args) {
        // Create a bank with unique account numbers and names
        Bank bank = new Bank();
        bank.add(new BankAccount("John Williams", 100001, 1000));
        bank.add(new BankAccount("Sarah Miller", 200002, 2000));
        bank.add(new BankAccount("Michael Chen", 300003, 3000));
        bank.add(new BankAccount("Emily Rodriguez", 400004, 4000));

        testAdd(bank);
        testDelete(bank);
        testDeposit(bank);
        testWithdraw(bank);

    }

    public static void testAdd(Bank bank) {
        // Test adding a new account with unique account number and name
        Boolean result1 = bank.add(new BankAccount("John Doe", 123456, 1000));
        System.out.println(result1 == true);
        // Test adding an account with a duplicate account number and name
        Boolean result2 = bank.add(new BankAccount("John Doe", 123456, 1000));
        System.out.println(result2 == false);
        // Test adding an account with a duplicate name
        Boolean result3 = bank.add(new BankAccount("John Doe", 123457, 1000));
        System.out.println(result3 == false);
        // Test adding an account with a duplicate account number
        Boolean result4 = bank.add(new BankAccount("Jane Doe", 123456, 1000));
        System.out.println(result4 == false);
    }

    public static void testDelete(Bank bank) {
        // Add an account to the bank
        bank.add(new BankAccount("John Doe", 123456, 1000));
        // Test deleting an account that exists by account number
        Boolean result1 = bank.delete(123456);
        System.out.println(result1 == true);
        // Test deleting an account that does not exist by account number
        Boolean result2 = bank.delete(123456);
        System.out.println(result2 == false);
        // add an account back
        bank.add(new BankAccount("John Doe", 123456, 1000));
        // Test deleting an account that exists by name
        Boolean result3 = bank.delete("John Doe");
        System.out.println(result3 == true);
        // Test deleting an account that does not exist by name
        Boolean result4 = bank.delete("John Doe");
        System.out.println(result4 == false);
    }

    public static void testDeposit(Bank bank) {
        // Delete accounts with matching account number and name
        bank.delete(123456);
        bank.delete("John Doe");
        // Create a new account
        BankAccount account = new BankAccount("John Doe", 123456, 1000);
        // Add a new account to the bank
        bank.add(account);
        // Test depositing money into an account
        Boolean result1 = bank.depositMoney(1000, 123456);
        System.out.println(result1 == true && account.getBalance() == 2000);
        // Test depositing money into an account that does not exist
        bank.delete(123456);
        Boolean result2 = bank.depositMoney(1000, 123456);
        System.out.println(result2 == false);
    }

    public static void testWithdraw(Bank bank) {
        // Delete accounts with matching account number and name
        bank.delete(123456);
        bank.delete("John Doe");
        // Create a new account
        BankAccount account = new BankAccount("John Doe", 123456, 1000);
        // Add a new account to the bank
        bank.add(account);
        // Test withdrawing money from an account
        Boolean result1 = bank.withdrawMoney(1000, 123456);
        System.out.println(result1 == true && account.getBalance() == 0);
        // Test withdrawing money from an empty account
        Boolean result2 = bank.withdrawMoney(1000, 123456);
        System.out.println(result2 == false);
        // Test withdrawing money from an account that does not exist
        bank.delete(123456);
        Boolean result3 = bank.withdrawMoney(1000, 123456);
        System.out.println(result3 == false);
    }
}
