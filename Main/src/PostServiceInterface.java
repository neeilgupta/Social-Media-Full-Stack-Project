import java.util.ArrayList;

public interface PostServiceInterface {
    public void addPost(int ID, String content, User user);

    public Post searchPost(int postID);

    public Post viewPost(int postID);

    public void likePost(Post currentPost, User currentUser);
    public void dislikePost(Post currentPost, User currentUser);
    public void addComment(Post currentPost, User currentUser, Comment comment);
}
