/**
 * Created by somal on 28.03.16.
 */
public class Lamp implements LampInterface {
    ConditionInterface cond;

    @Override
    public void On() {
        this.cond.On();
    }

    @Override
    public void Off() {
        this.cond.Off();
    }

    @Override
    public ConditionInterface getCondition() {
        return this.cond;
    }

    public Lamp(ConditionInterface conditionInterface){
        this.cond=conditionInterface;
    }
}
