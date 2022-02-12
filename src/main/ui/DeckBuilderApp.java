package ui;

// represents a console based interface that users can use to build decks
// created with assistance from TellerApp:
//      https://github.students.cs.ubc.ca/CPSC210/TellerApp

import model.Card;
import model.CardDeck;
import java.util.Scanner;

public class DeckBuilderApp {
    private CardDeck deck;
    private Scanner input;

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
        if (command.equals("a")) {
            doAddCard();
            //       } else if (command.equals("d")) {
            //           if (deck.isEmpty()) {
            //               System.out.println("There are no cards yet.");
            //           } else {
            //               doRemoveItem();
            //           }
        } else if (command.equals("e")) {
            if (deck.isEmpty()) {
                System.out.println("There are no cards to edit yet.");
            } else {
                doEditCard();
            }
        } else if (command.equals("v")) {
            if (deck.isEmpty()) {
                System.out.println("There are no cards to view yet.");
            } else {
                doViewCards();
            }
        } else {
            System.out.println("Selection not valid...");
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
//        System.out.println("\td -> delete card from deck");
        System.out.println("\te -> edit card");
        System.out.println("\tv -> view all cards in deck");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds an card to the deck
    private void doAddCard() {
        Card anCard;
        System.out.print("\nEnter a unique title for the card:\n");
        String title = input.next();

        if (title.length() > 0) {
            if (deck.containsCardFromTitle(title)) {
                System.out.println("There is already an card called " + "\"" + title + "\"\n");
            } else {
                anCard = new Card(title);
                deck.addCard(anCard);
                System.out.println("Added card: \"" + anCard.getTitle() + "\"\n");
            }
        } else {
            System.out.println("Cannot have a blank title.\n");
        }
    }

//    // MODIFIES: this
//    // EFFECTS: removes an card from the deck
//    private void doRemoveItem() {
//        System.out.print("\nEnter the title of the card to delete:\n");
//        String title = input.next();
//
//        if (deck.containsItemTitle(title)) {
//            deck.removeItemTitle(title);
//            System.out.println("Deleted \"" + title + "\"\n");
//        } else {
//            System.out.println("\"" + title + "\"" + " does not exist currently.\n");
//        }
//    }

    // MODIFIES: this
    // EFFECTS: displays edit options to user
    private void doEditCard() {
        String selection = "";
        while (!(selection.equals("t") || selection.equals("d") || selection.equals("p") || selection.equals("f"))) {
            doViewCards();
            System.out.print("Which card do you want to edit?:\n");
            String title = input.next();
            if (deck.containsCardFromTitle(title)) {
                System.out.print("\nWhat would you like to change?: \n");
                System.out.println("\td -> enter new description");
                System.out.println("\tp -> edit how many points the card is worth");
                System.out.println("\tf -> toggle favorite status\n");
                selection = input.next();
                selection = selection.toLowerCase();
                if (selection.equals("d")) {
                    doEditDescription(title);
                } else if (selection.equals("p")) {
                    doEditPoints(title);
                } else if (selection.equals("f")) {
                    doToggleFavourite(title);
                }
            } else {
                System.out.println("\"" + title + "\"" + " does not exist currently.\n");
            }
        }
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
}
