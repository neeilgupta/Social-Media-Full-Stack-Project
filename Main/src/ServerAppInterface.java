

/**
 * Server App Interface
 * <p>
 * Interface for the Server App class
 * <p>
 * Emerson Barrett
 *
 * @version November 17, 2024
 */

public interface ServerAppInterface {
    void run();
    //creation methods
    void createUser();
    void createPost();
    void createComment();
    //attribute changing methods
    void likePost();
    void dislikePost();
    void likeComment();
    void dislikeComment();
    void follow();
    void unfollow();
    void blockUser();
    //deletion methods
    void removeAccount();
    void deletePost();
    void deleteComment();
}
