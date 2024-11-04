import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedTest {
    private NewsFeed newsFeed;
    private User user1;
    private User user2;
    private User user3;
    private Post post1;
    private Post post2;
    private Post post3;

    @BeforeEach
    public void setUp() {
        newsFeed = new NewsFeed();
        user1 = new User(1, "User1");
        user2 = new User(2, "User2");
        user3 = new User(3, "User3");

        post1 = new Post("First post content", user1);
        post2 = new Post("Second post content", user2);
        post3 = new Post("Third post content", user3);

        newsFeed.addPost(post1);
        newsFeed.addPost(post2);
        newsFeed.addPost(post3);
    }

    @Test
    public void testAddPost() {
        Post newPost = new Post("New post content", user1);
        newsFeed.addPost(newPost);

        // Check if the post is added correctly
        List<Post> userFeed = newsFeed.getFeedForUser(user1.getUserID());
        assertTrue(userFeed.contains(newPost), "New post should be in the user's feed");
    }

    @Test
    public void testLikePost() {
        newsFeed.likePost(post1, user2); // User2 likes post1

        assertEquals(1, post1.getLikes().size(), "Post1 should have one like");
        assertTrue(post1.getLikes().contains(user2), "User2 should have liked post1");
    }

    @Test
    public void testDislikePost() {
        newsFeed.dislikePost(post2, user3); // User3 dislikes post2

        assertEquals(1, post2.getDislikes().size(), "Post2 should have one dislike");
        assertTrue(post2.getDislikes().contains(user3), "User3 should have disliked post2");
    }

    @Test
    public void testFeedOrderByLikes() {
        newsFeed.likePost(post1, user1);
        newsFeed.likePost(post1, user2);
        newsFeed.likePost(post2, user1);

        List<Post> userFeed = newsFeed.getFeedForUser(user1.getUserID());

        // Check that post1 appears before post2 in the feed due to more likes
        assertEquals(post1, userFeed.get(0), "Post1 should appear first in the feed");
        assertEquals(post2, userFeed.get(1), "Post2 should appear second in the feed");
    }

    @Test
    public void testFeedOrderByTimestamp() {
        Post post4 = new Post("Newest post content", user1);
        newsFeed.addPost(post4);

        List<Post> userFeed = newsFeed.getFeedForUser(user1.getUserID());

        // Check that the newest post appears first if likes are equal
        assertEquals(post4, userFeed.get(0), "Newest post should appear first in the feed");
    }
}
