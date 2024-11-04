import javax.swing.*;
import java.io.*;

//Creates a post and adds it to the file that stores post information
//Emerson

public class AccessPost implements Runnable{
    //runs the GUI for creating a post and adds it to a database of posts
    public void run(){
        String postFile = "posts.ser";

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(postFile))) {
            Post post = (Post) input.readObject();
            System.out.println(post);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
