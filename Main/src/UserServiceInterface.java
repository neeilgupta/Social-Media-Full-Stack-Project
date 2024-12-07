/**
 * UserService Interface
 *
 * Interface for the UsersServie class 
 *
 * Sameer Dadoo
 *
 * @version November 4th, 2024
 *
 */
public interface UserServiceInterface {
        // Method to add a user
        void addUser(int userID, String username, String password, String displayName);

        // Method to search for a user by username
        User searchUser(String username);

        // Method to view user details by username
        User viewUser(String username);

        // Method to add a follower to a user
        void addFollower(User currentUser, User otherUser);

        // Method to remove a follower from a user
        void removeFollower(User currentUser, User otherUser);
 }


