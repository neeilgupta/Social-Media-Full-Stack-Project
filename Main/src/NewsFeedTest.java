
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * The NewsFeedTest class tests the functionality of the NewsFeed class,
 * ensuring correct behavior for adding posts, liking, disliking, and ordering the feed.
 *
 * Created by Neeil Gupta
 * Date: November 3, 2024
 */

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
        user1 = new User(User.numUsers++, "user1", "pass123", "oney");
        user2 = new User(User.numUsers++, "user2", "pass123", "twoy");
        user3 = new User(User.numUsers++, "user2", "pass123", "threey");

        post1 = new Post(1,"First post content", user1);
        post2 = new Post(2,"Second post content", user2);
        post3 = new Post(3,"Third post content", user3);

        newsFeed.addPost(post1);
        newsFeed.addPost(post2);
        newsFeed.addPost(post3);
    }

    @Test
    public void testAddPost() {
        Post newPost = new Post(4,"New post content", user1);
        newsFeed.addPost(newPost);

        // Check if the post is added correctly
        List<Post> userFeed = newsFeed.getFeedForUser(user1);
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
        // Setup - creating users and posts
        user1 = new User(User.numUsers++, "user1", "pass123", "oney");
        user2 = new User(User.numUsers++, "user2", "pass123", "twoy");
        user3 = new User(User.numUsers++, "user2", "pass123", "threey");

        Post post1 = new Post(4,"Post 1 Content", user1);
        Post post2 = new Post(5,"Post 2 Content", user1);

        // Adding posts to the feed
        newsFeed.addPost(post1);
        newsFeed.addPost(post2);

        // Like post1 twice and post2 once
        newsFeed.likePost(post1, user1);
        newsFeed.likePost(post1, user2);
        newsFeed.likePost(post2, user1);

        // Get user feed for user1
        List<Post> userFeed = newsFeed.getFeedForUser(user1);

        // Debugging output
        System.out.println("User feed size: " + userFeed.size());
        for (Post post : userFeed) {
            System.out.println("Post in feed: " + post.getContent());
        }

        // Assertions to check feed order
        assertTrue(userFeed.size() >= 2, "User feed should have at least 2 posts for this test");

        if (userFeed.size() >= 2) {
            assertEquals(post1, userFeed.get(0), "Post1 should appear first in the feed due to more likes");
            assertEquals(post2, userFeed.get(1), "Post2 should appear second in the feed");
        }
    }

    @Test
    public void testFeedOrderByTimestamp() {
        Post post4 = new Post(6,"Newest post content", user1);
        newsFeed.addPost(post4);

        List<Post> userFeed = newsFeed.getFeedForUser(user1);

        // Check that the newest post appears first if likes are equal
        assertEquals(post4, userFeed.get(0), "Newest post should appear first in the feed");
    }
}
