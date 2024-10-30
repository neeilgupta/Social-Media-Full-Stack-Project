package Main.src;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
    private int ID;
    private String content;
    private int userID;
    private LocalDateTime dateTime;
    private ArrayList<Comment> comments;
    private int likes;
    private static int currentID;

    public Post(int userID, String content){
        this.userID = userID;
        synchronized (this){
            this.ID = currentID;
            currentID++;
        }
        this.content = content;
        this.dateTime = LocalDateTime.now();
        this.likes = 0;
        this.comments = new ArrayList<Comment>();
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
    public void setUserID(int userID){
        this.userID = userID;
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
    public int getLikes(){
        return likes;
    }
    public void addLike(){
        this.likes++;
    }
    public void dislike(){
        this.likes--;
    }

    public boolean deleteComment(int userID, int commentID){
        if (userID != this.userID) {
            return false;
        }
        for (Comment comment : this.comments){
            if (comment.getID() == commentID){
                comments.remove(comment);
                return true;
            }
        }
        return false;
    }

}
