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
    private UsersService userService;
    private UserFileDatabase database;

    public Server() {
        database = new UserFileDatabase("userinfo.txt");
        database.migrateSerializedUsers("path/to/ser/directory"); // Migrate data
        userService = new UsersService(database);
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
                    String action = line.substring(0, line.indexOf("##"));
                    String input = line.substring(line.indexOf(" ") + 1);
                    if (action.equals("create")) {
                        createUser(input);
                    } else if (action.equals("login")) {

                    }

                    line = "###";
                } catch (IOException e){
                    e.printStackTrace();
                    line = "###";
                }
            }
            socket.close();
            in.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createUser(String userInfo){
        String[] userComponents = userInfo.split(",");
        System.out.println(userComponents[0]);
        System.out.println(userComponents[1]);
        System.out.println(userComponents[2]);
        System.out.println(userComponents[3]);
        UserFileDatabase database = new UserFileDatabase("user_database.txt");
        UsersService usersService = new UsersService(database);
        User user = new User(Integer.parseInt(userComponents[0]), userComponents[1], userComponents[2],
                userComponents[3]);
        usersService.addUser(Integer.parseInt(userComponents[0]), userComponents[1], userComponents[2],
                userComponents[3]);
    }

    public static void main(String[] args) {
        // Initialize database
        UserFileDatabase database = new UserFileDatabase("userinfo.txt");

        // Migrate serialized user data
        database.migrateSerializedUsers("path/to/ser/directory");

        // Initialize the service
        UsersService userService = new UsersService(database);

        System.out.println("Application started, and users migrated.");

        new Server();

    }
}