import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by somal on 28.03.16.
 */
public class Condition implements ConditionInterface {
    private ConditionEnum cond;

    public Condition(ConditionEnum conditionEnum){
        this.cond=conditionEnum;
    }

    public Condition(){
        this.cond=ConditionEnum.Off;
    }

    public ConditionEnum ShowCondition(){
        return this.cond;
    }

    public void On(){
        this.cond=ConditionEnum.On;
    }

    public void Off(){
        this.cond=ConditionEnum.Off;
    }

    public Boolean isOn(){
        return this.cond==ConditionEnum.On;
    }

    public Boolean isOff(){
        return !isOn();
    }

}
