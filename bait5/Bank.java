public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;

	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	// END OF Given code -----------------------------------

	/**
	 * Adds a new bank account to the bank.
	 * The account is added to both name-based and account number-based trees.
	 * 
	 * @param newAccount The bank account to add
	 * @return true if the account was successfully added, false if an account with same name or number already exists
	 */
	public boolean add(BankAccount newAccount) {
		if (namesTree.contains(newAccount) || accountNumbersTree.contains(newAccount)) {
			return false;
		}
		namesTree.insert(newAccount);
		accountNumbersTree.insert(newAccount);
		namesTree.balance();
		accountNumbersTree.balance();
		return true;
	}

	/**
	 * Deletes an account from the bank by account holder's name.
	 * 
	 * @param name The name of the account holder
	 * @return true if the account was found and deleted, false otherwise
	 */
	public boolean delete(String name) {
		BankAccount account = findAccountByName(name);
		if (account == null) {
			return false;
		}
		namesTree.remove(account);
		accountNumbersTree.remove(account);
		namesTree.balance();
		accountNumbersTree.balance();
		return true;
	}

	/**
	 * Deletes an account from the bank by account number.
	 * 
	 * @param accountNumber The account number to delete
	 * @return true if the account was found and deleted, false otherwise
	 */
	public boolean delete(int accountNumber) {
		BankAccount account = findAccountByNumber(accountNumber);
		if (account == null) {
			return false;
		}
		accountNumbersTree.remove(account);
		namesTree.remove(account);
		accountNumbersTree.balance();
		namesTree.balance();
		return true;
	}

	/**
	 * Deposits money into an account identified by account number.
	 * 
	 * @param amount The amount to deposit
	 * @param accountNumber The account number to deposit to
	 * @return true if deposit was successful, false if account not found
	 */
	public boolean depositMoney(int amount, int accountNumber) {
		BankAccount account = findAccountByNumber(accountNumber);
		if (account == null) {
			return false;
		}
		account.depositMoney(amount);
		return true;
	}

	/**
	 * Deposits money into an account identified by account holder's name.
	 * 
	 * @param amount The amount to deposit
	 * @param name The name of the account holder
	 * @return true if deposit was successful, false if account not found
	 */
	public boolean depositMoney(int amount, String name) {
		BankAccount account = findAccountByName(name);
		if (account == null) {
			return false;
		}
		account.depositMoney(amount);
		return true;
	}

	/**
	 * Withdraws money from an account identified by account number.
	 * 
	 * @param amount The amount to withdraw
	 * @param accountNumber The account number to withdraw from
	 * @return true if withdrawal was successful, false if account not found or insufficient funds
	 */
	public boolean withdrawMoney(int amount, int accountNumber) {
		BankAccount account = findAccountByNumber(accountNumber);
		if (account == null) {
			return false;
		}
		return account.withdrawMoney(amount);
	}

	/**
	 * Transfers money between two accounts identified by account numbers.
	 * 
	 * @param amount The amount to transfer
	 * @param accountNumber1 The source account number
	 * @param accountNumber2 The destination account number
	 * @return true if transfer was successful, false if either account not found or insufficient funds
	 */
	public boolean transferMoney(int amount, int accountNumber1, int accountNumber2) {
		if (!withdrawMoney(amount, accountNumber1)) {
			return false;
		}
		depositMoney(amount, accountNumber2);
		return true;
	}

	/**
	 * Transfers money from an account identified by number to an account identified by name.
	 * 
	 * @param amount The amount to transfer
	 * @param accountNumber The source account number
	 * @param name The destination account holder's name
	 * @return true if transfer was successful, false if either account not found or insufficient funds
	 */
	public boolean transferMoney(int amount, int accountNumber, String name) {
		if (!withdrawMoney(amount, accountNumber)) {
			return false;
		}
		depositMoney(amount, name);
		return true;
	}

	/**
	 * Finds a bank account by account holder's name.
	 * 
	 * @param name The name to search for
	 * @return The found BankAccount, or null if not found
	 */
	private BankAccount findAccountByName(String name) {
		return namesTree.findData(new BankAccount(name, 1, 0));
	}

	/**
	 * Finds a bank account by account number.
	 * 
	 * @param accountNumber The account number to search for
	 * @return The found BankAccount, or null if not found
	 */
	private BankAccount findAccountByNumber(int accountNumber) {
		return accountNumbersTree.findData(new BankAccount("John Doe", accountNumber, 0));
	}
}
