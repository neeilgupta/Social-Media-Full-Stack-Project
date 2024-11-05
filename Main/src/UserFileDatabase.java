/**
 * UserFile DataBase
 *
 * Class that creates a database of files for each File object
 *
 * Sameer Dadoo
 *
 * @version November 4th, 2024
 *
 */
import java.io.*;

public class UserFileDatabase implements UserDataBaseInterface{
    private static final String FILE_PATH = "users/"; //denotes the file(MAY BE CHANGED LATER)
    private File directory;
    public UserFileDatabase(String fileName) throws IOException { //constructor

        this.directory = new File(FILE_PATH + fileName);
        directory.createNewFile();
    }

    @Override
    public void storeUser(User user) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH + user.getUsername() + ".ser" ))) {
            oos.writeObject(user); //this writes serialized object to file
            System.out.println("User " + user.getUsername() + " saved successfully"); //allows database to hold onto the User
        }
        catch(IOException e) {
            System.out.println("An error occurred while saving user");
            e.printStackTrace();
        }
    }

    @Override
    public User retrieveUser(String username) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH + username + ".ser"))) {
            User user = (User) ois.readObject();
            System.out.println("User " + username + " retrieved successfully.");
            return user;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An error occurred while saving user");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateUser(User user) {
        // To update a user, overwrite the file with the new updated user object
        storeUser(user);
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

