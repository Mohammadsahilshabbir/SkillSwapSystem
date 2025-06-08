import model.User;
import service.UserService;
import storage.UserStorage;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the SkillSwap console app.
 */
public class SkillSwapApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static UserService userService;

    public static void main(String[] args) {
        userService = new UserService(new UserStorage());
        System.out.println("=== Welcome to SkillSwap ===");

        boolean running = true;
        while (running) {
            printMenu();
            switch (scanner.nextLine().trim()) {
                case "1" -> handleRegistration();
                case "2" -> handleLogin();
                case "3" -> handleSkillSearch();
                case "4" -> displayAllUsers();
                case "5" -> running = false;
                case "help" -> printHelp();
                default -> System.out.println("Invalid input. Type 'help' for commands.");
            }
        }

        System.out.println("Thank you for using SkillSwap. Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n===== Main Menu =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Search by Skill");
        System.out.println("4. View All Users");
        System.out.println("5. Exit");
        System.out.print("Choose an option (1-5): ");
    }

    private static void printHelp() {
        System.out.println("\n=== Help Menu ===");
        System.out.println("1. Register - Sign up with name, email, skill, and password.");
        System.out.println("2. Login - Access your account.");
        System.out.println("3. Search by Skill - Find users who know a specific skill.");
        System.out.println("4. View All Users - Show all registered users.");
        System.out.println("5. Exit - Close the application.");
    }

    private static void handleRegistration() {
        System.out.println("\n--- Register ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Skill: ");
        String skill = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            if (userService.registerUser(name, email, skill, password)) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("User already exists with this email.");
            }
        } catch (Exception e) {
            System.err.println("Registration failed: " + e.getMessage());
        }
    }

    private static void handleLogin() {
        System.out.println("\n--- Login ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.loginUser(email, password);
        if (user != null) {
            System.out.println("Welcome, " + user.getName() + "!");
        } else {
            System.out.println("Login failed. Incorrect credentials.");
        }
    }

    private static void handleSkillSearch() {
        System.out.println("\n--- Search by Skill ---");
        System.out.print("Enter skill: ");
        String skill = scanner.nextLine();
        List<User> users = userService.getUsersBySkill(skill);
        if (users.isEmpty()) {
            System.out.println("No users found with this skill.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void displayAllUsers() {
        System.out.println("\n--- All Registered Users ---");
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
        } else {
            users.forEach(System.out::println);
        }
    }
}
