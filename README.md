Project Description
SkillSwapApp is a Java-based console application that connects people who want to exchange skills with each other. It allows users to:

Register with their skills they can teach and skills they want to learn

Find potential teachers for the skills they want to learn

Find potential learners for the skills they can teach

View all registered users in the system

How to Run the Application
Make sure you have Java installed on your system (JDK 11 or higher recommended)

Compile all the Java files:

text
javac -d . model/User.java storage/UserStorage.java service/UserService.java SkillSwapApp.java
Run the application:

text
java SkillSwapApp
Features
User Registration

Register with name, email, password

Specify skills you can teach

Specify skills you want to learn

User Matching

Find teachers who can teach the skills you want to learn

Find learners who want to learn the skills you can teach

User Management

View all registered users

Simple email-based authentication

Usage Instructions
When you run the application, you'll see a menu with these options:

Register a new user:

Enter your details including skills you can offer and skills you want to learn

Skills should be entered as comma-separated values (e.g., "Java,Python,Cooking")

View all users:

See a list of all registered users and their skills

Find who can teach me:

Enter your email to find users who can teach skills you want to learn

Find who wants to learn from me:

Enter your email to find users who want to learn skills you can teach

Exit:

Quit the application

Technical Details
Data Storage: Uses in-memory storage (users are not persisted between runs)

Password Security: Passwords are hashed using SHA-256 before storage

Email Validation: Basic email format validation is implemented

Limitations
Data is not persisted between application runs (all data is lost when you exit)

No password recovery mechanism

Basic console interface (no GUI)

No actual messaging or scheduling functionality

Future Enhancements
Add persistent data storage using files or database

Implement a proper login system

Add messaging between users

Create a scheduling system for skill exchange sessions

Develop a graphical user interface

Author
[Mohammad sahil shabbir] - Developed as a personal project to practice Java programming and application design.

License
This project is open-source and available for educational purposes. Feel free to modify and extend it as needed.
