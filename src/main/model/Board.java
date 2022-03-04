package model;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Board {
    private static final int BOARD_P1 = 24;   // standard board (with FREE space)
    private static final int BOARD_P2 = 25;   // standard board (no FREE space)
    private static final int BOARD_P3 = 8;    // small board (with FREE space)
    private static final int BOARD_P4 = 9;    // small board (no FREE space)

    private static ArrayList<BoardSpace> gameBoard;




    // EFFECTS:  creates a new game board array
    public Board() {
        gameBoard = new ArrayList<>();
    }

    public static ArrayList<BoardSpace> makeBoard(String code, CardDeck deck) {
        int boardSize = 0;
        ArrayList<Card> cards;
        cards = deck.getCardDetails();
        gameBoard = new ArrayList<>();
        if (Objects.equals(code, "p1")) {
            boardSize = BOARD_P1;
        } else if (Objects.equals(code, "p2")) {
            boardSize = BOARD_P2;
        } else if (Objects.equals(code, "p3")) {
            boardSize = BOARD_P3;
        } else if (Objects.equals(code, "p4")) {
            boardSize = BOARD_P4;
        } else {
            System.out.println("Invalid."); // TODO make exception.
        }
        for (int i = 1; i <= boardSize; i++) {
            BoardSpace space;
            space = new BoardSpace();
            space.setSpaceId(i);
            space.setCard(cards.get(i - 1));
            gameBoard.add(space);
        }
        return gameBoard;
    }
}
