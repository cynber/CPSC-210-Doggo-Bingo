package ui;

// Represents a graphical user interface that users can use to play the game

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
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html#create

// This specific class was made with assistance from
// https://stackhowto.com/how-to-change-jbutton-text-on-click/    (for changing JButton on click)

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
    private final CardDeck deck;
    private final String boardSize;
    private final String boardFree;
    private ArrayList<Card> shuffledDeck;

    String grid9 = "3x3 (need >=9 cards)";
    String grid25 = "5x5 (need >=25 cards)";
    String freeTrue = "Add a FREE space";
    String freeFalse = "No FREE spaces";

    JPanel gamePanel;

    // EFFECTS: creates game board
    public BingoGameGUI(CardDeck deckImport, String selectedSize, String selectedFree) {
        super("Bingo Game");

        deck = deckImport;

        boardSize = selectedSize;
        boardFree = selectedFree;
        boardRows = getBoardRows();
        boardColumns = getBoardColumns();
        gamePanel = new JPanel();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gamePanel.setLayout(new GridLayout(boardRows, boardColumns));

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

    // EFFECTS: adds first half of game board buttons
    private void makeHalfDeckFirst() {
        for (int i = 0; i <= (boardColumns * boardRows) / 2 - 1; i++) {
            String title = shuffledDeck.get(i).getTitle();
            makeButton(title, gamePanel, false);
        }
    }

    // EFFECTS: adds middle game button (either FREE or next card)
    private void makeFreeSpace() {
        if (Objects.equals(boardFree, freeTrue)) {
            makeButton("FREE", gamePanel, true);
        } else {
            String title = shuffledDeck.get((boardColumns * boardRows) / 2).getTitle();
            makeButton(title, gamePanel, false);
        }
    }

    // EFFECTS: adds second half of game baord buttons
    private void makeHalfDeckSecond() {
        for (int i = (boardColumns * boardRows) / 2 + 1; i <= (boardColumns * boardRows) - 1; i++) {
            String title = shuffledDeck.get(i).getTitle();
            makeButton(title, gamePanel, false);
        }
    }

    // EFFECTS: makes a game board button with a name, panel, and mark status
    private void makeButton(String s, JPanel deckEditPanel, Boolean marked) {
        JButton newButton = new JButton(s);
        newButton.setFont(new Font("Arial", Font.PLAIN, 20));
        newButton.setActionCommand("openCell");
        Color ogColor = newButton.getBackground();
        if (marked) {
            newButton.setBackground(Color.black);
        }
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

    // EFFECTS: creates a shuffled set of cards for game board to use
    private ArrayList<Card> makeShuffledDeck(CardDeck inputDeck) {
        ArrayList<Card> input = inputDeck.getCardDetails();
        Collections.shuffle(input);
        ArrayList<Card> output = new ArrayList<>();
        for (int i = 1; i <= (boardColumns * boardRows); i++) {
            output.add(input.get(i));
        }
        return output;
    }

    // EFFECTS: calculates the number of game board rows
    private int getBoardRows() {
        int value = 0;
        if (Objects.equals(boardSize, grid9)) {
            value = 3;
        } else if (Objects.equals(boardSize, grid25)) {
            value = 5;
        }
        return value;
    }

    // EFFECTS: calculates the number of game board columns
    private int getBoardColumns() {
        int value = 0;
        if (Objects.equals(boardSize, grid9)) {
            value = 3;
        } else if (Objects.equals(boardSize, grid25)) {
            value = 5;
        }
        return value;
    }

    // EFFECTS: follows user actions
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
