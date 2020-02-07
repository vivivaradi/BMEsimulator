package GUI;

import source.Game;
import source.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * A játékot megjelenítő panel.
 */
public class GamePanel extends JPanel implements ActionListener {
    /**
     * A folyamatban lévő játék.
     */
    private Game game;
    /**
     * A telejsítendő feladatok.
     */
    private ArrayList<Task> tasks;
    /**
     * A jelenlegi nap.
     */
    private JLabel day;
    /**
     * A jelenlegi óra.
     */
    private JLabel hour;
    /**
     * A diák jelenlegi energiaszintje.
     */
    private JLabel energyLevel;
    /**
     * A diák jelenlegi stressz szintje.
     */
    private JLabel stressLevel;
    /**
     * A taszkokat tartalmazó konténer.
     */
    private JPanel TaskContainer;
    /**
     * A megjelenítendő kép.
     */
    private JLabel picture;
    /**
     * Konstruktor.
     * @param name A játékos által megadott név.
     * @param difficulty A játékos által választott nehézség.
     */
    public GamePanel(String name, String difficulty) {
        game = new Game(name, difficulty);
        tasks = game.getTasks();

        initComponents();

        setVisible(true);
    }
    /**
     * Konstuktor fájlból, elmentett adat betöltésére.
     * @param filename A megnyitandó fájl neve.
     */
    public GamePanel(String filename){
        Game loaded = null;
        try {
            FileInputStream fin = new FileInputStream(new File(filename));
            ObjectInputStream in = new ObjectInputStream(fin);
            loaded = (Game) in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        game = loaded;
        tasks = game.getTasks();
        initComponents();

        setVisible(true);
    }
    /**
     * A panel komponenseinek létrehozása és megjelenítése.
     */
    public void initComponents(){
        setLayout(new BorderLayout());
        JPanel LeftContainer = new JPanel();
        this.add(LeftContainer, BorderLayout.WEST);
        LeftContainer.setLayout(new BoxLayout(LeftContainer, BoxLayout.Y_AXIS));
        JPanel TaskHeader = new JPanel();
        TaskHeader.setLayout(new GridLayout(1, 4, 30, 10));
        TaskHeader.setAlignmentY(TOP_ALIGNMENT);
        TaskContainer = new JPanel();
        LeftContainer.add(TaskHeader);
        LeftContainer.add(TaskContainer);
        TaskContainer.setLayout(new GridLayout(tasks.size(), 4, 30, 10));
        TaskHeader.add(new JLabel("Task"));
        TaskHeader.add(new JLabel("Work needed"));
        TaskHeader.add(new JLabel("Deadline"));
        TaskHeader.add(new JLabel(" "));


        initializeTaskPanels();

        JPanel BottomContainer = new JPanel();
        this.add(BottomContainer, BorderLayout.SOUTH);
        JButton coffeeButton = new JButton("Drink a coffee");
        coffeeButton.setActionCommand("coffeedrank");
        JButton sleepButton = new JButton("Sleep an hour");
        sleepButton.setActionCommand("slept");
        JButton videoButton = new JButton("Watch a cute video");
        videoButton.setActionCommand("videowatched");
        JButton cryButton = new JButton("Cry on the floor");
        cryButton.setActionCommand("cried");

        JLabel energyLabel = new JLabel("Energy level:");
        JLabel maxEnergyLabel = new JLabel("(max:" + game.getStudent().getMaxEnergy() + ")");
        JLabel StressLabel = new JLabel("Stress level:");
        JLabel maxStressLabel = new JLabel("(max:" + game.getStudent().getMaxStress() + ")");
        energyLevel = new JLabel(String.valueOf(game.getStudent().getCurrentEnergy()));
        stressLevel = new JLabel(String.valueOf(game.getStudent().getCurrentStress()));

        BottomContainer.add(energyLabel);
        BottomContainer.add(energyLevel);
        BottomContainer.add(maxEnergyLabel);
        BottomContainer.add(coffeeButton);
        BottomContainer.add(sleepButton);
        BottomContainer.add(videoButton);
        BottomContainer.add(cryButton);
        BottomContainer.add(StressLabel);
        BottomContainer.add(stressLevel);
        BottomContainer.add(maxStressLabel);

        coffeeButton.addActionListener(this);
        cryButton.addActionListener(this);
        sleepButton.addActionListener(this);
        videoButton.addActionListener(this);


        JPanel UpperContainer = new JPanel();
        this.add(UpperContainer, BorderLayout.NORTH);
        day = new JLabel("Day " + game.getCurrentTime().getDay());
        hour = new JLabel(game.getCurrentTime().getHour() + ":00");
        UpperContainer.add(day);
        UpperContainer.add(hour);

        picture = new JLabel(new ImageIcon("pics/student_basic.png"));
        this.add(picture, BorderLayout.CENTER);
        picture.setVisible(true);
    }
    /**
     * Az ActionListener ami figyeli a játékos tevékenységeit.
     * @param actionEvent A bekövetkezett esemény.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch(actionEvent.getActionCommand()){
            case "coffeedrank": game.getCoffee().doActivity();
                SwitchImage("pics/student_coffee.png");
                break;
            case "slept": game.getCurrentTime().increase();
                game.getSleep().doActivity();
                SwitchImage("pics/student_sleeping.png");
                break;
            case "taskperformed": game.getCurrentTime().increase();
                SwitchImage("pics/student_basic.png");
                break;
            case "cried": game.getCurrentTime().increase();
                game.getCrying().doActivity();
                SwitchImage("pics/student_crying.png");
                break;
            case "videowatched": game.getCurrentTime().increase();
                game.getCuteVideos().doActivity();
                SwitchImage("pics/student_video.png");
                break;
        }
        refreshTaskList();
        refreshNeeds();
        if (game.getStudent().getCurrentStress() >= game.getStudent().getMaxStress()){

            JTextArea lost = new JTextArea("Unfortunately your student reached the maximum amount of stress they can handle.\n " +
                    "They are not capable of finishing this semester anymore.\n " +
                    "But don't worry, you can try again by pressing New Game on the above menu!");
            lose(lost);
        }
        if (game.getStudent().getCurrentEnergy() == 0){
            game.getSleep().doActivity();
            SwitchImage("pics/student_sleeping.png");
            JFrame warning = new JFrame("Warning");
            warning.setPreferredSize(new Dimension(400, 200));
            warning.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JTextArea text = new JTextArea("Your student was too tired and fell asleep.");
            warning.add(text);
            warning.pack();
            warning.setVisible(true);
        }

        if (game.getCurrentTime().getHour() == 0){
            for (Task t : tasks){
                t.raiseGivenStress(1);
            }
        }

        if (game.getCurrentTime().equals(game.getEndTime())){
            this.setVisible(false);
            this.removeAll();
            JTextArea losetext = new JTextArea("Unfortunately (or not) the semester has ended. " +
                    "Enjoy your free time while it lasts!");
            lose(losetext);
        }

        refreshNeeds();

        refreshTime();
    }
    /**
     * Frissíti az aktuális taszkok listáját.
     */
    public void refreshTaskList(){
        for (int i = tasks.size() - 1; i >= 0; i--){
            if (game.getCurrentTime().equals(tasks.get(i).getDeadline())){
                tasks.get(i).fail();
                tasks.remove(i);
                refreshTaskPanels();
            }
            if (tasks.get(i).isDone()){
                tasks.remove(i);
                refreshTaskPanels();
            }
        }
        if(tasks.isEmpty()){
            this.setVisible(false);
            this.removeAll();
            JLabel won = new JLabel("Congrats " + game.getStudent().getName() + "! " +
                    "You successfully survived university without a mental breakdown!");
            this.add(won);
            won.setHorizontalAlignment(SwingConstants.CENTER);
            won.setVerticalAlignment(SwingConstants.CENTER);
            setVisible(true);
        }
    }
    /**
     * A taszkokat helyezi el a panelon.
     */
    public void initializeTaskPanels(){
        for (Task t: tasks){
            TaskPanel tp = new TaskPanel(t);
            TaskContainer.add(tp);
            JButton button = tp.getTaskButton();
            button.setActionCommand("taskperformed");
            button.addActionListener(this);
            tp.addListener(button);
        }
    }
    /**
     * Frissíti a taszkok megjelenítését.
     */
    public void refreshTaskPanels(){
        TaskContainer.setVisible(false);
        TaskContainer.removeAll();

        initializeTaskPanels();
        TaskContainer.setVisible(true);
    }
    /**
     * Frissíti a megjelenített időt.
     */
    public void refreshTime(){
        day.setText("Day " + game.getCurrentTime().getDay());
        hour.setText(game.getCurrentTime().getHour() + ":00");
    }
    /**
     * Frissíti a diák megjelenített állapotát.
     */
    public void refreshNeeds(){
        energyLevel.setText(String.valueOf(game.getStudent().getCurrentEnergy()));
        stressLevel.setText(String.valueOf(game.getStudent().getCurrentStress()));
    }
    /**
     * A játék gettere.
     */
    public Game getGame() {
        return game;
    }
    /**
     * A kép változtatására szolgáló függvény.
     * @param filename A képfájl neve.
     */
    public void SwitchImage(String filename){
        picture.setVisible(false);
        picture.removeAll();
        picture = new JLabel(new ImageIcon(filename));
        this.add(picture);
        picture.revalidate();
        picture.repaint();
        picture.setVisible(true);
    }
    /**
     * A játék elvesztésekor megjelenő frame összerakására szolgál.
     * @param text A kiírandó szöveg.
     */
    public void lose(JTextArea text){
        this.setVisible(false);
        this.removeAll();
        JFrame loseframe = new JFrame("Game Over");
        loseframe.setPreferredSize(new Dimension(600, 200));
        loseframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        loseframe.add(text);
        loseframe.pack();
        loseframe.setVisible(true);
    }
}
