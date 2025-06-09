import model.User;
import service.UserService;
import storage.UserStorage;

import java.util.*;

public class SkillSwapApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService(new UserStorage());

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> handleRegistration();
                case "2" -> displayAllUsers();
                case "3" -> findTeachers();
                case "4" -> findLearners();
                case "5" -> {
                    System.out.println("Exiting... Thank you for using SkillSwapApp.");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== SkillSwapApp Menu =====");
        System.out.println("1. Register a new user");
        System.out.println("2. View all users");
        System.out.println("3. Find who can teach me");
        System.out.println("4. Find who wants to learn from me");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleRegistration() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            System.out.print("Enter skills you can teach (comma separated): ");
            List<String> offered = Arrays.asList(scanner.nextLine().trim().split("\\s*,\\s*"));

            System.out.print("Enter skills you want to learn (comma separated): ");
            List<String> wanted = Arrays.asList(scanner.nextLine().trim().split("\\s*,\\s*"));

            boolean success = userService.registerUser(name, email, password, offered, wanted);
            if (success) System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Error during registration. Try again.");
        }
    }

    private static void displayAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
        } else {
            users.forEach(user -> {
                System.out.println("------------------------");
                System.out.println(user);
            });
        }
    }

    private static void findTeachers() {
        User currentUser = getUserByEmail();
        if (currentUser == null) return;

        List<User> teachers = userService.findTeachersForMe(currentUser);
        if (teachers.isEmpty()) {
            System.out.println("No one found who can teach you.");
        } else {
            System.out.println("Users who can teach you:");
            teachers.forEach(u -> System.out.println(u + "\n"));
        }
    }

    private static void findLearners() {
        User currentUser = getUserByEmail();
        if (currentUser == null) return;

        List<User> learners = userService.findLearnersForMe(currentUser);
        if (learners.isEmpty()) {
            System.out.println("No one found who wants to learn from you.");
        } else {
            System.out.println("Users who want to learn from you:");
            learners.forEach(u -> System.out.println(u + "\n"));
        }
    }

    private static User getUserByEmail() {
        System.out.print("Enter your registered email: ");
        String email = scanner.nextLine().trim();
        User user = userService.findUserByEmail(email);
        if (user == null) {
            System.out.println("User not found. Please register first.");
        }
        return user;
    }
}
