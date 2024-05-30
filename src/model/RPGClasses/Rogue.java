package model.RPGClasses;

import java.io.*;

import model.RPGClass;
import view.FileView;

public class Rogue extends RPGClass{
    private String stealthTechnique;

    public Rogue() {
        super("Rogue");
    }

    @Override
    public void askQuestions(FileView view) {
        view.displayMessage("What is your stealth technique?");
        stealthTechnique = view.getUserInput("Enter stealth technique: ");
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Stealth Technique: " + stealthTechnique);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Stealth Technique: ")) {
                stealthTechnique = line.substring("Stealth Technique: ".length());
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Stealth Technique: " + stealthTechnique);
    }
    
}
