/**
 * Created by somal on 21.02.16.
 */
public class CheckingAccount extends BankAccount {
    private int countOfDebit;


    /** <b> pre: countOfDebit<3 </b> */
    /**
     * <b> pos: countOfDebit=0 </b>
     */
    @Override
    public void credit(double amount) {
    }

    /** <b> pre: countOfDebit<=2 </b> */
    /**
     * <b> pos:countOfDebit=@pre countOfDebit +1 </b>
     */
    @Override
    public void debit(double amount) {
    }

    public CheckingAccount(Customer owner, Currencys currency) {
    }


}

/**
 * <b> inv: self.balance>=0 </b>
 */
