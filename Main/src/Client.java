import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Client
 * <p>
 * This class initiates which interact with the server, and runs the GUI of the social media program
 * <p>
 * Hossein Hatami, Emerson Barrett
 *
 * @version November 17, 2024
 */

public class Client extends Thread implements Runnable {
    int userID;
    String username;
    String displayName;
    String password;
    private int result;
    private User thisUser;
    private UserFileDatabase database; // creates the use of database in this class
    private NewsFeed newsFeed;

    private Socket clientSocket;
    DataOutputStream out;

    private JButton signUpButton;
    private JButton loginButton;
    JTextField usernameField;
    JPasswordField passwordField;
    JTextField displayNameField;
    private JButton autogenerateButton;
    private JButton showPasswordButton;
    private JPasswordField confirmPasswordField;
    private JFrame mainFrame = new JFrame("Welcome");

    private Client client;

    public Client() {
        try {
            clientSocket = new Socket("localhost", 4141);
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.newsFeed = new NewsFeed();
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
            if (confirmPasswordField != null) {
                confirmPasswordField.setEchoChar('\0');
            }
            ImageIcon icon = new ImageIcon("/Users/hhatami/IdeaProjects/group-project-cs180/Main/506282-" +
                    "200.png");


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
            if (confirmPasswordField != null) {
                confirmPasswordField.setEchoChar('●');
            }
            ImageIcon icon = new ImageIcon("/Users/hhatami/IdeaProjects/group-project-cs180/Main/777494-" +
                    "200.png");


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
        passwordField.setEchoChar('●');
        confirmPasswordField.setText(generatedPassword.toString());
        confirmPasswordField.setEchoChar('●');
        this.showPassword();
    }

    public void signUpPage() {
        JFrame frame = new JFrame("Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300); // Set a more compact size for better UI
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
        usernameField = new JTextField(24); // Limit the column size for a cleaner look
        panel.add(usernameField, gbc);

        // Display Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Display Name:"), gbc);

        gbc.gridx = 1;
        displayNameField = new JTextField(24);
        panel.add(displayNameField, gbc);

        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(24);
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(24);
        panel.add(confirmPasswordField, gbc);
        confirmPasswordField.setEchoChar('\0');

        // Autogenerate Password Button
        gbc.gridx = 0;
        gbc.gridy = 4;
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

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton confirmSignUp = new JButton("Confirm Sign Up");

        confirmSignUp.addActionListener(e -> {
            username = usernameField.getText();
            displayName = displayNameField.getText();
            password = passwordField.getText();
            String line;
            int id = 0;
            try {
                BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
                while ((line = bfr.readLine()) != null) {
                    if (Integer.parseInt(line.substring(0, line.indexOf(","))) > id) {
                        id = Integer.parseInt(line.substring(0, line.indexOf(",")));
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            userID = ++id;


            if (validUsernameSU(username) && validPasswordSU(password) && validDisplayName(displayName)) {
                System.out.println("Signing up: " + username);
                frame.dispose(); // Close the sign-up frame after confirming
                homePage();
                String createUserLine = "createUser##" + userID + "," + username + "," + password + "," + displayName;
                try {
                    out.writeUTF(createUserLine);
                    // make sure the file is added properly
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
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Set a more compact size for better UI
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

        // Password Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(16);
        panel.add(passwordField, gbc);

        // Show Password Button (next to password field)
        gbc.gridx = 2;
        gbc.gridy = 2;

        passwordField.setEchoChar('\0');
        ImageIcon icon = new ImageIcon("/Users/hhatami/IdeaProjects/group-project-cs180/Main/777494-200.png");


        // Get the preferred height of the password field
        int fieldHeight = passwordField.getPreferredSize().height;

        // Resize the image to a square with height equal to the field height
        BufferedImage resizedImage;
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


        gbc.gridy = 5;
        JButton confirmSULI = new JButton("Login");

        confirmSULI.addActionListener(_ -> {
            username = usernameField.getText();
            password = passwordField.getText();
            userID = User.numUsers++;

            if (validUsernameLI(username) && validPasswordLI(password)) {
                System.out.println("Logging in: " + username);
                frame.dispose();
                String createUserLine = "createUser##" + userID + "," + username + "," + password + "," + displayName;
                try {
                    out.writeUTF(createUserLine);
                    homePage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(confirmSULI, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void homePage() {
        JFrame homeFrame = new JFrame("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(400, 400);
        homeFrame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, " + username, JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        homeFrame.add(welcomeLabel, BorderLayout.NORTH);

        // Profile section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new GridLayout(4, 1));

        JLabel followersLabel = new JLabel("Followers: " + thisUser.getFollowers().size()); // Placeholder
        JLabel followingLabel = new JLabel("Following: " + thisUser.getFollowing().size());
        JLabel postsLabel = new JLabel("Posts: " + Post.getUserPostCount(thisUser));

        profilePanel.add(followersLabel);
        profilePanel.add(followingLabel);
        profilePanel.add(postsLabel);

        homeFrame.add(profilePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton createPostButton = new JButton("Create Post");
        JButton viewPostsButton = new JButton("View Posts");
        JButton newsFeedButton = new JButton("News Feed");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(createPostButton);
        buttonPanel.add(viewPostsButton);
        buttonPanel.add(newsFeedButton);
        buttonPanel.add(logoutButton);

        homeFrame.add(buttonPanel, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.dispose();
                loginPage();
            }
        });

        newsFeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeFrame.dispose();
                newsFeedPage();
            }
        });

        createPostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Post button clicked");
                createPost();
            }
        });

        viewPostsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("View Posts button clicked");
                homeFrame.dispose();
                viewUserPostsPage(thisUser);
            }
        });

        homeFrame.setVisible(true);
    }

    private void viewUserPostsPage(User currentUser) {
        JFrame viewUserPostsFrame = new JFrame("Your Posts");
        viewUserPostsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewUserPostsFrame.setSize(500, 600);
        viewUserPostsFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Your Posts", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        viewUserPostsFrame.add(titleLabel, BorderLayout.NORTH);

        // Scrollable panel for posts
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        viewUserPostsFrame.add(scrollPane, BorderLayout.CENTER);

        ArrayList<Post> userPosts = Post.getUserPosts(currentUser);

        if (userPosts.isEmpty()) {
            JLabel noPostsLabel = new JLabel("You haven't made any posts yet!", JLabel.CENTER);
            noPostsLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            postsPanel.add(noPostsLabel);
        } else {
            for (Post post : userPosts) {
                JPanel postPanel = new JPanel();
                postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
                postPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                postPanel.setBackground(Color.WHITE);

                JLabel contentLabel = new JLabel("<html><b>" + post.getContent() + "</b></html>");
                contentLabel.setFont(new Font("Arial", Font.PLAIN, 14));

                // Post meta (likes/dislikes)
                JLabel metaLabel = new JLabel(
                        "Likes: " + post.getLikes() + " | Dislikes: " + post.getDislikes(),
                        JLabel.RIGHT
                );
                metaLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                metaLabel.setForeground(Color.DARK_GRAY);

                postPanel.add(contentLabel);
                postPanel.add(Box.createVerticalStrut(5));
                postPanel.add(metaLabel);

                postsPanel.add(postPanel);
                postsPanel.add(Box.createVerticalStrut(10));
            }
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            viewUserPostsFrame.dispose(); // Close the current frame
            homePage(); // Navigate back to the home page
        });
        viewUserPostsFrame.add(backButton, BorderLayout.SOUTH);

        viewUserPostsFrame.setVisible(true);
    }

    private void createPost() {
        JFrame createPostFrame = new JFrame("Create Post");
        createPostFrame.setSize(400, 300);
        createPostFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        createPostFrame.setLayout(new BorderLayout());

        // Create a JLabel at the top
        JLabel instructionLabel = new JLabel("Enter your post below:", JLabel.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        createPostFrame.add(instructionLabel, BorderLayout.NORTH);

        JTextArea postTextArea = new JTextArea();
        postTextArea.setLineWrap(true);
        postTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(postTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        createPostFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton postButton = new JButton("Post");
        JButton cancelButton = new JButton("Cancel");

        buttonPanel.add(postButton);
        buttonPanel.add(cancelButton);
        createPostFrame.add(buttonPanel, BorderLayout.SOUTH);

        postButton.addActionListener(e -> {
            String postContent = postTextArea.getText().trim();
            if (postContent.isEmpty()) {
                JOptionPane.showMessageDialog(createPostFrame, "Post content cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.println("New Post: " + postContent);
                Post newPost = new Post(thisUser.getUserID(), postContent, thisUser);
                try {
                    out.writeUTF("createPost##" + thisUser.getUserID() + "," + postContent);
                    newsFeed.addPost(newPost);
                    JOptionPane.showMessageDialog(createPostFrame, "Post created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    createPostFrame.dispose();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(createPostFrame, "Failed to create post!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener for the "Cancel" button
        cancelButton.addActionListener(e -> createPostFrame.dispose());

        createPostFrame.setLocationRelativeTo(null); // Center the frame
        createPostFrame.setVisible(true);
    }

    private void newsFeedPage() {
        JFrame newsFeedFrame = new JFrame("News Feed");
        newsFeedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newsFeedFrame.setSize(600, 800);
        newsFeedFrame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("News Feed", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        newsFeedFrame.add(titleLabel, BorderLayout.NORTH);

        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(postsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        newsFeedFrame.add(scrollPane, BorderLayout.CENTER);

        ArrayList<Post> userFeed = newsFeed.getFeedForUser(thisUser);
        for (Post post : userFeed) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            postPanel.setBackground(Color.WHITE);

            JLabel contentLabel = new JLabel("<html><b>" + post.getContent() + "</b></html>");
            contentLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            JLabel metaLabel = new JLabel(
                    "Likes: " + post.getLikes().size() + " | Dislikes: " + post.getDislikes().size(),
                    JLabel.RIGHT
            );
            metaLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            metaLabel.setForeground(Color.DARK_GRAY);

            postPanel.add(contentLabel);
            postPanel.add(Box.createVerticalStrut(5));
            postPanel.add(metaLabel);
            postsPanel.add(postPanel);
            postsPanel.add(Box.createVerticalStrut(10));
        }

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            newsFeedFrame.dispose();
            homePage();
        });
        newsFeedFrame.add(backButton, BorderLayout.SOUTH);

        newsFeedFrame.setVisible(true);
    }


    //Methods below include all information that must be passed to the server.
    //Information in the method constructors can later be replaced with whatever necessary
    //once the GUI is implemented.
    //Method content, especially for determining id numbers, must remain.
    //      -Emerson
    void createPost(String content, User user) {
        String line;
        int id = 0;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("posts.ser"));
            while ((line = bfr.readLine()) != null) {
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) > id) {
                    id = Integer.parseInt(line.substring(0, line.indexOf(",")));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int postID = id;

        int userID = user.getUserID();
        String createPostLine = "createPost##" + postID + "," + content + "," + userID;
        try {
            out.writeUTF(createPostLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void createComment(String content, User user, Post post) {
        String line;
        int id = 0;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("comments.ser"));
            while ((line = bfr.readLine()) != null) {
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) > id) {
                    id = Integer.parseInt(line.substring(0, line.indexOf(",")));
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int commentID = id;

        int userID = user.getUserID();
        int postID = post.getID();
        String createCommentLine = "createComment##" + commentID + "," + content + "," + userID + "," + postID;
        try {
            out.writeUTF(createCommentLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void likePost(int postID, int userID) {
        String likePostLine = "likePost##" + postID + "," + userID;
        try {
            out.writeUTF(likePostLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void dislikePost(int postID, int userID) {
        String dislikePostLine = "dislikePost##" + postID + "," + userID;
        try {
            out.writeUTF(dislikePostLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void likeComment(int commentID, int userID) {
        String likeCommentLine = "likeComment##" + commentID + "," + userID;
        try {
            out.writeUTF(likeCommentLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void dislikeComment(int commentID, int userID) {
        String dislikeCommentLine = "dislikeComment##" + commentID + "," + userID;
        try {
            out.writeUTF(dislikeCommentLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void follow(int currentUserID, int otherUserID) {
        String followLine = "follow##" + currentUserID + "," + otherUserID;
        try {
            out.writeUTF(followLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void unfollow(int currentUserID, int otherUserID) {
        String unfollowLine = "unfollow##" + currentUserID + "," + otherUserID;
        try {
            out.writeUTF(unfollowLine);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void removeAccount(int userID) {
        String removeAccountLine = "removeAccount##" + userID;
    }

    private void deletePost(int postID) {
        //must validate that the user is the one who created the post
        String hidePostLine = "deletePost##" + postID;
    }

    private void deleteComment(int commentID) {
        //must validate that the user is either the one who created the post that the comment is on
        //or the one who created the comment itself
        String deleteCommentLine = "deleteComment##" + commentID;
    }
    //--Emerson's client methods ^^


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

        if (validUsernameSU(username) && validPasswordSU(password)) {
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
            JOptionPane.showMessageDialog(null, "Display name contains invalid characters!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (displayName.length() > 30) {
            JOptionPane.showMessageDialog(null, "Display name is too long!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    public boolean validPasswordSU(String password) {
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
        } else if (!password.equals(confirmPasswordField.getText())) {
            JOptionPane.showMessageDialog(mainFrame, "Passwords do not match");
            return false;
        } else {
            return true;
        }
    }

    public boolean validPasswordLI(String password) {
        try {
            File f = new File(username + ".ser");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.contains(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (password == null) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid password", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return false;
    }

    public boolean validUsernameSU(String username) {
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

    private boolean validUsernameLI(String username) {
        if (username == null) {
            JOptionPane.showMessageDialog(mainFrame, "Username not found", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        File f = new File(username + ".ser");
        if (!f.exists()) {
            JOptionPane.showMessageDialog(mainFrame, "Username does not exist");
            return false;
        }

        return false;
    }

    private boolean isUsernameTaken() {
        File file = new File(username + ".ser");
        if (!file.exists()) {
            return false;
        }

        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                if (line.split(",")[1].equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}


