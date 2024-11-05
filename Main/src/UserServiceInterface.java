public interface UserServiceInterface {
        // Method to add a user
        void addUser(String username, String password);

        // Method to search for a user by username
        User searchUser(String username);

        // Method to view user details by username
        User viewUser(String username);

        // Method to add a follower to a user
        void addFollower(User currentUser, User otherUser);

        // Method to remove a follower from a user
        void removeFollower(User currentUser, User otherUser);

        // Method to block a user
        void blockUser(User currentUser, User otherUser);

        // Method to unblock a user
        void unblockUser(User currentUser, User otherUser);
    }


