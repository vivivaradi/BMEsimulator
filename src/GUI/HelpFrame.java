package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A segítség kérésekor megjelenő frame.
 */
public class HelpFrame extends JFrame{
    /**
     * A kiírt segítség szövege.
     */
    private String helpText;
    /**
     * Konstruktor.
     */
    public HelpFrame(){
        helpText = "";
        setPreferredSize(new Dimension(400,200));
        setResizable(false);
        setTitle("Help");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        try {
            readText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JTextArea text = new JTextArea(helpText);
        text.setEditable(false);
        text.setFont(new Font("Lato", Font.PLAIN, 12));
        this.add(text);
        pack();
        setVisible(true);
    }
    /**
     * A szöveg beolvasása a fájlból.
     */
    public void readText() throws IOException {
        String fileName = "files/help.txt";
        InputStreamReader ifr = new FileReader(fileName);
        BufferedReader bfr = new BufferedReader(ifr);
        String line;
        while ((line = bfr.readLine()) != null){
            helpText = helpText.concat(line + '\n');
        }
    }
}
