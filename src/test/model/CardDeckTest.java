package model;

// tests for Card Deck

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardDeckTest {

    Card testCard1;
    Card testCard2;
    Card testCard3;
    Card testCard4;

    CardDeck testDeck1;
    CardDeck testDeck2;
    CardDeck testDeck3;
    CardDeck testDeck4;

    EventLog el;

    @BeforeEach
    public void setup() {
        testCard1 = new Card("title1");
        testCard2 = new Card("title2");
        testCard3 = new Card("title3");
        testCard4 = new Card("title4");
        testDeck1 = new CardDeck();

        testDeck2 = new CardDeck();
        testDeck2.addCard(testCard1);
        testDeck2.addCard(testCard2);
        testDeck2.addCard(testCard3);

        testDeck3 = new CardDeck();
        testDeck3.addCard(testCard1);
        testDeck3.addCard(testCard2);

        testDeck4 = new CardDeck();
        testDeck4.addCard(testCard1);

        el = EventLog.getInstance();
    }

    @Test
    public void testAddCard() {
        assertTrue(testDeck1.isEmpty());
        testDeck1.addCard(testCard1);
        assertTrue(testDeck1.containsCard(testCard1));
        assertEquals(testDeck1.getNumberOfCards(), 1);
        testDeck1.addCard(testCard2);
        assertTrue(testDeck1.containsCard(testCard1));
        assertTrue(testDeck1.containsCard(testCard2));
        assertEquals(testDeck1.getNumberOfCards(), 2);
    }

    @Test
    public void testRemoveCard() {
        assertEquals(testDeck2.getNumberOfCards(), 3);

        testDeck2.deleteCard(testCard2);  // second element added
        assertEquals(testDeck2.getNumberOfCards(), 2);
        assertFalse(testDeck2.containsCard(testCard2));

        testDeck2.deleteCard(testCard1);  // first element added
        assertEquals(testDeck2.getNumberOfCards(), 1);
        assertFalse(testDeck2.containsCard(testCard1));

        testDeck2.deleteCard(testCard3);  // last element added
        assertEquals(testDeck2.getNumberOfCards(), 0);
        assertFalse(testDeck2.containsCard(testCard1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(testDeck2.getNumberOfCards(), 3);
        testDeck2.deleteCard(testCard1);
        testDeck2.deleteCard(testCard2);
        testDeck2.deleteCard(testCard3);
        assertTrue(testDeck2.isEmpty());
    }

    @Test
    public void testEnoughCards() {
        assertFalse(testDeck2.enoughCards(4));
        assertTrue(testDeck2.enoughCards(3));
        assertTrue(testDeck2.enoughCards(2));
    }

    @Test
    public void testGetContainsFirstAdded() {
        assertTrue(testDeck2.containsCardFromTitle("title1"));
        assertFalse(testDeck2.containsCardFromTitle("hello"));
    }

    @Test
    public void testGetContainsMiddleAdded() {
        assertTrue(testDeck2.containsCardFromTitle("title2"));
        assertFalse(testDeck2.containsCardFromTitle("hello"));
    }

    @Test
    public void testGetContainsCardLastAdded() {
        assertTrue(testDeck2.containsCardFromTitle("title3"));
        assertFalse(testDeck2.containsCardFromTitle("hello"));
    }

//    @Test
//    public void testGetCardTitles() {
//        assertEquals(testDeck2.getCardFromTitle(), "\"title1\", \"title2\", and \"title3\".");
//        assertEquals(testDeck3.getCardFromTitle(), "\"title1\", and \"title2\".");
//        assertEquals(testDeck4.getCardFromTitle(), "\"title1\".");
//    }

    @Test
    public void testRenameDescriptionFromTitle() {
        Card card1 = testDeck2.getCardFromTitle("title1");
        assertEquals(card1.getDescription(), "");
        assertEquals(testDeck2.getDescriptionFromTitle("title1"), "This card does not have a description.");
        testDeck2.renameDescriptionFromTitle("title1", "TESTS");
        assertEquals(card1.getDescription(), "TESTS");
        assertEquals(testDeck2.getDescriptionFromTitle("title1"), "\"title1\" currently has the following description:\n" +
                " \"TESTS\"");
    }

    @Test
    public void testEditPointsFromTitle() {
        Card card1 = testDeck2.getCardFromTitle("title1");
        assertEquals(card1.getPointsWorth(), 100);
        testDeck2.editPointsFromTitle("title1", 1);
        assertEquals(card1.getPointsWorth(), 50);
    }

    @Test
    public void testToggleFavouriteFromTitle() {
        testDeck2.toggleFavouriteFromTitle("title1");
        assertTrue(testDeck2.getFavouriteFromTitle("title1"));
    }

    @Test
    public void testGetFavouriteFromTitle() {
        assertFalse(testDeck2.getFavouriteFromTitle("title1"));
    }

    @Test
    public void testGetDeckSize() {
        assertEquals(3,testDeck2.getDeckSize());
        assertEquals(2,testDeck3.getDeckSize());
        assertEquals(1,testDeck4.getDeckSize());
    }

    @Test
    public void testGetCardDetails() {
        ArrayList<Card> otherDeck;
        otherDeck = testDeck2.getCardDetails();

        assertEquals(otherDeck.size(),testDeck2.getDeckSize());
        assertEquals(otherDeck.get(0),testDeck2.getCardIndex(0));
        assertEquals(otherDeck.get(1),testDeck2.getCardIndex(1));
        assertEquals(otherDeck.get(2),testDeck2.getCardIndex(2));
    }

    @Test
    public void testSetCards() {
        ArrayList<Card> otherDeck;
        otherDeck = testDeck2.getCardDetails();
        CardDeck thirdDeck = new CardDeck();
        thirdDeck.setCards(otherDeck);

        assertEquals(otherDeck.size(),thirdDeck.getDeckSize());
        assertEquals(otherDeck.get(0),thirdDeck.getCardIndex(0));
        assertEquals(otherDeck.get(1),thirdDeck.getCardIndex(1));
        assertEquals(otherDeck.get(2),thirdDeck.getCardIndex(2));
    }

    @Test
    public void testLogAddCard() {
        el.clear();
        Card c1 = new Card("Hello");

        testDeck1.addCard(c1);

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.get(1).toString().contains("Added Card 'Hello'"));
    }

    @Test
    public void testLogClearDeck() {
        el.clear();
        testDeck1.clearDeck();

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.get(1).toString().contains("Deck has been cleared"));
    }


}