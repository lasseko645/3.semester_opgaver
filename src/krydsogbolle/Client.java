package krydsogbolle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            setBackground(Color.WHITE);
            add(label);
        }

        //we can with this change the label
        public void setIcon(Icon icon){
            label.setIcon(icon);
        }

    }
    //commit: 15 end


    //commit: 18 continue
    //i figured out i need som more logik and rather that give the responsibility for the task of using the window to another class.
    //i figured it would be better suited here
    //so what i will do here is make the jpanelsquare be usable(clints has to get at pop-up to play the game trough)
    public static void main(String[] args)throws Exception {
        while (true){
            String playerAdresse = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(playerAdresse);
            client.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            client.gameFrame.setSize(240, 210);
            client.gameFrame.setVisible(true);
            client.gameFrame.setResizable(false);
            client.initiateGame();
        }
    }
    //i have heremade the window pop up for the user of the game however it comes to me that it moght be good to make the players able to play multiple times if they wish
    //i will come back to it in future iterations if i find it nessecary


    //commit: 16 start
    //this method will the the constructor that will make instances of our players when they join the socket server
    //i am not sure if this will work in this commit but i will iterate if it takes further development
    private Client(String adresse) throws Exception{
        Socket c = new Socket(adresse, PORT);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);

        mgsLabel.setBackground(Color.lightGray);
        gameFrame.getContentPane().add(mgsLabel, "South");

        JPanel board = new JPanel();
        board.setBackground(Color.black);
        board.setLayout(new GridLayout(3,3,2,2));

        for (int i = 0; i < gameBox.length; i++){
            final int j = i;
            gameBox[i] = new JpanelSquare();
            gameBox[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    chosenSquare = gameBox[j];
                    output.println("move: " + j);
                }
            });
            board.add(gameBox[i]);
        }
        gameFrame.getContentPane().add(board, "Center");
    }
    //that should do it, im not intirely sure that the gridlayout will work.
    //i dont have an ability to test it right now but documentation states that i should have done it right
    //commit: 16 end


    //commit: 17 start
    //now the method that i think might be the last part of the program
    //this will be the method that will run when a player needs to start the game
    public void initiateGame() throws Exception{
        String response;
        try {
            response = input.readLine();
            if (response.startsWith("Welcome Player:")) {
                char mark = response.charAt(8);
                myIcon = new ImageIcon(mark == 'X' ? "x.gif" : "o.gif");
                theirIcon = new ImageIcon(mark == 'X' ? "o.gif" : "x.gif");
                gameFrame.setTitle("Tic Tac Toe - Player " + mark);
            }
            while (true){
                response = input.readLine();
                if (response.startsWith("Move_Made")) {
                    mgsLabel.setText("Great Move, wait for your opponent");
                    chosenSquare.setIcon(myIcon);
                    chosenSquare.repaint();
                }   else if (response.startsWith("your opponent marked:")){
                    int loc = Integer.parseInt(response.substring(15));
                    gameBox[loc].setIcon(theirIcon);
                    gameBox[loc].repaint();
                    mgsLabel.setText("your opponent made their move, your turn");
                }   else if (response.startsWith("Victory")){
                    mgsLabel.setText("you win... gratz, i guess ???");
                    break;
                }   else if (response.startsWith("Tie")){
                    mgsLabel.setText("this wont work... im srry guys you fucket it up  :(");
                    break;
                }   else if (response.startsWith("you lost")){
                    mgsLabel.setText("you have unfortunatly gotten completely trashed by your supirior opponent, tresure that guy he is better than you in every way.");
                    break;
                }   else if (response.startsWith("Info_Message")){
                    mgsLabel.setText(response.substring(8));
                }
            }
            output.println("Quitting...");
        }   finally {
            socket.close();
        }

    }
    //i believe that is all of the posabilities covered, i will iterate in the future if further development is required
    //commin: 17 end







}
