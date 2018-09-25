package krydsogbolle;



import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverside {


    public static void main(String[] args) throws Exception {


        //commit:1 start
        //i made the server from the serversocket class in the java libary
        //atm it does nothing but that will change in the future("outdated comment")
        ServerSocket server = new ServerSocket(7000);
        System.out.println("server is running...");
        //commit:1 end


        //commit:2 start
        //i made the loop that is ment to make the game run later,
        //this may be subject to change in future iterations ("outdated comment")
        //commit:13 start
        //i now have more of an idea of the bigger picture and i can now finish this bit
        //this is the loop that will make sure that once you start playing with a person it should stay as the same person
        try {
            while (true){
                TicTacToesGame gameInstance = new TicTacToesGame();
                TicTacToesGame.Client playerO = gameInstance.new Client(server.accept(), 'O');
                TicTacToesGame.Client playerX = gameInstance.new Client(server.accept(), 'X');
                playerO.setOpponent(playerX);
                playerX.setOpponent(playerO);
                gameInstance.playerHost = playerO;
                playerO.start();
                playerX.start();
            }
        } finally {
            server.close();
        }
        //commit:13 end
        //commit:2 end
    }




}
