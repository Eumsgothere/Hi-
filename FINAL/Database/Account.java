package FINAL.Database;

// Abstract Class => Account class is an abstract class that contains the properties and methods for the User subclass and override methods.
public abstract class Account {
    // Protected Variables => username, password, accountId, and balance are protected variables that can be accessed only by its inheritance, the User subclass.
    protected String username;
    protected String password;
    protected String accountId;
    protected double balance;

    public Account(String username, String password, String id, double balance) {
        this.username = username;
        this.password = password;
        this.accountId = id;
        this.balance = balance;
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

    public abstract void saveUser();
}
