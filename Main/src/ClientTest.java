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

    @Test
    void testCreatePost() throws IOException {
        User user = new User(1, "TestUser", "password", "DisplayName");
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.createPost("This is a test post", user);

        String expectedMessage = "createPost##0,This is a test post,1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for createPost");
    }

    @Test
    void testCreateComment() throws IOException {
        User user = new User(1, "TestUser", "password", "DisplayName");
        Post post = new Post(1, "Test Post Content", user);
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.createComment("This is a test comment", user, post);

        String expectedMessage = "createComment##0,This is a test comment,1,1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for createComment");
    }

    @Test
    void testLikePost() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.likePost(1, 1);

        String expectedMessage = "likePost##1,1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for likePost");
    }

    @Test
    void testDislikePost() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.dislikePost(1, 1);

        String expectedMessage = "dislikePost##1,1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for dislikePost");
    }

    @Test
    void testLikeComment() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.likeComment(1, 1);

        String expectedMessage = "likeComment##1,1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for likeComment");
    }

    @Test
    void testDislikeComment() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.dislikeComment(1, 1);

        String expectedMessage = "dislikeComment##1,1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for dislikeComment");
    }

    @Test
    void testFollow() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.follow(1, 2);

        String expectedMessage = "follow##1,2";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for follow");
    }

    @Test
    void testUnfollow() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.unfollow(1, 2);

        String expectedMessage = "unfollow##1,2";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for unfollow");
    }

    @Test
    void testRemoveAccount() throws IOException {
        Client client = new Client();
        client.out = new DataOutputStream(new ByteArrayOutputStream());

        client.removeAccount(1);

        String expectedMessage = "removeAccount##1";
        assertEquals(expectedMessage, client.out.toString().trim(), "Expected message format for removeAccount");
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