package service;

import model.User;
import storage.UserStorage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class UserService {
    private final List<User> users;

    public static final LocalDate START_DATE = LocalDate.parse("2025-06-01");
    public static final LocalDate END_DATE = LocalDate.parse("2025-08-31");

    public UserService() {
        users = UserStorage.loadUsers();
    }

    public void registerUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter skills you can teach (comma-separated): ");
        String teach = scanner.nextLine();

        System.out.print("Enter skills you want to learn (comma-separated): ");
        String learn = scanner.nextLine();

        LocalDate date;
        while (true) {
            System.out.print("Enter your preferred date (YYYY-MM-DD) between " + START_DATE + " and " + END_DATE + ": ");
            String dateStr = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(dateStr);
                if (!date.isBefore(START_DATE) && !date.isAfter(END_DATE)) break;
                System.out.println("Date not in valid range.");
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format.");
            }
        }

        users.add(new User(name, teach, learn, date.toString()));
        UserStorage.saveUsers(users);
        System.out.println("Registered successfully for " + date + "!");
    }

    public void viewUsers() {
        System.out.println("\n--- Registered Users ---");
        for (User user : users) {
            System.out.println("Name: " + user.name);
            System.out.println("Teaches: " + user.skillsToTeach);
            System.out.println("Wants to Learn: " + user.skillsToLearn);
            System.out.println("Preferred Date: " + user.preferredDate);
            System.out.println("-----------------------------");
        }
    }

    public void findTeachers(Scanner scanner) {
        System.out.print("Enter skill(s) you want to learn (comma-separated): ");
        Set<String> learnSkills = parseInputSkills(scanner.nextLine());

        boolean found = false;
        for (User user : users) {
            if (!Collections.disjoint(user.skillsToTeach, learnSkills)) {
                System.out.println(" " + user.name + " can teach you:");
                System.out.println("  Skills: " + user.skillsToTeach);
                System.out.println("  Date: " + user.preferredDate);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available teachers found for your selected skills.");
        }
    }

    public void findLearners(Scanner scanner) {
        System.out.print("Enter skill(s) you can teach (comma-separated): ");
        Set<String> teachSkills = parseInputSkills(scanner.nextLine());

        boolean found = false;
        for (User user : users) {
            if (!Collections.disjoint(user.skillsToLearn, teachSkills)) {
                System.out.println("  " + user.name + " wants to learn:");
                System.out.println("  Skills: " + user.skillsToLearn);
                System.out.println("  Date: " + user.preferredDate);
                found = true;
            }
        }

        if (!found) {
            System.out.println(" No learners found for your offered skills.");
        }
    }

    private Set<String> parseInputSkills(String input) {
        Set<String> skillSet = new HashSet<>();
        for (String skill : input.split(",")) {
            skillSet.add(skill.trim().toLowerCase());
        }
        return skillSet;
    }
}
