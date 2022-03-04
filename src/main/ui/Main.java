package ui;

// Runs application
// created with assistance from TellerApp:
//      https://github.students.cs.ubc.ca/CPSC210/TellerApp

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new DeckBuilderApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
