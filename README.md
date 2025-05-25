# Skill Swap System

The **Skill Swap System** is a Core Java-based console application designed to help users connect based on mutual interests in teaching and learning specific skills. It supports registration, file-based storage, and skill matching logic based on a layered architecture.

## 🛠️ Features

- Register users with:
  - Name
  - Skills they can teach
  - Skills they want to learn
  - Preferred availability date
- File-based persistent storage (`users.txt`)
- Match users if at least one teachable skill matches another's learnable skill
- Input validation and proper error handling
- Clean separation using layered architecture (`model`, `service`, `storage`)

## 🗂️ Project Structure

## 🧑‍💻 How to Compile and Run

### Requirements

- Java Development Kit (JDK 8 or higher)
- Any terminal or Java-compatible IDE

### Compilation

```bash
javac model/User.java
javac service/UserService.java
javac storage/UserStorage.java
javac SkillSwapApp.java
