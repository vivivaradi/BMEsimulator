package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * A játék fő ablakának az osztálya.
 */
public class MainFrame extends JFrame implements ActionListener{
    /**
     * A fent található menü.
     */
    private JMenuBar menu;
    /**
     * Az ablak többi része, a tartalom.
     */
    private JPanel content;
    /**
     * A játék létrehozására szolgáló panel.
     */
    private GameCreationPanel gameCreation;
    /**
     * A játék panelja.
     */
    private GamePanel currentGame;
    /**
     * Az új játékot indító gomb.
     */
    private JMenuItem new_game;
    /**
     * Konstruktor.
     */
    public MainFrame(){
        this.setTitle("BME Simulator");
        this.setMinimumSize(new Dimension(1280, 720));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setResizable(false);
        this.add(menu, BorderLayout.NORTH);

        content = gameCreation;
        this.add(content, BorderLayout.CENTER);

        this.pack();
    }
    /**
     * Az ablak komponenseinek összerakása.
     */
    public void initComponents(){
        menu = new JMenuBar();
        JMenu game_menu = new JMenu("Game");
        JMenu help_menu = new JMenu("Help");
        new_game = new JMenuItem("New Game");
        new_game.setActionCommand("newgame");
        new_game.setEnabled(false);
        JMenuItem load_game = new JMenuItem("Load Game");
        load_game.setActionCommand("loadgame");
        JMenuItem save_game = new JMenuItem("Save");
        save_game.setActionCommand("savegame");
        JMenuItem help = new JMenuItem("Open help.txt");
        help.setActionCommand("help");

        gameCreation = new GameCreationPanel();
        JButton startGameButton = gameCreation.getOkButton();
        startGameButton.setActionCommand("startgame");
        startGameButton.setEnabled(false);
        JTextField nameField = gameCreation.getNameTextField();
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                changed();
            }
            public void changed(){
                if(nameField.getText().isEmpty())
                    startGameButton.setEnabled(false);
                else
                    startGameButton.setEnabled(true);
            }
        });

        game_menu.add(new_game);
        game_menu.add(load_game);
        game_menu.add(save_game);
        help_menu.add(help);

        menu.add(game_menu);
        menu.add(help_menu);

        new_game.addActionListener(this);
        load_game.addActionListener(this);
        save_game.addActionListener(this);
        help.addActionListener(this);
        startGameButton.addActionListener(this);

    }

    /**
     * Az ActionListener, ami figyeli az eseményeket.
     */
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "startgame": new_game.setEnabled(true);
                currentGame = new GamePanel(gameCreation.getPlayerName(), gameCreation.getDifficulty());
                SwitchPanel(currentGame);
                break;
            case "newgame": new_game.setEnabled(false);
                SwitchPanel(new GameCreationPanel());
                break;
            case "loadgame": new_game.setEnabled(true);
                currentGame = new GamePanel("files/gamedata.txt");
                SwitchPanel(currentGame);
                break;
            case "savegame": new_game.setEnabled(true);
            serialize("files/gamedata.txt");
                break;
            case "help": new HelpFrame();
                break;
        }
    }
    /**
     * Az aktuális panel kicserélése egy másikra.
     * @param panel A látni kívánt panel.
     */
    public void SwitchPanel(JPanel panel){
        content.setVisible(false);
        content.removeAll();
        content = panel;
        this.add(content);
        content.revalidate();
        content.setVisible(true);
    }
    /**
     * A jelenlegi játékállás fájlba írása.
     * @param filename a Fájl neve, ahova írni akarunk.
     */
    public void serialize(String filename){
        try {
            FileOutputStream fout = new FileOutputStream(new File(filename));
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(currentGame.getGame());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
