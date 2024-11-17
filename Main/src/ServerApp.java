import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp extends Thread {
    private Socket socket;
    public ServerApp(Socket inSocket){
        socket = inSocket;
    }
    public void run() {
        try {
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );
            String line = "";
            while (!line.equals("###")) {
                try {
                    line = in.readUTF();
                    String action = line.substring(0, line.indexOf("##"));
                    String input = line.substring(line.indexOf("##") + 2);
                    if (action.equals("createUser")) {
                        createUser(input);
                    } else if (action.equals("createPost")) {
                        createPost(input);
                    } else if (action.equals("createComment")) {
                        createComment(input);
                    }

                    line = "###";
                } catch (IOException e) {
                    e.printStackTrace();
                    line = "###";
                }
            }
            in.close();

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
}
