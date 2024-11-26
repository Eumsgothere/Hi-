package FINAL.Directories;

// Interface for Transactionable subclass => contains the methods for adding transactions and getting transaction history
public interface Transactionable {
    void addTransaction(String transaction);
    String getTransactionHistory();
}
