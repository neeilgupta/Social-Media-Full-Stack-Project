import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerApp
 * <p>
 * Completes tasks requested by the client using the service classes for various social media components
 * <p>
 * Emerson Barrett
 *
 * @version November 17, 2024
 */

public class ServerApp extends Thread {
    private Socket socket;
    public ServerApp(Socket inSocket){
        socket = inSocket;
    }
    public void run() {
        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()))) {

            String line;
            while (true) {
                try {
                    line = in.readUTF();
                    if (line.equals("###")) {
                        break;
                    }

                    String action = line.substring(0, line.indexOf("##"));
                    String input = line.substring(line.indexOf("##") + 2);

                    switch (action) {
                        case "createUser" -> createUser(input);
                        case "createPost" -> createPost(input);
                        case "createComment" -> createComment(input);
                        case "likePost" -> likePost(input);
                        case "dislikePost" -> dislikePost(input);
                        case "likeComment" -> likeComment(input);
                        case "dislikeComment" -> dislikeComment(input);
                        case "follow" -> follow(input);
                        case "unfollow" -> unfollow(input);
                        case "removeAccount" -> removeAccount(input);
                        case "deletePost" -> deletePost(input);
                        case "deleteComment" -> deleteComment(input);
                    }
                } catch (EOFException e) {
                    System.out.println("Client disconnected.");
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    }

    private void createUser(String userInfo) {
        String[] userComponents = userInfo.split(",");
        UserFileDatabase database = new UserFileDatabase("users.ser");
        UsersService usersService = new UsersService(database);
        User user = new User(Integer.parseInt(userComponents[0]), userComponents[1], userComponents[2],
                userComponents[3]);
        usersService.addUser(Integer.parseInt(userComponents[0]), userComponents[1], userComponents[2],
                userComponents[3]);
    }
    private void createPost(String postInfo) throws IOException {
        String[] postComponents = postInfo.split(",");
        BufferedReader read = new BufferedReader(new FileReader("user.ser"));
        String line = read.readLine();
        User thisUser = null;
        while ((line = read.readLine()) != null) {
            if (line.substring(0, line.indexOf(",")).equals(postComponents[2])) {
                thisUser = User.deserialize(line);
            }
        }
        PostFileDatabase database = new PostFileDatabase("posts.ser");
        PostService postService = new PostService(database);
        Post post = new Post (Integer.parseInt(postComponents[0]), postComponents[1], thisUser);
    }
    private void createComment(String commentInfo) throws IOException {
        String[] commentComponents = commentInfo.split(",");
        BufferedReader read = new BufferedReader(new FileReader("comments.ser"));
        String line = read.readLine();
        Post thisPost = null;
        while ((line = read.readLine()) != null) {
            if (line.substring(0, line.indexOf(",")).equals(commentComponents[3])) {
                thisPost = Post.deserialize(line);
            }
        }
        User thisUser = null;
        while ((line = read.readLine()) != null) {
            if (line.substring(0, line.indexOf(",")).equals(commentComponents[2])) {
                thisUser = User.deserialize(line);
            }
        }
        CommentFileDatabase database = new CommentFileDatabase("comments.ser");
        CommentService commentService = new CommentService(database);
        Comment comment = new Comment (Integer.parseInt(commentComponents[0]), commentComponents[1], thisUser, thisPost);
    }
    public void likePost(String input) {
        String line;
        int postID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int userID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        Post thisPost = null;
        User thisUser = null;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == userID){
                    thisUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("posts.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == postID){
                    thisPost = Post.deserialize(line);
                }
            }
            PostFileDatabase database = new PostFileDatabase("posts.ser");
            PostService postService = new PostService(database);
            postService.likePost(thisPost, thisUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void dislikePost(String input) {
        String line;
        int postID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int userID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        Post thisPost = null;
        User thisUser = null;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == userID){
                    thisUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("posts.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == postID){
                    thisPost = Post.deserialize(line);
                }
            }
            PostFileDatabase database = new PostFileDatabase("posts.ser");
            PostService postService = new PostService(database);
            postService.dislikePost(thisPost, thisUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void likeComment(String input) {
        String line;
        int commentID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int userID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        Comment thisComment = null;
        User thisUser = null;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == userID){
                    thisUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("comments.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == commentID){
                    thisComment = Comment.deserialize(line);
                }
            }
            CommentFileDatabase database = new CommentFileDatabase("comments.ser");
            CommentService commentService = new CommentService(database);
            commentService.likeComment(thisComment, thisUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void dislikeComment(String input) {
        String line;
        int commentID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int userID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        Comment thisComment = null;
        User thisUser = null;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == userID){
                    thisUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("comments.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == commentID){
                    thisComment = Comment.deserialize(line);
                }
            }
            CommentFileDatabase database = new CommentFileDatabase("comments.ser");
            CommentService commentService = new CommentService(database);
            commentService.dislikeComment(thisComment, thisUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void follow(String input){
        int currentUserID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int otherUserID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        User currentUser = null;
        User otherUser = null;
        String line;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == currentUserID){
                    currentUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == otherUserID){
                    otherUser = User.deserialize(line);
                }
            }
            UserFileDatabase database = new UserFileDatabase("users.ser");
            UsersService userService = new UsersService(database);
            userService.addFollower(currentUser, otherUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void unfollow(String input){
        int currentUserID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int otherUserID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        User currentUser = null;
        User otherUser = null;
        String line;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == currentUserID){
                    currentUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == otherUserID){
                    otherUser = User.deserialize(line);
                }
            }
            UserFileDatabase database = new UserFileDatabase("users.ser");
            UsersService userService = new UsersService(database);
            userService.removeFollower(currentUser, otherUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void removeAccount(String input){
        File inputFile = new File("users.ser");
        File tempFile = new File(inputFile.getAbsolutePath() + ".temp");
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (line.substring(0, line.indexOf(",")).equals(input)){
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            if (inputFile.delete()){
                if (!tempFile.renameTo(inputFile)){
                    System.out.println("Failed to rename temp file");
                }
            } else {
                System.out.println("Failed to delete og file");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void blockUser(String input){
        int currentUserID = Integer.parseInt(input.substring(0, input.indexOf(",")));
        int otherUserID = Integer.parseInt(input.substring(input.indexOf(",") + 1));
        User currentUser = null;
        User otherUser = null;
        String line;
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == currentUserID){
                    currentUser = User.deserialize(line);
                }
            }
            BufferedReader bfr2 = new BufferedReader(new FileReader("users.ser"));
            while ((line = bfr2.readLine()) != null){
                if (Integer.parseInt(line.substring(0, line.indexOf(","))) == otherUserID){
                    otherUser = User.deserialize(line);
                }
            }
            UserFileDatabase database = new UserFileDatabase("users.ser");
            UsersService userService = new UsersService(database);
            userService.blockUser(currentUser, otherUser);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void deletePost(String input) {
        File inputFile = new File("posts.ser");
        File tempFile = new File(inputFile.getAbsolutePath() + ".temp");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.substring(0, line.indexOf(",")).equals(input)) {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Failed to rename temp file");
                }
            } else {
                System.out.println("Failed to delete og file");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteComment(String input) {
        File inputFile = new File("comments.ser");
        File tempFile = new File(inputFile.getAbsolutePath() + ".temp");
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.substring(0, line.indexOf(",")).equals(input)) {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            if (inputFile.delete()) {
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Failed to rename temp file");
                }
            } else {
                System.out.println("Failed to delete og file");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
