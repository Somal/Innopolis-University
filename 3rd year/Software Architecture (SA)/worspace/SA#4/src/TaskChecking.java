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
    public void test11() {
        task.setPcd(10);
        task.setPsd(20);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test12() {
        task.setPcd(10);
        task.setPsd(50);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test13() {
//        task.setPcd(-1);
//        task.setPsd(-1);
        task.setPcd(0);
        task.setPsd(50);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test14() {
        task.setPcd(50);
        task.setPsd(0);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test15() {
        task.setPcd(-5);
        task.setPsd(10);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test16() {
        task.setPcd(4);
        task.setPsd(-6);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test17() {
        task.setPcd(0);
        task.setPsd(-2);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test18() {
        task.setPcd(-9);
        task.setPsd(0);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test19() {
        task.setPcd(100);
        task.setPsd(-100);
        assertTrue(task.getPsd() <= task.getPcd() && checking());
    }

    @Test
    public void test21() {
        task.setAcd(2);
        task.setAsd(-1);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }

    @Test
    public void test22() {
        task.setAcd(-1);
        task.setAsd(-1);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }

    @Test
    public void test23() {
        task.setAcd(2);
        task.setAsd(-4);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }

    @Test
    public void test24() {
        task.setAcd(0);
        task.setAsd(0);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }

    @Test
    public void test25() {
        task.setAcd(5);
        task.setAsd(6);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }

    @Test
    public void test26() {
        task.setAcd(-5);
        task.setAsd(6);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }

    @Test
    public void test27() {
        task.setAcd(0);
        task.setAsd(6);
        assertTrue(task.getAcd() != -1 ? task.getAsd() > 0 : true && checking());
    }


    @Test
    public void test3() {
        task.setAcd(5);
        task.setAsd(10);
        assertTrue(task.getAcd() == -1 ? true : task.getAsd() < task.getAcd() && checking());
    }

    @Test
    public void test31() {
        task.setAcd(-1);
        task.setAsd(10);
        assertTrue(task.getAcd() == -1 ? true : task.getAsd() < task.getAcd() && checking());
    }

    @Test
    public void test32() {
        task.setAcd(5);
        task.setAsd(0);
        assertTrue(task.getAcd() == -1 ? true : task.getAsd() < task.getAcd() && checking());
    }

    @Test
    public void testALl() {
        for (int i = -5; i < 5; i++)
            for (int j = -5; j < 5; j++) {
                task.setPsd(i);
                task.setPcd(j);
                task.setAsd(i);
                task.setAcd(j);
                assertTrue(task.getPsd() <= task.getPcd() && checking());
                assertTrue(task.getAcd() != -1 ? task.getAsd() != -1 : true && checking());
                assertTrue(task.getAcd() == -1 ? true : task.getAsd() < task.getAcd() && checking());
            }
    }

    public boolean checking() {
        return (task.getPcd() == -1 || task.getPcd() > 0) && (task.getPsd() == -1 || task.getPsd() > 0) && (task.getAcd() == -1 || task.getAcd() > 0) && (task.getAsd() == -1 || task.getAsd() > 0);
    }
}