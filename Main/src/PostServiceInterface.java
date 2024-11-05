import java.util.ArrayList;

/**
 * Post Service Interface
 *
 * Interface for the post service class
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */


public interface PostServiceInterface {
    public void addPost(int ID, String content, User user);

    public Post searchPost(int postID);

    public Post viewPost(int postID);

    public void likePost(Post currentPost, User currentUser);
    public void dislikePost(Post currentPost, User currentUser);
    public void addComment(Post currentPost, User currentUser, Comment comment);
}
