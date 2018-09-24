package sockettest;

import java.net.ServerSocket;
import java.net.Socket;

public class Host {
    public static void main(String[] args) {
        try {


        ServerSocket server = new ServerSocket(777);
        Socket socket = server.accept();
            System.out.println("vi fik det sku");
        }   catch (Exception e){

        }
    }
}
