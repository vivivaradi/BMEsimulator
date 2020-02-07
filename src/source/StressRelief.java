package source;

/**
 * A stressz szintet csökkentő tevékenységeket reprezentáló osztály.
 */
public class StressRelief extends Activity {
    /**
     * Konstruktor.
     * @param student Az átvett diák.
     * @param effect Az átvett hatás.
     */
    public StressRelief(Student student, int effect){
        super(student, effect);
    }
    /**
     * Az ősosztály implementált absztrakt függvénye, hatást fejt ki a diákra.
     */
    public void doActivity(){
        student.relieveStress(effect);
        student.energyDecay();
    }
}
