package krydsogbolle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TicTacToesGame {

    //commit:3 start
    //here i make an array that handle userside information about current board
    //right now this array wont work bacause the Playboard class has not been made yet
    //as default i make all the values nothing
    private Client[] gameBoard = {
            null, null, null,
            null, null, null,
            null, null, null};
    //commit:3 end


    //commit: 9 start
    //i will just create the first client as the server
    Client playerHost;
    //this will do here for now just to keep track of what needs to get done


    //i will create the statement for how to win,
    //i easily found this logic online but read trough it
    //and you'll find that its quite simple slavic walktrought of all possible correct cominations
    public boolean winnerFound() {
        return (
                gameBoard[0] != null && gameBoard[0] == gameBoard[1] && gameBoard[0] == gameBoard[2])
                || (gameBoard[3] != null && gameBoard[3] == gameBoard[4] && gameBoard[3] == gameBoard[5])
                || (gameBoard[6] != null && gameBoard[6] == gameBoard[7] && gameBoard[6] == gameBoard[8])
                || (gameBoard[0] != null && gameBoard[0] == gameBoard[3] && gameBoard[0] == gameBoard[6])
                || (gameBoard[1] != null && gameBoard[1] == gameBoard[4] && gameBoard[1] == gameBoard[7])
                || (gameBoard[2] != null && gameBoard[2] == gameBoard[5] && gameBoard[2] == gameBoard[8])
                || (gameBoard[0] != null && gameBoard[0] == gameBoard[4] && gameBoard[0] == gameBoard[8])
                || (gameBoard[2] != null && gameBoard[2] == gameBoard[4] && gameBoard[2] == gameBoard[6]);

    }
    //this should do it, unfortunately as of the time of this commit i have no way to test if it works so ill just have to trust in my planning
    //commit: 9 end

    //commit: 10 start
    //i will here make the program have the ability to figure out if there are no more empty tiles in on the board
    public boolean fullBoard() {
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == null) {
                return false;
            }
        }
        return true;
    }
    //i think this should be able to be called to check that the board is full or not
    //commit: 10 end


    //commit: 11 start
    //i now need my program to be able to find out if the move a client is trying to do is a legal move
    public synchronized boolean possibleMove(int tileID, Client client) {
        if (client == playerHost && gameBoard[tileID] == null) {
            gameBoard[tileID] = playerHost;
            playerHost = playerHost.enamy;
            playerHost.enemyMadeMove(tileID);
            return true;
        }
        return false;
    }
    //this should be enough logically, as far as i can see to make the information transfer sufficient
    //commit: 11 end


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
        public Client(Socket socket, char x_Or_O) {
            this.socket = socket;
            this.x_Or_O = x_Or_O;
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("Welcome Player:" + x_Or_O);
                output.println("Waiting for player to connect...");
            } catch (Exception e) {
                System.out.println("oponent disconnected" + e);
            }
        }
        //i am not sure if this will do it but it should surfice acording to my documentation
        //commit:5 end

        //commit:6 start
        //i will make a method that the server can use to set any given clients opponent
        public void setOpponent(Client opponent) {
            this.enamy = opponent;
        }
        //should be as sipmle as that, ill work out the heavy logic on the serverside backend.
        //commit:6 end

        //commit:7 start
        //this method will be used to let the server register a move from any given client
        public void enemyMadeMove(int tileID) {
            System.out.println("your opponent marked: " + tileID);
            //commit:12 start
            //i need this small extra bit of code to make
            //i need the program to be able to tell if your opponents move will make them win the match or not
            output.println(winnerFound() ? "you lost" : fullBoard() ? "noone wins" : "");
            //now the program should ask if there is a winner, if its a tie or if nothing happened, and it will ask this every time any given clients opponents move gets registered for them
            //commit:12 end

        }
        //i think that should do it, but i will come back to it laterif i need to
        //if i come back to it, it will be in a commit inside the method
        //commit:7 end

        //commit:8
        //i now take the step to build the method that will control what happens while the client threads are running.
        //a part of the logic is taken from the internet and not understood intirely
        //the possibleMove method, winnerFound method and the fillBoard method are methods for later declaration
        public void run() {
            try {

                output.println("both players are ready player X makes the first move...");

                if (x_Or_O == 'X') {
                    output.println("you are player X, make your move...");
                }

                while (true) {
                    String cmd = input.readLine();
                    if (cmd.startsWith("Move")) {
                        int tileID = Integer.parseInt(cmd.substring(5));
                        if (possibleMove(tileID, this)) {
                            output.println("Move Made!");
                            //this next line, i am slightly confused about the logic inwolved.
                            output.println(winnerFound() ? "Victory" : fullBoard() ? "Tie" : "");
                        } else {
                            output.println("Quitting...");
                        }
                    } else if (cmd.startsWith("Quitting...")) {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("player disconnected: " + e);
            } finally {
                try {
                    socket.close();
                } catch (Exception e) {

                }
            }
        }
        //det skulle være logikken gjordt godt nok til at virke,
        //de metoder som ikke er lavet indu er planlagt før skrivning af kode og skulle oplyses senere i projektet
        //commit:8 end


    }
}