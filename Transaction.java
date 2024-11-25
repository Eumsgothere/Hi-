
import java.util.Scanner;

public class Transaction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("My Bank");

        Account account1 = new Account(101, "Alice", 5000);
        Account account2 = new Account(102, "Bob", 3000);

        bank.addAccount(account1);
        bank.addAccount(account2);

        boolean running = true;
        while (running) {
            System.out.println("==============================================================================");
            System.out.println("=== Hi? Banking System Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Display Accounts");
            System.out.println("3. Close Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Create Account
                    System.out.println("\n==============================================================================");
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter account holder name: ");
                    String accountHolder = scanner.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    if (initialDeposit >= 0) {
                        Account newAccount = new Account(accountNumber, accountHolder, initialDeposit);
                        bank.addAccount(newAccount);
                        System.out.println("Account created successfully.");
                    } else {
                        System.out.println("Initial deposit cannot be negative.");
                    }
                    break;
                case 2:
                System.out.println("\n==============================================================================");
                    bank.displayAccounts();
                    break;
                case 3:
                System.out.println("\n==============================================================================");
                System.out.print("Enter account number to close: ");
                int accountNumberToClose = scanner.nextInt();
                scanner.nextLine(); // consume newline
                bank.closeAccount(accountNumberToClose);
                System.out.print("Account Closed\n");
                break;
                case 4:
                System.out.println("\n==============================================================================");
                    System.out.print("Enter account number: ");
                    int accNum = scanner.nextInt();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    Account acc = bank.getAccount(accNum);
                    if (acc != null) {
                        acc.deposit(depositAmount);
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                System.out.println("\n==============================================================================");
                    System.out.print("Enter account number: ");
                    accNum = scanner.nextInt();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    acc = bank.getAccount(accNum);
                    if (acc != null) {
                        try {
                            acc.withdraw(withdrawalAmount);
                            System.out.println("Withdrawal successful.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 6:
                System.out.println("\n==============================================================================");
                System.out.println("              Thank you for using our system.\n");
                System.out.println("\t\t\tGroup Hi?");
                System.out.println("Naomie Feona G. Capapas\t\tElaine Bernadette Montesa");
                System.out.println("Louise Veronica L. Cunanan\tPetercen Nikolai R. Giron\n");
                System.out.println("\t\t\tGoodbye!");
                System.out.println("\n==============================================================================");
                System.out.println("Exiting the system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
