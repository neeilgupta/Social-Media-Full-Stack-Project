import java.io.Serializable;
import java.util.ArrayList;

public class CommentService implements Serializable {
    private CommentFileDatabase database;

    public CommentService(CommentFileDatabase database) {
        this.database = database;
    }

    public void addComment(int ID, String content, User user, Post post) {
        // Create new User object
        Comment comment = new Comment(ID, content, user, post);
        // Store the User object in the database
        database.storeComment(comment);

    }

    public Comment searchComment(int commentID) {
        // Retrieve and return a User object by username from the database
        return database.retrieveComment(commentID);
    }

    public Comment viewComment(int commentID) {
        // Retrieve and return User details by username from the database
        return database.retrieveComment(commentID);
    }

    public void likeComment(Comment currentComment, User currentUser) {
        currentComment.addLike(currentUser);
        database.updateComment(currentComment);
    }
    public void dislikeComment(Comment currentComment, User currentUser) {
        currentComment.dislike(currentUser);
        database.updateComment(currentComment);
    }

}
