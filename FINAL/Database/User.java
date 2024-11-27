package FINAL.Database;

import java.io.*;
import java.util.*;
import FINAL.Directories.Transactionable;

// User Class => class inheritance from the superclass Account with interface implementation of Transactionable. 
// With transactionable interface, the User class is forced to implement the methods that are declared in the interface.
public class User extends Account implements Transactionable {
    private String transactionHistory;

    public User () { //Unused constructor for generic referencing
        super();
    }

    /**
     * User Constructor => This constructor initializes the username, password, accountId, balance, and transactionHistory of the User class.
     * @param username => The username of the account.
     * @param password => The password of the account.
     * @param id => The accountId of the account.
     * @param balance => The balance of the account. 
     * @param transactionHistory => The transaction history of the account.
     */
    public User(String username, String password, String id, double balance, String transactionHistory) {
        super(username, password, id, balance); // inherits the properties of the superclass Account
        this.transactionHistory = transactionHistory; // initializes the transaction history of the account
    }

    // Getters => These methods return the properties of the User class. (encapsulation)
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

    @Override // forced implementation of the getTransactionHistory method from the Transactionable interface
    public String getTransactionHistory() {
        return transactionHistory;
    }

    // getTransactionHistoryList => We declared this method to return the transaction history of the account as a list (loading it from the hibank_transactions.txt file) so that we can display it in the dashboard.
    public List<String> getTransactionHistoryList() {
        return Arrays.asList(transactionHistory.split("\n")); // registry expression to split the transaction history by line
    }
    
    // saveUser => This method saves the user data to the hibank_user.txt file so that we can load it back when we re-open the system and able to input saved data.
    public void saveUser() {
        String filePath = "FINAL\\Database\\hibank_user.txt";
        List<User> users = loadUser(); // load the user data from the file and putting it back into the arrayList
        boolean userExists = false;
        
        // this try-catch block is used to write the user data to the specified file path and if there is an error it will be caught and displayed in the console log.
        try (FileWriter hibank_writeUser = new FileWriter(filePath)) {
            for (User user : users) { // for each loop to iterate through the users list array
                if (user.getAccountId().equals(this.accountId)) {
                    hibank_writeUser.write(this.accountId + "," + this.username + "," + this.password + "," + this.balance + "\n");
                    userExists = true;
                } else {
                    hibank_writeUser.write(user.getAccountId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getBalance() + "\n");
                }
            }
            if (!userExists) { // if the user does not exist in the users list array, it will write the user data to the file
                hibank_writeUser.write(this.accountId + "," + this.username + "," + this.password + "," + this.balance + "\n");
            }
        } catch (IOException hibank_e) {
            System.out.println("Error saving user data: " + hibank_e);
        }
    }

    // this is where the declaration of the loadUser method is located using the List<User> return type to store the loaded user data into the users list array when we re-opened the system
    public static List<User> loadUser() {
        List<User> users = new ArrayList<>(); // using the arraylist approach to store the user data from the file
        String filePath = "FINAL\\Database\\hibank_user.txt";
        try (BufferedReader userReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] user_data = line.split(","); // registry expression to split the user data by comma
                String username = user_data[1]; // storing the user data into the variables and each index matches the variable where [1] is the username 
                String password = user_data[2]; // storing the user data into the variables and each index matches the variable where [2] is the password 
                String accId = user_data[0]; // storing the user data into the variables and each index matches the variable where [0] is the accountId
                double balance = Double.parseDouble(user_data[3]);  // storing the user data into the variables and each index matches the variable where [3] is the balance
                String transactionHistory = loadTransaction(accId);
                users.add(new User(username, password, accId, balance, transactionHistory));
            }
        } catch (IOException e) {
            System.out.println("Error loading user data handled: " + e);
        }
        return users;
    }

    // loadTransaction => This method loads the transaction history of the account from the hibank_transactions.txt file.
    // this is where we can load the transaction history of the account by passing the accountId as a parameter to the method.
    // and it will return the transaction history of the account as a string.
    // Also, by having this method, we can display the transaction history of the account in the dashboard by loding it from the file and calling the getTransactionHistoryList method.
    private static String loadTransaction(String accountId) {
        StringBuilder transactionHistory = new StringBuilder(); // using the stringbuilder approach to append the transaction history of the account
        String filePath = "FINAL\\Database\\hibank_transactions.txt";
        try (BufferedReader transactionReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = transactionReader.readLine()) != null) { // while loop to read the transaction data from the file
                String[] transaction_data = line.split(","); // with registry expression to split the transaction data by comma
                if (transaction_data[0].equals(accountId)) { // if the accountId matches the transaction data, it will append the       transaction history of the account
                    transactionHistory.append(transaction_data[1]).append("\n"); // append the transaction history of the account
                }
            }
        } catch (IOException hibank_e) {
            System.out.println("Error loading transactions: " + hibank_e); 
        }
        return transactionHistory.toString(); // return the transaction history of the account as a string to be displayed in the dashboard
    }

    // addTransaction => This method adds the transaction to the transaction history of the account and saves it to the hibank_transactions.txt file.
    String filePath = "FINAL\\Database\\hibank_transactions.txt";
    public void addTransaction(String transaction) {
        transactionHistory += transaction + "\n"; // with this declared variables we can append the transaction history of the account
        try (FileWriter hibank_writer = new FileWriter(filePath, true)) { // append = true
            hibank_writer.write(accountId + "," + transaction + "\n"); // write the transaction history of the account to the file
        } catch (IOException hibank_e) {
            System.out.println("Error saving transaction: " + hibank_e);
        }
    }

    // runtime polymorphism => overriding the deposit and withdraw methods from the superclass Account
    // deposit => This method deposits money to the account and saves the user data.
    public void deposit(double amount) {
        this.balance += amount;
        saveUser(); // save the user data
    }
    
    // withdraw => This method withdraws money from the account and saves the user data.
    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            saveUser(); // save the user data
            return true;
        }
        return false;
    }
}