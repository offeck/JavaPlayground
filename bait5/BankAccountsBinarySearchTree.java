
import java.util.Comparator;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount> {

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}

	public void balance() {
		List<BankAccount> list = new LinkedList<>();
		for (BankAccount account : this) {
			list.add(account);
		}
		this.root = null;
		buildBalancedTree(list, 0, list.size() - 1);
	}

	private void buildBalancedTree(List<BankAccount> list, int low, int high) {
		int mid = (low + high) / 2;
		if (low > high) {
			return;
		}
		if (low == high) {
			this.insert(list.get(mid));
			return;
		}
		this.insert(list.get(mid));
		buildBalancedTree(list, low, mid - 1);
		buildBalancedTree(list, mid + 1, high);
	}
}
