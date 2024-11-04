public interface PostDataBaseInterface {
    //void connect(); //connection to the database
    //void closeConnection(); //closes connection to the database
    void storePost(Post post); //Save post information in the database
    Post retrievePost(int postID); //Gets the post via its postID
    void updatePost(Post post); //Updates an already existing post
}
