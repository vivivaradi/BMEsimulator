package test;

import org.junit.Test;
import source.Time;

import static org.junit.Assert.*;

public class TimeTest {
    private Time time1;
    private Time time2;
    @Test
    public void TestConstructor(){
        time1 = new Time(36);
        time2 = new Time(24);
        assertEquals("time1", 1, time1.getDay());
        assertEquals("time1", 12, time1.getHour());
        assertEquals("time2", 1, time2.getDay());
        assertEquals("time2", 0, time2.getHour());
    }
    @Test
    public void TestIncrease(){
        time1 = new Time();
        time1.increase();
        assertEquals("time1 value", 9, time1.getHour());
    }
    @Test
    public void TestEquality(){
        time1 = new Time();
        time2 = new Time(8);
        assertTrue("equal", time1.equals(time2));
    }
}