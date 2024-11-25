import java.util.List;
import java.util.ArrayList;

public class Bank {
    private String name;
    private List<Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    // Add account to the bank
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Retrieve account by account number
    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    // Remove an account by account number (close account)
    public Account closeAccount(int accountNumber) {
        Account accountToRemove = null;
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                accountToRemove = account;
                break;
            }
        }
        if (accountToRemove != null) {
            accounts.remove(accountToRemove);
            return accountToRemove;
        }
        return null;
    }

    // Display all accounts in a readable format
    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            System.out.println("\n=== List of Accounts ===");
            for (Account account : accounts) {
                System.out.println("===================================");
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Account Holder: " + account.getAccountHolder());
                System.out.println("Balance: $" + String.format("%.2f", account.getBalance()));
                System.out.println("===================================\n");
            }
        }
    }
}
