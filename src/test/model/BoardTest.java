package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    BoardSpace bs1;
    BoardSpace bs2;
    Board bd;

    Card c1;
    Card c2;

    @BeforeEach
    public void setup() {
        c1 = new Card("title1");
        c1 = new Card("title2");
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
}
