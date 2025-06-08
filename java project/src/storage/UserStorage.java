package storage;

import model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles in-memory storage for User objects.
 */
public class UserStorage {
    private final List<User> users = new ArrayList<>();

    public boolean addUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null.");
        if (getUserByEmail(user.getEmail()) != null) return false;
        users.add(user);
        return true;
    }

    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }
        return null;
    }

    public List<User> getUsersBySkill(String skill) {
        List<User> matched = new ArrayList<>();
        for (User user : users) {
            if (user.getSkill().equalsIgnoreCase(skill)) matched.add(user);
        }
        return matched;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users); // defensive copy
    }
}
