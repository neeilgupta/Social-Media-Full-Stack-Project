/**
 * Comment Service Interface
 *
 * Interface for the comment service class
 *
 * Emerson Barrett
 *
 * @version November 4th, 2024
 *
 */


public interface CommentServiceInterface {

    public void addComment(int ID, String content, User user, Post post);
    public Comment searchComment(int commentID);
    public Comment viewComment(int commentID);
    public void likeComment(Comment currentComment, User currentUser);
    public void dislikeComment(Comment currentComment, User currentUser);
}
