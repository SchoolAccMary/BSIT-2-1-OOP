import java.util.Scanner;
public class ATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
// One account to use with the ATM (name and starting balance).
        Account account = new Account("Juan Dela Cruz", 1000.0, "1234");

        System.out.print("Enter your 4-digit PIN: ");
        String enteredPin = input.next();
        if (!account.checkPin(enteredPin)) {
            System.out.println("Incorrect PIN. Access denied.");
            input.close();
            return;
        }
        System.out.println("PIN accepted.");

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("===== WELCOME TO CLI ATM =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
// TODO 7: print the owner and balance using the getters.
                    System.out.println("Account holder: " + account.getOwner());
                    System.out.println("Account balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double d = input.nextDouble();
// TODO 8: call account.deposit(d);
                    account.deposit(d);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double w = input.nextDouble();
// TODO 9: call account.withdraw(w);
                    account.withdraw(w);
                    break;
                case 4:
                    account.printTransaction();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using CLI ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        input.close();
    }
}