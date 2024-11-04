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

    // JOptionPane.showMessageDialog(panel, "Perfect! Your username is " + username);
    //        panel.removeAll();
    //        frame.remove(panel);
    //        frame.dispose();
    //
    //        panel.add(new JLabel("Please enter your display name:"));
    //        frame.add(panel);
    //
    //        while (true) {
    //            displayName = JOptionPane.showInputDialog(panel, "Please enter your display name:");
    //            result = JOptionPane.showConfirmDialog(null, panel, "Create your display name",
    //                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    //            if (result == JOptionPane.OK_OPTION) {
    //                if (displayName == null) {
    //                    System.exit(0);
    //                } else if (displayName.isEmpty() || username.length() >= 40) {
    //                    JOptionPane.showMessageDialog(panel,
    //                            "Make sure username is not empty and shorter than 40 characters");
    //                } else {
    //                    break;
    //                }
    //            } else {
    //                System.exit(0);
    //            }
    //        }
    //
    //
    //        emails.add(email);

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


    public Collection<User> getFollowers() { //Additions by Sameer for Junit
        return followers;
    }

    public Collection<User>getBlockedUsers() { //Additions by Sameer for Junit
        return blockedUsers;
    }
}
