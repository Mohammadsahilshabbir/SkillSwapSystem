package model;

import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String email;
    private String passwordHash;
    private List<String> skillsOffered;
    private List<String> skillsWanted;

    public User(String name, String email, String passwordHash, List<String> skillsOffered, List<String> skillsWanted) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.skillsOffered = skillsOffered;
        this.skillsWanted = skillsWanted;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<String> getSkillsOffered() {
        return skillsOffered;
    }

    public List<String> getSkillsWanted() {
        return skillsWanted;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + 
               "\nCan Teach: " + skillsOffered + 
               "\nWants to Learn: " + skillsWanted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return email.equalsIgnoreCase(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}
