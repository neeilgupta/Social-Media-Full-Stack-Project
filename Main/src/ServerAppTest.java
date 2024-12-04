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
 * Author: Sameer Dadoo
 * Date: November 17, 2024
 * Updated Version
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
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();

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
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();

        writeToClient("createPost##1,This is a post,john_doe\n###\n");
        serverApp.run();

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
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createUser##2,jane_doe,password456,Jane\n###\n");
        serverApp.run();

        writeToClient("follow##1,2\n###\n");
        serverApp.run();

        UserFileDatabase database = new UserFileDatabase("users.ser");
        User john = database.retrieveUser("john_doe");
        assertNotNull(john, "User john_doe should exist in the database");
        assertTrue(john.getFollowing().contains("jane_doe"), "John should follow Jane");
    }

    /**
     * Tests the likePost method to ensure a user can like a post.
     */
    @Test
    public void testLikePost() throws IOException {
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,This is a post,john_doe\n###\n");
        serverApp.run();

        writeToClient("likePost##1,1\n###\n");
        serverApp.run();

        PostFileDatabase database = new PostFileDatabase("posts.ser");
        Post post = database.retrievePost(1);
        assertEquals(1, post.getLikes(), "Post should have one like");
    }

    /**
     * Tests the removeAccount method to ensure a user is removed from the database.
     */
    @Test
    public void testRemoveAccount() throws IOException {
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();

        writeToClient("removeAccount##1\n###\n");
        serverApp.run();

        UserFileDatabase database = new UserFileDatabase("users.ser");
        User user = database.retrieveUser("john_doe");
        assertNull(user, "User should be removed from the database");
    }

    /**
     * Tests the deletePost method to ensure a post is removed from the database.
     */
    @Test
    public void testDeletePost() throws IOException {
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,This is a post,john_doe\n###\n");
        serverApp.run();

        writeToClient("deletePost##1\n###\n");
        serverApp.run();

        PostFileDatabase database = new PostFileDatabase("posts.ser");
        Post post = database.retrievePost(1);
        assertNull(post, "Post should be removed from the database");
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

    @Test
    public void testCreateComment() throws IOException {
        // Add a post and user to simulate valid conditions
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,First Post,john_doe\n###\n");
        serverApp.run();

        // Simulate creating a comment
        writeToClient("createComment##1,This is a comment,1,1\n###\n"); // CommentID, Content, UserID, PostID
        serverApp.run();

        // Verify the comment exists
        CommentFileDatabase database = new CommentFileDatabase("comments.ser");
        Comment comment = database.retrieveComment(1);
        assertNotNull(comment, "Comment should be added to the database");
        assertEquals("This is a comment", comment.getContent(), "Comment content should match");
    }

    @Test
    public void testDislikePost() throws IOException {
        // Add a user and a post
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,First Post,john_doe\n###\n");
        serverApp.run();

        // Simulate disliking the post
        writeToClient("dislikePost##1,1\n###\n"); // PostID, UserID
        serverApp.run();

        // Verify the post's dislike count
        PostFileDatabase database = new PostFileDatabase("posts.ser");
        Post post = database.retrievePost(1);
        assertEquals(1, post.getDislikes(), "Post should have one dislike");
    }

    @Test
    public void testLikeComment() throws IOException {
        // Add a user, post, and comment
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,First Post,john_doe\n###\n");
        serverApp.run();
        writeToClient("createComment##1,This is a comment,1,1\n###\n"); // CommentID, Content, UserID, PostID
        serverApp.run();

        // Simulate liking the comment
        writeToClient("likeComment##1,1\n###\n"); // CommentID, UserID
        serverApp.run();

        // Verify the comment's like count
        CommentFileDatabase database = new CommentFileDatabase("comments.ser");
        Comment comment = database.retrieveComment(1);
        assertEquals(1, comment.getLikes(), "Comment should have one like");
    }

    @Test
    public void testDislikeComment() throws IOException {
        // Add a user, post, and comment
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,First Post,john_doe\n###\n");
        serverApp.run();
        writeToClient("createComment##1,This is a comment,1,1\n###\n"); // CommentID, Content, UserID, PostID
        serverApp.run();

        // Simulate disliking the comment
        writeToClient("dislikeComment##1,1\n###\n"); // CommentID, UserID
        serverApp.run();

        // Verify the comment's dislike count
        CommentFileDatabase database = new CommentFileDatabase("comments.ser");
        Comment comment = database.retrieveComment(1);
        assertEquals(1, comment.getDislikes(), "Comment should have one dislike");
    }

    @Test
    public void testUnfollow() throws IOException {
        // Add two users and establish a follow relationship
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createUser##2,jane_doe,password456,Jane\n###\n");
        serverApp.run();
        writeToClient("follow##1,2\n###\n");
        serverApp.run();

        // Simulate unfollowing
        writeToClient("unfollow##1,2\n###\n"); // CurrentUserID, OtherUserID
        serverApp.run();

        // Verify the unfollow relationship
        UserFileDatabase database = new UserFileDatabase("users.ser");
        User john = database.retrieveUser("john_doe");
        assertNotNull(john, "User john_doe should exist in the database");
        assertFalse(john.getFollowing().contains("jane_doe"), "John should no longer follow Jane");
    }

    @Test
    public void testBlockUser() throws IOException {
        // Add two users
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createUser##2,jane_doe,password456,Jane\n###\n");
        serverApp.run();

        // Simulate blocking
        writeToClient("blockUser##1,2\n###\n"); // CurrentUserID, OtherUserID
        serverApp.run();

        // Verify the block relationship
        UserFileDatabase database = new UserFileDatabase("users.ser");
        User john = database.retrieveUser("john_doe");
        assertNotNull(john, "User john_doe should exist in the database");
        assertTrue(john.getBlockedUsers().contains("jane_doe"), "John should have blocked Jane");
    }

    @Test
    public void testDeleteComment() throws IOException {
        // Add a user, post, and comment
        writeToClient("createUser##1,john_doe,password123,John\n###\n");
        serverApp.run();
        writeToClient("createPost##1,First Post,john_doe\n###\n");
        serverApp.run();
        writeToClient("createComment##1,This is a comment,1,1\n###\n"); // CommentID, Content, UserID, PostID
        serverApp.run();

        // Simulate deleting the comment
        writeToClient("deleteComment##1\n###\n"); // CommentID
        serverApp.run();

        // Verify the comment no longer exists
        CommentFileDatabase database = new CommentFileDatabase("comments.ser");
        Comment comment = database.retrieveComment(1);
        assertNull(comment, "Comment should be removed from the database");
    }







}
