import java.io.*;
import java.net.*;

/**
 * Client
 * <p>
 * DESCRIPTION GOES HERE
 * <p>
 * Hossein Hatami
 *
 * @version November 3rd, 2024
 *
 */

public class Client{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4141);

        //send data to and receive data from server
        //basically create the UI, and send messages to the server so the server can do stuff
        //can modify existing sign up and login methods to use that GUI, but the client doesn't process anything
        //use complex GUI's, not just pop-ups

        socket.close();
    }
}


//OptionSignUpOrLogin osul = new OptionSignUpOrLogin();
//Thread SUoL = new Thread(osul);
//Thread signUp = new Thread(new SignUp());
//Thread login = new Thread(new Login());
//        SUoL.start();
//        try {
//                SUoL.join();
//        } catch (InterruptedException ie) {
//        throw new RuntimeException(ie);
//        }
//                if (osul.isSignUpButtonClicked()) {
//        System.out.println("Starting SignUp thread..."); //delete later
//            signUp.start();
//        } else if (osul.isLoginButtonClicked()) {
//        System.out.println("Starting Login thread..."); // delete later
//            login.start();
//        }