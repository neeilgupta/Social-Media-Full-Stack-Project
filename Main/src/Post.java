import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post implements PostInterface {
    private int ID;
    private String content;
    private Main user;
    private LocalDateTime dateTime;
    private ArrayList<Comment> comments;
    private ArrayList<Main> likes;
    private ArrayList<Main> dislikes;


    public Post(){

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
    public Main getUser(){
        return user;
    }
    @Override
    public void setUser(Main user){
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
    public ArrayList<Main> getLikes(){
        return likes;
    }
    @Override
    public void setLikes(ArrayList<Main> likes){
        this.likes = likes;
    }
    @Override
    public ArrayList<Main> getDislikes() {
        return dislikes;
    }
    @Override
    public void setDislikes(ArrayList<Main> dislikes) {
        this.dislikes = dislikes;
    }

}
