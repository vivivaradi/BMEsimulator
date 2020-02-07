package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import source.Student;
import source.Task;
import source.Time;

public class TaskTest {
    private Student student;
    private Task task;
    private Time time;
    @Before
    public void setup(){
        student = new Student("Vivi", 100, 100);
        time = new Time(2*24);
        task = new Task("Szofttech", student, 20, 3, time);
    }
    @Test
    public void TestDo(){
        task.doActivity();
        assertEquals("task remaining", 2, task.getRemainingTime());
        assertEquals("student stress", 20, student.getCurrentStress());
    }
    @Test
    public void TestComplete(){
        task.doActivity();
        task.doActivity();
        task.doActivity();
        assertTrue("task done", task.isDone());
    }
    @Test
    public void TestRaise(){
        task.raiseGivenStress(20);
        task.doActivity();
        assertEquals("student stress", 40, student.getCurrentStress());
    }
}