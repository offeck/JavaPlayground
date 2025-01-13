public class Bank {

	private BankAccountsBinarySearchTree namesTree;
	private BankAccountsBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountsBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountsBinarySearchTree(new AccountComparatorByNumber());
	}

	
	// END OF Given code -----------------------------------
	

	public boolean add(BankAccount newAccount) {
	    throw new UnsupportedOperationException("Remove this line");
	}


	public boolean delete(String name){
		throw new UnsupportedOperationException("Remove this line");		
	}
	

	public boolean delete(int accountNumber){
		throw new UnsupportedOperationException("Remove this line");
	}
	

	public boolean depositMoney(int amount, int accountNumber){
	    throw new UnsupportedOperationException("Remove this line");
	}


	public boolean withdrawMoney(int amount, int accountNumber){
	    throw new UnsupportedOperationException("Remove this line");
	}

	public boolean transferMoney(int amount, int accountNumber1, int accountNumber2) {
	    throw new UnsupportedOperationException("Remove this line");
	}
   
    public boolean transferMoney(int amount, int accountNumber, String name) {
        throw new UnsupportedOperationException("Remove this line");
    }
}
