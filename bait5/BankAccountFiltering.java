

public class BankAccountFiltering {
    public static List<BankAccount> getAllValidAccounts(Iterable<BankAccount> accounts,
            int requiredBalance,
            int minAccountNumber,
            int maxAccountNumber) {
        List<Filter<BankAccount>> filters = new LinkedList<>();
        filters.add(new FilterByBalance(requiredBalance));
        filters.add(new FilterByAccountNumber(minAccountNumber, maxAccountNumber));
        FilterIterator<BankAccount> filter = new FilterIterator<>(accounts, filters);
        List<BankAccount> result = new LinkedList<>();
        while (filter.hasNext()) {
            result.add(filter.next());
        }
        return result;
    }
}
