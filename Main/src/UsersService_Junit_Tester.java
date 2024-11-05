import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class UsersService_Junit_Tester {

    private FileDatabase database; //Firebase object
    private UsersService userService; //User service object

    @BeforeEach
    void setUp() throws IOException {
        // Initialize the FileDatabase and UserService
        database = new FileDatabase("UserInfo.txt"); // Assuming a test file
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
        userService.addUser("Sameer_Dadoo", "password");

        // Check that the user was added to the file database
        User storedUser = database.retrieveUser("Sameer_Dadoo");
        assertNotNull(storedUser); //This parameter most not be null
        assertEquals("Sameer_Dadoo", storedUser.getUsername());
    }

    @Test
    void testSearchUser() {
        User user = new User("Sameer_Dadoo", "password");
        database.storeUser(user);

        User retrievedUser = userService.searchUser("Sameer_Dadoo");

        // Verify that the retrieved user is correct
        assertNotNull(retrievedUser);//This parameter must not be null
        assertEquals("Sameer_Dadoo", retrievedUser.getUsername());
    }

    @Test
    void testFollowUser() {
        User john = new User("john_doe", "password123");
        User jane = new User("jane_doe", "password456");

        john.follow(jane);

        // Assert that Jane is now in John's followers list
        assertTrue(john.getFollowers().contains(jane));
    }

    @Test
    void testUnfollowUser() {
        User sam = new User("sam_doe", "password123");
        User sammy = new User("sammy_doe", "password456");

        sam.follow(sammy);
        sam.unfollow(sammy);

        // Assert that Jane is no longer in John's followers list
        assertFalse(sam.getFollowers().contains(sammy));
    }

    @Test
    void testBlockUser() {
        User john = new User("john_doe", "password123");
        User jane = new User("jane_doe", "password456");

        john.block(jane);

        // Assert that Jane is in John's blocked users list and not in followers
        assertTrue(john.getBlockedUsers().contains(jane));
        assertFalse(john.getFollowers().contains(jane));
    }

    @Test
    void testUnblockUser() {
        User john = new User("john_doe", "password123");
        User jane = new User("jane_doe", "password456");

        john.block(jane);
        john.unblock(jane);

        // Assert that Jane is no longer in John's blocked users list
        assertFalse(john.getBlockedUsers().contains(jane));
    }

    @Test
    void testViewUser() {
        User user = new User("john_doe", "password123");
        database.storeUser(user);

        User viewedUser = userService.viewUser("john_doe");

        // Verify that the viewed user matches the expected user
        assertNotNull(viewedUser); //This parameter must not be null
        assertEquals("john_doe", viewedUser.getUsername());
    }

    @Test
    void testAddFollower() {
        User john = new User("john_doe", "password123");
        User jane = new User("jane_doe", "password456");

        database.storeUser(john);
        database.storeUser(jane);

        userService.addFollower(john, jane);

        // Assert that Jane is now in John's followers
        assertTrue(john.getFollowers().contains(jane));
    }

    @Test
    void testRemoveFollower() {
        User john = new User("john_doe", "password123");
        User jane = new User("jane_doe", "password456");

        john.follow(jane);
        userService.removeFollower(john, jane);

        // Assert that Jane is no longer in John's followers
        assertFalse(john.getFollowers().contains(jane));
    }
}
