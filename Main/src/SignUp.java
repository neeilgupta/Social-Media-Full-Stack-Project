import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SignUp implements Runnable {
    private int userID;
    private String username;
    private String displayName;
    private String password;
    public boolean usernameTaken;
    private static Set<String> usernames = new HashSet<>();

    @Override
    public void run() {

        JFrame frame = new JFrame("Sign Up");
        JPanel panel = new JPanel();
        JTextField myOutput = new JTextField();
        panel.add(new JLabel("Please enter your username:"));
        while (true) {
            username = JOptionPane.showInputDialog(panel, "Please enter your username:");
            usernameTaken = usernames.contains(username);

            if (username == null || username.length() < 3 || username.length() > 16) {
                myOutput.setText("Make sure username is longer than 3 characters and shorter than 16 characters");
            } else if (!username.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c == 95) ||
                    (c >= 97 && c <= 122) )) {
                myOutput.setText("Make sure to include only letters, digits, or underscores");
            } else if (usernameTaken) {
                myOutput.setText("Username taken");
            } else {

                usernames.add(username);
                break;
            }
        }
        myOutput.setText("Perfect! Your username is " + username);

        panel.setBounds(130, 100, 100, 40);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
