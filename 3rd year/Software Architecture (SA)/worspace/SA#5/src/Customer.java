/**
 * Created by somal on 21.02.16.
 */
public abstract class Customer {
    private String firstName, secondName;
    protected double initBalance, overdraft;

    public String getFirstName(){
        return this.firstName;
    }

    public String getSecondName(){
        return this.secondName;
    }

    public double getInitBalance(){
        return this.initBalance;
    }

    public double getOverdraft(){
        return this.overdraft;
    }

    public Customer(String firstName ,String secondName){
        this.firstName=firstName;
        this.secondName=secondName;
    }

}

 /** <b> inv: self.accounts->select(BankAccount b| b.owner==self)->size()=2 </b> */
