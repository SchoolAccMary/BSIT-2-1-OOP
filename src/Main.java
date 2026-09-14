import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();

        gateway.add(new GCashPayment(1001, "Ana", 1500.00, "0917-555-0134"));
        gateway.add(new MayaPayment(1002, "Jerome", 899.50, "jerome@liceo.edu.ph"));
        gateway.add(new CashPayment(1003, "Liza", 250.00));

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=======================================");
            System.out.println("           LICEO PAY");
            System.out.println("=======================================");
            System.out.println("1. Make a payment");
            System.out.println("2. Show all receipts");
            System.out.println("3. Find a payment by ID");
            System.out.println("4. Show total collected");
            System.out.println("5. Refund all refundable payments");
            System.out.println("6. Show service fees");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> makePayment(scanner, gateway);
                case "2" -> {
                    System.out.println("All receipts:");
                    gateway.processAll();
                }
                case "3" -> findPayment(scanner, gateway);
                case "4" -> System.out.printf("Total collected: PHP %.2f%n", gateway.totalCollected());
                case "5" -> {
                    System.out.println("Refunding every payment that can be refunded:");
                    gateway.refundAll();
                }
                case "6" -> {
                    System.out.println("Service fees (the two serviceFee methods):");
                    gateway.showServiceFees();
                }
                case "0" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Not a valid option. Try again.");
            }
        }

        scanner.close();
    }

    private static void makePayment(Scanner scanner, PaymentGateway gateway) {
        System.out.print("Provider (gcash/maya/cash): ");
        String provider = scanner.nextLine().trim().toLowerCase();

        System.out.print("Payer name: ");
        String name = scanner.nextLine().trim();

        double amount;
        while (true) {
            System.out.print("Amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        int newId = 1000 + gateway.count() + 1;
        Payment payment;

        switch (provider) {
            case "gcash" -> {
                System.out.print("Mobile number: ");
                String mobile = scanner.nextLine().trim();
                payment = new GCashPayment(newId, name, amount, mobile);
            }
            case "maya" -> {
                System.out.print("Email: ");
                String email = scanner.nextLine().trim();
                payment = new MayaPayment(newId, name, amount, email);
            }
            case "cash" -> payment = new CashPayment(newId, name, amount);
            default -> {
                System.out.println("Unknown provider. Payment not added.");
                return;
            }
        }
        gateway.add(payment);
        payment.printReceipt();
        payment.printThankYou();
    }

    private static void findPayment(Scanner scanner, PaymentGateway gateway) {
        System.out.print("Enter ID to search: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Payment found = gateway.findById(id);
            if (found == null) {
                System.out.println("No payment found with ID " + id + ".");
            } else {
                System.out.println("Payment found:");
                found.printReceipt();
            }
        } catch (NumberFormatException e) {
            System.out.println("That's not a valid ID number.");
        }
    }
}