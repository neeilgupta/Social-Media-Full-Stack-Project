public class UsersService implements UserServiceInterface {
    // Properties:
    private UserFileDatabase database;

    public UsersService(UserFileDatabase database) {
        this.database = database;
    }

    // Constructor(database: Database)
    //ignore the fact that there are two constructors. Intellij being weird.
    public void UserService(UserFileDatabase database) {
        this.database = database;
    }

   @Override
    public void addUser(String username, String password) {
        // Create new User object
        User user = new User(username, password);
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

    @Override
    public void blockUser(User currentUser, User otherUser) {
        // Call currentUser.block(otherUser)
        currentUser.block(otherUser);
        // Update blocked list in the database
        database.updateUser(currentUser);
    }

    @Override
    public void unblockUser(User currentUser, User otherUser) {
        // Call currentUser.unblock(otherUser)
        currentUser.unblock(otherUser);
        // Update blocked list in the database
        database.updateUser(currentUser);
    }

}
