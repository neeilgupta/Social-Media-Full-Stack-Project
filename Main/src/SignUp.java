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
    private boolean validEmailEndings;
    public boolean usernameTaken; // Doesn't save usernames as of yet
    private static Set<String> usernames = new HashSet<>();
    private int result;


    @Override
    public void run() {
        JFrame frame = new JFrame("Sign Up");
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setResizable(true);

        while (true) {
//            result = JOptionPane.showConfirmDialog(null, panel, "Please enter a valid email address",
//                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//            email = JOptionPane.showInputDialog(panel, "Please enter your email address:");
            if (result == JOptionPane.OK_OPTION) {
                email = JOptionPane.showInputDialog(frame, "Enter your email address");
                if (email == null) {
                    throw new NullPointerException();
                }
                if (email.length() < 6) {
                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
                }
                if (!email.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 64 && c <= 90) || (c >= 45 && c <= 46) ||
                        (c >= 97 && c <= 122))) {
                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
                } else if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
                } else {
                    break;
                }
            } else if (result == JOptionPane.CANCEL_OPTION) {
                System.exit(0);
            }
        }
        panel.removeAll();
        frame.remove(panel);
//        String verificationCode = String.valueOf(new Random().nextInt(10000)); for later, for fun ;)

        panel.add(new JLabel("Please create a new password:"));
        frame.add(panel);
        JButton button = new JButton("Autogenerate new password");
        panel.setSize(500, 500);
        button.setLocation(panel.getX() - 100, panel.getY() - 100);
        panel.add(button);
        JPasswordField passwordField = new JPasswordField(32);
        passwordField.setEchoChar('\0');
        panel.add(new JScrollPane(passwordField));
        StringBuilder generatedPassword = new StringBuilder(30);
        button.addActionListener(e -> {
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-_=+/?.>,<`~[{]}:;";
            for (int i = 0; i < 30; i++) {
                int random = new Random().nextInt(chars.length());
                generatedPassword.append(chars.charAt(random)); // Append each character to build the password
            }

            passwordField.setText(generatedPassword.toString()); // Set the generated password once
        });


        while (true) {
            result = JOptionPane.showConfirmDialog(null, panel, "Create Password",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                password = new String(passwordField.getPassword());
                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters");
                } else if (password.length() > 32) {
                    JOptionPane.showMessageDialog(panel, "Password must be at most 32 characters");
                } else {
                    break;
                }
            } else {
                frame.dispose();
                System.exit(0);
                break;
            }
        }
        JOptionPane.showMessageDialog(panel, "Password set successfully to " + password);
        panel.removeAll();
        frame.remove(panel);
        frame.dispose();

        panel.add(new JLabel("Please enter your username:"));
        while (true) {
            username = JOptionPane.showInputDialog(panel, "Please enter your username:");
            usernameTaken = usernames.contains(username);

            if (username == null || username.length() < 3 || username.length() > 16) {
                JOptionPane.showMessageDialog(panel,
                        "Make sure username is longer than 3 characters and shorter than 16 characters");
            } else if (!username.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c == 95) ||
                    (c >= 97 && c <= 122))) {
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
