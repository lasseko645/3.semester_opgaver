package krydsogbolle;

import java.net.*;

public class Serverside {


    public static void main(String[] args) throws Exception {


        //commit:1 start
        //i made the server from the serversocket class in the java libary
        //atm it does nothing but that will change in the future("outdated comment")
        ServerSocket server = new ServerSocket(7777);
        System.out.println("server is running...");
        //commit:1 end


        //commit:2 start
        //i made the loop that is ment to make the game run later,
        //this may be subject to change in future iterations ("outdated comment")
        try {
            while (true){

            }
        } finally {
            server.close();
        }
        //commit:2 end
    }




}
