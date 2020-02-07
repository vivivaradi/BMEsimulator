package source;

import java.io.Serializable;

/**
 * A játékban végezhető különböző tevékenységek közös absztrakt őse.
 */
public abstract class Activity implements Serializable {
    /**
     * A diák, akire hatással van az esemény.
     */
    protected Student student;
    /**
     * Mekkora hatással van az esemény a diákra.
     */
    protected int effect;
    /**
     * Konstruktor.
     * @param st Az átvett diák.
     * @param e Az átvett hatás.
     */
    public Activity(Student st, int e){
        student = st;
        effect = e;
    }
    /**
     * A tevékenységek elvégzését biztosító függvény, minden osztály maga valósítja meg.
     */
    public abstract void doActivity();
}
