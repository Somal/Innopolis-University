/**
 * Created by somal on 21.02.16.
 */
public abstract class BankAccount {
    protected double balance;
    public Currencys currency;
    protected Customer owner;

    public double getBalance() {
        return balance;
    }

    public Currencys getCurrency() {
        return this.currency;

    }

    public Customer getOwner() {
        return owner;
    }

    public void credit(double amount) {
    }

    public void debit(double amount) {
    }
}
