import java.io.*;
import java.net.*;

/**
 * Server
 * <p>
 * DESCRIPTION GOES HERE
 * <p>
 * Emerson Barrett
 *
 * @version November 3rd, 2024
 *
 */


public class Server {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(4141);


        while (true){
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);



            //process client requests
            //use existing login and sign up method processing

            clientSocket.close();
        }
    }
}