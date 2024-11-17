import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileDatabase implements UserDataBaseInterface {
    private final String fileName;

    public UserFileDatabase(String fileName) {
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize database file", e);
        }
    }


    public void clear() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            writer.print(""); // Truncate the file
        } catch (IOException e) {
            throw new RuntimeException("Failed to clear database file", e);
        }
    }

    @Override
    public void storeUser(User user) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(user.serialize() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Failed to store user", e);
        }
    }

    @Override
    public User retrieveUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.deserialize(line);
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to retrieve user", e);
        }
        return null;
    }

    public void updateUser(User updatedUser) {
        List<User> users = new ArrayList<>();
        boolean userFound = false;

        // Read all users from the database file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.deserialize(line);
                if (user.getUsername().equals(updatedUser.getUsername())) {
                    users.add(updatedUser); // Replace with updated user
                    userFound = true;
                } else {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from the database file", e);
        }

        // If the user was not found, throw an exception
        if (!userFound) {
            throw new IllegalArgumentException("User not found in the database: " + updatedUser.getUsername());
        }

        // Write all users back to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false))) {
            for (User user : users) {
                writer.println(user.serialize());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to the database file", e);
        }
    }

}
