import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * Server
 * <p>
 * Initiates the server, and creates threads for ServerApp that complete the tasks requested by the client
 * <p>
 * Emerson Barrett, Hossein Hatami
 *
 * @version November 17, 2024
 */


public class Server{
    public static void main(String[] args) throws Exception{
        try (ServerSocket serverSocket = new ServerSocket(4141)) {
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