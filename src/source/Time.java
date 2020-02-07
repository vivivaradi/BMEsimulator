package source;

import java.io.Serializable;

/**
 * A játékban az időt reprezentáló osztály.
 */

public class Time implements Serializable {
    /**
     * Az eltelt napok száma.
     */
    private int day;
    /**
     * Az eltelt órák száma.
     */
    private int hour;
    /**
     * Default konstruktor, a játék kezdeti idejét állítja.
     */
    public Time(){
        hour = 8;
        day = 0;
    }
    /**
     * Egyparaméterű konstruktor, határidőket állít.
     * @param value Az eltelt időegységek száma.
     */
    public Time(int value){
        day = value / 24;
        hour = value % 24;
    }
    public void increase(){
        if (hour + 1 > 23){
            hour = 0;
            day++;
        }
        else
            hour++;

    }
    /**
     * Egyenlőséget vizsgál két időobjektum között.
     * @param other A jelenlegivel összehasonlítani kívánt másik objektum.
     */
    public boolean equals(Time other){
        return hour == other.hour && day == other.day;
    }
    /**
     * Getter az eltelt napok számára.
     */
    public int getDay(){
        return day;
    }
    /**
     * Getter az eltelt órák számára.
     */
    public int getHour(){
        return hour;
    }
}
