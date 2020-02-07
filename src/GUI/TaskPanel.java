package GUI;

import source.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Egy taszkot megjelenítő panel.
 */
public class TaskPanel extends JPanel implements ActionListener {
    /**
     * A taszk, amelyet megjelenít.
     */
    private Task task;
    /**
     * A taszk teljesítéséhez szükséges munka.
     */
    private JLabel remaining;
    /**
     * A taszk elindításának a gombja.
     */
    private JButton taskButton;
    /**
     * Konstruktor.
     * @param t A megjelenítendő taszk.
     */
    public TaskPanel(Task t){
        task = t;
        setLayout(new GridLayout(1, 4, 20, 40));
        JLabel name = new JLabel(task.getName());
        name.setHorizontalTextPosition(JLabel.CENTER);
        remaining = new JLabel(String.valueOf(task.getRemainingTime() + " more hours"));
        JLabel deadline = new JLabel("Day " + task.getDeadline().getDay() + " " + task.getDeadline().getHour() + ":00");
        taskButton = new JButton("Work");
        this.add(name);
        this.add(remaining);
        this.add(deadline);
        this.add(taskButton);

        setVisible(true);
    }
    /**
     * Hozzárendel egy ActionListenert az adott gombhoz.
     * @param button A gomb, melyhez rendeljük.
     */
    public void addListener(JButton button){
        button.addActionListener(this);
    }
    /**
     * A taszk indítási gombjának gettere.
     */
    public JButton getTaskButton(){
        return taskButton;
    }
    /**
     * A panel reagálása az eseményekre.
     * @param actionEvent A lereagálandó esemény.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        task.doActivity();
        remaining.setText(String.valueOf(task.getRemainingTime() + " more hours"));
    }

}
