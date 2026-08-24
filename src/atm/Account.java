package atm;

public abstract class Account {
        private String accountNumber;
        private String ownerName;
        private double balance;

        public Account(String accountNumber, String ownerName, double openingBalance) {
            if (ownerName == null || ownerName.isBlank()) {
                throw new IllegalArgumentException("Owner name is required.");
            }
            if (accountNumber == null || accountNumber.isBlank()) {
                throw new IllegalArgumentException("Account number is required.");
            }
            if (openingBalance < 0) {
                throw new IllegalArgumentException("Opening balance should not be negative.");
            }

            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = openingBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount should not be negative.");
            }
            balance += amount;
        }

        public void withdraw(double amount) throws InsufficientFundsException {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount should not be negative.");
            }
            if (amount > balance) {
                throw new InsufficientFundsException("Amount should not exceed remaining balance.");
            }
            balance -= amount;
        }

        public abstract String getAccountType();

        protected void applyWithdrawal(double amount) {
            balance -= amount;
        }

        @Override
        public String toString() {
            return getAccountType() + " " + accountNumber + " (" + ownerName + ")";
        }
}
