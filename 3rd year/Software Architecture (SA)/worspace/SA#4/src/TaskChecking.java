import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by somal on 13.02.16.
 */
public class TaskChecking {
    Task task;

    @Before
    public void init() {
        task = new Task();
    }

    @Test
    public void test1() {
        task.setPcd(10);
        task.setPsd(20);
        assertTrue(task.getPsd() <= task.getPcd());
    }

    @Test
    public void test2() {
        task.setAcd(2);
        task.setAsd(-1);
        assertTrue(task.getAcd() != -1 ? task.getAsd() != -1 : true);
    }

    @Test
    public void test3() {
        task.setAcd(5);
        task.setAsd(10);
        assertTrue(task.getAcd() == -1 ? true : task.getAsd() < task.getAcd());
    }

    @Test
    public void testALl() {
        for (int i = -5; i < 5; i++)
            for (int j = -5; j < 5; j++) {
                task.setPsd(i);
                task.setPcd(j);
                task.setAsd(i);
                task.setAcd(j);
                assertTrue(task.getPsd() <= task.getPcd());
                assertTrue(task.getAcd() != -1 ? task.getAsd() != -1 : true);
                assertTrue(task.getAcd() == -1 ? true : task.getAsd() < task.getAcd());
            }

    }

}