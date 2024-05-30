package model;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import model.RPGClasses.*;

import view.FileView;

public class FileModel {
    public Map<String, RPGClass> classes;

    public FileModel() {
        classes = new HashMap<>();
        classes.put("Mage", new Mage());
        classes.put("Bard", new Bard());
        classes.put("Berserker", new Berserker());
        classes.put("Paladin", new Paladin());
        classes.put("Rogue", new Rogue());
        // Add other RPG classes here
    }

    public void saveToFile(User user, String fileName, String characterName, RPGClass rpgClass) throws IOException {
        fileName = fileName.trim(); // Trim whitespace from file name
        File userDir = user.getUserDirectory(); // Use user's directory
        File file = new File(userDir, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Character Name: " + characterName); // Include character name in the file
            writer.newLine();
            rpgClass.saveToFile(writer);
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    public RPGClass readFromFile(User user, String fileName, FileView view) throws IOException {
        fileName = fileName.trim();
        File userDir = user.getUserDirectory(); // Use user's directory
        File file = new File(userDir, fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && line.startsWith("Character Name: ")) {
                String characterName = line.substring("Character Name: ".length());
                view.displayMessage("Character Name: " + characterName);
                
                line = reader.readLine(); // Read the next line
                if (line != null && line.startsWith("Class: ")) {
                    String className = line.substring("Class: ".length());
                    RPGClass rpgClass = classes.get(className);
                    if (rpgClass != null) {
                        rpgClass.readFromFile(reader);
                        return rpgClass;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
        return null; // Return null if the data is invalid or not found
    }

    public boolean deleteFile(User user, String fileName) {
        fileName = fileName.trim(); // Trim whitespace from file name
        File userDir = user.getUserDirectory(); // Use user's directory
        File fileToDelete = new File(userDir, fileName);
        
        if (fileToDelete.exists() && fileToDelete.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
