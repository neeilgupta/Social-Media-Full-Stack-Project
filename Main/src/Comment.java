import java.time.LocalDateTime;

public class Comment {
    private int ID;
    private String content;
    private User user;
    private LocalDateTime dateTime;
    private int likes;
    private static int idIndex = 1;

    public Comment(String content, User user, Post post){
        this.ID = idIndex++;
        this.content = content;
        this.user = user;
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
