
//Made by both Neeil and Sameer to be utilized for saving in databases and newsfeed
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * The User class represents a user in the application, with functionalities
 * for managing followers, blocked users, and user information.
 * This class is used in the NewsFeed and for database storage purposes.
 *
 * Created by Neeil Gupta and Sameer Dadoo
 * Date: November 3, 2024
 */

public class User extends Main implements Serializable {
    private static User currentUser;
    //Neeil's instance variables
    private int userID;
    private String username;
    private ArrayList<Post> hiddenPosts;
    //Sameer's instance variables
    private List<User> followers;
    private List<User> blockedUsers;
    private String email = "";
    //Neeil's methods and constructers
    public User(int userID, String username) {
        this.userID = userID;
        this.username = username;
        this.hiddenPosts = new ArrayList<>();
    }

    //This reads from the Userinfo.txt file
    public User(String fileName) {
        String lineInstance = "";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
             String line = " ";
             lineInstance = "";
             while((line = br.readLine()) != null) {
                 lineInstance += line;
             }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        assert lineInstance != null;
        String[] lineInstances = lineInstance.split(",");
        this.userID = Integer.parseInt(lineInstances[0]);
        this.email = lineInstances[1];
        this.username = lineInstances[2];
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

    public ArrayList<Post> getHiddenPosts() {
        return hiddenPosts;
    }

    public void hidePost(Post post) {
        if (!hiddenPosts.contains(post)) {
            hiddenPosts.add(post);
        }
    }

    public void unhidePost(Post post) {
        hiddenPosts.remove(post);
    }
    //Sameer methods and constructers:
    public User(String username, String password) {
        // Properties:
        this.followers = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
        this.hiddenPosts = new ArrayList<>();
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
        if (followers == null) {
            followers = new ArrayList<>();
        }
        return followers;

    }

    public Collection<User>getBlockedUsers() { //Additions by Sameer for Junit
        return blockedUsers;
    }
}
