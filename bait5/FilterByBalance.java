
public class FilterByBalance implements Filter<BankAccount> {
	private int balanceThreshold;

	public FilterByBalance(int balanceThreshold) {
		this.balanceThreshold = balanceThreshold;
	}
	
	public boolean accept(BankAccount element) {
		return element.getBalance() >= this.balanceThreshold;
	}
}
