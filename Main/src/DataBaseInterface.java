public interface DataBaseInterface {
    //void connect(); //connection to the database
    //void closeConnection(); //closes connection to the database
    void storeUser(User user); //Save user information in the database
    User retrieveUser(String username); //Gets the user via their username
    void updateUser(User user); //Updates an already existing user
}
