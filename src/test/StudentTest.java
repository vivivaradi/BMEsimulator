package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import source.Student;


public class StudentTest {
    private Student student;
    @Before
    public void setup(){
        student = new Student("Vivi", 160, 200);
    }
    @Test
    public void TestAttributes(){
        assertEquals("new maxenergy", 160, student.getMaxEnergy());
        assertEquals("new maxstress", 200, student.getMaxStress());
        assertEquals("new energy", 160, student.getCurrentEnergy());
        assertEquals("new stress", 0, student.getCurrentStress());
    }
    @Test
    public void TestEnergyDecay(){
        student.energyDecay();
        assertEquals("energylevel", 150, student.getCurrentEnergy());
    }
    @Test
    public void TestEnergyRaise(){
        student.energyDecay();
        student.raiseEnergy(10);
        assertEquals("energylevel", 160, student.getCurrentEnergy());
    }
    @Test
    public void TestStressModification(){
        student.raiseStress(40);
        assertEquals("stresslevel", 40, student.getCurrentStress());
        student.relieveStress(20);
        assertEquals("stresslevel", 20, student.getCurrentStress());
    }
}
