package model;

import java.time.LocalDate;
import java.util.*;

public class User {
    public String name;
    public Set<String> skillsToTeach;
    public Set<String> skillsToLearn;
    public LocalDate preferredDate;

    public User(String name, String teach, String learn, String dateStr) {
        this.name = name.trim();
        this.skillsToTeach = parseSkills(teach);
        this.skillsToLearn = parseSkills(learn);
        this.preferredDate = LocalDate.parse(dateStr);
    }

    private Set<String> parseSkills(String skills) {
        Set<String> skillSet = new HashSet<>();
        for (String skill : skills.split(",")) {
            skillSet.add(skill.trim().toLowerCase());
        }
        return skillSet;
    }

    public String toStorageString() {
        return name + "|" + String.join(",", skillsToTeach) + "|" + String.join(",", skillsToLearn) + "|" + preferredDate;
    }

    public static User fromStorageString(String line) {
        String[] parts = line.split("\\|");
        return new User(parts[0], parts[1], parts[2], parts[3]);
    }
}
