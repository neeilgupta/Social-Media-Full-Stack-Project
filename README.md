## Attribution

This project is based on a group project originally developed for CS 180.

Original repository:
https://github.com/emerson1203/group-project-cs180

This repository exists to showcase my personal contributions,
extensions, and refactoring for portfolio purposes.


# Emerson Barrett
## Compiling and Running the Project:
Clone the repository and open the project in your preferred IDE. Compile and run the Junit Test classes to test the functionality of various portions of the code. 

## Who submitted which portions: 
Emerson Barrett submitted to Vocareum Workspace. 

## Classes: 
### Post:
This class creates Post objects, which interact with users and comments. Users can make posts, which are then associated with that user. Users may also add comments to posts, posts have an array of comments. The testing on this class is done through the Post Junit Test class.

### PostService:
This class contains methods that utilize the methods of the Post class in tandem with files from the PostFileDatabase to read and write post files properly. Testing is done through the Post Junit Test class. 

### PostFileDatabase:
Creates serialized files in the post directory for each post that is created. These files can be manipulated and referenced as needed by the program. 

### Post Junit Test:
Tests the methods of the Post Service class which in turn tests the Post and Post File Database classes as they all work in tandem. 

### Comment:
This class creates Comment objects, which interact with posts and users. Users can make comments, which are then associated with that user, along with a specific post. The testing on this class is done through the Comment Junit Test class.

### CommentService:
This class contains methods that utilize the methods of the Comment class in tandem with files from the CommentFileDatabase to read and write post files properly. Testing is done through the Comment Junit Test class. 

### CommentFileDatabase:
Creates serialized files in the comment directory for each Comment that is created. These files can be manipulated and referenced as needed by the program. 

### Comment Junit Test: 
Tests the methods of the Comment Service class which in turn tests the Comment and Comment File Database classes as they all work in tandem. 

### Server:
Creates a server that spawns a server app thread for each client request so that clients can run concurrently.

### Server App: 
Communicates with the client and runs methods for processing different client requests such as creating users, posts, and comments, removing them, liking and disliking posts and comments, and more. It is tested with the Client test class.

# Sameer Dadoo:
## User Management System
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


# Hossein Hatami
## Creating a user
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
**Show Password buttton** shows the password
**Autogenerate Password** Autogenerate password

Classes Overview

Server
-
The server that creates a connection with the client

Client
-
Has all the GUI and preforms signing up and logging in properly. The main place where everything is run


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



# Neeil Gupta
## News Feed:
This project implements the News Feed functionality for a social media platform, allowing users to create posts, view friends' posts, interact with posts through upvotes, downvotes, and comments, and manage visibility of posts through hiding. The News Feed aggregates content from friends while ensuring that users can engage with posts effectively.

## Features:
Post Creation: Users can create posts to share updates or content with their friends.

Friends' Feed: The feed displays posts from users that the current user is following, sorted by engagement.

Post Interaction: Users can upvote or downvote posts, allowing for content ranking.

Commenting System: Users can add comments to posts, enabling conversations and feedback.

Comment Interaction: Any user who can view a comment can upvote or downvote it; the post owner and comment owner have the ability to delete their comments.


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

# Client Test Class:
JUnit test suite designed to verify the functionality of the Client class, including methods for username validation, password generation, and user sign-up interactions. It simulates server-client communication to ensure correct data handling and behavior, testing edge cases like username length and format as well as password generation logic.

# Sample Test Cases
testValidUsername: Validates the username based on length, allowed characters, and whether it is already taken.

testAutogeneratePassword: Verifies that the password generation method creates a non-null password of exactly 30 characters.

testSignUpPageInteraction: Simulates a user sign-up process by entering data into the GUI and checking if the correct data is sent to the server.

# Server Class:
The server's entry point is managing a ServerSocket to listen for client connections. It initializes shared resources like users.ser and posts.ser and spawns a new ServerApp thread for each client connection, continuously accepting requests until the server shuts down.

# ServerApp Class: 
The class handles individual client connections using a Socket and extends Thread for concurrency. Its run method processes client commands such as createUser, createPost, follow, and others by invoking private methods that interact with UsersService, PostService, and their respective databases. The connection is closed gracefully after processing client requests.

# ServerApp Test Class: 
A JUnit test suite that uses in-memory streams to simulate socket communication for testing ServerApp verifies functionality such as creating users and posts and handling relationships like following users. Simulated input is sent via helper methods, and database contents are checked after each operation to ensure correctness.

