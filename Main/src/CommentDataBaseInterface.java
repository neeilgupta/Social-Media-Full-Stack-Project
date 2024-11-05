public interface CommentDataBaseInterface {

    /**
     * Comment Database Interface
     *
     * Interface for the comment database
     *
     * Emerson Barrett
     *
     * @version November 4th, 2024
     *
     */

    //void connect(); //connection to the database
    //void closeConnection(); //closes connection to the database
    void storeComment(Comment comment); //Save comment information in the database
    Comment retrieveComment(int commentID); //Gets the comment via its commentID
    void updateComment(Comment comment); //Updates an already existing comment

}
