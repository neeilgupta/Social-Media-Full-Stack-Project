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
    ArrayList<Main> getLikes();
    void setLikes(ArrayList<Main> likes);
    ArrayList<Main> getDislikes();
    void setDislikes(ArrayList<Main> likes);
}
