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
            while (!line.equals("###")) {
                try {
                    line = in.readUTF();
                    String[] userComponents = line.split(",");
                    UsersService.addUser(userComponents[0], userComponents[1], userComponents[2], userComponents[3]);

                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);


                        //process client requests
                        //use existing login and sign up method processing

                        clientSocket.close();
                    }
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main (String[]args){
        new Server();
    }
}