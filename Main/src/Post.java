import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post implements PostInterface {
    private int ID;
    private String content;
    private User user;
    private LocalDateTime dateTime;
    private ArrayList<Comment> comments;
    private ArrayList<User> likes;
    private ArrayList<User> dislikes;


    public Post(int ID, String content, User user, LocalDateTime dateTime, ArrayList<Comment> comments, ArrayList<User> likes, ArrayList<User> dislikes){
        this.ID = ID;
        this.content = content;
        this.user = user;
        this.dateTime = dateTime;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
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
        String postDisplay = "Post ID: " + this.getID() + ", Likes: " + this.getLikes().size() + ", Timestamp: " + this.getDateTime();
        return postDisplay;
    }

}
