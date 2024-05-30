// UserModel.java
package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserModel {
    private Map<String, User> users;
    private static final String BASE_DIRECTORY = "C:/javaUserFilesTest"; // Base directory
    private User currentUser; // Add a field to store the current user

    public UserModel() {
        users = new HashMap<>();
        loadExistingUsers();
    }

    public boolean addUser(String username) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        User user = new User(username);
        users.put(username, user);
        createUserDirectory(user);
        return true;
    }

    public boolean deleteUser(String username) {
        User user = users.get(username);
        if (user != null) {
            File userDir = user.getUserDirectory();
            if (userDir.exists() && userDir.isDirectory()) {
                deleteDirectory(userDir);
            }
            users.remove(username);
            return true;
        }
        return false;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    private void createUserDirectory(User user) {
        File userDir = user.getUserDirectory();
        if (!userDir.exists()) {
            userDir.mkdirs(); // Ensure directories are created recursively
        }
    }

    private void loadExistingUsers() {
        File baseDir = new File(BASE_DIRECTORY);
        File[] userDirs = baseDir.listFiles(File::isDirectory);
        if (userDirs != null) {
            for (File userDir : userDirs) {
                String username = userDir.getName();
                User user = new User(username);
                users.put(username, user);
            }
        }
    }

    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }
}
