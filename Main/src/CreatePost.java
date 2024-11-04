import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

//Creates a post and adds it to the file that stores post information
//Emerson

public class CreatePost implements Runnable{
    //runs the GUI for creating a post and adds it to a database of posts
    public void run(){
        JFrame frame = new JFrame("Make a Post");
        JPanel panel = new JPanel();
        frame.add(panel);
        frame.setResizable(true);
        panel.add(new JLabel("Post content"));
        String content = JOptionPane.showInputDialog(panel, "What would you like to say?");
        panel.setBounds(130, 100, 100, 40);
        User user = User.getCurrentUser();

        String postFile = "posts.ser";

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(postFile))) {
            Post post = new Post(content, user);
            output.writeObject(post);
            output.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
