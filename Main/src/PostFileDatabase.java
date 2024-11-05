/**
 * Post Database
 *
 * Class that creates a database of files that store information about each post
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */


import java.io.*;

public class PostFileDatabase implements PostDataBaseInterface, Serializable{
    private static final String FILE_PATH = "posts/"; //denotes the file(MAY BE CHANGED LATER)
    private final File directory;
    public PostFileDatabase(String fileName) throws IOException { //constructor
        this.directory = new File(FILE_PATH + fileName);
    }

    @Override
    public void storePost(Post post) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH + post.getID() + ".ser"))) {
            oos.writeObject(post); //this writes serialized object to post
            System.out.println("Post " + post.getID() + " saved successfully"); //allows database to hold onto the User
        }
        catch(IOException e) {
            System.out.println("An error occurred while saving post");
            e.printStackTrace();
        }
    }

    @Override
    public Post retrievePost(int postID) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH + postID + ".ser"))) {
            Post post = (Post) ois.readObject();
            System.out.println("Post " + postID + " retrieved successfully.");
            return post;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while saving post");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePost(Post post) {
        // To update a post, overwrite the file with the new updated post object
        storePost(post);
    }


    public void clear() {
        try (FileWriter writer = new FileWriter(directory, false)) {
            // Open file, in overwrite mode, to clear its contents
            writer.write("");  // Write an empty string to clear the file
        } catch (IOException e) {
            System.out.println("Error clearing the database: " + e.getMessage()); //can't clean the file
        }
    }


}

