package bank;

public class BusinessAccount extends BankAccount {
    private static final double TRANSACTION_FEE = 2.50;

    public BusinessAccount(String accountHolderName, int accountNumber, double initialBalance) {
        super(accountHolderName, accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        double totalAmount = amount + TRANSACTION_FEE;
        if (totalAmount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal including transaction fee.");
        }
        balance -= totalAmount;
        System.out.println("Withdrawn: " + amount + " (Transaction fee: " + TRANSACTION_FEE + ")");
    }
}