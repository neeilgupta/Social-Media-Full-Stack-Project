import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * The NewsFeed class represents a social media feed for users.
 * It stores posts, retrieves a feed for a specific user filtered by followers,
 * and supports liking,disliking, and hiding posts. It also allows for liking,
 * disliking, and adding comments
 *
 * Created by Neeil Gupta
 * Date: Nov 3, 2024
 */

public class NewsFeed {
    // List to store posts
    private ArrayList<Post> posts;

    // Constructor initializes the posts list
    public NewsFeed() {
        this.posts = new ArrayList<>();
    }

    // Retrieves a feed for the user, filtering by followers and sorted by likes and timestamp
    public static ArrayList<Post> getFeedForUser(User user) {
        ArrayList<Post> userFeed = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("post.ser"))) {
            String line;
            boolean processLine = false;
            while ((line = reader.readLine()) != null) {
                if(processLine) {
                    String[] parts = line.split(",", 5);
                    if (parts.length == 5) {
                        try {
                            int lineUserId = Integer.parseInt(parts[0].trim());
                            String content = parts[1].trim();
                            LocalDateTime time = LocalDateTime.parse(parts[2].trim());
                            int postLikes = Integer.parseInt(parts[3].trim());
                            int postDislikes = Integer.parseInt(parts[4].trim());

                            Post post = new Post(lineUserId, content, User.getCurrentUser(), postLikes, postDislikes);
                            if ((post.getID() == lineUserId || isFollowing(user, post.getUser()))) {
                                insertInOrder(userFeed, post);
                            }
                        } catch (NumberFormatException | DateTimeParseException e) {
                            System.out.println("Invalid data format in file: " + line);
                        }
                    } else {
                        System.out.println("Skipping malformed line: " + line);
                    }
                }
                processLine = !processLine;
            }
        } catch (FileNotFoundException e) {
            System.out.println("post.ser not found. Returning an empty list.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userFeed;
    }

    // Method to insert posts into userFeed based on likes and timestamp
    private static void insertInOrder(List<Post> userFeed, Post post) {
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
    private static boolean isFollowing(User user, User postUser) {
        if(user.getFollowing().contains(postUser)) {
            return true;
        }
        return false;
    }

    public void likePost(Post post, User user) {
        boolean alreadyLiked = false;
        for (User likedUser : post.getLikes())
            if (likedUser.getUserID() == user.getUserID()) {
                alreadyLiked = true;
                break;
            }
        if (!alreadyLiked) {
            post.getLikes().add(user);
        }
    }
    public void dislikePost(Post post, User user) {
        boolean alreadyDisliked = false;
        for (User dislikedUser : post.getDislikes()) {
            if (dislikedUser.getUserID() == user.getUserID()) {
                alreadyDisliked = true;
                break;
            }
        }
        if (!alreadyDisliked) {
            post.getDislikes().add(user);
        }
    }

    public void addComment(Post post, Comment comment) {
        post.getComments().add(comment);
    }

    public void likeComment(Post post, Comment comment, User user) {
        if (post.getComments().contains(comment)) {
            if (!comment.getLikes().contains(user)) {
                comment.getLikes().add(user);
            } else {
                System.out.println("User has already liked this comment.");
            }
        } else {
            System.out.println("Comment does not belong to this post.");
        }
    }

}