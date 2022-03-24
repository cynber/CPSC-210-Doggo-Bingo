package model;

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    Card c1;
    Card c2;
    Card c3;
    Card c4;
    Card c5;
    Card c6;
    Card c7;
    Card c8;
    Card c9;

    CardDeck cd;

    BoardSpace bs1;
    BoardSpace bs2;

    Board bd;

    @BeforeEach
    public void setup() {
        c1 = new Card("title1");
        c2 = new Card("title2");
        c3 = new Card("title3");
        c4 = new Card("title4");
        c5 = new Card("title5");
        c6 = new Card("title6");
        c7 = new Card("title7");
        c8 = new Card("title8");
        c9 = new Card("title9");

        cd = new CardDeck();
        cd.addCard(c1);
        cd.addCard(c2);
        cd.addCard(c3);
        cd.addCard(c4);
        cd.addCard(c5);
        cd.addCard(c6);
        cd.addCard(c7);
        cd.addCard(c8);
        cd.addCard(c9);

        bs1 = new BoardSpace();
        bs1.setAllFields(1, c1, true);
        bs2 = new BoardSpace();
        bs2.setAllFields(2, c2, false);

        bd = new Board();
        bd.addBoardSpace(bs1);
        bd.addBoardSpace(bs2);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, bd.getNumberOfSpaces());
        assertEquals(bs1,bd.getIndexBoardSpace(0));
        assertEquals(bs2,bd.getIndexBoardSpace(1));
    }

    @Test
    public void testMakeBoardGoodCodeP4() {
        ArrayList<BoardSpace> bdTest = Board.makeBoard("p4",cd);
        assertEquals(9,bdTest.size());

        checkCardandSpaceId(bdTest, c1, 0, 1);
        checkCardandSpaceId(bdTest, c2, 1, 2);
        checkCardandSpaceId(bdTest, c3, 2, 3);
        checkCardandSpaceId(bdTest, c4, 3, 4);
        checkCardandSpaceId(bdTest, c5, 4, 5);
        checkCardandSpaceId(bdTest, c6, 5, 6);
        checkCardandSpaceId(bdTest, c7, 6, 7);
        checkCardandSpaceId(bdTest, c8, 7, 8);
        checkCardandSpaceId(bdTest, c9, 8, 9);
    }

//    @Test
//    public void testMakeBoardBadCode() {
//        assertEquals("Invalid.",Board.makeBoard("junk",cd));
//    }

    private void checkCardandSpaceId(ArrayList<BoardSpace> bdTest, Card c1, int i, int i2) {
        assertEquals(c1, bdTest.get(i).getCard());
        assertEquals(i2, bdTest.get(i).getSpaceId());
    }
}
