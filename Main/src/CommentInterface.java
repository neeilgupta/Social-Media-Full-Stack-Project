import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Comment Interface
 *
 * Interface for the comment class
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */

public interface CommentInterface {
    public int getID();
    public void setID(int ID);
    public String getContent();
    public void setContent(String content);
    public User getUserID();
    public void setUser(User user);
    public LocalDateTime getDateTime();
    public void setDateTime(LocalDateTime dateTime);
    public ArrayList<User> getLikes();
    public void setLikes(ArrayList<User> likes);
    public ArrayList<User> getDislikes();
    public void setDislikes(ArrayList<User> dislikes);
    public void addLike(User user);
    public void dislike(User user);
}
