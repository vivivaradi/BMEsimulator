package source;

import java.io.Serializable;

/**
 * Az energiát növelő tevékenységeket reprezentáló osztály.
 */
public class EnergyRelief extends Activity implements Serializable {
    /**
     * Konstruktor.
     * @param student Az átvett diák.
     * @param effect Az átvett hatás.
     */
    public EnergyRelief(Student student, int effect){
        super(student, effect);
    }
    /**
     * Az ősosztály absztrakt függvénye implementálva, hatást fejt ki a diákra.
     */
    public void doActivity(){
        student.raiseEnergy(effect);
    }
}
