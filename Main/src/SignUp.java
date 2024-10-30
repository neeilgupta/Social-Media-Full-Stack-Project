import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SignUp implements Runnable {
    private int userID;
    private String email = "";
    private String username;
    private String displayName;
    private String password;
    public boolean usernameTaken; // Doesn't save usernames as of yet
    private static Set<String> usernames = new HashSet<>();
    private int textOutput;


    @Override
    public void run() {

//        JFrame frame = new JFrame("Sign Up");
        JPanel panel = new JPanel();


        panel.add(new JLabel("Please enter your email address:"));
        while (true) {
            email = JOptionPane.showInputDialog(panel, "Please enter your email address:");
            if (!email.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 64 && c <= 90) || (c >= 45 && c <= 46) ||
                    (c >= 97 && c <= 122) )) {
                JOptionPane.showMessageDialog(panel, "Not a valid email address");
            } else if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(panel, "Not a valid email address");
            } else {
                break;
            }
        }

//        String verificationCode = String.valueOf(new Random().nextInt(10000)); for later, for fun ;)

        panel.add(new JLabel("Please create a new password:"));
        JButton button = new JButton("Autogenerate new password");
        panel.setSize(500, 500);
        button.setBounds(100,100,100,100);
        panel.add(button);
        while (true) {

            JPasswordField passwordField = new JPasswordField();
            // come back to this, too tired now FIX LATER.
            // For now, Make sure password is hidden, and create autogenerate button
            button.addActionListener(_ -> {
                JTextArea textArea = new JTextArea();
                panel.add(new JScrollPane(passwordField));
                passwordField.setEchoChar('*');
            });


            password = JOptionPane.showInputDialog(panel, "Please enter your password;");
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters");
            } else if (password.length() > 32) {
                JOptionPane.showMessageDialog(panel, "Password must be at most 32 characters");
            } else {
                break;
            }
        }

        panel.add(new JLabel("Please enter your username:"));
        while (true) {
            username = JOptionPane.showInputDialog(panel, "Please enter your username:");
            usernameTaken = usernames.contains(username);

            if (username == null || username.length() < 3 || username.length() > 16) {
                JOptionPane.showMessageDialog(panel,
                        "Make sure username is longer than 3 characters and shorter than 16 characters");
            } else if (!username.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c == 95) ||
                    (c >= 97 && c <= 122) )) {
                JOptionPane.showMessageDialog(panel,
                        "Make sure to include only letters, digits, or underscores");
            } else if (usernameTaken) {
                JOptionPane.showMessageDialog(panel, "Username taken");
            } else {
                usernames.add(username);
                break;
            }
        }

        JOptionPane.showMessageDialog(panel, "Perfect! Your username is " + username);
//        panel.setBounds(130, 100, 100, 40);
//        frame.setSize(400, 500);
//        frame.setLayout(null);
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
