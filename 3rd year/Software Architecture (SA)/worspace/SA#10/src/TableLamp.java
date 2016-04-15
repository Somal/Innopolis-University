/**
 * Created by somal on 28.03.16.
 */
public class TableLamp implements TableLampInterface{
    SwitchInterface switchInterface;
    LampInterface lampInterface;

    public TableLamp(SwitchInterface switchInterface, LampInterface lampInterface){
        this.lampInterface=lampInterface;
        this.switchInterface=switchInterface;
    }

    @Override
    public void On() {
        switchInterface.On();
    }

    @Override
    public void Off() {
        switchInterface.Off();
    }

    @Override
    public Condition getCondition() {
        return (switchInterface.getCondition().isOn() && lampInterface.getCondition().isOn())?new Condition(ConditionEnum.On):new Condition(ConditionEnum.Off);
    }
}
