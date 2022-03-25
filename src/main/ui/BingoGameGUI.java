package ui;

// GOT HELP FROM
// - https://stackhowto.com/how-to-change-jbutton-text-on-click/

import model.Card;
import model.CardDeck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

public class BingoGameGUI extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private final int boardColumns;
    private final int boardRows;

    private CardDeck deck;
    private ArrayList<Card> shuffledDeck;
    private String boardSize;
    private String boardFree;

    String grid9 = "3x3 (need >=9 cards)";
    String grid25 = "5x5 (need >=25 cards)";
    String freeTrue = "Add a FREE space";
    String freeFalse = "No FREE spaces";

    JPanel gamePanel;

    public BingoGameGUI(CardDeck deckImport, String selectedSize, String selectedFree) {
        super("Bingo Game");

        deck = deckImport;

        boardSize = selectedSize;
        boardFree = selectedFree;
        boardRows = getBoardRows();
        boardColumns = getBoardColumns();
        gamePanel = new JPanel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gamePanel.setLayout(new GridLayout(boardRows,boardColumns));

        shuffledDeck = makeShuffledDeck(deck);

        makeHalfDeckFirst();
        makeFreeSpace();
        makeHalfDeckSecond();

        add(gamePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    private void makeHalfDeckFirst() {
        for (int i = 0; i <= (boardColumns * boardRows) / 2 - 1; i++) {
            String title = shuffledDeck.get(i).getTitle();
            makeButton(title, "openCell", gamePanel);
        }
    }

    private void makeFreeSpace() {
        if (Objects.equals(boardFree, freeTrue)) {
            makeButton("FREE", "openCell", gamePanel);
        } else {
            String title = shuffledDeck.get((boardColumns * boardRows) / 2).getTitle();
            makeButton(title, "openCell", gamePanel);
        }
    }

    private void makeHalfDeckSecond() {
        for (int i = (boardColumns * boardRows) / 2 + 1; i <= (boardColumns * boardRows) - 1; i++) {
            String title = shuffledDeck.get(i).getTitle();
            makeButton(title, "openCell", gamePanel);
        }
    }

    private void makeButton(String s, String action, JPanel deckEditPanel) {
        JButton newButton = new JButton(s);
        newButton.setFont(new Font("Arial", Font.PLAIN, 20));
        newButton.setActionCommand(action);
        Color ogColor = newButton.getBackground();
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (newButton.getBackground() == Color.black) {
                    newButton.setBackground(ogColor);
                } else {
                    newButton.setBackground(Color.black);
                }

            }
        });
        gamePanel.add(newButton);
    }

    private ArrayList<Card> makeShuffledDeck(CardDeck unshuffledDeck) {
        ArrayList<Card> input = deck.getCardDetails();
        Collections.shuffle(input);
        ArrayList<Card> output = new ArrayList<Card>();
        for (int i = 1; i <= (boardColumns * boardRows); i++) {
            output.add(input.get(i));
        }
        return output;
    }

    private int getBoardRows() {
        int value = 0;
        if (Objects.equals(boardSize, grid9)) {
            value = 3;
        } else if (Objects.equals(boardSize, grid25)) {
            value = 5;
        }
        return value;
    }

    private int getBoardColumns() {
        int value = 0;
        if (Objects.equals(boardSize, grid9)) {
            value = 3;
        } else if (Objects.equals(boardSize, grid25)) {
            value = 5;
        }
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
