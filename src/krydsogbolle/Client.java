package krydsogbolle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client extends Thread {


    //commit:4
    //ill make a list of variables that will be needed for the game later in the class
    //this part is mainly to give more clarity of the bigger picture for the programming team
    Client enamy;
    char x_Or_O;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    //commit:4 end


    //commit:5
    //I will start with my socket programming here and later get back to the logic of the game itself
    //i make the individual socket for every player.
    //my goal with this is to make posible for more than two people two pair up in seperate pairs and play with each other, and ONLY eachother
    public Client(Socket socket, char x_Or_O){
        this.socket = socket;
        this.x_Or_O = x_Or_O;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            output.println("Welcome Player:" + x_Or_O);
            output.println("Waiting for player to connect...");
        }   catch (Exception e){
            System.out.println("oponent disconnected" + e);
        }
    }
    //i am not sure if this will do it but it should surfice acording to my documentation
    //commit:5 end

    //commit:6 start
    //i will make a method that the server can use to set any given clients opponent
    public void setOpponent(Client opponent){
        this.enamy = opponent;
    }
    //should be as sipmle as that, ill work out the heavy logic on the serverside backend.
    //commit:6 end

    //commit:7 start
    //this method will be used to let the server register a move from any given client
    public void enemyMadeMove(int tileID){
        System.out.println("your opponent marked: " + tileID);
    }
    //i think that should do it, but i will come back to it laterif i need to
    //if i come back to it, it will be in a commit inside the method
    //commit:7 end

    //commit:8
    //i now take the step to build the method that will control what happens while the client threads are running.
    //a part of the logic is taken from the internet and not understood intirely
    //the possibleMove method, winnerFound method and the fillBoard method are methods for later declaration
    public void run(){
        try {

            output.println("both players are ready player X makes the first move...");

            if(x_Or_O == 'X'){
                output.println("you are player X, make your move...");
            }

            while (true) {
                String cmd = input.readLine();
                if (cmd.startsWith("Move")) {
                    int tileID = Integer.parseInt(cmd.substring(5));
                    if (possibleMove(tileID, this)) {
                        output.println("Move Made!");
                        //this next line, i am slightly confused about the logic inwolved.
                        output.println(winnerFound() ? "Victory" : fillBoard() ? "Tie" : "");
                    } else {
                        output.println("Quitting...");
                    }
                } else if (cmd.startsWith("Quitting...")) {
                    return;
                }
            }
        }   catch (Exception e){
            System.out.println("player disconnected: " + e);
        }   finally {
            try {
                socket.close();
            }   catch (Exception e){

            }
        }
    }
    //det skulle være logikken gjordt godt nok til at virke,
    //de metoder som ikke er lavet indu er planlagt før skrivning af kode og skulle oplyses senere i projektet
    //commit:8 end









}
