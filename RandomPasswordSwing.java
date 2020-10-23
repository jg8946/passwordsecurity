package randompasswordswing;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author john
 */
public class RandomPasswordSwing {
    
    public JFrame frameSetup() {
        JFrame initialFrame = new JFrame("Random Secure Password Generator");
        initialFrame.setSize(500, 150);
        initialFrame.setLayout(new FlowLayout());
        initialFrame.setLocationByPlatform(true);
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panelOne = new JPanel();
        JLabel passwordArea = new JLabel("Welcome to the password generator!");
        panelOne.add(passwordArea);
        JPanel panelTwo = new JPanel();
        JButton generatePassword = new JButton("Generate a password...");
        panelTwo.add(generatePassword);
        
        generatePassword.addActionListener((ActionEvent e) -> {
            passwordArea.setText("");
            HashMap<Integer, String> tempMap = fillMap();
            String generatedPassword = getMapChars(tempMap);
            passwordArea.setText("Your password is " + generatedPassword);
        });
        
        initialFrame.add(panelOne);
        initialFrame.add(panelTwo);
        return initialFrame;
    }

    public static void main(String[] args) {
        RandomPasswordSwing rps = new RandomPasswordSwing();
        JFrame generatorFrame = rps.frameSetup();
        generatorFrame.setVisible(true);
    }
    
     /* Creating a hash table of String variables, beggining with upper and lower case letters,
    and continuing to numbers and special characters. */
    public HashMap<Integer, String> fillMap() {
        HashMap<Integer, String> passwordChars = new HashMap<Integer, String>(){{
        
        put(0, "A"); put(1, "B"); put(2, "C"); put(3, "D"); put(4, "E"); put(5, "F");
        put(6, "G"); put(7, "H"); put(8, "I"); put(9, "J"); put(10, "K"); put(11, "L");
        put(12, "M"); put(13, "N"); put(14, "O"); put(15, "P"); put(16, "Q"); put(17, "R");
        put(18, "S"); put(19, "T"); put(20, "U"); put(21, "V"); put(22, "W"); put(23, "X");
        put(24, "Y"); put(25, "Z");
        
        put(26, "a"); put(27, "b"); put(28, "c"); put(29, "d"); put(30, "e"); put(31, "f");
        put(32, "g"); put(33, "h"); put(34, "i"); put(35, "j"); put(36, "k"); put(37, "l");
        put(38, "m"); put(39, "n"); put(40, "o"); put(41, "p"); put(42, "q"); put(43, "r");
        put(44, "s"); put(45, "t"); put(46, "u"); put(47, "v"); put(48, "w"); put(49, "x");
        put(50, "y"); put(51, "z");
        
        put(52, "1"); put(53, "2"); put(54, "3"); put(55, "4"); put(56, "5"); put(57, "6");
        put(58, "7"); put(59, "8"); put(60, "9"); put(61, "0");
        
        put(62, "!"); put(63, "£"); put(64,"$"); put(65, "%"); put(66, "^"); put(67, "&");
        put(68, "*"); put(69, "("); put(70, "(");
        
        }};
        return passwordChars;
    }
    
    /* Code that interates over each character in the password, 
    pulling a different character from the map each time.
    It is ensured that there is at least one of each type of character 
    through the usage of a switch statement.*/
    public String getMapChars(HashMap<Integer, String> h) {
        int mapKey;
        String mapString;
        String tempPass = "";
        Random rand = new Random();
        for (int i = 0; i < 13; i++) {
            switch(i) {
                case 2:
                    mapKey = rand.nextInt(25);
                    mapString = h.get(mapKey);
                    tempPass = tempPass.concat(mapString);
                    break;
                case 3:
                    mapKey = (26 + (rand.nextInt(25)));
                    mapString = h.get(mapKey);
                    tempPass = tempPass.concat(mapString);
                    break;
                case 4:
                    mapKey = (52 + (rand.nextInt(9)));
                    mapString = h.get(mapKey);
                    tempPass = tempPass.concat(mapString);
                    break;
                case 9:
                    mapKey = (62 + (rand.nextInt(8)));
                    mapString = h.get(mapKey);
                    tempPass = tempPass.concat(mapString);
                    break;
                default :
                    mapKey = rand.nextInt(70);
                    mapString = h.get(mapKey);
                    tempPass = tempPass.concat(mapString);
                    break;
            }
        }
        return tempPass;
    }
    
}
