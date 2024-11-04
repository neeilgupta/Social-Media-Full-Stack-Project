import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;

public class Comment {
    private int ID;
    private String content;
    private int userID;
    private LocalDateTime dateTime;
    private ArrayList<User> likes;
    private ArrayList<User> dislikes;

    public Comment(int ID, String content, int UserID){
        this.ID = ID;
        this.content = content;
        this.userID = UserID;
        this.dateTime = LocalDateTime.now();
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
    public int getUserID(){
        return userID;
    }
    public void setUser(int userID){
        this.userID = userID;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }
    public ArrayList<User> getLikes(){ return likes;}
    public ArrayList<User> getDislikes(){ return dislikes;}
    public void setLikes(ArrayList<User> likes) { this.likes = likes; }
    public void setDislikes(ArrayList<User> dislikes) { this.dislikes = dislikes; }


}
