package passwordstrengthswing;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * A variant of a prior password strength tester reconfigured to work with a simple Swing interface.
 */
public class PasswordStrengthSwing {
    
    /* An enumerated list describing whether each character in the password string is of a new type.*/
    enum charType {
        LOWERCASE, UPPERCASE, DIGIT, SPECIAL, ALREADY_ENTERED
    }
    
    /* A function to set up the initially functioning window. */
    public JFrame setupFrame() {
        JFrame frame = new JFrame("Password Strength Checker");
        frame.setSize(450,300);
        frame.setLayout(new FlowLayout());
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        JPanel panelThree = new JPanel();
        JLabel intro = new JLabel("Please enter the password you wish to test:");
        panelOne.add(intro);
        JTextField passwordEntryPane = new JTextField(20);
        JButton testPassword = new JButton("Check strength");
        panelTwo.add(passwordEntryPane);
        panelTwo.add(testPassword);
        JTextArea strengthResult = new JTextArea();
        panelThree.add(strengthResult);
        frame.add(panelOne);
        frame.add(panelTwo);
        frame.add(panelThree);

        /* Action in response to the user clicking the "Check strength" button. 
        The text the user has entered in the entry text field is ran through the 
        test password feature and returned in the strength result text area.  */
        testPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                strengthResult.setText("");
                String password = passwordEntryPane.getText();
                System.out.println(password);
                strengthResult.append("The password you have entered is " + password + "\n");
                String results  = testPassword(password);
                System.out.println(results);
                strengthResult.append(results);
            }
        });
        
        return frame;  
    }
    
    /* The main program, which sets up a new instance of the Swing frame. */
    public static void main(String[] args) {
        PasswordStrengthSwing pss = new PasswordStrengthSwing();
        JFrame passwordFrame = pss.setupFrame();
        passwordFrame.setVisible(true);
        
    }
    
    /* Function for testing the password. 
    A complexity score and booleans for what types of characters are present are initialized.*/
    public String testPassword(String p) {
        int complexity = 0;
        String textToReturn = "";
        boolean containsLowerCase = false, containsUpperCase = false, containsNumber = false, containsSpecialChar = false;
        /* The string is changed to a character list to examine each character in turn. */
        char[] characterList = p.toCharArray();
        if (characterList.length >= 11) {
            textToReturn = textToReturn.concat("This password meets the length standard.\n");
            System.out.println(textToReturn);
            complexity++;
        }
        /* A loop that checks each character to see if is a enumerated type not already counted.*/
        for (int i = 0; i < characterList.length; i++) {
            
            charType myCharType = charType.ALREADY_ENTERED;
            
            boolean charIsLowerCase = Character.isLowerCase(characterList[i]);
            if ((charIsLowerCase == true) && (containsLowerCase == false)) {
                myCharType = charType.LOWERCASE;
                containsLowerCase = true;
            }
            boolean charIsUppercase = Character.isUpperCase(characterList[i]);
            if ((charIsUppercase == true) && (containsUpperCase == false)) {
                myCharType = charType.UPPERCASE;
                containsUpperCase = true;
            }
            boolean charIsNumber = Character.isDigit(characterList[i]);
            if ((charIsNumber == true) && (containsNumber == false)) {
                myCharType = charType.DIGIT;
                containsNumber = true;
            }
            boolean charIsNotSpecialChar = Character.isLetterOrDigit(characterList[i]);
            if ((charIsNotSpecialChar == false) && (containsSpecialChar == false)) {
                myCharType = charType.SPECIAL;
                containsSpecialChar = true;
            }
            
            switch(myCharType) {
                case LOWERCASE :
                    textToReturn = textToReturn.concat("This password contains a lower-case letter.\n");
                    complexity++;
                    break;
                case UPPERCASE :
                    textToReturn = textToReturn.concat("This password contains an upper-case letter.\n");
                    complexity++;
                    break;
                case DIGIT :
                    textToReturn = textToReturn.concat("This password contains at least one number.\n");
                    complexity++;
                    break;
                case SPECIAL :
                    textToReturn = textToReturn.concat("This password contains punctuation.\n");
                    complexity++;
                    break;
                case ALREADY_ENTERED :
                    break;
            }
        }
        
        textToReturn = textToReturn.concat("Your passwords complexity score is " + complexity + "\n");
        if (complexity == 5) {
            textToReturn = textToReturn.concat("This password is secure.");
        } else {
            textToReturn = textToReturn.concat("This password isn't secure.");
        }
        return textToReturn;
    }
}
