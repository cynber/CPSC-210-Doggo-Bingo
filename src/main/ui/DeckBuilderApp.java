package ui;

import model.Item;
import model.ItemDeck;

import java.util.Scanner;


public class DeckBuilderApp {
    private ItemDeck deck;

    private Scanner input;

    // EFFECTS: runs the Item Deck Builder application
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
            doAddItem();
        } else if (command.equals("d")) {
            doRemoveItem();
        } else if (command.equals("e")) {
            doEditItem();
        } else if (command.equals("v")) {
            doViewItems();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes deck
    private void init() {
        deck = new ItemDeck();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add item to deck");
        System.out.println("\td -> delete item from deck");
        System.out.println("\te -> edit item");
        System.out.println("\tv -> view all items in deck");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds an item to the deck
    private void doAddItem() {
        Item anItem;
        System.out.print("Enter a unique title for the item: ");
        String title = input.next();

        if (title.length() > 0) {
            anItem = new Item(title);
            deck.addItem(anItem);
            System.out.println("Added item: \"" + anItem.getTitle() + "\"");
        } else {
            System.out.println("Cannot have a blank title.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an item to the deck
    private void doRemoveItem() {
        System.out.print("Enter the title of the item to delete: ");
        String title = input.next();

        if (deck.containsItemTitle(title)) {
            deck.removeItemTitle(title);
            System.out.println("Deleted \"" + title + "\"");
        } else {
            System.out.println("\"" + title + "\"" + " does not exist currently.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an item to the deck
    private void doEditItem() {
        String selection = "";

        while (!(selection.equals("t") || selection.equals("d") || selection.equals("p") || selection.equals("f"))) {
            doViewItems();
            System.out.print("Which item do you want to edit?:");
            String title = input.next();
            if (deck.containsItemTitle(title)) {

                System.out.print("What would you like to change?: \n");
                System.out.println("\td -> enter new description");
                System.out.println("\tp -> edit how many points the item is worth");
                System.out.println("\tf -> toggle favorite status");

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

    private void doEditDescription(String title) {
        System.out.print("Enter the new description:");
        String newDescription = input.next();
        deck.renameDescriptionFromTitle(title, newDescription);
        System.out.print("\"" + title + "\"'s description has been updated to \"" + newDescription + "\"");
    }

    private void doEditPoints(String title) {
        System.out.print("The difficulty options are as follows:");
        System.out.print("\n [1] easy");
        System.out.print("\n [1] medium");
        System.out.print("\n [1] hard");
        System.out.print("Enter the appropriate difficulty for this item:");
        int value = input.nextInt();
        deck.editPointsFromTitle(title, value);
        System.out.print("\"" + title + "\"'s points have been updated");
    }

    private void doToggleFavourite(String title) {
        Boolean oldStatus = deck.getFavouriteFromTitle(title);
        String output = "";
        if (oldStatus = true) {
            output = "Item is no longer marked as a favourite.";
        } else {
            output = "Item is now marked as a favourite.";
        }
        deck.toggleFavouriteFromTitle(title);
        System.out.print(output);
    }

    // EFFECTS: prints out the current list of items
    private void doViewItems() {
        System.out.println("The current item(s) in the deck are: " + deck.getItemTitles());
    }




}