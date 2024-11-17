import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Client Test
 * <p>
 *     JUnit test suite designed to verify the functionality of the Client class, including methods for username validation, password generation,
 *     and user sign-up interactions. It simulates server-client communication to ensure correct data handling and behavior, testing edge cases like
 *     username length and format as well as password generation logic.
 * <p>
 * Neeil Gupta
 *
 * @version November 17th, 2024
 */
public class ClientTest {
    private static final int TEST_PORT = 4141;
    private ServerSocket serverSocket;
    private Thread serverThread;

    @BeforeEach
    public void setUp() throws IOException {
        //Start of a simple test that simulates the server
        serverSocket = new ServerSocket(TEST_PORT);
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Socket client = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                    String input;
                    while((input = in.readLine()) != null) {
                        //We are able to see the recieved messages
                        System.out.println("Server recieved " + input);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        serverThread.start();
    }
    @Test
    public void testValidUsername() {
        Client client = new Client();
        assertTrue(client.validUsernameSU("Valid_User1"));
        assertFalse(client.validUsernameSU("ab"), "Expected invalid username (too short");
        assertFalse(client.validUsernameSU("thisusernameiswaytoolong"), "Excpected invalid username(too long)");
        assertFalse(client.validUsernameSU("invalid!name"), "Expected invalid useranme(special characters");
    }

    @Test
    public void testAutogeneratePassword() {
        Client client = new Client();
        client.passwordField = new JPasswordField();

        //autogenerates password
        client.autogenerate();
        String generatedPassword = client.passwordField.getText();

        assertNotNull(generatedPassword, "Generated password should not be null");
        assertEquals(String.valueOf(30), generatedPassword.length(), "Generated password should have 30 characters");
    }

    @Test
    void testSignUpPageInteraction() throws IOException {
        Client client = new Client();
        //Making the GUI fields
        client.usernameField = new JTextField("TestUser");
        client.displayNameField = new JTextField("TestDisplay");
        client.passwordField = new JPasswordField("TestPassword");

        //This is the user input when signing up
        client.signUpPage();
        client.username = client.usernameField.getText();
        client.displayName = client.displayNameField.getText();
        client.password = client.passwordField.getText();
        String expectedOutput = client.userID + "," + client.username + "," + client.password + "," + client.displayName;

        //Sends expectedOutput to the server
        try{
            Socket socket = new Socket("localhost", TEST_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(expectedOutput);
            String response = in.readLine();
            assertEquals("Server recieved: " + expectedOutput, response, "Expected server acknowledgement of sign-up data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    //resets and cleans up resources from server
    public void tearDown() throws IOException {
        if(serverSocket != null) {
            serverSocket.close();
        }
        if(serverThread != null) {
            serverThread.interrupt();
        }
    }
}