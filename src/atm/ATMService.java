package atm;

public class ATMService {
    public void deposit(Account account, double amount) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2fn", amount);
    }

    public void deposit(Account account, double amount, String note) {
        account.deposit(amount);
        System.out.println(note);
    }

    public double depositAll(Account account, double... amounts) {
        double total = 0;
        for (double amt : amounts) {
            account.deposit(amt);
            total += amt;
        }
        return total;
    }

    public void tryToReplace(Account account) {
        account = new SavingsAccount("XX-000", "Ghost Account", 0, 0);
        System.out.println("Inside the method: " + account);
        // it points to the original account because there is only 1 parameter passed in the tryToReplace method
    }

    public void addBonus(Account account, double bonus) {
       account.deposit(bonus);
       // because it declared its own parameter by bonus
    }

    public void transfer(Account from, Account to, double amount) throws InsufficientFundsException {
        from.withdraw(100.0);
        to.deposit(1000.0);
    }
}