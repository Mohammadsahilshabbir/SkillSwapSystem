package service;

import model.User;
import storage.UserStorage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

public class UserService {
    private UserStorage storage;

    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public boolean registerUser(String name, String email, String password, List<String> offered, List<String> wanted) {
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }

        if (isEmailTaken(email)) {
            System.out.println("Email already registered.");
            return false;
        }

        String hashedPassword = hashPassword(password);
        User user = new User(name, email, hashedPassword, offered, wanted);
        storage.addUser(user);
        return true;
    }

    public List<User> getAllUsers() {
        return storage.getAllUsers();
    }

    public List<User> findTeachersForMe(User currentUser) {
        List<User> matches = new ArrayList<>();
        for (User u : storage.getAllUsers()) {
            if (!u.equals(currentUser)) {
                for (String want : currentUser.getSkillsWanted()) {
                    if (u.getSkillsOffered().contains(want)) {
                        matches.add(u);
                        break;
                    }
                }
            }
        }
        return matches;
    }

    public List<User> findLearnersForMe(User currentUser) {
        List<User> matches = new ArrayList<>();
        for (User u : storage.getAllUsers()) {
            if (!u.equals(currentUser)) {
                for (String offer : currentUser.getSkillsOffered()) {
                    if (u.getSkillsWanted().contains(offer)) {
                        matches.add(u);
                        break;
                    }
                }
            }
        }
        return matches;
    }

    public User findUserByEmail(String email) {
        for (User u : storage.getAllUsers()) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    private boolean isEmailTaken(String email) {
        return findUserByEmail(email) != null;
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", email);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing password.");
            return password;
        }
    }
}
