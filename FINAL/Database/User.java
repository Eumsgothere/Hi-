package FINAL.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import FINAL.Directories.Transactionable;

import java.util.ArrayList;

// User Class => a subclass of Account and implements Transactionable interface for adding transactions and getting transaction history
public class User extends Account implements Transactionable {
    private String transactionHistory; // Transaction history as a string for saving transaction in the file and this is privately unique for each user

    public User(String username, String password, String id, double balance, String transactionHistory) {
        super(username, password, id, balance);
        this.transactionHistory = transactionHistory;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    @Override // Overriding the addTransaction method from the Transactionable interface
    public String getTransactionHistory() {
        return transactionHistory;
    }

    @Override // Overriding the saveUser method from the Account class
    public void saveUser() {
        // Save the user data in the file database with the account ID, username, password, and balance separated by comma when the program is exiting
        String filePath = "FINAL\\Database\\hibank_user.txt";
        try (FileWriter hibank_writeUser = new FileWriter(filePath, true)) {
            hibank_writeUser.write(accountId + "," + username + "," + password + "," + balance + "\n");
        } catch (IOException hibank_e) {
            System.out.println("Error saving user data: " + hibank_e);
        }
    }

    // Using Java Collections, we can load the user data from the file database and return a list of users when the program starts up
    public static List<User> loadUser() {
        List<User> users = new ArrayList<>();

        String filePath = "FINAL\\Database\\hibank_user.txt";
        try (BufferedReader userReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] user_data = line.split(","); // Split the line by comma to get the user data
                String username = user_data[1]; // Get the username from the user data
                String password = user_data[2]; // Get the password from the user data 
                String accId = user_data[0]; // Get the account ID from the user data
                double balance = Double.parseDouble(user_data[3]);  // Get the balance from the user data
                String transactionHistory = loadTransaction(accId); // Load the transaction history for the user

                users.add(new User(username, password, accId, balance, transactionHistory));
            }
        } catch (IOException e) {
            System.out.println("Error loading user data handled: " + e);
        }
        return users;
    }

    private static String loadTransaction(String accountId) {
        StringBuilder transactionHistory = new StringBuilder();

        String filePath = "FINAL\\Database\\hibank_transactions.txt";
        try (BufferedReader transactionReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = transactionReader.readLine()) != null) {
                String[] transaction_data = line.split(",");
                if (transaction_data[0].equals(accountId)) {
                    transactionHistory.append(transaction_data[1]).append("\n");
                }
            }
        } catch (IOException hibank_e) {
            System.out.println("Error loading transactions: " + hibank_e);
        }
        return transactionHistory.toString();
    }

    String filePath = "FINAL\\Database\\hibank_transactions.txt";
    @Override
    public void addTransaction(String transaction) {
        transactionHistory += transaction + "\n";
        try (FileWriter hibank_writer = new FileWriter(filePath, true)) {
            hibank_writer.write(accountId + "," + transaction + "\n");
        } catch (IOException hibank_e) {
            System.out.println("Error saving transaction: " + hibank_e);
        }
    }
}