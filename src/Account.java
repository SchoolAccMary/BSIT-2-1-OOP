import java.util.ArrayList;
import java.util.List;

public class Account {
    // Fields are PRIVATE — no one outside can touch them directly.
    private String owner;
    private double balance;

    private String pin;

    private List<String> transactionHistory;
    // Constructor: runs when the account is created.
    public Account(String owner, double openingBalance, String pin) {
        this.owner = owner;
// TODO 1: only accept an opening balance of 0 or more.
// If it is negative, set the balance to 0 instead.
        if (openingBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = openingBalance;
        }
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add(String.format("Account opened with balance: %.2f", this.balance));
    }
// TODO 2: add a getter named getOwner() that RETURNS the owner.
    public String getOwner() {
        return owner;
    }
    // TODO 3: add a getter named getBalance() that RETURNS the balance.
    public double getBalance() {
        return balance;
    }

    public boolean checkPin(String attempt) {
        return pin.equals(attempt);
    }

    public void deposit(double amount) {
// TODO 4: if amount &lt;= 0, print &quot;Invalid amount.&quot; and return.
// Otherwise add amount to balance and print the new balance.
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount + ". New balance: " + balance);
        transactionHistory.add(String.format("Deposited: " + amount + ". Balance :" + balance));
    }
    public void withdraw(double amount) {
// TODO 5: if amount &lt;= 0, print &quot;Invalid amount.&quot; and return.
        if (amount <= 0) {
            System.out.println("Invalid amount");
            return;
        }
// TODO 6: if amount &gt; balance, print &quot;Insufficient funds.&quot; and return.
// Otherwise subtract amount from balance and print the new balance.
        if (amount > 0) {
            System.out.println("Insufficient funds.");
            transactionHistory.add(String.format("Withdraw failed. "+ amount + " requested, insufficient funds."));
            return;
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount + ". New balance: " + balance);
        transactionHistory.add(String.format("Withdraw (balance: )", amount, balance));
    }

    public void printTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("=== Transaction History ===");
        for (int i = 0; i < transactionHistory.size(); i++) {
            System.out.println((i + 1) + "." + transactionHistory.get(i));
        }
        System.out.println("===========================");
    }
}