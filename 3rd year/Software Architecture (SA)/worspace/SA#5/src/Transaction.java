/**
 * Created by somal on 21.02.16.
 */
public class Transaction {
    private String type;
    private double amount;
    protected BankAccount account;

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public BankAccount getAccount() {
        return account;
    }

    public Transaction(String type, double amount, BankAccount account) {
    }
}
