package ui;

// TOOK FROM:       C3-LectureLabSolution IntersectionGUI    & Sample code from project P3 page
// https://stackoverflow.com/questions/15746984/how-to-run-jframe-maximized-in-java
// https://docs.oracle.com/javase/tutorial/uiswing/components/html.html
//     - for how to do font sizes
// https://stackoverflow.com/questions/20462167/increasing-font-size-in-a-jbutton
//      - for how to do button size

import model.Card;
import model.CardDeck;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;


public class AppDeckBuilderGUI extends JFrame implements ActionListener {
    private static final String STATUS = "Deck Builder Running...";
    private static final String JSON_STORE = "./data/CardDeckProgress.json";
    private static final String JSON_STORE_DOG = "./data/DogCardDeck.json";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private CardDeck deck;
    private Object[] listOfTitles;

    private JPanel messageFrame;
    private JFrame viewFrame;

    private JLabel statusLabel;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonReader jsonReaderDog;


    // EFFECTS: runs the Card Deck Builder application
    public AppDeckBuilderGUI(String title) throws FileNotFoundException {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));

        initStatus();
        initEditPanel();
        initSaveLoadOptionsPanel();
        initGame();

        initDeck();

        initMessageFrame();
        initViewFrame();

        setLayout(new GridLayout(4,1));

        pack();
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setResizable(true);
    }




    private void initMessageFrame() {
        messageFrame = new JPanel();
        messageFrame.setSize(200, 200);
        messageFrame.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
        messageFrame.setVisible(false);
    }

    private void initViewFrame() {
        viewFrame = new JFrame("Card");
        viewFrame.setSize(400, 600);
        viewFrame.setFont(new Font("Sans-Serif", Font.PLAIN, 12));
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(false);
    }

    private void initStatus() {
        JPanel statusPanel = new JPanel();
        statusLabel = new JLabel("    Status: " + STATUS);
        statusLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 30));
        statusLabel.setForeground(Color.darkGray);
        add(statusPanel, BorderLayout.CENTER);
        statusPanel.add(statusLabel, BorderLayout.CENTER);
    }

    private void initEditPanel() {
        JPanel deckEditPanel = new JPanel();
        deckEditPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(deckEditPanel);

        makeButton("Add a Card", "addCard", deckEditPanel);
        makeButton("Edit a Card", "editCard", deckEditPanel);
        makeButton("View Cards", "viewCards", deckEditPanel);
    }

    private void initSaveLoadOptionsPanel() {
        JPanel saveLoadPanel = new JPanel();
        saveLoadPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(saveLoadPanel);

        makeButton("Save current Deck", "saveDeck", saveLoadPanel);
        makeButton("Clear current Deck", "clearDeck", saveLoadPanel);
        makeButton("Load saved Deck", "loadDeckP", saveLoadPanel);
        makeButton("Load a custom Deck", "loadDeckC", saveLoadPanel);
    }

    private void initGame() {
        JPanel gameplayPanel = new JPanel();
        gameplayPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(gameplayPanel);

        makeButton("Play Game", "playGame", gameplayPanel);
    }





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
            JOptionPane.showMessageDialog(messageFrame, "OPTION NOT AVAILABLE YET."); //TODO
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
    // EFFECTS: displays edit options to user and follows user input

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

    private void displayEditingWindow(String title) {
        Card c = deck.getCardFromTitle(title);
        new CardEditorGUI("Editing: " + c.getTitle(), c);
    }


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



    private void displayCardDetails(String title) {
        Card c = deck.getCardFromTitle(title);
        new CardViewerGUI(c.getTitle(), c);
    }

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
        deck = new CardDeck();
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
