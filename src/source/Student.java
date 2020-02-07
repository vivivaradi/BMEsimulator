package source;

import java.io.Serializable;

/**
 * A diákot reprezentáló osztály.
 */
public class Student implements Serializable {
    /**
     * A diák neve.
     */
    private String name;
    /**
     * A diák energiaszintje.
     */
    private int energy;
    /**
     * A diák maximum energiaszintje.
     */
    private int maxenergy;
    /**
     * A diák stressz szintje.
     */
    private int stress;
    /**
     * A diák maximum stressz szintje.
     */
    private int maxstress;
    /**
     * Konstruktor.
     * @param n A diák neve.
     * @param maxe A maximum energia.
     * @param maxs A maximum stressz.
     */
    public Student(String n, int maxe, int maxs){ //TODO mennyiségeket kitalálni és inicializálni
        name = n;
        maxenergy = maxe;
        energy = maxenergy;
        maxstress = maxs;
        stress = 0;
    }
    /**
     * A név gettere.
     */
    public String getName(){
        return name;
    }
    /**
     * A jelenlegi energiaszint gettere.
     */
    public int getCurrentEnergy(){
        return energy;
    }
    /**
     * A jelenlegi stressz szint gettere.
     */
    public int getCurrentStress(){
        return stress;
    }
    /**
     * A maximum energia gettere.
     */
    public int getMaxEnergy(){
        return maxenergy;
    }
    /**
     * A maximum stressz gettere.
     */
    public int getMaxStress(){
        return maxstress;
    }
    /**
     * Csökketni a diák energiáját.
     */
    public void energyDecay(){
        if (energy - 10 <= 0)
            energy = 0;
        else
            energy -= 10;
    }
    /**
     * Növeli a diák energiáját.
     * @param amount A mennyiség, amivel növeli.
     */
    public void raiseEnergy(int amount){
        if (energy + amount >= maxenergy)
            energy = maxenergy;
        else
            energy += amount;
    }
    /**
     * Növeli a diák stressz szintjét.
     * @param amount A mennyiség, amivel növeli.
     */
    public void raiseStress(int amount){
        stress += amount;
    }
    /**
     * Csökkenti a diák stressz szintjét.
     * @param amount A mennyiség, amivel csökkenti.
     */
    public void relieveStress(int amount){
        if (stress - amount <= 0)
            stress = 0;
        else
            stress -= amount;
    }

}
