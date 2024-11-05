import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * Post Interface
 *
 * Interface for the post class
 *
 * Neeil G.
 *
 * @version November 4th, 2024
 *
 */

public interface PostInterface {
    int getID();
    void setID(int ID);
    String getContent();
    void setContent(String content);
    User getUser();
    void setUser(User user);
    LocalDateTime getDateTime();
    void setDateTime(LocalDateTime dateTime);
    ArrayList<Comment> getComments();
    void setComments(ArrayList<Comment> comments);
    ArrayList<User> getLikes();
    void setLikes(ArrayList<User> likes);
    ArrayList<User> getDislikes();
    void setDislikes(ArrayList<User> likes);
}
