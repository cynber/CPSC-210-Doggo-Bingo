//package ui;
//
//// Represents a console based interface that users can use to build decks
//
//// Created with assistance from TellerApp and JsonSerializationDemo:
////   https://github.students.cs.ubc.ca/CPSC210/TellerApp
////   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//
//import model.Card;
//import model.CardDeck;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class AppDeckBuilder {
//    private CardDeck deck;
//    private Scanner input;
//    private static final String JSON_STORE = "./data/CardDeckProgress.json";
//    private static final String JSON_STORE_DOG = "./data/DogCardDeck.json";
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//    private JsonReader jsonReaderDog;
//
//    // EFFECTS: runs the Card Deck Builder application
//    public AppDeckBuilder() throws FileNotFoundException {
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        jsonReaderDog = new JsonReader(JSON_STORE_DOG);
//        runDeckBuilder();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runDeckBuilder() throws FileNotFoundException {
//        boolean keepGoing = true;
//        String command;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("\nDeck building process complete.");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) throws FileNotFoundException {
//        if ("a".equals(command)) {
//            doAddCard();
//        } else if ("e".equals(command)) {
//            if (deck.isEmpty()) {
//                System.out.println("There are no cards to edit yet.");
//            } else {
//                doEditCard();
//                System.out.println("\nBE SURE TO SAVE YOUR PROGRESS LATER!");
//            }
//        } else if ("v".equals(command)) {
//            if (deck.isEmpty()) {
//                System.out.println("There are no cards to view yet.");
//            } else {
//                displayViewOptions();
//            }
//        } else if ("s".equals(command) || "l".equals(command)) {
//            processCommandSaveLoad(command);
//        } else if ("p".equals(command)) {
//            new AppBingoGame();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//    private void processCommandSaveLoad(String command) {
//        if ("s".equals(command)) {
//            doSaveProgress();
//        } else if ("l".equals(command)) {
//            displayLoadOptions();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes deck, scanner, and reader / writer
//    private void init() {
//        deck = new CardDeck();
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\ta -> add card to deck");
//        System.out.println("\te -> edit card(s)");
//        System.out.println("\tv -> view card(s)\n");
//        System.out.println("\ts -> save deck building progress");
//        System.out.println("\tl -> load a deck\n");
//        System.out.println("\tp -> PLAY THE GAME");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds a card to the deck
//    private void doAddCard() {
//        Card anCard;
//        System.out.print("\nEnter a unique title for the card:\n");
//        String title = input.next();
//
//        if (title.length() > 0) {
//            if (deck.containsCardFromTitle(title)) {
//                System.out.println("There is already a card called " + "\"" + title + "\"\n");
//            } else {
//                anCard = new Card(title);
//                deck.addCard(anCard);
//                System.out.println("Added card: \"" + anCard.getTitle() + "\"\n");
//            }
//            System.out.println("BE SURE TO SAVE YOUR PROGRESS LATER!");
//        } else {
//            System.out.println("Cannot have a blank title.\n");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: displays edit options to user and follows user input
//    private void doEditCard() {
//        String selection = "";
//        while (!(selection.equals("t") || selection.equals("d") || selection.equals("p") || selection.equals("f"))) {
//            doViewCardTitles();
//            System.out.print("Which card do you want to edit?:\n");
//            String title = input.next();
//            if (deck.containsCardFromTitle(title)) {
//                displayEditOptions();
//                selection = input.next().toLowerCase();
//                if ("d".equals(selection)) {
//                    doEditDescription(title);
//                } else if ("p".equals(selection)) {
//                    doEditPoints(title);
//                } else if ("f".equals(selection)) {
//                    doToggleFavourite(title);
//                }
//            } else {
//                System.out.println("\"" + title + "\"" + " does not exist currently.\n");
//            }
//        }
//    }
//
//    // EFFECTS: displays edit options to user
//    private void displayEditOptions() {
//        System.out.print("\nWhat would you like to change?: \n");
//        System.out.println("\td -> enter new description");
//        System.out.println("\tp -> edit how many points the card is worth");
//        System.out.println("\tf -> toggle favorite status\n");
//    }
//
//    //MODIFIES: card in deck with matching title
//    //EFFECTS: processes user command to rename description of selected card
//    private void doEditDescription(String title) {
//        System.out.print(deck.getDescriptionFromTitle(title));
//        System.out.print("\nEnter the new description:\n");
//        String newDescription = input.next();
//        deck.renameDescriptionFromTitle(title, newDescription);
//        System.out.print("\"" + title + "\"'s description has been updated to \"" + newDescription + "\"");
//    }
//
//    //MODIFIES: card in deck with matching title
//    //EFFECTS: processes user command to edit the points value of selected card
//    private void doEditPoints(String title) {
//        System.out.print("\nThe difficulty options are as follows:");
//        System.out.print("\n [1] easy");
//        System.out.print("\n [2] medium");
//        System.out.print("\n [3] hard");
//        System.out.print("\n Enter the appropriate difficulty for this card:\n");
//        int value = input.nextInt();
//        if (value == 1 || value == 2 || value == 3) {
//            deck.editPointsFromTitle(title, value);
//            System.out.print("\n\"" + title + "\"'s points have been updated\n");
//        } else {
//            System.out.print("\nThat was an invalid selection.\n");
//        }
//    }
//
//    //MODIFIES: card in deck with matching title
//    //EFFECTS: toggles favourite status of selected card
//    private void doToggleFavourite(String title) {
//        Boolean oldStatus = deck.getFavouriteFromTitle(title);
//        String output;
//        if (oldStatus) {
//            output = "\" is no longer marked as a favourite.\n";
//        } else {
//            output = "\" is now marked as a favourite.\n";
//        }
//        deck.toggleFavouriteFromTitle(title);
//        System.out.print("\"" + title + output);
//    }
//
//    // EFFECTS: displays the load menu options
//    private void displayViewOptions() {
//        System.out.println("What would you like to view?");
//        System.out.println("t -> view card titles for all cards");
//        System.out.println("f -> view full card details for all cards");
//        String selection = input.next();
//        if (Objects.equals(selection, "t")) {
//            doViewCardTitles();
//        } else {
//            doViewCardDetails();
//        }
//    }
//
//    // EFFECTS: prints out the card titles of all cards in the deck
//    private void doViewCardTitles() {
//        System.out.println("\nThe current card(s) in the deck are: " + deck.getCardFromTitle());
//    }
//
//    // EFFECTS: prints out the current details for all cards in deck
//    private void doViewCardDetails() {
//        ArrayList<Card> results;
//        results = deck.getCardDetails();
//        for (Card c : results) {
//            System.out.println("Title: " + c.getTitle());
//            System.out.println("Description: " + c.getDescription());
//            System.out.println("This card has been used " + c.getUsedCount() + " time(s).");
//        //  System.out.println("This card has been found " + c.getFoundCount() + " time(s).");
//            if (c.isFavourite()) {
//                System.out.println("This card is marked as a favourite.");
//            } else {
//                System.out.println("This card is NOT marked as a favourite.\n");
//            }
//        }
//    }
//
//    // EFFECTS: saves the deck builder progress to file
//    private void doSaveProgress() {
//        try {
//    //        updateDeckName();         // TODO: Make custom save option, possibly have a few save states
//                                        // TODO: check if we are allowed to have save states
//            jsonWriter.open();
//            jsonWriter.writeCardDeck(deck);
//            jsonWriter.close();
//            System.out.println("Saved " + deck.getDeckName() + " to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: changes the title of the deck
////    private void updateDeckName() {     // TODO: add rename method after custom save option
////        String deckName = deck.getDeckName();
////        if (Objects.equals(deckName, "")) {
////            System.out.println("Please enter a name for this deck:\n");
////            deckName = input.next();
////        } else {
////            System.out.println("This deck currently has the following name:\n" + deckName);
////            System.out.println("Would you like to update the name? (y/n)");
////            String selection = input.next();
////            if (Objects.equals(selection, "y")) {
////                System.out.println("Enter a new name:");
////                deckName = input.next();
////                deck.setDeckName(deckName);
////            } else if (Objects.equals(selection, "n")) {
////                System.out.println("Name was kept as:\n" + deckName);
////            } else {
////                System.out.println("That was not a valid selection...");
////                System.out.println("Name was kept as:\n" + deckName);
////            }
////        }
////    }
//
//    // EFFECTS: displays the load menu options
//    private void displayLoadOptions() {
//        System.out.println("Would you like to load your progress or load a custom deck?");
//        System.out.println("p -> load deck building progress");
//        System.out.println("c -> load a custom deck");
//        String selection = input.next();
//        if (Objects.equals(selection, "p")) {
//            doLoadProgress();
//        } else {
//            System.out.println("There is only one custom deck currently:");
//            System.out.println("d -> Doggo Bingo");
//            System.out.println("Loading custom deck...");
//            doLoadCustom();
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads the deck builder progress from file
//    private void doLoadProgress() {
//        try {
//            deck = jsonReader.read();
//            System.out.println("Loaded " + deck.getDeckName() + " from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads the deck builder progress from file
//    private void doLoadCustom() {
//        try {
//            deck = jsonReaderDog.read();
//            System.out.println("Loaded " + deck.getDeckName() + " from " + JSON_STORE_DOG);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE_DOG);
//        }
//    }
//}
