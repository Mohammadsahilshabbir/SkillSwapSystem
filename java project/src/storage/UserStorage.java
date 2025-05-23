package storage;

import model.User;

import java.io.*;
import java.util.*;

public class UserStorage {
    private static final String FILE_PATH = "users.txt";

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(User.fromStorageString(line));
            }
        } catch (IOException e) {
            System.out.println("⚠️ Could not load users. Starting fresh.");
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                bw.write(user.toStorageString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving users.");
        }
    }
}
