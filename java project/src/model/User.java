package model;

/**
 * Represents a user in the SkillSwap system.
 */
public class User {
    private final String name;
    private final String email;
    private final String skill;
    private final String passwordHash;

    public User(String name, String email, String skill, String passwordHash) {
        if (name == null || email == null || skill == null || passwordHash == null) {
            throw new IllegalArgumentException("User fields cannot be null.");
        }
        this.name = name.trim();
        this.email = email.trim().toLowerCase();
        this.skill = skill.trim();
        this.passwordHash = passwordHash;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSkill() { return skill; }
    public String getPasswordHash() { return passwordHash; }

    @Override
    public String toString() {
        return String.format("Name: %s | Email: %s | Skill: %s", name, email, skill);
    }
}
