import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * Server
 * <p>
 * DESCRIPTION GOES HERE
 * <p>
 * Emerson Barrett
 *
 * @version November 3rd, 2024
 */


public class Server{
    public static void main(String[] args) throws Exception{
        try(ServerSocket serverSocket = new ServerSocket(4141)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerApp sa = new ServerApp(socket);
                sa.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}