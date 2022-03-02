package ui;

// represents a console based interface that users can use to build decks
// created with assistance from TellerApp:
//      https://github.students.cs.ubc.ca/CPSC210/TellerApp

import model.Card;
import model.CardDeck;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class DeckBuilderApp {
    private CardDeck deck;
    private Scanner input;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Card Deck Builder application
    public DeckBuilderApp() {
        runDeckBuilder();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDeckBuilder() {
        boolean keepGoing = true;
        String command = null;

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
        System.out.println("\nDeck building process complete.");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doAddCard();
                break;
            case "e":
                if (deck.isEmpty()) {
                    System.out.println("There are no cards to edit yet.");
                } else {
                    doEditCard();
                }
                break;
            case "v":
                if (deck.isEmpty()) {
                    System.out.println("There are no cards to view yet.");
                } else {
                    doViewCards();
                }
                break;
            case "s":
                doSaveProgress();
                break;
            case "l":
                doLoadProgress();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes deck
    private void init() {
        deck = new CardDeck();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add card to deck");
        System.out.println("\te -> edit card");
        System.out.println("\tv -> view all cards in deck");
        System.out.println("\ts -> save deck building progress");
        System.out.println("\tl -> load deck building progress");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a card to the deck
    private void doAddCard() {
        Card anCard;
        System.out.print("\nEnter a unique title for the card:\n");
        String title = input.next();

        if (title.length() > 0) {
            if (deck.containsCardFromTitle(title)) {
                System.out.println("There is already a card called " + "\"" + title + "\"\n");
            } else {
                anCard = new Card(title);
                deck.addCard(anCard);
                System.out.println("Added card: \"" + anCard.getTitle() + "\"\n");
            }
        } else {
            System.out.println("Cannot have a blank title.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays edit options to user
    private void doEditCard() {
        String selection = "";
        while (!(selection.equals("t") || selection.equals("d") || selection.equals("p") || selection.equals("f"))) {
            doViewCards();
            System.out.print("Which card do you want to edit?:\n");
            String title = input.next();
            if (deck.containsCardFromTitle(title)) {
                dialogueEditCard();
                selection = input.next().toLowerCase();
                switch (selection) {
                    case "d":
                        doEditDescription(title);
                        break;
                    case "p":
                        doEditPoints(title);
                        break;
                    case "f":
                        doToggleFavourite(title);
                        break;
                }
            } else {
                System.out.println("\"" + title + "\"" + " does not exist currently.\n");
            }
        }
    }

    private void dialogueEditCard() {
        System.out.print("\nWhat would you like to change?: \n");
        System.out.println("\td -> enter new description");
        System.out.println("\tp -> edit how many points the card is worth");
        System.out.println("\tf -> toggle favorite status\n");
    }

    //MODIFIES: card in deck with matching title
    //EFFECTS: processes user command to rename description of selected card
    private void doEditDescription(String title) {
        System.out.print(deck.getDescriptionFromTitle(title));
        System.out.print("\nEnter the new description:\n");
        String newDescription = input.next();
        deck.renameDescriptionFromTitle(title, newDescription);
        System.out.print("\"" + title + "\"'s description has been updated to \"" + newDescription + "\"");
    }

    //MODIFIES: card in deck with matching title
    //EFFECTS: processes user command to edit the points value of selected card
    private void doEditPoints(String title) {
        System.out.print("\nThe difficulty options are as follows:");
        System.out.print("\n [1] easy");
        System.out.print("\n [2] medium");
        System.out.print("\n [3] hard");
        System.out.print("\n Enter the appropriate difficulty for this card:\n");
        int value = input.nextInt();
        if (value == 1 || value == 2 || value == 3) {
            deck.editPointsFromTitle(title, value);
            System.out.print("\n\"" + title + "\"'s points have been updated\n");
        } else {
            System.out.print("\nThat was an invalid selection.\n");
        }
    }

    //MODIFIES: card in deck with matching title
    //EFFECTS: toggles favourite status of selected card
    private void doToggleFavourite(String title) {
        Boolean oldStatus = deck.getFavouriteFromTitle(title);
        String output = "";
        if (oldStatus) {
            output = "\" is no longer marked as a favourite.\n";
        } else {
            output = "\" is now marked as a favourite.\n";
        }
        deck.toggleFavouriteFromTitle(title);
        System.out.print("\"" + title + output);
    }

    // EFFECTS: prints out the current list of card
    private void doViewCards() {
        System.out.println("\nThe current card(s) in the deck are: " + deck.getCardFromTitle());
    }

    // EFFECTS: saves the deck builder progress to file
    private void saveWorkRoom() {
        try {
            updateDeckName();
            jsonWriter.open();
            jsonWriter.write(deck);
            jsonWriter.close();
            System.out.println("Saved " + deck.getDeckName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void updateDeckName() {
        String deckName = deck.getDeckName();
        if (Objects.equals(deckName, "")) {
            System.out.println("Please enter a name for this deck:\n");
            deckName = input.next();
        } else {
            System.out.println("This deck currently has the following name:\n" + deckName);
            System.out.println("Would you like to update the name? (y/n)");
            String selection = input.next();
            if (selection == "y") {
                System.out.println("Enter a new name:\n");
                deckName = input.next();
            } else if (selection == "n") {
                System.out.println("Name was kept as:\n" + deckName);
            } else {
                System.out.println("That was not a valid selection...");
                System.out.println("Name was kept as:\n" + deckName);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the deck builder progress from file
    private void loadWorkRoom() {
        try {
            deck = jsonReader.read();
            System.out.println("Loaded " + deck.getDeckName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
