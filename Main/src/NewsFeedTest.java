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
        User user4 = new User(4, "User4");

        Post post1 = new Post("Post 1 Content", user1);
        Post post2 = new Post("Post 2 Content", user2);
        Post post3 = new Post("Post 3 Content", user3);
        Post post4 = new Post("Post 4 Content", user1);
        Post post5 = new Post("Post 5 Content", user2);

        post1.setLikes(new ArrayList<User>(){{add(user1);add(user2);add(user3);}});
        post2.setLikes(new ArrayList<User>(){{add(user1);add(user2);}});
        post3.setLikes(new ArrayList<User>(){{add(user1);add(user2);add(user3);add(user4);}});


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
