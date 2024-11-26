package FINAL.Database;

// Abstract Class => Account class is an abstract class that contains the properties and methods for the User subclass and override methods.
public abstract class Account {
    // Protected Variables => username, password, accountId, and balance are protected variables that can be accessed only by its inheritance, the User subclass.
    protected String username;
    protected String password;
    protected String accountId;
    protected double balance;

    // Account Constructor (Default) => This constructor initializes the Account class. (unused)
    public Account() {

    }

    /**
     * Account Constructor => This constructor initializes the username, password, accountId, and balance of the Account class.
     * @param username => The username of the account.
     * @param password => The password of the account.
     * @param id => The accountId of the account.
     * @param balance => The balance of the account.
     */

    public Account(String username, String password, String id, double balance) {
        this.username = username;
        this.password = password;
        this.accountId = id;
        this.balance = balance;
    }

    // Getters => These methods return the username, password, accountId, and balance of the account.
    // These concepts are covered with the use of encapsulation.
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

    // Methods => These methods deposit and withdraw money from the account.
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Abstract Method => This abstract method saves the user data and it is forced to implement on the User subclass.
    public abstract void saveUser();
}
