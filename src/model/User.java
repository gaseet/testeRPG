package model;

import java.io.File;

public class User {
    private String username;
    private static final String BASE_DIRECTORY = "C:/javaUserFilesTest"; // Base directory

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public File getUserDirectory() {
        return new File(BASE_DIRECTORY, username); // Use base directory
    }
}
