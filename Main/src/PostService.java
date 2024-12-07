import java.io.Serializable;
import java.util.ArrayList;

/**
 * Post Service
 *
 * A class that converts methods of the post class into methods that work with the post interface
 * to create and manipulate post files
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */


public class PostService implements Serializable {
    private PostFileDatabase database;

    public PostService(PostFileDatabase database) {
        this.database = database;
    }

    public void addPost(int ID, String content, User user) {
        // Create new User object
        Post post = new Post(ID, content, user, 0, 0);
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
        currentPost.likePost(currentUser);
        database.updatePost(currentPost);
    }
    public void dislikePost(Post currentPost, User currentUser) {
        currentPost.dislikePost(currentUser);
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
