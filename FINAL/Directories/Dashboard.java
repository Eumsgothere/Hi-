package FINAL.Directories;

import java.util.*;

import FINAL.Database.User;

public class Dashboard {
    private static List<User> users = User.loadUser();
    private static User currentUser;

    public static void deposit(Scanner scanner, User user) {
        System.out.println("Enter amount to deposit (minimum of 1000): ");
        double amount = scanner.nextDouble();

        if (amount < 1000) {
            System.out.println("Minimum deposit amount is 1000.");
            return;
        }

        user.deposit(amount);
        user.addTransaction("Deposited: " + amount);
        System.out.println("Deposit successful. New balance: " + user.getBalance());
    }

    public static void withdraw(Scanner scanner, User user) {
        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount > user.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        user.withdraw(amount);
        user.addTransaction("Withdrawn: " + amount); 
        System.out.println("Withdrawal successful. New balance: " + user.getBalance());
    }

    public static void accountDetails(User user) {
        System.out.println("Account ID: " + user.getAccountId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Balance: " + user.getBalance());
    }

    public static void transfer(Scanner scanner, User user) {
        System.out.println("Enter recipient's account ID: ");
        String recipientAccId = scanner.nextLine();
        System.out.println("Enter amount to transfer: ");

        double amount = scanner.nextDouble();

        if (currentUser.getBalance() < amount) {
            System.out.println("Insufficient balance.");
            return;
        }

        if (currentUser.getAccountId().equals(recipientAccId)) {
            System.out.println("Cannot transfer to own account.");
            return;
        } else {
            User recipient = findBank_User(recipientAccId);

            if (recipient == null) {
                System.out.println("Recipient not found.");
                return;
            }

            currentUser.withdraw(amount);
            currentUser.addTransaction("Transferred " + amount + " to " + recipientAccId);

            recipient.deposit(amount);
            recipient.addTransaction("Received " + amount + " from " + currentUser.getAccountId());

            System.out.println("Transfer successful.");
        }
    }

    public static User findBank_User(String accId) {
        for (User user : users) {
            if (user.getAccountId().equals(accId)) {
                return user;
            }
        }
        return null;
    }

    public static void currencyConverter(Scanner hiScanner) {
        Map<String, Double> currencyRates = new HashMap<>();

        currencyRates.put("PHP to USD", 50.0);
        currencyRates.put("USD to PHP", 1 / 50.0);
        currencyRates.put("USD to EURO", 0.94);
        currencyRates.put("EURO to USD", 1.06);

        System.out.println("Choose conversion: ");
        System.out.println("[1] PHP to USD");
        System.out.println("[2] USD to PHP");
        System.out.println("[3] USD to EURO");
        System.out.println("[4] EURO to USD");

        int choice = hiScanner.nextInt();
        hiScanner.nextLine();

        System.out.println("Enter amount: ");
        double amount = hiScanner.nextDouble();

        double convertedAmount = 0;

        switch (choice) {
            case 1:
                convertedAmount = amount * currencyRates.get("PHP to USD");
                break;
            case 2:
                convertedAmount = amount * currencyRates.get("USD to PHP");
                break;
            case 3:
                convertedAmount = amount * currencyRates.get("USD to EURO");
                break;
            case 4:
                convertedAmount = amount * currencyRates.get("EURO to USD");
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }
        System.out.println("Converted amount: " + convertedAmount);
    }
}
