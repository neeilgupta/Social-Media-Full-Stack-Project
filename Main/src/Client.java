import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * Client
 * <p>
 * DESCRIPTION GOES HERE
 * <p>
 * Hossein Hatami
 *
 * @version November 3rd, 2024
 */

public class Client extends Thread implements Runnable {
    int userID;
    String username;
    String displayName;
    String password;
    private int result;
    private User thisUser;
    private UserFileDatabase database; // creates the use of database in this class

    private Socket clientSocket;
    private DataOutputStream out;

    private JButton signUpButton;
    private JButton loginButton;
    JTextField usernameField;
    JPasswordField passwordField;
    JTextField displayNameField;
    private JButton autogenerateButton;
    private JButton showPasswordButton;
    private JButton confirmPasswordField;
    private JFrame mainFrame = new JFrame("Welcome");

    private Client client;

    public Client() {
        try {
            clientSocket = new Socket("localhost", 4141);
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == signUpButton) {
                mainFrame.dispose();
                client.signUpPage();

            }
            if (e.getSource() == loginButton) {
                client.loginPage();
            }
            if (e.getSource() == autogenerateButton) {
                client.autogenerate();
            }
            if (e.getSource() == showPasswordButton) {
                client.showPassword();
            }
        }
    };

    private void showPassword() {
        if (passwordField.getEchoChar() == '●') {
            passwordField.setEchoChar('\0');
            ImageIcon icon = new ImageIcon("/Users/hhatami/IdeaProjects/group-project-cs180/Main/506282-200.png");


            // Get the preferred height of the password field
            int fieldHeight = passwordField.getPreferredSize().height;

            // Resize the image to a square with height equal to the field height
            BufferedImage resizedImage = null;
            Graphics2D g2d;

            resizedImage = new BufferedImage(fieldHeight, fieldHeight, BufferedImage.TYPE_INT_ARGB);
            g2d = resizedImage.createGraphics();
            g2d.drawImage(icon.getImage(), 0, 0, fieldHeight, fieldHeight, null);
            g2d.dispose();

            showPasswordButton.setIcon(new ImageIcon(resizedImage));


        } else if (passwordField.getEchoChar() == '\0') {
            passwordField.setEchoChar('●');
            ImageIcon icon = new ImageIcon("/Users/hhatami/IdeaProjects/group-project-cs180/Main/777494-200.png");


            // Get the preferred height of the password field
            int fieldHeight = passwordField.getPreferredSize().height;

            // Resize the image to a square with height equal to the field height
            BufferedImage resizedImage = null;
            Graphics2D g2d;

            resizedImage = new BufferedImage(fieldHeight, fieldHeight, BufferedImage.TYPE_INT_ARGB);
            g2d = resizedImage.createGraphics();
            g2d.drawImage(icon.getImage(), 0, 0, fieldHeight, fieldHeight, null);
            g2d.dispose();

            showPasswordButton.setIcon(new ImageIcon(resizedImage));
        }
    }

    void autogenerate() {
        StringBuilder generatedPassword = new StringBuilder(30);
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-_=+/?.><`~[{]}:;";
        for (int i = 0; i < 30; i++) {
            int random = new Random().nextInt(chars.length());
            generatedPassword.append(chars.charAt(random));
        }
        passwordField.setText(generatedPassword.toString());
        passwordField.setEchoChar('\0');
        this.showPassword();
    }

    public void signUpPage() {
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350); // Set a more compact size for better UI
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components

        // Username Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(16); // Limit the column size for a cleaner look
        panel.add(usernameField, gbc);

        // Display Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Display Name:"), gbc);

        gbc.gridx = 1;
        displayNameField = new JTextField(16);
        panel.add(displayNameField, gbc);

        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(16);
        panel.add(passwordField, gbc);


        // Autogenerate Password Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        autogenerateButton = new JButton("Autogenerate Password");
        autogenerateButton.addActionListener(actionListener);
        panel.add(autogenerateButton, gbc);

        // Show Password Button (next to password field)
        gbc.gridx = 2;
        gbc.gridy = 2;

        passwordField.setEchoChar('\0');
        ImageIcon icon = new ImageIcon("/Users/hhatami/IdeaProjects/group-project-cs180/Main/777494-200.png");


        // Get the preferred height of the password field
        int fieldHeight = passwordField.getPreferredSize().height;

        // Resize the image to a square with height equal to the field height
        BufferedImage resizedImage = null;
        Graphics2D g2d;

        resizedImage = new BufferedImage(fieldHeight, fieldHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = resizedImage.createGraphics();
        g2d.drawImage(icon.getImage(), 0, 0, fieldHeight, fieldHeight, null);
        g2d.dispose();

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        if (showPasswordButton == null) {
            showPasswordButton = new JButton(resizedIcon);
            showPasswordButton.setBorderPainted(false);
            showPasswordButton.setFocusPainted(false);
            showPasswordButton.setContentAreaFilled(false);
            showPasswordButton.setPreferredSize(new Dimension(fieldHeight, fieldHeight));
            showPasswordButton.addActionListener(actionListener);

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            panel.add(showPasswordButton, gbc);
        } else {
            showPasswordButton.setIcon(resizedIcon);
        }


        gbc.gridy = 4;
        JButton confirmSignUp = new JButton("Confirm Sign Up");

        confirmSignUp.addActionListener(e -> {
            username = usernameField.getText();
            displayName = displayNameField.getText();
            password = passwordField.getText();
            userID = User.numUsers++;

            if (validUsername(username) && validPassword(password) && validDisplayName(displayName)) {
                System.out.println("Signing up: " + username);
                frame.dispose(); // Close the sign-up frame after confirming
                String createUserLine = "create##" + userID + "," + username + "," + password + "," + displayName;
                try {
                    out.writeUTF(createUserLine);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(confirmSignUp, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }


    private void loginPage() {
    }


    public static void main(String[] args) throws IOException {
        Client client = new Client();

        //send data to and receive data from server
        //basically create the UI, and send messages to the server so the server can do stuff
        //can modify existing sign up and login methods to use that GUI, but the client doesn't process anything
        //use complex GUI's, not just pop-ups
        //socket.close();
    }


    @Override
    public void run() {
        client = this;

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Container content = mainFrame.getContentPane();
//        content.setLayout(new GridLayout(2, 1));
        mainFrame.setSize(200, 100);
        mainFrame.setLocationRelativeTo(null); // adjust later on to fit directly center
        JPanel panel = new JPanel(new GridLayout(2, 1));
        signUpButton = new JButton("Sign Up");
        panel.add(signUpButton);
        signUpButton.addActionListener(actionListener);

        loginButton = new JButton("Login");
        panel.add(loginButton);
        loginButton.addActionListener(actionListener);

        if (validUsername(username) && validPassword(password)) {
            mainFrame.dispose();
            //link to homepage
        }


//        content.add(panel, BorderLayout.CENTER);
        mainFrame.add(panel);


//        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public boolean validDisplayName(String displayName) {
        if (displayName.contains(",")) {
            JOptionPane.showMessageDialog(null, "Display name contains invalid characters!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (displayName.length() > 30) {
            JOptionPane.showMessageDialog(null, "Display name is too long!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    public boolean validPassword(String password) {
        if (password == null) {
            return false;
        } else if (password.length() < 8) {
            JOptionPane.showMessageDialog(mainFrame, "Password must be at least 8 characters");
            return false;
        } else if (password.length() > 32) {
            JOptionPane.showMessageDialog(mainFrame, "Password must be at most 32 characters");
            return false;
        } else if (password.contains(",")) {
            JOptionPane.showMessageDialog(mainFrame, "Password contains invalid characters");
            return false;
        } else {
            return true;
        }
    }

    public boolean validUsername(String username) {
//        User user = database.retrieveUser(username);
        if (username == null) {
            return false;
        } else if (username.length() < 3 || username.length() > 16) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Make sure username is longer than 3 characters and shorter than 16 characters");
            return false;
        } else if (!username.chars().allMatch(c -> (c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c == 95) ||
                (c >= 97 && c <= 122))) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Make sure to include only letters, digits, or underscores");
            return false;
        } else if (this.isUsernameTaken()) {
            JOptionPane.showMessageDialog(mainFrame, "Username taken");
            return false;
        } else {
            return true;
        }
    }

    private boolean isUsernameTaken() {
        try (Socket socket = new Socket("localhost", 4141)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.write(username);
            writer.flush();

            BufferedReader bfr = new BufferedReader(new FileReader("UserInfo.txt"));
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.split(",")[0].equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


//OptionSignUpOrLogin osul = new OptionSignUpOrLogin();
//Thread SUoL = new Thread(osul);
//Thread signUp = new Thread(new SignUp());
//Thread login = new Thread(new Login());
//        SUoL.start();
//        try {
//                SUoL.join();
//        } catch (InterruptedException ie) {
//        throw new RuntimeException(ie);
// m      }
//                if (osul.isSignUpButtonClicked()) {
//        System.out.println("Starting SignUp thread..."); //delete later
//            signUp.start();
//        } else if (osul.isLoginButtonClicked()) {
//        System.out.println("Starting Login thread..."); // delete later
//            login.start();
//        }