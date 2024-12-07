/**
 * UserService class
 *
 * Class that search, follows, unfollows, blocks, and unblocks users
 *
 * Sameer Dadoo
 *
 * @version November 4th, 2024
 *
 */
public class UsersService implements UserServiceInterface {
    // Properties:
    private UserFileDatabase database;

    public UsersService(UserFileDatabase database) {
        this.database = database;
    }

    public static void addUser(String userComponent, String userComponent1, String userComponent2, String userComponent3) {
    }

    // Constructor(database: Database)
    //ignore the fact that there are two constructors. Intellij being weird.
    //public void UserService(UserFileDatabase database) {
    //    this.database = database;
    //}

    @Override
    public void addUser(int userID, String username, String password, String displayName) {
        // Create new User object
        User user = new User(userID, username, password, displayName);
        // Store the User object in the database
        database.storeUser(user);
    }

    @Override
    public User searchUser(String username) {
        // Retrieve and return a User object by username from the database
        return database.retrieveUser(username);
    }

    @Override
    public User viewUser(String username) {
        // Retrieve and return User details by username from the database
        return database.retrieveUser(username);
    }

    @Override
    public void addFollower(User currentUser, User otherUser) {
        // Call currentUser.follow(otherUser)
        currentUser.follow(otherUser);
        // Update follower list in the database
        database.updateUser(currentUser);
    }

    @Override
    public void removeFollower(User currentUser, User otherUser) {
        // Call currentUser.unfollow(otherUser)
        currentUser.unfollow(otherUser);
        // Update follower list in the database
        database.updateUser(currentUser);
    }



}