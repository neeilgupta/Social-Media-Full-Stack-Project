import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment implements Serializable {
    private int ID;
    private String content;
    private User user;
    private LocalDateTime dateTime;
    private ArrayList<User> likes;
    private ArrayList<User> dislikes;
    private Post post;

    //comment id, content of the comment, the user making the comment, the post the comment is being made on
    public Comment(int id, String content, User user, Post post){
        this.ID = id;
        this.content = content;
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.likes = new ArrayList<>();
        this.post = post;
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public User getUserID(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    public ArrayList<User> getLikes(){
        return likes;
    }
    public void setLikes(ArrayList<User> likes){
        this.likes = likes;
    }
    public ArrayList<User> getDislikes(){
        return dislikes;
    }
    public void setDislikes(ArrayList<User> dislikes){
        this.dislikes = dislikes;
    }
    public void addLike(User user){
        this.likes.add(user);
    }
    public void dislike(User user){
        this.likes.add(user);
    }

}
