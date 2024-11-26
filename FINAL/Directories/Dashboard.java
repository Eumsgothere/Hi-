package FINAL.Directories;

import java.util.*;

import FINAL.Assets.Design;
import FINAL.Assets.Screen;
import FINAL.Database.User;

// final int x => the x-coordinate of the box
// final int y => the y-coordinate of the box
// final int width => the width of the box
// final int height => the height of the box
// final String content => the content of the box
// final String textColor => the text color of the box
// final String bgColor => the background color of the box

// Dashboard class => This class contains the methods for the dashboard functionalities such as deposit, withdraw, account details, transfer, and currency converter.
public class Dashboard {
    private static List<User> users = User.loadUser(); // load the user data from the file into the users list array, impelementation is in the User class @line 64 col 40
    // private static User currentUser;

    // deposit funds to the current account
    public static void deposit(Scanner scanner, User user) {
        Screen.clearScreen();
        Design.drawBox(
            28, 5, 69, 4, 
            "ACCOUNT DEPOSIT", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            28, 10, 69, 2, 
            "Enter amount to deposit (minimum of 1000): ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        double amount = scanner.nextDouble();
    
        if (amount < 1000) {
            Design.drawBox(
                28, 13, 69, 2, 
                "Minimum deposit amount is 1000.", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                28, 16, 0, 0, 
                "", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );
            Screen.screenPause();
            return;
        }
    
        user.deposit(amount);
        user.addTransaction("Deposited: " + amount);
        
        Design.drawBox(
            28, 13, 69, 2, 
            "Deposit successful. New balance: " + user.getBalance(), 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            28, 16, 0, 0, 
            "", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        Screen.screenPause();
    }

    public static void withdraw(Scanner scanner, User user) {
        Screen.clearScreen();
        Design.drawBox(
            28, 5, 69, 4, 
            "ACCOUNT WITHDRAW", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            28, 10, 69, 2, 
            "Enter amount to withdraw: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        double amount = scanner.nextDouble();
    
        if (amount > user.getBalance()) {
            Design.drawBox(
                28, 13, 69, 2, 
                "Insufficient balance!", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 16, 0, 0, 
                "", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );
            Screen.screenPause();
            return;
        }
    
        user.withdraw(amount);
        user.addTransaction("Withdrawn: " + amount);

        Design.drawBox(
            28, 13, 69, 2, 
            "Withdrawal successful. New balance: " + user.getBalance(), 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
             33, 16, 0, 0, 
             "", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        Screen.screenPause();
    }

    public static void accountDetails(User user) {
        Screen.clearScreen();
        Design.drawBox(
            33, 5, 50, 4, 
            user.getUsername() + "'s ACCOUNT DETAILS", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 10, 50, 2, 
            "Account ID: " + user.getAccountId(), 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 13, 50, 2, 
            "Username: " + user.getUsername(), 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 16, 50, 2, 
            "Balance: " + user.getBalance(), 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 19, 0, 0, 
            "", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        Screen.screenPause();
    } 

    // Transfer funds to another account (depracated, unused)
    public static void transfer(Scanner scanner, User user) {
        Screen.clearScreen();
        Design.drawBox(33, 5, 50, 3, "Hi?Bank: Transfer Funds ", Design.GREEN_TEXT, Design.BLACK_BG);
        Design.drawBox(33, 7, 50, 2, "Enter recipient's account ID: ", Design.GREEN_TEXT, Design.BLACK_BG);
        String recipientAccId = scanner.nextLine().trim(); // Ensure this reads the entire line correct
        scanner.nextLine();  // This consumes the newline 

        Design.drawBox(33, 9, 50, 2, "Enter amount to transfer: ", Design.GREEN_TEXT, Design.BLACK_BG);
        double amount = scanner.nextDouble(); 

    
    if (user.getBalance() < amount) {
        Design.drawBox(33, 13, 0, 0, "Insufficient Balance", Design.GREEN_TEXT, Design.BLACK_BG);
        Screen.screenPause();
        return;
    }

    if (user.getAccountId().equals(recipientAccId)) {
        Design.drawBox(33, 13, 0, 0, "Cannot transfer to own account!", Design.GREEN_TEXT, Design.BLACK_BG);
        Screen.screenPause();
        return;
    } else {
        User recipient = findBank_User(recipientAccId);

        if (recipient == null) {
            Design.drawBox(33, 13, 0, 0, "Recipient not found!", Design.GREEN_TEXT, Design.BLACK_BG);
            Screen.screenPause();
            return;
        }

        user.withdraw(amount);
        user.addTransaction("Transferred " + amount + " to " + recipientAccId);
        Design.drawBox(33, 13, 0, 0, "Transferred " + amount + " to " + recipientAccId, Design.GREEN_TEXT, Design.BLACK_BG);

        recipient.deposit(amount);
        recipient.addTransaction("Received " + amount + " from " + user.getAccountId());
        Design.drawBox(33, 13, 0, 0, "Received " + amount + " from " + user.getAccountId(), Design.GREEN_TEXT, Design.BLACK_BG);

        System.out.println("Transfer successful.");
        Screen.screenPause();
        }
    }

    public static User findBank_User(String accId) {
        if (accId == null || accId.trim().isEmpty()) {
            System.out.println("Invalid Account ID provided for search.");
            return null;
        }
    
        for (User user : users) {
            String storedAccountId = user.getAccountId().trim();
            String inputAccountId = accId.trim();
    
            // for debugging purposes
            System.out.println("Searching for recipient: [" + inputAccountId + "]");
            System.out.println("Checking against stored account: [" + storedAccountId + "]");
    
            if (storedAccountId.equalsIgnoreCase(inputAccountId)) {
                return user;
            }
        }
        return null; // Return null if no match is found
    }
    
    // this method is used to convert currency based on the user's choice and is using java collection's hash map to store the currency rates in pair values.
    public static void currencyConverter(Scanner hiScanner) {
        // we mapped <string, double> to store the currency rates with a pair of string and double values as we can see from the .put method
        Map<String, Double> currencyRates = new HashMap<>();

        // currency rates are based on the current rates as of November 2024 (for the sake of demonstration)
        currencyRates.put("PHP to USD", 0.017);
        currencyRates.put("USD to PHP", 59.03);
        currencyRates.put("USD to EURO", 0.95);
        currencyRates.put("EURO to USD", 1.05);
        currencyRates.put("YEN to PHP", 0.38);
        currencyRates.put("PHP to YEN", 2.61);
        
        Screen.clearScreen();
        Design.drawBox(
            33, 5, 50, 4, 
            "Hi?Bank: Currency Converter", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 10, 50, 2, 
            "[1] PHP to USD", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 13, 50, 2, 
            "[2] USD to PHP", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 16, 50, 2, 
            "[3] USD to EURO", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 19, 50, 2, 
            "[4] EURO to USD", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 22, 50, 2, 
            "[5] YEN to PHP", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 25, 50, 2, 
            "[6] PHP to YEN", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 28, 50, 2, 
            "Choose conversion: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        int choice = hiScanner.nextInt();
        hiScanner.nextLine();

        Design.drawBox(
            33, 25, 50, 2, 
            "Enter amount: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        double amount = hiScanner.nextDouble();
        double convertedAmount = 0;

        switch (choice) {
            // provided six options for currency conversion (for the sake of demonstration)
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
            case 5: 
                convertedAmount = amount * currencyRates.get("YEN to PHP");
                break;
            case 6:
                convertedAmount = amount * currencyRates.get("PHP to YEN");
                break;
            default:
                Design.drawBox(
                    33, 28, 50, 2, 
                    "Invalid option.", 
                    Design.GREEN_TEXT, 
                    Design.BLACK_BG
                );

                Design.drawBox(
                    33, 30, 0, 0, 
                    "", 
                    Design.GREEN_TEXT, 
                    Design.BLACK_BG
                );
                Screen.screenPause();
                return;
        }

        Design.drawBox(
            33, 28, 50, 2, 
            "Converted amount: " + convertedAmount, 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 33, 0, 0, 
            "", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        Screen.screenPause();
    }
}