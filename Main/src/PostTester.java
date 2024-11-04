import java.time.LocalDateTime;

//tests the post and create post classes
//Emerson

public class PostTester {
    public static void main(String[] args) {
        User testUser = new User (5, "user1");
        Post testPost = new Post ("post content 1", testUser);
        System.out.println("Expected: 1,post content 1,0,0," + testUser + LocalDateTime.now());
        System.out.println("Actual: " + testPost);

        CreatePost createPostTest = new CreatePost();
        createPostTest.run();
        AccessPost accessPostTest = new AccessPost();
        System.out.println("Expected: 2,this is my post :),0,0,null," + LocalDateTime.now());
        System.out.print("Actual: ");
        accessPostTest.run();

        CreatePost createPostTest2 = new CreatePost();
        createPostTest2.run();
        CreatePost createPostTest3 = new CreatePost();
        createPostTest3.run();
        AccessPost accessPostTest2 = new AccessPost();
        accessPostTest2.run();


    }
}
