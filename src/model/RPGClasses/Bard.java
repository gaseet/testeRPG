package model.RPGClasses;
import java.io.*;

import model.RPGClass;
import view.FileView;

public class Bard extends RPGClass {
    private String instrument;

    public Bard() {
        super("Bard");
    }

    @Override
    public void askQuestions(FileView view) {
        int instrumentChoice = -1;

        do {
            view.displayMessage("What is your instrument?");
            view.displayMessage("1. Violin");
            view.displayMessage("2. Flute");
            instrumentChoice = view.getIntAnswer();

            switch (instrumentChoice) {
                case 1:
                    instrument = "Violin";
                    break;
                case 2:
                instrument = "Flute";
                    break;
                default:
                    view.displayMessage("Invalid number.");
                    instrumentChoice = -1; // Reset to continue the loop
            }
        } while (instrumentChoice == -1);
    }

    @Override
    public void saveToFile(BufferedWriter writer) throws IOException {
        writer.write("Class: " + className);
        writer.newLine();
        writer.write("Instrument: " + instrument);
        writer.newLine();
    }

    @Override
    public void readFromFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Instrument: ")) {
                instrument = line.substring("Instrument: ".length());
            }
            if (instrument != null) { // VERY IMPORTANT TO BREAK WHEN FOUND ALL
                break;
            }
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Instrument: " + instrument);
    }
}
