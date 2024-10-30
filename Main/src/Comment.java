import java.time.LocalDateTime;

public class Comment {
    private int ID;
    private String content;
    private int userID;
    private LocalDateTime dateTime;
    private int likes;

    public Comment(int ID, String content, int UserID){
        this.ID = ID;
        this.content = content;
        this.userID = UserID;
        this.dateTime = LocalDateTime.now();
        this.likes = 0;
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
    public int getLikes(){
        return likes;
    }
    public void addLike(){
        likes++;
    }
    public void dislike(){
        likes--;
    }

}
