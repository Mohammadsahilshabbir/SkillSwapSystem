package storage;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private List<User> users;

    public UserStorage() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }
}
