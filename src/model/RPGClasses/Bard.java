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
        view.displayMessage("What is your instrument?");
        instrument = view.getUserInput("Violin/Flute: ");
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
        String line = reader.readLine();
        if (line.startsWith("Instrument: ")) {
            instrument = line.substring("Instrument: ".length());
        }
    }

    @Override
    public void displayData(FileView view) {
        view.displayMessage("Class: " + className);
        view.displayMessage("Instrument: " + instrument);
    }
}
