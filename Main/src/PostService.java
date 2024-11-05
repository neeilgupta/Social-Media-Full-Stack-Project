import java.io.Serializable;
import java.util.ArrayList;

public class PostService implements Serializable {
    private PostFileDatabase database;

    public PostService(PostFileDatabase database) {
        this.database = database;
    }

    public void addPost(int ID, String content, User user) {
        // Create new User object
        Post post = new Post(ID, content, user);
        // Store the User object in the database
        database.storePost(post);

    }

    public Post searchPost(int postID) {
        // Retrieve and return a User object by username from the database
        return database.retrievePost(postID);
    }

    public Post viewPost(int postID) {
        // Retrieve and return User details by username from the database
        return database.retrievePost(postID);
    }

    public void likePost(Post currentPost, User currentUser) {
        // Call currentUser.block(otherUser)
        currentPost.likePost(currentUser);
        // Update blocked list in the database
        database.updatePost(currentPost);
    }
    public void dislikePost(Post currentPost, User currentUser) {
        // Call currentUser.block(otherUser)
        currentPost.dislikePost(currentUser);
        // Update blocked list in the database
        database.updatePost(currentPost);
    }
    public void addComment(Post currentPost, User currentUser, Comment comment) {
        ArrayList<Comment> newComments = new ArrayList<Comment>();
        newComments = currentPost.getComments();
        newComments.add(comment);
        currentPost.setComments(newComments);
        database.updatePost(currentPost);
    }

}
