package ui;

// Represents a console based interface that users can use to play the game

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Made with help from StackOverflow thread on outputting as a table:
// https://stackoverflow.com/questions/2745206/output-in-a-table-format-in-javas-system-out

import model.Board;
import model.BoardSpace;
import model.CardDeck;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BingoGame {
    private CardDeck deck;
    private ArrayList<BoardSpace> bd;
    private ArrayList<String> bdMarks;
    private String gameMode;

    private Scanner input;

    private static final String JSON_STORE = "./data/CardDeckProgress.json";
    private static final String JSON_STORE_DOG = "./data/DogCardDeck.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonReader jsonReaderDog;

    // EFFECTS: runs the Bingo Game application
    public BingoGame() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReaderDog = new JsonReader(JSON_STORE_DOG);
        runBingoGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runBingoGame() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for playing. Bye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes deck, scanner, and reader / writer
    private void init() {
        deck = new CardDeck();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tl -> Load a deck to play with");
        System.out.println("\tq -> Quit game \n");
        System.out.println("\tp1 -> UNAVAILABLE - Play on standard board (with FREE space)"); //TODO: other game modes
        System.out.println("\tp2 -> UNAVAILABLE - Play on standard board (no FREE space)");
        System.out.println("\tp3 -> UNAVAILABLE - Play on small board (with FREE space)");
        System.out.println("\tp4 -> Play on small board (no FREE space)\n");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if ("l".equals(command)) {
            displayLoadOptions();
        } else if (Objects.equals(command, "p4")) {
            int minimumSize = 9;
//        } else if (Objects.equals(command, "p1")      \\TODO: add other game modes
//                || Objects.equals(command, "p2")
//                || Objects.equals(command, "p3")
//                || Objects.equals(command, "p4")) {
            if (deck.getDeckSize() < minimumSize) {
                System.out.println("You do not have enough cards in this deck to play on this board.");
                System.out.println("Do one of the following:");
                System.out.println("\t - Add some more cards to the deck.");
                System.out.println("\t - Pick a different deck.");
                System.out.println("\t - Pick a different board.");
                return;
            } else {
                gameMode = command;
                bd = newBoard(gameMode, deck);
                System.out.println("WELCOME TO BINGO!");
                playGame();
            }
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: generates a new board using cards from the deck
    private ArrayList<BoardSpace> newBoard(String code, CardDeck deck) {
        bd = Board.makeBoard(code,deck);
        bdMarks = new ArrayList<String>();
        for (int i = 0; i < bd.size(); i++) {
            bdMarks.add("missing");
        }
        return bd;
    }

    // EFFECTS: starts the game
    private void playGame() {
        checkWin(gameMode);


        displayBoard(gameMode);
        displayTurnOptions();
        playTurn();
    }

    // EFFECTS: displays gameplay options
    private void displayTurnOptions() {
        System.out.println("Select an option:");
        System.out.println("\t m -> mark a box");
        System.out.println("\t s -> skip turn and do nothing");
    }

    // MODIFIES: this
    // EFFECTS: uses user input to modify board
    private void playTurn() {
        String command = input.next();
        command = command.toLowerCase();
        if ("m".equals(command)) {
            editBoard();
        } else {
            System.out.println("Skipping turn...");
        }

        playGame();
    }

    // MODIFIES: this
    // EFFECTS: uses user input to mark a board space as "found"
    private void editBoard() {
        System.out.println("Which box should be marked off? Type the box code (ex. \"A1\")");
        String command = input.next();
        command = command.toLowerCase();
        if (Objects.equals(gameMode, "p4")) {
            editBoardHalfOne(command);
            editBoardHalfTwo(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: uses user input to mark a board space as "found"
    private void editBoardHalfOne(String command) {     //TODO: check if we can suppress checkstyle for long switch
        if ("a1".equals(command)) {
            bdMarks.set(0, "FOUND");
        } else if ("a2".equals(command)) {
            bdMarks.set(1, "FOUND");
        } else if ("a3".equals(command)) {
            bdMarks.set(2, "FOUND");
        } else if ("a4".equals(command)) {
            bdMarks.set(3, "FOUND");
        } else if ("a5".equals(command)) {
            bdMarks.set(4, "FOUND");
        } else {
            editBoardHalfTwo(command);
        }
    }

    // MODIFIES: this
    // EFFECTS: uses user input to mark a board space as "found"
    private void editBoardHalfTwo(String command) {
        if ("a6".equals(command)) {
            bdMarks.set(5, "FOUND");
        } else if ("a7".equals(command)) {
            bdMarks.set(6, "FOUND");
        } else if ("a8".equals(command)) {
            bdMarks.set(7, "FOUND");
        } else if ("a9".equals(command)) {
            bdMarks.set(8, "FOUND");
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if the board has a win state
    private void checkWin(String gameMode) { //TODO

    }

    // MODIFIES: this
    // EFFECTS: displays the game board
    private void displayBoard(String gameMode) {
        if (Objects.equals(gameMode, "p4")) {
            System.out.format("%40s%40s%40s%1s", "A", "B", "C","\n\n");
            System.out.format("%40s%40s%40s%1s", "A1", "B1", "C1", "\n");
            System.out.format("%40s%40s%40s%1s", bdMarks.get(0), bdMarks.get(1), bdMarks.get(2), "\n");
            System.out.format("%40s%40s%40s%1s",
                    bd.get(0).getCard().getTitle(),
                    bd.get(1).getCard().getTitle(),
                    bd.get(2).getCard().getTitle(),"\n\n");
            System.out.format("%40s%40s%40s%1s", "A2", "B2", "C2", "\n");
            System.out.format("%40s%40s%40s%1s", bdMarks.get(3), bdMarks.get(4), bdMarks.get(5), "\n");
            System.out.format("%40s%40s%40s%1s",
                    bd.get(3).getCard().getTitle(),
                    bd.get(4).getCard().getTitle(),
                    bd.get(5).getCard().getTitle(),"\n\n");
            System.out.format("%40s%40s%40s%1s", "A3", "B3", "C3", "\n");
            System.out.format("%40s%40s%40s%1s", bdMarks.get(6), bdMarks.get(7), bdMarks.get(8), "\n");
            System.out.format("%40s%40s%40s%1s",
                    bd.get(6).getCard().getTitle(),
                    bd.get(7).getCard().getTitle(),
                    bd.get(8).getCard().getTitle(),"\n\n\n\n");
        }
    }

    // EFFECTS: displays the load menu options
    private void displayLoadOptions() {
        System.out.println("Would you like to load your progress or load a custom deck?");
        System.out.println("p -> load deck building progress");
        System.out.println("c -> load a custom deck");
        String selection = input.next();
        if (Objects.equals(selection, "p")) {
            doLoadProgress();
        } else {
            System.out.println("There is only one custom deck currently:");
            System.out.println("d -> Doggo Bingo");
            System.out.println("Loading custom deck...");
            doLoadCustom();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the deck builder progress from file
    private void doLoadProgress() {
        try {
            deck = jsonReader.read();
            System.out.println("Loaded " + deck.getDeckName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the deck builder progress from file
    private void doLoadCustom() {
        try {
            deck = jsonReaderDog.read();
            System.out.println("Loaded " + deck.getDeckName() + " from " + JSON_STORE_DOG);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_DOG);
        }
    }
}
