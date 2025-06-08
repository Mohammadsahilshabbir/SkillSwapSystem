package service;

import model.User;
import storage.UserStorage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Provides business logic for user operations.
 */
public class UserService {
    private final UserStorage storage;

    public UserService(UserStorage storage) {
        this.storage = storage;
    }

    public boolean registerUser(String name, String email, String skill, String password) {
        validateInput(name, email, skill, password);
        String hashed = hashPassword(password);
        return storage.addUser(new User(name, email, skill, hashed));
    }

    public User loginUser(String email, String password) {
        User user = storage.getUserByEmail(email);
        if (user == null) return null;
        return user.getPasswordHash().equals(hashPassword(password)) ? user : null;
    }

    public List<User> getUsersBySkill(String skill) {
        return storage.getUsersBySkill(skill);
    }

    public List<User> getAllUsers() {
        return storage.getAllUsers();
    }

    private void validateInput(String name, String email, String skill, String password) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required.");
        if (!isValidEmail(email)) throw new IllegalArgumentException("Invalid email format.");
        if (skill == null || skill.isBlank()) throw new IllegalArgumentException("Skill is required.");
        if (password == null || password.length() < 4) throw new IllegalArgumentException("Password must be at least 4 characters.");
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", email);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password.", e);
        }
    }
}
