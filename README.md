### Emerson Barrett
## Compiling and Running the Project:
Clone the repository and open the project in your preferred IDE. Compile and run the Junit Test classes to test the functionality of various portions of the code. 

## Who submitted which portions: 
Emerson Barrett submitted to Vocareum Workspace. 

## Classes: 
# Post:
This class creates Post objects, which interact with users and comments. Users can make posts, which are then associated with that user. Users may also add comments to posts, posts have an array of comments. The testing on this class is done through the Post Junit Test class.

# PostService:
This class contains methods that utilize the methods of the Post class in tandem with files from the PostFileDatabase to read and write post files properly. Testing is done through the Post Junit Test class. 

# PostFileDatabase:
Creates serialized files in the post directory for each post that is created. These files can be manipulated and referenced as needed by the program. 

# Post Junit Test:
Tests the methods of the Post Service class which in turn tests the Post and Post File Database classes as they all work in tandem. 

# Comment:
This class creates Comment objects, which interact with posts and users. Users can make comments, which are then associated with that user, along with a specific post. The testing on this class is done through the Comment Junit Test class.

# CommentService:
This class contains methods that utilize the methods of the Comment class in tandem with files from the CommentFileDatabase to read and write post files properly. Testing is done through the Comment Junit Test class. 

# CommentFileDatabase:
Creates serialized files in the comment directory for each Comment that is created. These files can be manipulated and referenced as needed by the program. 

# Comment Junit Test: 
Tests the methods of the Comment Service class which in turn tests the Comment and Comment File Database classes as they all work in tandem. 



## Sameer Dadoo:
# User Management System
This project is a Java-based User Management System designed to handle user-related functionalities, specifically those related to following/unfollowing/blocking users, finding users, and storing data in a file-based database. The system is structured to allow services like UserService, holding the bulk of the previously mentioned functionalities along with a flexible data storage layer using FileDatabase. The data is pulled from the signup class through an already-implemented, mock GUI. 
#Table of Contents
Features
Architecture
Classes Overview
Getting Started
Database Setup
Testing
Future Improvements
Features
User Profile Management: Create, view, and search for user profiles.
Followers & Blocking System: Users can follow/unfollow or block/unblock each other, preventing blocked users from interacting with those who block them, all validated through edge case testing.
File-Based Storage: User data is stored and retrieved from the UserInfo.txt file, making it easy to manage.
Clear Database Method: The FileDatabase class includes a clear method to reset the data for testing or a fresh start.
JUnit Testing: Unit tests are provided to validate core functionalities of the overall User Management System.
Architecture
The project follows a layered architecture, where:

Service Layer: UserService contains business logic for operations like user creation, following, and blocking.
Data Layer: FileDatabase manages data persistence, storing user data in a file and retrieving it as needed.
Classes Overview
FileDatabase
The FileDatabase class is responsible for data persistence. It provides methods for storing and retrieving user data from a file. Key methods:
storeUser(User user): Stores a user’s information in the file.
retrieveUser(String username): Retrieves a user’s information based on username.
clear(): Clears the file contents to provide a fresh start.
updateUser(): overwrites the UserInfo.txt file to implement the new updated user
User(Sameer’s methods)
Represents each individual user in the system. Key properties and methods:
Properties: username, password, followers, blockedUsers.
Methods:
follow(User otherUser): Adds another user to the follower list if they’re not blocked.
unfollow(User otherUser): Removes another user from the follower list.
block(User otherUser): Blocks a user, removing them from followers if they exist there.
unblock(User otherUser): Unblocks a previously blocked user.
UserService
Handles the main user-related operations:
addUser(String username, String password): Adds a new user to the system.
searchUser(String username): Searches for a user by their username.
viewUser(String username): Displays user details.
addFollower(User currentUser, User otherUser): Allows a user to follow another user.
removeFollower(User currentUser, User otherUser): Allows a user to unfollow another user.
blockUser(User currentUser, User otherUser): Allows a user to block another user.
unblockUser(User currentUser, User otherUser): Allows a user to unblock a previously blocked user.

FileDatabase interface(DataBaseInterface): 
Interface for FileDatabe:
storeUser(User user): Save user information in the database
retrieveUser(String username): 
Getting Started
Prerequisites
Java JDK 8 or higher
A Java-compatible IDE (e.g., IntelliJ, Eclipse)
Junit 4
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/emerson1203/group-project-cs180.git
Open the project in your preferred IDE.
Compile and run Main.java to start the program.
Database Setup
The FileDatabase class uses a file to store user data. The default file location can be specified in the class constructor or as an argument when running the program.
Testing
JUnit is used for unit testing in this project. To run the tests:
Ensure JUnit is added as a dependency in your project.
Run UsersService_Junit_Tester to execute test cases covering all primary functions, such as adding a user, following, blocking, and viewing user information.
Note: For simplicity, direct file manipulation is used instead of mocking libraries.
Sample Test Cases
Some of the test cases included:
testAddUser(): Checks if a user is correctly added to the database.
testSearchUser(): Searches for an existing user and verifies if the retrieved data matches.
testFollowUser(): Ensures a user can follow another user.
testBlockUser(): Confirms a user can block another and is removed from their followers list.
Future Improvements
Improved Data Persistence: Better integrate FileDatabase with the GUI


## Hossein Hatami
# Creating a user
This is the main page, it is the first thing the user sees, When the main class is ran, the user gets a menu where it can either sign up or login. The sign up class lets users create unique usernames and register based off their email. They input their email and create a password, which can be autogenerated. There are restrictions on what each three of those could be. Each user gets to choose their display name as well and every user gets assigned a specific user ID.


Features
-
**Frame:** The menu where both the sign up button and the log in button appear
**Sign up button:** This button allows the user to register and directs to the signUp.java class.
**Log in:** This button allows the user to log in based on already existing user information. 
**Closing GUI:** Should the user decide to leave the program, they can do so by closing the window
**Creating an email:** Must be valid
**Creating a password:** Restriction in length exists
**Creating a username:** Restriction in length and availability exists
**Creating a display name:** No restrictions
**Creating a user ID:** auto assigned

Classes Overview
OptionSignUpOrLogIn Class
Method
Parameters
Purpose
-

Public void run()
-
Runs the GUI menu

Public boolean isSignUpButtonClicked()
-
Lets the main method know whether to start the signUp thread

Public boolean isLogInButtonClicked() 
-
Lets the main method know whether to start the Login thread

Sign Up Class
Method
Parameters
Purpose
run()
-
Manages the user registration process through GUI dialogs for email, password, username, and display name input.


isEmailTaken()	
-
Checks if the current email is already in use by reading the UserInfo.txt file. Returns true if taken, false otherwise.


isUsernameTaken()	
-
Checks if the current username is already in use by reading the UserInfo.txt file. Returns true if taken, false otherwise.


getUserID()
-
Returns the user ID.


setUserID(int userID)
-
Sets the user ID to the given integer value.
getUsername()
Returns the username

setUsername(String username)
-
Sets the username to the given string.

getDisplayName()	
-
Returns the display name

setDisplayName(String displayName)	
-
Sets the display name to the given string.

getPassword()
-
Returns the password.

setPassword(int password)
-
Sets the password to the given string.

## Getting Started
Prerequisites
- 
Java JDK 8 or higher
A Java-compatible IDE (e.g., IntelliJ, Eclipse)
Junit 4
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/emerson1203/group-project-cs180.git
Open the project in your preferred IDE.
Compile and run Main.java to start the program.
Database Setup
The SignUp class uses a file to store user data. This file is named UserInfo.txt

Testing
-
JUnit is used for unit testing in this project. To run the tests:
Ensure JUnit is added as a dependency in your project.
Run NewsFeedTest to execute test cases covering the core functionalities such as adding posts, liking, disliking, and ordering the feed.

Future Improvements:
-
Add email verification (optional)
Complete Log in class
Fix panel bugs in Sign Up class
Add more detail and design to the frames in sign up class



##Neeil Gupta
#News Feed System
This project implements the News Feed functionality for a social media platform, allowing users to create posts, view friends' posts, interact with posts through upvotes, downvotes, and comments, and manage visibility of posts through hiding. The News Feed aggregates content from friends while ensuring that users can engage with posts effectively.
Table of Contents
Features
Architecture
Classes Overview
Getting Started
Database Setup
Testing
Future Improvements
Features
Post Creation: Users can create posts to share updates or content with their friends.
Friends' Feed: The feed displays posts from users that the current user is following, sorted by engagement.
Post Interaction: Users can upvote or downvote posts, allowing for content ranking.
Commenting System: Users can add comments to posts, enabling conversations and feedback.
Comment Interaction: Any user who can view a comment can upvote or downvote it; the post owner and comment owner have the ability to delete their comments.
Architecture
The project follows a modular architecture, where:
NewsFeed Class: Manages the collection of posts and user interactions such as liking, disliking, and commenting.
Post Class: Represents individual posts, storing content, user data, and metadata including timestamps and likes/dislikes.
User Class: Handles user-specific functionalities, including followers, blocked users, and hidden posts.
Classes Overview
NewsFeed Class
Method
Parameters
Purpose
NewsFeed()
-
Initializes an empty list to store posts.
List<Post> getFeedForUser()
User user
Retrieves a user's feed, filtering posts by their followers and hidden posts.
private void insertInOrder()
List<Post> userFeed, Post post
inserts a new post into the user feed in the correct order based on likes, dislikes, and timestamps
void addPost()
Post post
Adds a new post to the feed.
void likePost()
Post post, User user
Adds the user to the list of likes for the specified post.
void dislikePost()
Post post, User user
Adds the user to the list of dislikes for the specified post.
void addComment(Post post, Comment comment)
Post post, Comment comment
Adds a comment to the specified post.
void likeComment(Post post, Comment comment, User user)
Post post, Comment comment, User user
Allows a user to like a specified comment on a post.

Post Class
Method
Parameters
Purpose
Post()
String content, User user
Constructs a new post with the specified content and associated user.
int getID()
-
Returns the unique ID of the post.
String getContent()
-
Returns the content of the post.
User getUser()
-
Returns the user who created the post.
LocalDateTime getDateTime()
-
Returns the timestamp of when the post was created.
ArrayList<Comment> getComments()
-
Returns the list of comments associated with the post.
ArrayList<User> getLikes()
-
Returns the list of users who liked the post.
ArrayList<User> getDislikes()
-
Returns the list of users who disliked the post.
void setContent()
String content
Sets the content of the post.
void setUser()
User user
Sets the user associated with the post.

User Class
Method
Parameters
Purpose
User()
int userID, String username
Constructs a new user with a specified ID and username.
int getUserID()
-
Returns the user's ID.
String getUsername()
-
Returns the username of the user.
setUserID()
int userID
Sets the user’s ID
setUsername()
String username
Sets the user’s username
User getCurrentUser()
-
Returns the current user
ArrayList<Post> getHiddenPosts()
-
Returns the list of posts hidden by the user.
hidePost()
Post post
Hides a specified post from the user’s feed.
unhidePost()
Post post
Removes a specified post from a user’s hidden posts


Getting Started
Prerequisites
Java JDK 8 or higher
A Java-compatible IDE (e.g., IntelliJ, Eclipse)
Junit 4
Installation
Clone the repository:
bash
Copy code
git clone https://github.com/emerson1203/group-project-cs180.git
Open the project in your preferred IDE.
Compile and run Main.java to start the program.
Database Setup
The PostFileDatabase class uses a file to store post data. The default file location can be specified in the class constructor or as an argument when running the program.
Testing
JUnit is used for unit testing in this project. To run the tests:
Ensure JUnit is added as a dependency in your project.
Run NewsFeedTest to execute test cases covering the core functionalities such as adding posts, liking, disliking, and ordering the feed.
Sample Test Cases
Some of the test cases included:
testAddPost(): Verifies that a post is correctly added to the feed.
testLikePost(): Confirms that a user can like a post.
testDislikePost(): Confirms that a user can dislike a post.
testFeedOrderByLikes(): Ensures that the feed orders posts by the number of likes and time.
Future Improvements
Enhanced Sorting Algorithms: Implement more advanced algorithms for feed sorting based on user engagement and reliability.
User Notifications: Add notifications for users when their posts are liked or commented on.
Improved GUI: Develop a more interactive user interface for better user experience.




