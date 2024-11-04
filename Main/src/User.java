//Made by both Neeil and Sameer to be utilized for saving in databases and newsfeed
import java.util.*;
public class User extends Main {
    private static User currentUser;
    //Neeil's instance variables
    private int userID;
    private String username;
//Sameer's instance variables
    private List<User> followers;
    private List<User> blockedUsers;
    private String email = "";
//Neeil's methods and constructers
    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
//Sameer methods and constructers:
    public User(String username, String password) {
        // Properties:
        this.followers = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
    }

    // Method follow(otherUser: User)
    public void follow(User otherUser) {
        // If otherUser is not blocked and otherUser is not in followers
        if (!blockedUsers.contains(otherUser) && !followers.contains(otherUser)) {
            followers.add(otherUser);
        }
    }

    // Method unfollow(otherUser: User)
    public void unfollow(User otherUser) {
        // If otherUser is in followers
        followers.remove(otherUser);
    }

    // Method block(otherUser: User)
    public void block(User otherUser) {
        // If otherUser is in followers
        followers.remove(otherUser);
        // If otherUser is not in blockedUsers
        if (!blockedUsers.contains(otherUser)) {
            blockedUsers.add(otherUser);
        }
    }

    // Method unblock(otherUser: User)
    public void unblock(User otherUser) {
        // If otherUser is in blockedUsers
        blockedUsers.remove(otherUser);
    }
}
