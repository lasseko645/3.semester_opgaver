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








}
