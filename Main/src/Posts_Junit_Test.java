import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

/**
 * Posts Junit Test
 *
 * Junit test class that tests the different methods of the post service class to ensure proper functionality
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */


class Posts_Junit_Test {

    private PostFileDatabase database;
    private PostService postService;

    @BeforeEach
    void setUp() throws IOException {
        database = new PostFileDatabase("test_post_database.ser"); // Assuming a test file
        postService = new PostService(database);
        // Clear any existing data in the test database file
        database.clear(); // Ensure this method clears the database for a fresh start
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clear test data after each test to prevent interference between tests
        database.clear();
    }

    @Test
    void testAddPost() {
        //create user with unique id
        User user = new User(User.numUsers++, "user1", "pass123", "oney");

        postService.addPost(15,"this is the first post",user);

        // Check that the user was added to the file database
        Post storedPost = database.retrievePost(15);
        assertNotNull(storedPost);
        assertEquals(15, storedPost.getID());
        assertEquals("this is the first post", storedPost.getContent());

    }

    @Test
    void testSearchPost() {
        User user = new User(User.numUsers++, "user2","pass123","twoy");
        Post post = new Post(10, "this is the second post", user, 0, 0);

        database.storePost(post);

        Post retrievedPost = postService.searchPost(10);
        // Verify that the retrieved user is correct
        assertNotNull(retrievedPost);
        assertEquals(10, retrievedPost.getID());
        assertEquals("this is the second post", retrievedPost.getContent());

    }


    @Test
    void testViewPost() {
        User user = new User(User.numUsers++,"user3", "pass123", "threey");
        Post post = new Post(64, "this is the third post", user, 0, 0);
        database.storePost(post);

        Post viewedPost = postService.viewPost(64);

        // Verify that the viewed post matches the expected post
        assertNotNull(viewedPost);
        assertEquals(64, viewedPost.getID());
        assertEquals("this is the third post", viewedPost.getContent());

    }
}
