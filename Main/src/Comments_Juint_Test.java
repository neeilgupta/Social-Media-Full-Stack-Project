import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

/**
 * Comment Junit Tester
 *
 * Junit tester that tests the methods of the comment class and comment service class
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */

class Comments_Junit_Test {

    private CommentFileDatabase database;
    private CommentService commentService;

    @BeforeEach
    void setUp() throws IOException {
        database = new CommentFileDatabase("test_comment_database.ser"); // Assuming a test file
        commentService = new CommentService(database);
        // Clear any existing data in the test database file
        database.clear(); // Ensure this method clears the database for a fresh start
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clear test data after each test to prevent interference between tests
        database.clear();
    }

    @Test
    void testAddComment() {
        commentService.addComment(15,"this is the first comment",
                new User(User.numUsers++, "user1", "pass123", "oney"), new Post(98, "this is a post",
                        new User (User.numUsers++, "user2", "pass123", "twoy")));

        // Check that the comment was added to the file database
        Comment storedComment = database.retrieveComment(15);
        assertNotNull(storedComment);
        assertEquals(15, storedComment.getID());
    }

    @Test
    void testSearchComment() {
        Comment comment = new Comment(10,"this is the second comment",
                new User(User.numUsers++, "user2", "pass123", "twoy"),
                new Post (546, "this is another post",
                        new User (User.numUsers++, "user2", "pass123", "twoy")));
        database.storeComment(comment);

        Comment retrievedComment = commentService.searchComment(10);

        // Verify that the retrieved comment is correct
        assertNotNull(retrievedComment);
        assertEquals(10, retrievedComment.getID());
    }


    @Test
    void testViewComment() {
        Comment comment = new Comment(64, "this is the third comment",
                new User(User.numUsers++, "user3", "pass123", "threey"),
                new Post(280, "this is yet another post",
                        new User (User.numUsers++, "user4", "pass123", "foury")));
        database.storeComment(comment);

        Comment viewedComment = commentService.viewComment(64);

        // Verify that the viewed comment matches the expected comment
        assertNotNull(viewedComment);
        assertEquals(64, viewedComment.getID());
    }
}
