package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Mage extends RPGClass {
    private String element;

    public Mage() {
        super("Mage");
    }

    @Override
    public void askQuestions(FileView view) {
        view.displayMessage("What is your Element?");
        element = view.getUserInput("Fire/Ice: ");
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Element: " + element);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line.startsWith("Element: ")) {
            element = line.substring("Element: ".length());
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Element: " + element);
    }
}
