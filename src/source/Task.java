package source;

import java.io.Serializable;

/**
 * A játékban megjelenő feladatokat reprezentáló osztály.
 */
public class Task extends Activity implements Serializable {
    /**
     * A taszk megjelenő neve.
     */
    private String name;
    /**
     * A taszk elvégzéséhez szükséges hátralévő idő.
     */
    private int remainingtimetocomplete;
    /**
     * A taszk határideje.
     */
    protected Time deadline;
    /**
     * Megmondja, hogy az adott taszk készen van-e már.
     */
    private boolean done = false;
    /**
     * Konstruktor.
     * @param n A taszk neve.
     * @param student A taszkot végző diák.
     * @param effect A taszk hatása a diák stressz szintjére.
     * @param time A taszk elvégzéséhez szükséges idő.
     * @param dl A taszk határideje.
     */
    public Task(String n, Student student, int effect, int time, Time dl){
        super(student, effect);
        name = n;
        remainingtimetocomplete = time;
        deadline = dl;
    }
    /**
     * Az ősosztály implementált absztrakt függvénye, a taszk végzését teszi lehetővé.
     */
    public void doActivity(){
        remainingtimetocomplete--;
        student.raiseStress(effect);
        if (remainingtimetocomplete == 0)
            complete();
        student.energyDecay();
    }
    /**
     * A taszk elbukásakor hívódik meg, a diák stressz szintjét növeli drasztikusan.
     */
    public void fail(){
        student.raiseStress(effect*2);
    }
    /**
     * A taszk teljesítésekor hívódik meg.
     */
    private void complete(){
        done = true;
    }
    /**
     * A határidő gettere.
     */
    public Time getDeadline(){
        return deadline;
    }
    /**
     * A hátralévő szükséges munka gettere.
     */
    public int getRemainingTime(){
        return remainingtimetocomplete;
    }
    /**
     * A taszk nevének gettere.
     */
    public String getName(){
        return name;
    }
    /**
     * Getter a taszk teljesítettségére.
     */
    public boolean isDone(){
        return done;
    }
    /**
     * Megemeli a taszk által diákra kifejtett stresszmennyiséget.
     * @param amount Ennyit ad hozzá a jelenlegi értékhez.
     */
    public void raiseGivenStress(int amount){
        effect += amount;
    }
}
