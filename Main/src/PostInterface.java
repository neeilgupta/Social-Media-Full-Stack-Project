import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PostInterface {
    int getID();
    void setID(int ID);
    String getContent();
    void setContent(String content);
    Main getUser();
    void setUser(Main user);
    LocalDateTime getDateTime();
    void setDateTime(LocalDateTime dateTime);
    ArrayList<Comment> getComments();
    void setComments(ArrayList<Comment> comments);
    ArrayList<User> getLikes();
    void setLikes(ArrayList<User> likes);
    ArrayList<User> getDislikes();
    void setDislikes(ArrayList<User> likes);
}
