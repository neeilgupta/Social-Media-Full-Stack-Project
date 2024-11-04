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


    public Post(int postID, String content, User user, LocalDateTime dateTime, int likeCount, int dislikeCount){
        this.ID = postID;
        this.content = content;
        this.user = user;
        this.dateTime = dateTime;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();

        for (int i = 0; i < likeCount; i++) { //created dummy users to match likes
            likes.add(new User(i + 100, "Liker" + (i + 1)));
        }

        for (int j = 0; j < dislikeCount; j++) { //creates dummy Users to match dislikes
            dislikes.add(new User(j + 200, "Disliker" + (j + 1)));
        }
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
    public void setUser(Main user) {

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
        String postDisplay = "Post ID: " + this.getID() + ", Likes: " + this.getLikes().size() + ", Dislikes: " + this.getDislikes().size() + ", Timestamp: " + this.getDateTime();
        return postDisplay;
    }

}
