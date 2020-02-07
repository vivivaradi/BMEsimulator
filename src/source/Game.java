package source;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A játékot kezelő osztály.
 */
public class Game implements Serializable {
    /**
     * A játék diákja.
     */
    private Student student;
    /**
     * A játék során teljesítendő taszkok listája.
     */
    private ArrayList<Task> tasks;
    /**
     * A jelenlegi idő a játékban.
     */
    private Time currentTime;
    /**
     * A játék véget ér ebben az időpontban.
     */
    private Time endTime;
    /**
     * Az alvás tevékenységét reprezentáló adattag.
     */
    private EnergyRelief sleep;
    /**
     * A kávéivást reprezentáló adattag.
     */
    private EnergyRelief coffee;
    /**
     * A cuki videók nézését reprezentáló adattag.
     */
    private StressRelief cuteVideos;
    /**
     * A sírást reprezentáló adattag.
     */
    private StressRelief crying;
    /**
     * Konstruktor.
     * @param name A játékos által megadott név.
     * @param dif A játékos által választott nehézség.
     */
    public Game(String name, String dif) {
        switch (dif){
            case "EASY": EasyGameSetup(name);
                break;
            case "MEDIUM": MediumGameSetup(name);
                break;
            case "HARD": HardGameSetup(name);
                break;
            default: throw new InvalidParameterException("Nem jo az input");
        }
        sleep = new EnergyRelief(student, 20);
        coffee = new EnergyRelief(student, 10);
        cuteVideos = new StressRelief(student, 20);
        crying = new StressRelief(student, 10);
    }
    /**
     * A könnyű játék beállítására szolgál.
     * @param name A játékos által megadott név.
     */
    private void EasyGameSetup(String name) {
        student = new Student(name, 160, 200);
        currentTime = new Time();
        endTime = new Time(24*10);
        readTasks("files/easy.txt");
    }
    /**
     * A közepes nehézségű játék beállítására szolgál.
     * @param name A játékos által megadott név.
     */
    private void MediumGameSetup(String name) {
        student = new Student(name, 160, 200);
        currentTime = new Time();
        endTime = new Time(24*11);
        readTasks("files/medium.txt");
    }
    /**
     * A nehéz játék beállítására szolgáló függvény.
     * @param name A játékos által megadott név.
     */
    private void HardGameSetup(String name)  {
        student = new Student(name, 160, 200);
        currentTime = new Time();
        endTime = new Time(24*13);
        readTasks("files/hard.txt");
    }
    /**
     * A taszkok listájának gettere.
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    /**
     * A jelenlegi idő gettere.
     */
    public Time getCurrentTime(){
        return currentTime;
    }
    /**
     * A félév végének gettere.
     */
    public Time getEndTime(){
        return endTime;
    }
    /**
     * A kávézás gettere.
     */
    public EnergyRelief getCoffee() {
        return coffee;
    }
    /**
     * Az alvás gettere.
     */
    public EnergyRelief getSleep() {
        return sleep;
    }
    /**
     * A sírás gettere.
     */
    public StressRelief getCrying() {
        return crying;
    }
    /**
     * A cuki videók gettere.
     */
    public StressRelief getCuteVideos() {
        return cuteVideos;
    }
    /**
     * A diák gettere.
     */
    public Student getStudent() {
        return student;
    }
    /**
     * A taszkok beolvasása fájlból.
     * @param filename A fájl neve, melyből olvasni kell.
     */
    public void readTasks(String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int taskcount = Integer.parseInt(file.nextLine());
        tasks = new ArrayList<>(taskcount);
        for (int i = 0; i < taskcount; i++){
            String input = file.nextLine();
            String[] split = input.split("-");
            String name = split[0];
            int effect = Integer.parseInt(split[1]);
            int rtime = Integer.parseInt(split[2]);
            Time deadline = new Time(Integer.parseInt(split[3]) * 24 - 1);
            tasks.add(new Task(name, student, effect, rtime, deadline));
        }
    }

}
