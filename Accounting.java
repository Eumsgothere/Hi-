public class Account {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public Account(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;  // Getter for accountHolder
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) throws Exception {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new Exception("Insufficient balance or invalid amount.");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
               "accountNumber=" + accountNumber +
               ", accountHolder='" + accountHolder + '\'' +
               ", balance=" + balance +
               '}';
    }
}
