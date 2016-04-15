import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by somal on 28.03.16.
 */
public interface ConditionInterface {

    public ConditionEnum ShowCondition();

    public void On();

    public void Off();

    public Boolean isOn();

    public Boolean isOff();

}
