package ui;

// Runs application

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            //         new DeckBuilderApp();
            new SplashScreenGUI("Loading...");
            new AppDeckBuilderGUI("Bingo Deck Builder");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
