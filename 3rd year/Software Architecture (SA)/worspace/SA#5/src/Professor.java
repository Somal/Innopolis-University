/**
 * Created by somal on 21.02.16.
 */
public class Professor extends Customer{
    protected double overdraft=200.0, initBalance=200.0;

    @Override
    public double getOverdraft() {
        return overdraft;
    }

    @Override
    public double getInitBalance() {
        return initBalance;
    }


    public Professor(String firstName, String secondName){
        super(firstName, secondName);

    }


}
