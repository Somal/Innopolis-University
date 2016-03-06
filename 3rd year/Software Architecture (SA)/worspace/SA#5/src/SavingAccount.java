/**
 * Created by somal on 21.02.16.
 */
public class SavingAccount extends BankAccount {
    protected double overdraftProtection;
    private double interestRate;


    public double getOverdraftProtection() {
        return overdraftProtection;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void credit(double amount) {
    }


    /** <b> pre: amount>0</b> */
    /** <b> pre: balance>=0</b> */
    /**
     * <b> pos: balance<0 implies -balance<=overdraftProtection </b>
     */
    public void debit(double amount) {
    }

    public SavingAccount(Customer owner, double interestRate, Currencys currency) {
        this.overdraftProtection=owner.getOverdraft();
    }


}
