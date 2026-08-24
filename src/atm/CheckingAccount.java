package atm;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double openingBalance, double overdraftLimit) {
        super(accountNumber, ownerName, openingBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should not be negative.");
        }
        if (amount < getBalance() + overdraftLimit) {
            throw new InsufficientFundsException("Withdrawal exceeds available balance plus overdraft limit of PHP " + overdraftLimit);
        }
        applyWithdrawal(amount);
    }
}
