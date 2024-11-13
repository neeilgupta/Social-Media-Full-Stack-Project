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
 *
 */


public class Server {
    private Socket socket;
    private ServerSocket serverSocket;
    private DataInputStream in;
    private static final int PORT = 4141;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            String line = "";
            while (!line.equals("###") && in.available() > 0) {
                try {
                    line = in.readUTF();
                    String[] userComponents = line.split(",");
                    //UsersService.addUser(userComponents[0], userComponents[1], userComponents[2], userComponents[3]);

                    System.out.println(line);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            socket.close();
            in.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}