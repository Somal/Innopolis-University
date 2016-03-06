/**
 * Created by somal on 21.02.16.
 */
public class Student extends Customer {
    protected double overdraft=100.0, initBalance=100.0;

    @Override
    public double getOverdraft() {
        return overdraft;
    }

    @Override
    public double getInitBalance() {
        return initBalance;
    }

    public String(String firstName, String secondName){
        super(firstName, secondName);
    }
}
