import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;

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


    public Post(int ID, String content, User user){
        this.ID = ID;
        this.content = content;
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
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

}
