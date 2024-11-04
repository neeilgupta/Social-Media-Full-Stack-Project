import java.time.LocalDateTime;

//tests the post and create post classes
//Emerson

public class PostTester {
    public static void main(String[] args) {
        User testUser = new User (5, "user1");
        Post testPost = new Post ("post content 1", testUser);
        System.out.println("Expected: 1,post content 1," + testUser.toString() + LocalDateTime.now());
        System.out.println("Actual: " + testPost.toString());

        CreatePost createPostTest = new CreatePost();
        createPostTest.run();
    }
}
