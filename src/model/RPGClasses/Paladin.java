package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Paladin extends RPGClass {
    private String oath;

    public Paladin() {
        super("Paladin");
    }

    @Override
    public void askQuestions(FileView view) {
        view.displayMessage("What is your Oath?");
        oath = view.getUserInput("Enter oath: ");
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Oath: " + oath);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Oath: ")) {
                oath = line.substring("Oath: ".length());
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Oath: " + oath);
    }
}
