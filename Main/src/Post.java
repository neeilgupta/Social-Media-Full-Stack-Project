import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Post
 *
 * Class that creates a post object assigned to a specific users, posts have arrays of comments, likes, and dislikes
 *
 * Emerson Barrett, Neeil G.
 *
 * @version November 4th, 2024
 *
 */


public class Post implements PostInterface, Serializable{
    private int ID;
    private String content;
    private User user;
    private LocalDateTime dateTime;
    private ArrayList<Comment> comments;
    private ArrayList<User> likes;
    private ArrayList<User> dislikes;
    private static List<Post> posts = new ArrayList<>();

    public Post(int ID, String content, User user){
        this.ID = ID;
        this.content = content;
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
    }

    public static ArrayList<Post> getUserPosts(User currentUser) {
        ArrayList<Post> userPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getUser().getUserID() == currentUser.getUserID()) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }


    public static Post deserialize(String data) throws IOException {
        String[] parts = data.split(",");
        BufferedReader read = new BufferedReader(new FileReader("user.ser"));
        String line = read.readLine();
        User thisUser = null;
        while ((line = read.readLine()) != null) {
            if (line.substring(0, line.indexOf(",")).equals(parts[2])) {
                thisUser = User.deserialize(line);
            }
        }
        return new Post(Integer.parseInt(parts[0]), parts[1], thisUser);
    }

    @Override
    public int getID(){
        return ID;
    }
    @Override
    public void setID(int ID){
        this.ID = ID;
    }
    @Override
    public String getContent(){
        return content;
    }
    @Override
    public void setContent(String content){
        this.content = content;
    }
    @Override
    public User getUser(){
        return user;
    }
    @Override
    public void setUser(User user){
        this.user = user;
    }
    @Override
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    @Override
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    @Override
    public ArrayList<Comment> getComments() {
        return comments;
    }
    @Override
    public void setComments(ArrayList<Comment> comments){
        this.comments = comments;
    }
    @Override
    public ArrayList<User> getLikes(){
        return likes;
    }
    @Override
    public void setLikes(ArrayList<User> likes){
        this.likes = likes;
    }
    @Override
    public ArrayList<User> getDislikes() {
        return dislikes;
    }
    @Override
    public void setDislikes(ArrayList<User> dislikes) {
        this.dislikes = dislikes;
    }

    public String toString() {
        String postDisplay = this.getID() + "," + this.getContent() + "," + this.getLikes().size() + "," + this.getDislikes().size() + "," + this.getUser() + "," + this.getDateTime();
        return postDisplay;
    }

    public void likePost(User user) {
        boolean alreadyLiked = false;
        for (User likedUser : this.getLikes())
            if (likedUser.getUserID() == user.getUserID()) {
                alreadyLiked = true;
                break;
            }
        if (!alreadyLiked) {
            this.getLikes().add(user);
        }
    }
    public void dislikePost(User user) {
        boolean alreadyDisliked = false;
        for (User dislikedUser : this.getDislikes()) {
            if (dislikedUser.getUserID() == user.getUserID()) {
                alreadyDisliked = true;
                break;
            }
        }
        if (!alreadyDisliked) {
            this.getDislikes().add(user);
        }
    }

    public static int getUserPostCount(User user) {
        int count = 0;
        for (Post post : posts) {
            if (post.getUser().getUserID() == user.getUserID()) {
                count++;
            }
        }
        return count;
    }

    // Write posts to post.ser
    public static void writePostsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("post.ser"))) {
            oos.writeObject(posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read posts from post.ser
    @SuppressWarnings("unchecked")
    public static void readPostsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("post.ser"))) {
            posts = (List<Post>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("post.ser not found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
