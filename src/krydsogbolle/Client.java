package krydsogbolle;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    //commit: 14
    //in this class i want to make sure that the server can run on its own and give instruktion to the players about what each other does in terms of the game.
    //the responsability of this class is to make sure the player has somthing to look at aswell as handling some of the computing (however little there is in this program)
    //i found most of the UI elements on the internet

    //varables some to use sooner rather than later
    private JFrame gameFrame = new JFrame("Kruds og Bolle");
    private JLabel mgsLabel = new JLabel();
    private ImageIcon myIcon;
    private ImageIcon theirIcon;
    private JpanelSquare[] gameBox = new JpanelSquare[9];
    private JpanelSquare chosenSquare;
    private static int PORT = 7777;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    //the only variable that wont be used emiedietly is the JpanelSquare, but i will make the class for it next. the plan is to make it the visual gameboard
    //commit: 14 end


    //commit: 15 start
    //here we need to declare the class for the JpanelSquare variables
    public class JpanelSquare extends JPanel{
        JLabel label = new JLabel((Icon)null);

        //via swift this should make a box and take the label to name the small window and also changes its background-color(at the time of commiting the color is cyan for the sake of testing)
        public JpanelSquare(){
            setBackground(Color.CYAN);
            add(label);
        }

        //we can with this change the label
        public void setIcon(Icon icon){
            label.setIcon(icon);
        }
    }
    //commit: 15 end




}
