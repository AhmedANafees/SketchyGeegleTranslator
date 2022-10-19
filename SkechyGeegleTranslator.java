
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 *
 * @author nafea8846
 */
public class SkechyGeegleTranslator implements Runnable, ActionListener {

    // Class Variables
    JPanel mainPanel;
    JLabel titleLabel;
    JLabel arrowLabel;
    JTextField inputTextField;
    JButton[] firstLangButton = new JButton[3];
    JButton[] secondLangButton = new JButton[3];
    JButton translateButton;
    Translator myTranslator;

    //constructor that ceates a new transltor
    public SkechyGeegleTranslator() {
        myTranslator = new Translator();
    }

    // Method to assemble our GUI
    @Override
    public void run() {

        // Creats a JFrame. that is 800 pixels by 600 pixels, and closes when you click on the X
        JFrame frame = new JFrame("Skechy Geegle Translator");
        // Makes the X button close the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes the windows 800 pixel wide by 600 pixels tall
        frame.setSize(500, 300);
        // shows the window
        frame.setVisible(true);

        //creating and placeing elements in gui
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titleLabel = new JLabel("Translate a word, lauages avalible now: English, UbbiDubbi and Javanais");
        titleLabel.setBounds(25, 15, 450, 50);

        inputTextField = new JTextField();
        inputTextField.setBounds(20, 60, 450, 25);

        arrowLabel = new JLabel("TO");
        arrowLabel.setBounds(225, 125, 25, 25);

        firstLangButton[0] = new JButton("English");
        firstLangButton[0].setBounds(75, 100, 100, 25);
        firstLangButton[0].addActionListener(this);
        firstLangButton[0].setActionCommand("firstEng");

        firstLangButton[1] = new JButton("UbbiDubbi");
        firstLangButton[1].setBounds(75, 125, 100, 25);
        firstLangButton[1].addActionListener(this);
        firstLangButton[1].setActionCommand("firstUbbi");

        firstLangButton[2] = new JButton("Javanais");
        firstLangButton[2].setBounds(75, 150, 100, 25);
        firstLangButton[2].addActionListener(this);
        firstLangButton[2].setActionCommand("firstJava");

        secondLangButton[0] = new JButton("English");
        secondLangButton[0].setBounds(300, 100, 100, 25);
        secondLangButton[0].addActionListener(this);
        secondLangButton[0].setActionCommand("secondEng");

        secondLangButton[1] = new JButton("UbbiDubbi");
        secondLangButton[1].setBounds(300, 125, 100, 25);
        secondLangButton[1].addActionListener(this);
        secondLangButton[1].setActionCommand("secondUbbi");

        secondLangButton[2] = new JButton("Javanais");
        secondLangButton[2].setBounds(300, 150, 100, 25);
        secondLangButton[2].addActionListener(this);
        secondLangButton[2].setActionCommand("secondJava");

        translateButton = new JButton("Translate");
        translateButton.setBounds(185, 200, 100, 50);
        translateButton.addActionListener(this);
        translateButton.setActionCommand("translate");

        //aading elements to mainPanel
        mainPanel.add(translateButton);
        mainPanel.add(firstLangButton[0]);
        mainPanel.add(firstLangButton[1]);
        mainPanel.add(firstLangButton[2]);
        mainPanel.add(secondLangButton[0]);
        mainPanel.add(secondLangButton[1]);
        mainPanel.add(secondLangButton[2]);
        mainPanel.add(arrowLabel);
        mainPanel.add(inputTextField);
        mainPanel.add(titleLabel);
        frame.add(mainPanel);

    }

    // method called when a button is pressed
    @Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e) {
        // get the command from the action
        String command = e.getActionCommand();
        
        //when first English button is pressed
        if (command.equals("firstEng")) {
            firstLangButton[0].setEnabled(false);
            firstLangButton[1].setEnabled(true);
            firstLangButton[2].setEnabled(true);
        }
        //when first UbbiDubbi button is pressed
        if (command.equals("firstUbbi")) {
            firstLangButton[0].setEnabled(true);
            firstLangButton[1].setEnabled(false);
            firstLangButton[2].setEnabled(true);;
        
        //when first Javanias button is pressed
        }
        if (command.equals("firstJava")) {
            firstLangButton[0].setEnabled(true);
            firstLangButton[1].setEnabled(true);
            firstLangButton[2].setEnabled(false);
        }

        //when second English button is pressed
        if (command.equals("secondEng")) {
            secondLangButton[0].setEnabled(false);
            secondLangButton[1].setEnabled(true);
            secondLangButton[2].setEnabled(true);
        }
        
        //when second UbbiDubbi button is pressed
        if (command.equals("secondUbbi")) {
            secondLangButton[0].setEnabled(true);
            secondLangButton[1].setEnabled(false);
            secondLangButton[2].setEnabled(true);
        }
        
        //when second Javanais button is pressed
        if (command.equals("secondJava")) {
            secondLangButton[0].setEnabled(true);
            secondLangButton[1].setEnabled(true);
            secondLangButton[2].setEnabled(false);
        }
        
        //when translate button is pressed
        if (command.equals("translate")) {
            //get word entered
            String word = inputTextField.getText();

            //when translating English to UbbiDubbi
            if (!firstLangButton[0].isEnabled() && !secondLangButton[1].isEnabled()) {
                String translate = myTranslator.engToUbbiDubbi(word);
                inputTextField.setText(translate);
            } 
            //when translating English to Javanais
            else if (!firstLangButton[0].isEnabled() && !secondLangButton[2].isEnabled()) {
                String translate = myTranslator.engToJava(word);
                inputTextField.setText(translate);
            } 
            //when translating UbbiDubbi to English
            else if (!firstLangButton[1].isEnabled() && !secondLangButton[0].isEnabled()) {
                String translate;
                translate = myTranslator.ubbiDubbiToEng(word);
                inputTextField.setText(translate);
            } 
            //when translating Javanias to English
            else if (!firstLangButton[2].isEnabled() && !secondLangButton[0].isEnabled()) {
                String translate;
                translate = myTranslator.javaToEng(word);
                inputTextField.setText(translate);
            }
            //when translating UbbiDubbi to Javanais
            else if (!firstLangButton[1].isEnabled() && !secondLangButton[2].isEnabled()) {
                String translate;
                translate = myTranslator.ubbiDubbiToEng(word);
                translate = myTranslator.engToJava(translate);
                inputTextField.setText(translate);
            }
            //when translating Javanais to UbbiDubbi
            else if (!firstLangButton[2].isEnabled() && !secondLangButton[1].isEnabled()) {
                String translate;
                translate = myTranslator.javaToEng(word);
                translate = myTranslator.engToUbbiDubbi(translate);
                inputTextField.setText(translate);
            //when both languages entered are the same
            }else if ((!firstLangButton[0].isEnabled() && !secondLangButton[0].isEnabled()) || (!firstLangButton[1].isEnabled() && !secondLangButton[1].isEnabled()) || (!firstLangButton[2].isEnabled() && !secondLangButton[2].isEnabled())){
                inputTextField.setText(word);
            //when nothing is entered
            } else{
                inputTextField.setText("Error");
            }

        }

    }

// Main method to start our program
    public static void main(String[] args) throws IOException {
        // Creates an instance of our program
        SkechyGeegleTranslator gui = new SkechyGeegleTranslator();
        // Lets the computer know to start it in the event thread
        SwingUtilities.invokeLater(gui);
    }
}
