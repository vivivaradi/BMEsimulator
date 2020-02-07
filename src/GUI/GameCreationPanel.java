package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * A játék paramétereinek megadására szolgáló panel.
 */
public class GameCreationPanel extends JPanel {
    /**
     * A név mezője.
     */
    private JTextField nameTextField;
    /**
     * A nehézséget lehet kiválasztani vele.
     */
    private JComboBox diffValueBox;
    /**
     * A játék elindítására szolgáló gomb.
     */
    private JButton okButton;
    /**
     * Konstruktor.
     */
    public GameCreationPanel(){
        setLayout(new FlowLayout());
        JLabel nameLabel = new JLabel("Name: ");
        nameTextField = new JTextField(50);
        JLabel diffLabel = new JLabel("Difficulty: ");
        Object[] difficulty = { "EASY", "MEDIUM", "HARD" };
        diffValueBox = new JComboBox(difficulty);
        okButton = new JButton("Start Game");
        this.add(nameLabel);
        this.add(nameTextField);
        this.add(diffLabel);
        this.add(diffValueBox);
        this.add(okButton);

        setVisible(true);
    }
    /**
     *A beírt név gettere.
     */
    public String getPlayerName(){
        return nameTextField.getText();
    }
    /**
     * A név mezőjének gettere.
     */
    public JTextField getNameTextField(){
        return nameTextField;
    }
    /**
     * A kiválasztott nehézség gettere.
     */
    public String getDifficulty(){
        return (String) diffValueBox.getSelectedItem();
    }
    /**
     * Az indító gomb gettere.
     */
    public JButton getOkButton(){
        return okButton;
    }
}
