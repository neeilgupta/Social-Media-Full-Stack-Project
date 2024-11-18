import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * JUnit Tester for ServerApp class.
 * <p>
 * This test suite verifies the functionality of the ServerApp methods, including
 * user, post, and comment operations. It uses simulated sockets and in-memory streams
 * to isolate and test the ServerApp's behavior.
 *
 * Author: [Your Name]
 * Date: November 17, 2024
 * Version: 1.0
 */
public class ServerAppTest {

    private ServerApp serverApp;
    private PipedInputStream pipedInput;
    private PipedOutputStream pipedOutput;

    /**
     * Sets up in-memory streams and initializes the ServerApp instance.
     * This simulates a client-server socket interaction.
     */
    @BeforeEach
    public void setUp() throws IOException {
        pipedInput = new PipedInputStream();
        pipedOutput = new PipedOutputStream(pipedInput);
        serverApp = new ServerApp(new PipedSocket(pipedInput, pipedOutput));
    }

    /**
     * Tests the createUser method to ensure it processes user input and adds a user correctly.
     */
    @Test
    public void testCreateUser() throws IOException {
        // Simulate client input for creating a user
        writeToClient("createUser##1,john_doe,password123,John\n###\n");

        // Run the server logic
        serverApp.run();

        // Verify the user was added
        UserFileDatabase database = new UserFileDatabase("users.ser");
        User user = database.retrieveUser("john_doe");
        assertNotNull(user, "User should be added to the database");
        assertEquals("john_doe", user.getUsername(), "Usernames should match");
    }

    /**
     * Tests the createPost method to ensure it creates a post for a valid user.
     */
    @Test
    public void testCreatePost() throws IOException {
        // Add a user to the system
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();

        // Simulate client input for creating a post
        writeToClient("createPost##1,This is a post,john_doe\n###\n");
        serverApp.run();

        // Verify the post was created
        PostFileDatabase database = new PostFileDatabase("posts.ser");
        Post post = database.retrievePost(1);
        assertNotNull(post, "Post should be created in the database");
        assertEquals("This is a post", post.getContent(), "Post content should match");
    }

    /**
     * Tests the follow method to ensure one user can follow another.
     */
    @Test
    public void testFollow() throws IOException {
        // Add two users
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createUser##2,jane_doe,password456,Jane\n###\n");
        serverApp.run();

        // Simulate client input for following
        writeToClient("follow##1,2\n###\n");
        serverApp.run();

        // Verify the follow relationship
        UserFileDatabase database = new UserFileDatabase("users.ser");
        User john = database.retrieveUser("john_doe");
        assertNotNull(john, "User john_doe should exist in the database");
        assertTrue(john.getFollowing().contains("jane_doe"), "John should follow Jane");
    }

    /**
     * Simulates writing to the client socket (as input for the server).
     *
     * @param input The string to write to the server
     * @throws IOException If an I/O error occurs
     */
    private void writeToClient(String input) throws IOException {
        pipedOutput.write(input.getBytes());
        pipedOutput.flush();
    }
}
