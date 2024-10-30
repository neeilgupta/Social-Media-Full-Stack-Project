import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
    private int ID;
    private String content;
    private Main user;
    private LocalDateTime dateTime;
    private ArrayList<Comment> comments;
    private ArrayList<Main> likes;

    public Post(){

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
    public Main getUser(){
        return user;
    }
    public void setUser(Main user){
        this.user = user;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
    public void setComments(ArrayList<Comment> comments){
        this.comments = comments;
    }
    public ArrayList<Main> getLikes(){
        return likes;
    }
    public void setLikes(ArrayList<Main> likes){
        this.likes = likes;
    }

}
