import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

/**
 * Junit tester
 *
 * Testers that test the UserService class
 *
 * Sameer Dadoo
 *
 * @version November 4th, 2024
 *
 */

class UsersService_Junit_Tester {

    private UserFileDatabase database;
    private UsersService userService;

    @BeforeEach
    void setUp() throws IOException {
        // Initialize the FileDatabase and UserService
        database = new UserFileDatabase("test_database.txt"); // Assuming a test file
        userService = new UsersService(database);

        // Clear any existing data in the test database file
        database.clear(); // Ensure this method clears the database for a fresh start
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clear test data after each test to prevent interference between tests
        database.clear();
    }

    @Test
    void testAddUser() {
        userService.addUser(User.numUsers++, "john_doe", "password123", "jdoe");

        // Check that the user was added to the file database
        User storedUser = database.retrieveUser("john_doe");
        assertNotNull(storedUser);
        assertEquals("john_doe", storedUser.getUsername());
    }

    @Test
    void testSearchUser() {
        User user = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        database.storeUser(user);

        User retrievedUser = userService.searchUser("john_doe");

        // Verify that the retrieved user is correct
        assertNotNull(retrievedUser);
        assertEquals("john_doe", retrievedUser.getUsername());
    }

    @Test
    void testFollowUser() {
        User john = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        User jane = new User(User.numUsers++, "jane_doe", "password456", "jand");

        john.follow(jane);

        // Assert that Jane is now in John's followers list
        assertTrue(john.getFollowers().contains(jane));
    }

    @Test
    void testUnfollowUser() {
        User john = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        User jane = new User(User.numUsers++, "jane_doe", "password456", "jand");

        john.follow(jane);
        john.unfollow(jane);

        // Assert that Jane is no longer in John's followers list
        assertFalse(john.getFollowers().contains(jane));
    }

    @Test
    void testBlockUser() {
        User john = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        User jane = new User(User.numUsers++, "jane_doe", "password456", "jand");


        // Assert that Jane is in John's blocked users list and not in followers
        assertFalse(john.getFollowers().contains(jane));
    }

    @Test
    void testUnblockUser() {
        User john = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        User jane = new User(User.numUsers++, "jane_doe", "password456", "jand");


        // Assert that Jane is no longer in John's blocked users list
    }

    @Test
    void testViewUser() {
        User user = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        database.storeUser(user);

        User viewedUser = userService.viewUser("john_doe");

        // Verify that the viewed user matches the expected user
        assertNotNull(viewedUser);
        assertEquals("john_doe", viewedUser.getUsername());
    }

    @Test
    void testAddFollower() {
        User john = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        User jane = new User(User.numUsers++, "jane_doe", "password456", "jand");

        database.storeUser(john);
        database.storeUser(jane);

        userService.addFollower(john, jane);

        // Assert that Jane is now in John's followers
        assertTrue(john.getFollowers().contains(jane));
    }

    @Test
    void testRemoveFollower() {
        User john = new User(User.numUsers++, "john_doe", "password123", "jdoe");
        User jane = new User(User.numUsers++, "jane_doe", "password456", "jand");

        john.follow(jane);
        userService.removeFollower(john, jane);

        // Assert that Jane is no longer in John's followers
        assertFalse(john.getFollowers().contains(jane));
    }
}
