package controller;
import java.io.File;
import java.io.IOException;

import model.FileModel;
import model.RPGClass;
import model.User;
import model.UserModel;
import view.FileView;

public class FileController {
    private UserModel userModel;
    private FileModel fileModel;
    private FileView view;
    private User currentUser;

    public FileController(UserModel userModel, FileModel fileModel, FileView view) {
        this.userModel = userModel;
        this.fileModel = fileModel;
        this.view = view;
    }

    private void classesMenu() {
        System.out.println("Choose class: ");
        System.out.println("1. Mage");
        System.out.println("2. Bard");
        System.out.println("3. Berserker");
        System.out.println("4. Paladin");
        System.out.println("5. Rogue");
    }

    private String getClassNumber(int classChoice) {
        switch (classChoice) {
            case 1:
                return "Mage";
            case 2:
                return "Bard";
            case 3:
                return "Berserker";
            case 4:
                return"Paladin";
            case 5:
                return "Rogue";
            default:
                return null;
        }
    }

    public void run() {
        while (true) {
            int choice = view.getMenuChoice();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    selectUser();
                    break;
                case 3:
                    showCurrentUser();
                    break;
                case 4:
                    deleteSelectedUser();
                    break;
                case 5:
                    showUserFiles();
                    break;
                case 6:
                    saveInformation();
                    break;
                case 7:
                    readInformation();
                    break;
                case 8:
                    editInformation();
                    break;
                case 9:
                    deleteInformation();
                    break;
                case 10:
                    view.displayMessage("Exiting...");
                    view.closeScanner();
                    return;
                default:
                    view.displayMessage("Invalid choice.");
                    break;
            }
        }
    }  

    public static String capitalizeWords(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return phrase;
        }

        StringBuilder result = new StringBuilder(phrase.length());
        String[] words = phrase.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty()) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                String rest = word.substring(1).toLowerCase();
                result.append(firstChar).append(rest).append(" ");
            }
        }

        return result.toString().trim();
    }

    private void addUser() {
        String username = view.getUserInput("Enter new username: ");
        username = capitalizeWords(username);
        if (userModel.addUser(username)) {
            view.displayMessage("User " + username + " successfully.");
            selectUserWhenAdding(username);
        } else {
            view.displayMessage("User already exists.");
        }
    }

    private void selectUserWhenAdding(String username) {
        currentUser = userModel.getUser(username);
        if (currentUser != null) {
            view.displayMessage("User " + username + " selected.");
        } else {
            view.displayMessage("User not found.");
        }
    }

    private void deleteSelectedUser() {
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
    
        String confirmation = view.getUserInput("Are you sure you want to delete the selected user? Type CONFIRM to proceed: ");
        if (!confirmation.equals("CONFIRM")) {
            view.displayMessage("Deletion canceled.");
            return;
        }
    
        String username = currentUser.getUsername();
        boolean deleted = userModel.deleteUser(username);
        if (deleted) {
            view.displayMessage("User deleted successfully.");
            currentUser = null; // Reset currentUser after deletion
        } else {
            view.displayMessage("User not found or failed to delete.");
        }
    }  

    private void selectUser() {
        String username = view.getUserInput("Enter username: ");
        username = capitalizeWords(username);
        currentUser = userModel.getUser(username);
        if (currentUser != null) {
            userModel.setCurrentUser(currentUser);
            view.displayMessage("User " + username + " selected.");
        } else {
            view.displayMessage("User not found.");
        }
    }

    private void showCurrentUser() {
        User currentUser = userModel.getCurrentUser(); // Get the current user from the model
        if (currentUser != null) {
            view.displayMessage("Current user: " + currentUser.getUsername());
        } else {
            view.displayMessage("No user selected.");
        }
    }

    private void saveInformation() {
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to save: ");
        File userDir = currentUser.getUserDirectory();
        File fileToSave = new File(userDir, fileName);
        
        if (fileToSave.exists()) {
            view.displayMessage("File already exists. Please choose a different file name.");
            return;
        }

        String characterName = view.getFileName("Enter the character's name: ");
    
        classesMenu();
        int classChoice = view.getClassChoice();
        String className = getClassNumber(classChoice);
    
        // Get the RPGClass instance based on the selected class name
        RPGClass rpgClass = fileModel.classes.get(className);
    
        if (rpgClass != null) {
            rpgClass.askQuestions(view);
            try {
                fileModel.saveToFile(currentUser, fileName, characterName, rpgClass);
                view.displayMessage("Data saved to file.");
            } catch (IOException e) {
                view.displayMessage("An error occurred while saving to file.");
                e.printStackTrace();
            }
        } else {
            view.displayMessage("Invalid class.");
        }
    }      

    private void readInformation() {
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to read: ");
        try {
            RPGClass rpgClass = fileModel.readFromFile(currentUser, fileName, view);
            if (rpgClass != null) {
                rpgClass.displayData(view);
            } else {
                view.displayMessage("File is empty or missing data.");
            }
        } catch (IOException e) {
            view.displayMessage("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }

    private void editInformation() {
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to edit: ");

        String characterName = view.getFileName("Enter the character's name: ");
        
        classesMenu();
        int classChoice = view.getClassChoice();
        String className = getClassNumber(classChoice);
    
        RPGClass rpgClass = fileModel.classes.get(className);
        if (rpgClass != null) {
            try {
                rpgClass.askQuestions(view); // Ask questions specific to the class
                fileModel.saveToFile(currentUser, fileName, characterName, rpgClass);
                view.displayMessage("Data updated in file.");
            } catch (IOException e) {
                view.displayMessage("An error occurred while editing the file.");
                e.printStackTrace();
            }
        } else {
            view.displayMessage("Invalid class.");
        }
    }
    
    
    private void deleteInformation() {
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        String fileName = view.getFileName("Enter the file name to delete: ");
        File userDir = currentUser.getUserDirectory();
        File fileToDelete = new File(userDir, fileName);
        
        if (fileToDelete.exists()) {
            // Prompt for confirmation only if the file exists
            String confirmation = view.getUserInput("Are you sure you want to delete the file? (Type CONFIRM to proceed): ");
            if (!confirmation.equalsIgnoreCase("CONFIRM")) {
                view.displayMessage("Deletion cancelled.");
                return;
            }
            boolean deleted = fileModel.deleteFile(currentUser, fileName);
            
            if (deleted) {
                view.displayMessage("File deleted successfully.");
            } else {
                view.displayMessage("Failed to delete the file.");
            }
        } else {
            view.displayMessage("File does not exist.");
        }
    }


    private void showUserFiles() {
        if (currentUser == null) {
            view.displayMessage("No user selected.");
            return;
        }
        File userDir = currentUser.getUserDirectory();
        File[] files = userDir.listFiles();
        if (files != null && files.length > 0) {
            view.displayMessage("Files in current user directory:");
            for (File file : files) {
                view.displayMessage(file.getName());
            }
        } else {
            view.displayMessage("No files found in current user directory.");
        }
    }
}
