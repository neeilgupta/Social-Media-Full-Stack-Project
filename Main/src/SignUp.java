import javax.swing.*;
import java.io.*;
import java.util.Random;

/**
 * Social media
 * <p>
 * Creating a way for users to sign up
 * <p>
 * Hossein Hatami
 *
 * @version November 3rd, 2024
 *
 */

public class SignUp implements Runnable, Serializable {
    private int userID;
//    private String email = "";
    private String username;
    private String displayName;
    private String password;
//    private boolean validEmailEndings;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setResizable(true);

        while (true) {
            result = JOptionPane.showConfirmDialog(null, panel, "Create Username",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                username = JOptionPane.showInputDialog(null, "Please enter your username:");
                if (username == null) {
                    return;
                } else if (username.length() < 3 || username.length() > 16) {
                    JOptionPane.showMessageDialog(panel,
                            "Make sure username is longer than 3 characters and shorter than 16 characters");
                } else if (!username.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c == 95) ||
                        (c >= 97 && c <= 122))) {
                    JOptionPane.showMessageDialog(panel,
                            "Make sure to include only letters, digits, or underscores");
                } else if (this.isUsernameTaken()) {
                    JOptionPane.showMessageDialog(panel, "Username taken");
                } else {
                    break;
                }
            } else {
                return;
            }
        }
        JOptionPane.showMessageDialog(panel, "Perfect! Your username is " + username);
        panel.removeAll();
        frame.remove(panel);
        frame.dispose();

//        while (true) {
//            if (result == JOptionPane.OK_OPTION) {
//                email = JOptionPane.showInputDialog(frame, "Enter your email address");
//                if (email == null) {
//                    return;
//                } else if (email.length() < 6) {
//                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
//                } else if (!email.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 64 && c <= 90) || (c >= 45 && c <= 46) ||
//                        (c >= 97 && c <= 122))) {
//                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
//                } else if (!email.contains("@") || !email.contains(".")) {
//                    JOptionPane.showMessageDialog(panel, "Not a valid email address");
//                } else if (this.isEmailTaken()) {
//                    JOptionPane.showMessageDialog(panel, "This email is already being used. Please exit and log-in");
//                } else {
//                    break;
//                }
//            } else {
//                return;
//            }
//        }
        panel.removeAll();
        frame.remove(panel);

        panel.add(new JLabel("Please create a new password"));
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
                    return;
                } else if (password.length() < 8) {
                    JOptionPane.showMessageDialog(panel, "Password must be at least 8 characters");
                } else if (password.length() > 32) {
                    JOptionPane.showMessageDialog(panel, "Password must be at most 32 characters");
                } else {
                    break;
                }
            } else {
                return;
            }
        }
        JOptionPane.showMessageDialog(panel, "Password set successfully to " + password);
        panel.removeAll();
        frame.remove(panel);
//        frame.dispose();



//        panel.add(new JLabel("Please enter your display name:"));
//        frame.add(panel);

        while (true) {
            displayName = JOptionPane.showInputDialog(panel, "Please enter your display name:");
            result = JOptionPane.showConfirmDialog(null, panel, "Create your display name",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (displayName == null) {
                    return;
                } else if (displayName.isEmpty() || displayName.length() >= 40) {
                    JOptionPane.showMessageDialog(panel,
                            "Make sure username is not empty and shorter than 40 characters");
                } else {
                    break;
                }
            } else {
                return;
            }
        }

        int lineCount = 1;
        try (BufferedReader bfr = new BufferedReader(new FileReader("UserInfo.txt"))) {
            while ((bfr.readLine()) != null) {
                lineCount++;
            }
            userID = lineCount;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            File f = new File("UserInfo.txt");
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos), true);

            pw.write(String.format("%d,%s,%s,%s,%s", userID, username, displayName, password));
            pw.println();
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        panel.setBounds(130, 100, 100, 40);
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.dispose();
    }

//    public boolean isEmailTaken() {
//        try {
//            File f = new File("UserInfo.txt");
//            FileReader fr = new FileReader(f);
//            BufferedReader bfr = new BufferedReader(fr);
//            String line;
//
//            while ((line = bfr.readLine()) != null) {
//                if (line.contains(email)) {
//                    return true;
//                }
//            }
//            bfr.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public boolean isUsernameTaken() {
        try {
            File f = new File("UserInfo.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line;

            while ((line = bfr.readLine()) != null) {
                if (line.contains(username)) {
                    return true;
                }
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return false;
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

//    public void setEmail(String mail) {
//    }
}
