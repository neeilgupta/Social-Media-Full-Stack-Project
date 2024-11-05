import java.io.*;

public class CommentFileDatabase implements CommentDataBaseInterface, Serializable{
    private static final String FILE_PATH = "comments/"; //denotes the file(MAY BE CHANGED LATER)
    private final File directory;
    public CommentFileDatabase(String fileName) throws IOException { //constructor
        this.directory = new File(FILE_PATH + fileName);
    }

    @Override
    public void storeComment(Comment comment) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH + comment.getID() + ".ser"))) {
            oos.writeObject(comment); //this writes serialized object to post
            System.out.println("User " + comment.getID() + " saved successfully"); //allows database to hold onto the User
        }
        catch(IOException e) {
            System.out.println("An error occurred while saving user");
            e.printStackTrace();
        }
    }

    @Override
    public Comment retrieveComment(int commentID) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH + commentID + ".ser"))) {
            Comment comment = (Comment) ois.readObject();
            System.out.println("Comment " + commentID + " retrieved successfully.");
            return comment;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while saving user");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateComment(Comment comment) {
        // To update a post, overwrite the file with the new updated post object
        storeComment(comment);
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

