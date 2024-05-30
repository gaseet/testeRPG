package model;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import model.RPGClasses.Bard;
import model.RPGClasses.Berserker;
import model.RPGClasses.Mage;

public class FileModel {
    public Map<String, RPGClass> classes;

    public FileModel() {
        classes = new HashMap<>();
        classes.put("Mage", new Mage());
        classes.put("Bard", new Bard());
        classes.put("Berserker", new Berserker());
        // Add other RPG classes here
    }

    public void saveToFile(User user, String fileName, RPGClass rpgClass) throws IOException {
        fileName = fileName.trim(); // Trim whitespace from file name
        File userDir = user.getUserDirectory(); // Use user's directory
        File file = new File(userDir, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            rpgClass.saveToFile(writer);
        }
    }

    public RPGClass readFromFile(User user, String fileName) throws IOException {
        fileName = fileName.trim(); 
        File userDir = user.getUserDirectory(); // Use user's directory
        File file = new File(userDir, fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null && line.startsWith("Class: ")) {
                String className = line.substring("Class: ".length());
                RPGClass rpgClass = classes.get(className);
                if (rpgClass != null) {
                    rpgClass.readFromFile(reader);
                    return rpgClass;
                }
            }
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
