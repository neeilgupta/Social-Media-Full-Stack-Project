import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedTest {

    public static void main(String[] args) {
        NewsFeed newsFeed = new NewsFeed();

        // Create test users
        User user1 = new User(1, "User1");
        User user2 = new User(2, "User2");
        User user3 = new User(3, "User3");

        Post post1 = new Post(101, "Post 1 Content", user1, LocalDateTime.of(2024, 11, 1, 12, 30), 120, 60);
        Post post2 = new Post(102, "Post 2 Content", user2, LocalDateTime.of(2024, 11, 2, 14, 0), 75, 5);
        Post post3 = new Post(103, "Post 3 Content", user3, LocalDateTime.of(2024, 11, 2, 10, 0), 120, 20);
        Post post4 = new Post(104, "Post 4 Content", user1, LocalDateTime.of(2024, 10, 31, 9, 0), 200, 30);
        Post post5 = new Post(105, "Post 5 Content", user2, LocalDateTime.of(2024, 11, 1, 8, 45), 50, 0);

        newsFeed.addPost(post1);
        newsFeed.addPost(post2);
        newsFeed.addPost(post3);
        newsFeed.addPost(post4);
        newsFeed.addPost(post5);

        List<Post> userFeed = newsFeed.getFeedForUser(user1.getUserID());

        System.out.println("Expected order: \n" + post4 + "\n" + post3 + "\n" + post2 + "\n" + post1 + "\n" + post5);

        System.out.println("Actual order:");
        for (Post post : userFeed) {
            System.out.println(post);
        }
    }
}
