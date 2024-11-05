public interface CommentDataBaseInterface {
    //void connect(); //connection to the database
    //void closeConnection(); //closes connection to the database
    void storeComment(Comment comment); //Save post information in the database
    Comment retrieveComment(int commentID); //Gets the post via its postID
    void updateComment(Comment comment); //Updates an already existing post

}
