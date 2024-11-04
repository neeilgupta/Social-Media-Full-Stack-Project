import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.*;
import java.io.Serializable;

//Includes constructors for creating a post, post fields, and all getter and setter methods
//Plus a few extra methods as needed
//Emerson

public class Post implements PostInterface, Serializable {
    private int ID;
    private String content;
    private User user;
    private LocalDateTime dateTime;
    private ArrayList<Comment> comments;
    private ArrayList<User> likes;
    private ArrayList<User> dislikes;
    private static int idIndex = 1;

    BufferedWriter bfr;

    //creates a post with specified user and content, automatically assigns other variables
    public Post(String content, User user){
        this.ID = idIndex++;
        this.content = content;
        this.user = user;
        this.dateTime = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();
    }

    //getter and setter methods
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

    //to string method, in case it is needed
    public String toString(){
        String result = ID + ",";
        result += content + ",";
        result += user.toString() + ",";
        result += dateTime.toString() + ",";

        return result;
    }

}
