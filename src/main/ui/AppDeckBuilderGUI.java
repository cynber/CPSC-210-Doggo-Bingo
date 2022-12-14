package ui;

// Represents a graphical user interface that users can use to build decks

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// GUI created with assistance from following projects & code from Project Phase 3 page:
//   https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabSolution
//   https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
//   https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
//   https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
//   https://learning.edge.edx.org/course/course-v1:UBC+CPSC210+all/block-v1:UBC+CPSC210+all+type@sequential+block
//    @2319e011dd3848d5940b8d7aa19ad5d9/block-v1:UBC+CPSC210+all+type@vertical+block@45c6cfa614d8417ebcf74d1fed323c24

// Also made with assistance from the following:
// https://stackoverflow.com/questions/15746984/how-to-run-jframe-maximized-in-java   (for window formatting tips)
// https://docs.oracle.com/javase/tutorial/uiswing/components/html.html               (for font editing)
// https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton     (for button sizing)
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#create        (for menu bar)
// https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
//          (for printing log when closing application)

import model.Card;
import model.CardDeck;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;


public class AppDeckBuilderGUI extends JFrame implements ActionListener {
    private static final String STATUS = "Deck Builder Running...";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/CardDeckProgress.json";
    private static final String JSON_STORE_DOG = "./data/DogCardDeck.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonReader jsonReaderDog;

    private CardDeck deck;
    private Object[] listOfTitles;

    private JPanel messageFrame;
    private JLabel statusLabel;
    private JMenuBar menuBar;

    String grid9 = "3x3 (need >=9 cards)";
    String grid25 = "5x5 (need >=25 cards)";
    String freeTrue = "Add a FREE space";
    String freeFalse = "No FREE spaces";

    // EFFECTS: runs the Card Deck Builder application
    public AppDeckBuilderGUI(String title) throws FileNotFoundException {
        super(title);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog(EventLog.getInstance());                           // see references section for source
                System.exit(0);
            }
        });

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        initAll();

        setLayout(new GridLayout(4, 1));

        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(true);
    }

    private void initAll() {
        initStatus();
        initEditPanel();
        initSaveLoadOptionsPanel();
        initGame();

        initDeck();

        initMessageFrame();
        initViewFrame();
        initMenus();
    }

    // EFFECTS: creates the menu bar with 4 menu categories
    private void initMenus() {
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(100, 30));

        JMenu fileMenu = makeMenu("  File  ", KeyEvent.VK_F, "Menu for saving and loading decks from file.");
        JMenu editMenu = makeMenu("  Edit  ", KeyEvent.VK_E, "Menu for editing the deck and cards.");
        JMenu viewMenu = makeMenu("  View  ", KeyEvent.VK_V, "Menu for viewing information for deck and cards.");
        JMenu helpMenu = makeMenu("  Help  ", KeyEvent.VK_H, "Menu for getting help resources.");
        //TODO: update with methods to edit & view deck info

        makeMenuItem(fileMenu, "Save Deck Progress", KeyEvent.VK_S, KeyEvent.VK_1,
                "Saves Deck Progress", "saveDeck");
        makeMenuItem(fileMenu, "Load Deck Progress", KeyEvent.VK_P, KeyEvent.VK_2,
                "Loads Deck Progress", "loadDeckP");
        makeMenuItem(fileMenu, "Load Custom Deck", KeyEvent.VK_C, KeyEvent.VK_3,
                "Loads a custom deck", "loadDeckC");
        makeMenuItem(editMenu, "Edit a Card", KeyEvent.VK_E, KeyEvent.VK_4,
                "Opens card editor", "editCard");
        makeMenuItem(viewMenu, "View a Card", KeyEvent.VK_V, KeyEvent.VK_5,
                "Opens card viewer", "viewCards");
        makeMenuItem(helpMenu, "Help Building Decks", KeyEvent.VK_H, KeyEvent.VK_6,
                "Opens help page for deck building", "notAvailable");
        makeMenuItem(helpMenu, "Print Log", KeyEvent.VK_L, KeyEvent.VK_7,
                "Prints log to console", "printLog");

        this.setJMenuBar(menuBar);
    }

    // EFFECTS: creates individual menu bar items with a given category, name, keystroke,
    //          secondary keystroke, accessibility text, and action
    private void makeMenuItem(JMenu category, String s, int vkS, int vk1, String s2, String action) {
        JMenuItem menuItem = new JMenuItem(s, vkS);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(vk1, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(s2);
        menuItem.addActionListener(this);
        menuItem.setActionCommand(action);
        menuItem.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        category.add(menuItem);
    }

    // EFFECTS: creates menu bar categories with a given title, keystroke, and accessibility text
    private JMenu makeMenu(String s, int vkF, String s2) {
        JMenu menu = new JMenu(s);
        menu.setMnemonic(vkF);
        menu.getAccessibleContext().setAccessibleDescription(s2);
        menu.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        menuBar.add(menu);
        return menu;
    }

    // EFFECTS: creates the message frame
    private void initMessageFrame() {
        messageFrame = new JPanel();
        messageFrame.setSize(200, 200);
        messageFrame.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        messageFrame.setVisible(false);
    }

    // EFFECTS: creates the view frame
    private void initViewFrame() {
        JFrame viewFrame = new JFrame("Card");
        viewFrame.setSize(400, 600);
        viewFrame.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(false);
    }

    // EFFECTS: creates the panel for the status text
    private void initStatus() {
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("    Status: " + STATUS);
        statusLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 30));
        statusLabel.setForeground(Color.darkGray);
        add(statusPanel, BorderLayout.CENTER);
        statusPanel.add(statusLabel, BorderLayout.CENTER);
    }

    // EFFECTS: creates the panel for card editing buttons and creates buttons to add, edit, and view cards
    private void initEditPanel() {
        JPanel deckEditPanel = new JPanel();
        deckEditPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(deckEditPanel);

        makeButton("Add a Card", "addCard", deckEditPanel);
        makeButton("Edit a Card", "editCard", deckEditPanel);
        makeButton("View Cards", "viewCards", deckEditPanel);
    }

    // EFFECTS: creates the panel for persistence options, with buttons to save, clear and load decks
    private void initSaveLoadOptionsPanel() {
        JPanel saveLoadPanel = new JPanel();
        saveLoadPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(saveLoadPanel);

        makeButton("Save current Deck", "saveDeck", saveLoadPanel);
        makeButton("Clear current Deck", "clearDeck", saveLoadPanel);
        makeButton("Load saved Deck", "loadDeckP", saveLoadPanel);
        makeButton("Load a custom Deck", "loadDeckC", saveLoadPanel);
    }

    // EFFECTS: creates the panel for game play buttons with button to play game
    private void initGame() {
        JPanel gameplayPanel = new JPanel();
        gameplayPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(gameplayPanel);

        makeButton("Play Game", "playGame", gameplayPanel);
    }

    // EFFECTS: creates a button with a given title, action, and panel location
    private void makeButton(String s, String action, JPanel deckEditPanel) {
        JButton newButton = new JButton(s);
        newButton.setFont(new Font("Arial", Font.PLAIN, 30));
        newButton.setActionCommand(action);
        newButton.addActionListener(this); // Sets "this" object as an action listener for btn
        deckEditPanel.add(newButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes deck, scanner, and reader / writer
    private void initDeck() {
        deck = new CardDeck();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReaderDog = new JsonReader(JSON_STORE_DOG);
    }

    // EFFECTS: responds to button presses
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addCard")) {
            doAddCard();
        } else if (e.getActionCommand().equals("editCard")) {
            doEditCard();
        } else if (e.getActionCommand().equals("viewCards")) {
            doViewDeck();
        } else if (e.getActionCommand().equals("saveDeck")) {
            doSaveProgress();
        } else if (e.getActionCommand().equals("clearDeck")) {
            doClearDeck();
        } else if (e.getActionCommand().equals("loadDeckP")) {
            doLoadProgress();
        } else if (e.getActionCommand().equals("loadDeckC")) {
            doLoadCustom();
        } else if (e.getActionCommand().equals("playGame")) {
            doPlayGame();
        } else if (e.getActionCommand().equals("printLog")) {
            printLog(EventLog.getInstance());
        } else if (e.getActionCommand().equals("notAvailable")) {
            JOptionPane.showMessageDialog(messageFrame,
                    "OPTION NOT AVAILABLE YET AS ASSIGNMENT DOES NOT ALLOW FEATURE"); //TODO
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a card to the deck
    private void doAddCard() {
        statusLabel.setText("Adding Card...");

        Card anCard;

        String title = JOptionPane.showInputDialog(null,
                "Card title:",
                "New Card",
                QUESTION_MESSAGE);

        if (title.length() > 0) {
            if (deck.containsCardFromTitle(title)) {

                JOptionPane.showMessageDialog(messageFrame,
                        "There is already a card called " + "\"" + title + "\"\n");
            } else {
                anCard = new Card(title);
                deck.addCard(anCard);
                statusLabel.setText("Added card: \"" + anCard.getTitle() + ". Be sure to save your progress!");
            }
        } else {
            JOptionPane.showMessageDialog(messageFrame, "Cannot have a blank title.");
        }
        statusLabel.setText("Deck Builder Running...");
    }

    // MODIFIES: this
    // EFFECTS: displays edit options to user and follows user input to edit cards
    private void doEditCard() {
        statusLabel.setText("Opening Card Editor...");

        if (deck.getDeckSize() <= 1) {
            JOptionPane.showMessageDialog(messageFrame,
                    "Please add more than one card to view deck.");
        } else {
            doCardSelection();
            Object selection = JOptionPane.showInputDialog(null, "Choose a card to edit:",
                    "Card Viewer", QUESTION_MESSAGE, null,
                    listOfTitles,
                    listOfTitles[1]);

            String selectionTitle = selection.toString();

            displayCardDetails(selectionTitle);
            displayEditingWindow(selectionTitle);
        }
        statusLabel.setText("Deck Builder Running...");
    }

    // EFFECTS: displays editing GUI
    private void displayEditingWindow(String title) {
        Card c = deck.getCardFromTitle(title);
        new CardEditorGUI("Editing: " + c.getTitle(), c);
    }

    // EFFECTS: displays view options to user and follows user input to view cards
    private void doViewDeck() {
        statusLabel.setText("Opening Card Viewer...");

        if (deck.getDeckSize() <= 1) {
            JOptionPane.showMessageDialog(messageFrame,
                    "Please add more than one card to view deck.");
        } else {
            doCardSelection();
            Object selection = JOptionPane.showInputDialog(null, "Choose a card to view:",
                    "Card Viewer", QUESTION_MESSAGE, null,
                    listOfTitles,
                    listOfTitles[1]);

            String selectionTitle = selection.toString();

            displayCardDetails(selectionTitle);
        }
        statusLabel.setText("Deck Builder Running...");
    }

    // EFFECTS: displays card viewer GUI
    private void displayCardDetails(String title) {
        Card c = deck.getCardFromTitle(title);
        new CardViewerGUI(c.getTitle(), c);
    }

    // EFFECTS: creates list of card titles
    private void doCardSelection() {
        ArrayList<Card> cards = deck.getCardDetails();
        ArrayList<String> titles = new ArrayList<>();
        for (Card c : cards) {
            titles.add(c.getTitle());
        }
        listOfTitles = titles.toArray();
    }

    // EFFECTS: saves the deck builder progress to file
    private void doSaveProgress() {
        try {
            // updateDeckName(); // TODO: Make custom save option, possibly have a few save states (if allowed)
            jsonWriter.open();
            jsonWriter.writeCardDeck(deck);
            jsonWriter.close();
            statusLabel.setText("Saved '" + deck.getDeckName() + "' to \"" + JSON_STORE + "\"");
        } catch (FileNotFoundException e) {
            statusLabel.setText("Unable to write to file: \"" + JSON_STORE + "\"");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all cards from current deck
    private void doClearDeck() {
        deck.clearDeck();
        statusLabel.setText("All cards have been cleared from current deck.");
    }

    // MODIFIES: this
    // EFFECTS: loads the deck builder progress from file
    private void doLoadProgress() {
        try {
            deck = jsonReader.read();
            statusLabel.setText("Loaded '" + deck.getDeckName() + "' from \"" + JSON_STORE + "\"");
        } catch (IOException e) {
            statusLabel.setText("Unable to read from file: \"" + JSON_STORE + "\"");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the deck builder progress from file
    private void doLoadCustom() {
        try {
            deck = jsonReaderDog.read();
            statusLabel.setText("Loaded '" + deck.getDeckName() + "' from \"" + JSON_STORE_DOG + "\"");
        } catch (IOException e) {
            statusLabel.setText("Unable to read from file: \"" + JSON_STORE_DOG + "\"");
        }
    }

    // EFFECTS: displays game play options and launches game GUI
    private void doPlayGame() {
        statusLabel.setText("Launching game...");

        Object[] boardSizes = {grid9, grid25};

        Object[] freeSpaces = {freeTrue, freeFalse};

        Object size = JOptionPane.showInputDialog(null, "What size board do you want?",
                "Card Viewer", QUESTION_MESSAGE, null, boardSizes, boardSizes[1]);
        String selectedSize = size.toString();

        Object freeSpace = JOptionPane.showInputDialog(null, "Free spaces?",
                "Card Viewer", QUESTION_MESSAGE, null, freeSpaces, freeSpaces[1]);
        String selectedFree = freeSpace.toString();

        if (isEnoughCards(selectedSize, selectedFree)) {
            //JOptionPane.showMessageDialog(messageFrame,"Deck is big enough. Game not yet available though...");
            new BingoGameGUI(deck, selectedSize, selectedFree);
        } else {
            JOptionPane.showMessageDialog(messageFrame, "Please add more cards to use this deck size.");
        }
        statusLabel.setText("Deck Builder Running...");
    }

    // EFFECTS: checks if deck has enough cards for selected option
    private boolean isEnoughCards(String size, String freeSpace) {
        int reqSize = 0;

        if (Objects.equals(size, grid9)) {
            reqSize += 9;
        } else if (Objects.equals(size, grid25)) {
            reqSize += 25;
        }
        if (Objects.equals(freeSpace, freeTrue)) {
            reqSize -= 1;
        }
        return deck.getDeckSize() >= reqSize;
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }

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
}
