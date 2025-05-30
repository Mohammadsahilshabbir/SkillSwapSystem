import service.UserService;
import java.util.Scanner;

public class SkillSwapApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        int choice;

        do {
            System.out.println("\n===== Skill Swap System =====");
            System.out.println("1. Register a new user");
            System.out.println("2. View all users");
            System.out.println("3. Find who can teach me");
            System.out.println("4. Find who wants to learn from me");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> userService.registerUser(scanner);
                case 2 -> userService.viewUsers();
                case 3 -> userService.findTeachers(scanner);
                case 4 -> userService.findLearners(scanner);
                case 5 -> System.out.println(" ...Exiting...");
                default -> System.out.println(" ...Invalid choice...");
            }
        } while (choice != 5);
    }
}
