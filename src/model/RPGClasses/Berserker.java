package model.RPGClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import model.RPGClass;
import view.FileView;

public class Berserker extends RPGClass {
    private String martialArt;
    private String weapon;

    public Berserker() {
        super("Berserker");
    }

    @Override
    public void askQuestions(FileView view) {
        view.displayMessage("What is your Martial Art?");
        martialArt = view.getUserInput("Enter martial art: ");
        
        view.displayMessage("What is your Weapon?");
        weapon = view.getUserInput("Enter weapon: ");
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Martial Art: " + martialArt);
        writer.newLine();
        writer.write("Weapon: " + weapon);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Martial Art: ")) {
                martialArt = line.substring("Martial Art: ".length());
            } else if (line.startsWith("Weapon: ")) {
                weapon = line.substring("Weapon: ".length());
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Martial Art: " + martialArt);
        view.displayMessage("Weapon: " + weapon);
    }
}
