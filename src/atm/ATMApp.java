package atm;

import java.util.Scanner;

public class ATMApp {
    public static void main(String[] args) throws InsufficientFundsException {
        Scanner input = new Scanner(System.in);

        ATMService atm = new ATMService();

        Account account = new SavingsAccount("SA-1001", "Juan Dela Cruz", 5000.0, 0.02);

        boolean running = true;
        while(running) {
            printHeader(account);
            printMenu();
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        System.out.printf("Balance: PHP %.2f%n", account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = Double.parseDouble(input.nextLine());
                        atm.deposit(account, depositAmount);
                        System.out.println("Deposit successful.");
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = Double.parseDouble(input.nextLine());
                        account.withdraw(withdrawAmount);
                        System.out.println("Withdrawal successful.");
                        break;
                    case 4:
                        double total = atm.depositAll(account, 100, 250.5, 300);
                        System.out.printf("Total deposited: PHP %.2f%n", total);
                        break;
                    case 5:
                        System.out.printf("Balance before: PHP %.2f%n", account.getBalance());
                        atm.tryToReplace(account);
                        atm.addBonus(account, 50);
                        System.out.printf("Balance after: PHP %.2f%n", account.getBalance());
                        break;
                    case 0:
                        running = false;
                    default:
                        System.out.println("Invalid option. Choose 0 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please type a number, not letters.");
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("=========================================");
            }
        }
        input.close();
        System.out.println("Thank you for using Liceo ATM!");
    }

    private static void printHeader(Account account) {
        System.out.println("=============================");
        System.out.println("      LICEO ATM MACHINE      ");
        System.out.println("=============================");
        System.out.println("Account No: " + account.getAccountNumber());
        System.out.println("Owner: " + account.getOwnerName());
        System.out.println("Type: " + account.getAccountType());
        System.out.printf("Balance: PHP %.2f%n", account.getBalance());
    }

    private static void printMenu() {
        System.out.println("[1] Check Balance");
        System.out.println("[2] Deposit");
        System.out.println("[3] Withdraw");
        System.out.println("[4] Deposit Multiple Amounts");
        System.out.println("[5] Pass-by-Value Demo");
        System.out.println("[0] Exit");
    }
}
