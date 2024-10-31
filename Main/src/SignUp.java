import javax.swing.*;
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
    public boolean emailTaken;
    public boolean usernameTaken; // Doesn't save usernames as of yet
    private static Set<String> usernames = new HashSet<>();
    private static Set<String> emails = new HashSet<>();
    private int result;

//    public SignUp(String email, String password, String username, String displayName, int userID ) {
//        this.email = email;
//        this.password = password;
//        this.username = username;
//        this.displayName = displayName;
//        this.userID = userID;
//    }

    @Override
    public void run() throws NullPointerException {
        JFrame frame = new JFrame("Sign Up");
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setResizable(true);

        while (true) {
            if (result == JOptionPane.OK_OPTION) {
                email = JOptionPane.showInputDialog(frame, "Enter your email address");
                emailTaken = emails.contains(email);
                if (email == null) {
                    System.exit(0);
                } else if (email.length() < 6) {
                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
                } else if (!email.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 64 && c <= 90) || (c >= 45 && c <= 46) ||
                        (c >= 97 && c <= 122))) {
                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
                } else if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
                } else if (emailTaken) {
                    JOptionPane.showMessageDialog(panel, "This email is already being used. Please exit and log-in");
                } else {
                    break;
                }
            } else {
                System.exit(0);
            }
        }
        panel.removeAll();
        frame.remove(panel);
//        String verificationCode = String.valueOf(new Random().nextInt(10000)); for later, for fun ;)

        panel.add(new JLabel(email + ":\nPlease create a new password"));
        frame.add(panel);
        JButton button = new JButton("Autogenerate password");
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
                if (password == null) {
                    System.exit(0);
                } else if (password.length() < 8) {
                    JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters");
                } else if (password.length() > 32) {
                    JOptionPane.showMessageDialog(panel, "Password must be at most 32 characters");
                } else {
                    break;
                }
            } else {
                System.exit(0);
            }
        }
        JOptionPane.showMessageDialog(panel, "Password set successfully to " + password);
        panel.removeAll();
        frame.remove(panel);
        frame.dispose();

        panel.add(new JLabel("Please enter your username:"));
        frame.add(panel);
        while (true) {
            usernameTaken = usernames.contains(username);
            result = JOptionPane.showConfirmDialog(null, panel, "Create Username",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                username = JOptionPane.showInputDialog(panel, "Please enter your username:");
                if (username == null) {
                    System.exit(0);
                } else if (username.length() < 3 || username.length() > 16) {
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
            } else {
                System.exit(0);
            }
        }
        JOptionPane.showMessageDialog(panel, "Perfect! Your username is " + username);
        panel.removeAll();
        frame.remove(panel);
        frame.dispose();

        panel.add(new JLabel("Please enter your display name:"));
        frame.add(panel);

        while (true) {
            displayName = JOptionPane.showInputDialog(panel, "Please enter your display name:");
            result = JOptionPane.showConfirmDialog(null, panel, "Create your display name",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (displayName == null) {
                    System.exit(0);
                } else if (displayName.isEmpty() || username.length() >= 40) {
                    JOptionPane.showMessageDialog(panel,
                            "Make sure username is not empty and shorter than 40 characters");
                } else {
                    break;
                }
            } else {
                System.exit(0);
            }
        }


        emails.add(email);
        panel.setBounds(130, 100, 100, 40);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


//    public int makeUserID() {
//        return (int) (Math.random() * 1000000000);
//    }

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
