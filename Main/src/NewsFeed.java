import java.util.ArrayList;
import java.util.List;

public class NewsFeed {
    // List to store posts
    private ArrayList<Post> posts;

    // Constructor initializes the posts list
    public NewsFeed() {
        this.posts = new ArrayList<>();
    }

    // Retrieves a feed for the user, filtering by followers and sorted by likes and timestamp
    public List<Post> getFeedForUser(int userID) {
        List<Post> userFeed = new ArrayList<>();

        // Stores posts from user and people they follow
        for (Post post : posts) {
            if (post.getID() == userID || isFollowing(userID, post.getID())) {
                insertInOrder(userFeed, post);
            }
        }

        return userFeed;
    }

    // Method to insert posts into userFeed based on likes and timestamp
    private void insertInOrder(List<Post> userFeed, Post post) {
        int i = 0;
        while (i < userFeed.size()) {
            // Check the number of likes
            if (post.getLikes().size()-post.getDislikes().size() > userFeed.get(i).getLikes().size()-userFeed.get(i).getDislikes().size()) {
                break; // The new post has more likes, so insert it here
            } else if (post.getLikes().size()-post.getDislikes().size() == userFeed.get(i).getLikes().size()-userFeed.get(i).getDislikes().size()) {
                // If likes are the same, compare the timestamps
                if (post.getDateTime().isAfter(userFeed.get(i).getDateTime())) {
                    break; // The new post is newer, so insert it here
                }
            }
            i++; // Move to the next post
        }
        userFeed.add(i, post); // Insert the post at the found index
    }


    // Method to add posts to the feed
    public void addPost(Post post) {
        posts.add(post);
    }

    // Helper method to check if a user follows another user
    private boolean isFollowing(int userID, int followedUserID) {
        // Idk if I should implement this method here because this is the newsfeed but I just had it return true;
        return true;
    }

}
