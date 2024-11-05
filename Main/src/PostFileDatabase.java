import java.io.*;

public class PostFileDatabase implements PostDataBaseInterface{
    private static final String FILE_PATH = "posts/"; //denotes the file(MAY BE CHANGED LATER)
    private File directory;
    public PostFileDatabase(String fileName) throws IOException { //constructor
        this.directory = new File(FILE_PATH + fileName);
    }

    @Override
    public void storePost(Post post) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH + post.getID() + ".ser" ))) {
            oos.writeObject(post); //this writes serialized object to file
            System.out.println("User " + post.getID() + " saved successfully"); //allows database to hold onto the User
        }
        catch(IOException e) {
            System.out.println("An error occurred while saving user");
            e.printStackTrace();
        }
    }

    @Override
    public Post retrievePost(int postID) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH + postID + ".ser"))) {
            Post post = (Post) ois.readObject();
            System.out.println("User " + postID + " retrieved successfully.");
            return post;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while saving user");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePost(Post post) {
        // To update a user, overwrite the file with the new updated user object
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

