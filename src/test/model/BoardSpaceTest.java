package model;

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardSpaceTest {

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
        assertEquals(1, bs1.getSpaceId());
        assertEquals(c1,bs1.getCard());
        assertTrue(bs1.isFound());
    }

    @Test
    public void testGettersAndSetters() {
        bs1.setSpaceId(5);
        bs1.setCard(c2);
        bs1.setFoundStatus(false);
        assertEquals(5,bs1.getSpaceId());
        assertEquals(c2,bs1.getCard());
        assertFalse(bs1.isFound());
    }
}