package model;

// tests for ItemDeck
// created with assistance from TellerApp:
//      https://github.students.cs.ubc.ca/CPSC210/TellerApp

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card testCard1;
    Card testCard2;
    CardDeck testDeck1;

    @BeforeEach
    public void setup() {
        testCard1 = new Card("title1");
        testCard2 = new Card("title2");

        testDeck1 = new CardDeck();
        testDeck1.addCard(testCard1);
        testDeck1.addCard(testCard2);
    }

    @Test
    public void testRenameCard() {
        assertEquals(testCard1.getTitle(), "title1");
        testCard1.setTitle("New Title 1");
        assertEquals(testCard1.getTitle(), "New Title 1");
    }

    @Test
    public void testRenameDescription() {
        assertEquals(testCard1.getDescription(), "");
        testCard1.setDescription("New description.");
        assertEquals(testCard1.getDescription(), "New description.");
    }

    @Test
    public void testUseCard() {
        assertEquals(testCard1.getUsedCount(), 0);
        testCard1.useItem(1);
        assertEquals(testCard1.getUsedCount(), 1);
        testCard1.useItem(3);
        assertEquals(testCard1.getUsedCount(), 4);
    }

    @Test
    public void testEditPoints() {
        assertEquals(testCard1.getPointsWorth(), 100);
        testCard1.setPoints(1);
        assertEquals(testCard1.getPointsWorth(), 50);
        testCard1.setPoints(3);
        assertEquals(testCard1.getPointsWorth(), 200);
        testCard1.setPoints(2);
        assertEquals(testCard1.getPointsWorth(), 100);
    }

    @Test
    public void testToggleFavourite() {
        assertFalse(testCard1.isFavourite());
        testCard1.toggleFavourite();
        assertTrue(testCard1.isFavourite());
        testCard1.toggleFavourite();
        assertFalse(testCard1.isFavourite());
    }

    @Test
    public void testGetId() {
        assertEquals(testCard1.getId(), 1);
        assertEquals(testCard2.getId(), 2);
    }

}