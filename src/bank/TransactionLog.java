package bank;

public interface TransactionLog {
    void recordTransaction(String type, double amount);
}