
public class FilterByAccountNumber implements Filter<BankAccount> {
	private int minAccountNum;
	private int maxAccountNum;

	public FilterByAccountNumber(int minAccountNum, int maxAccountNum) {
		this.minAccountNum = minAccountNum;
		this.maxAccountNum = maxAccountNum;
	}

	public boolean accept(BankAccount element) {
		return element.getAccountNumber() >= this.minAccountNum && element.getAccountNumber() <= this.maxAccountNum;
	}
}
