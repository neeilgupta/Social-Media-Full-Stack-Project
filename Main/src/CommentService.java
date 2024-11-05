import java.io.Serializable;
import java.util.ArrayList;

/**
 * Comment Service
 *
 * Class that takes the methods of the Comment class
 * and converts them into methods compatible with the comment file database class
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */


public class CommentService implements Serializable {
    private CommentFileDatabase database;

    public CommentService(CommentFileDatabase database) {
        this.database = database;
    }

    public void addComment(int ID, String content, User user, Post post) {
        // Create new comment object
        Comment comment = new Comment(ID, content, user, post);
        // Store the comment object in the database
        database.storeComment(comment);

    }

    public Comment searchComment(int commentID) {
        // Retrieve and return a comment object by commentID from the database
        return database.retrieveComment(commentID);
    }

    public Comment viewComment(int commentID) {
        // Retrieve and return comment details by commentID from the database
        return database.retrieveComment(commentID);
    }

    public void likeComment(Comment currentComment, User currentUser, Post currentPost) {
        currentComment.addLike(currentUser, currentPost);
        database.updateComment(currentComment);
    }
    public void dislikeComment(Comment currentComment, User currentUser, Post currentPost) {
        currentComment.dislike(currentUser, currentPost);
        database.updateComment(currentComment);
    }

}
