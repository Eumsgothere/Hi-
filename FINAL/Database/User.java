package FINAL.Database;

import java.io.*;
import java.util.*;
import FINAL.Directories.Transactionable;

public class User extends Account implements Transactionable {
    private String transactionHistory;

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

    @Override
    public String getTransactionHistory() {
        return transactionHistory;
    }

    public List<String> getTransactionHistoryList() {
        return Arrays.asList(transactionHistory.split("\n"));
    }

    @Override
    public void saveUser() {
        String filePath = "FINAL\\Database\\hibank_user.txt";
        List<User> users = loadUser();
        boolean userExists = false;
    
        try (FileWriter hibank_writeUser = new FileWriter(filePath)) {
            for (User user : users) {
                if (user.getAccountId().equals(this.accountId)) {
                    hibank_writeUser.write(this.accountId + "," + this.username + "," + this.password + "," + this.balance + "\n");
                    userExists = true;
                } else {
                    hibank_writeUser.write(user.getAccountId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getBalance() + "\n");
                }
            }
            if (!userExists) {
                hibank_writeUser.write(this.accountId + "," + this.username + "," + this.password + "," + this.balance + "\n");
            }
        } catch (IOException hibank_e) {
            System.out.println("Error saving user data: " + hibank_e);
        }
    }

    public static List<User> loadUser() {
        List<User> users = new ArrayList<>();
        String filePath = "FINAL\\Database\\hibank_user.txt";
        try (BufferedReader userReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] user_data = line.split(",");
                String username = user_data[1];
                String password = user_data[2];
                String accId = user_data[0];
                double balance = Double.parseDouble(user_data[3]);
                String transactionHistory = loadTransaction(accId);
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

    public void deposit(double amount) {
        this.balance += amount;
        saveUser();
    }
   
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            saveUser();
            return true;
        }
        return false;
    }
}