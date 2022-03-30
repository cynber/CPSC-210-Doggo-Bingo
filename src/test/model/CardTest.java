package model;

// tests for ItemDeck

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    Card testCard1;
    Card testCard2;
    CardDeck testDeck1;
    EventLog el;

    @BeforeEach
    public void setup() {
        testCard1 = new Card("title1");
        testCard1.setCardFields(1,"title1","",0,100,false);
        testCard2 = new Card("title2");
        testCard2.setCardFields(2,"title2","",0,100,false);

        testDeck1 = new CardDeck();
        testDeck1.addCard(testCard1);
        testDeck1.addCard(testCard2);

        el = EventLog.getInstance();
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
        testCard1.setPointsFromCode(1);
        assertEquals(testCard1.getPointsWorth(), 50);
        testCard1.setPointsFromCode(3);
        assertEquals(testCard1.getPointsWorth(), 200);
        testCard1.setPointsFromCode(2);
        assertEquals(testCard1.getPointsWorth(), 100);
    }

    @Test
    public void testGetDifficulty() {
        assertEquals(testCard1.getPointsWorth(), 100);
        assertEquals(testCard1.getDifficulty(), "Medium");
        testCard1.setPointsFromCode(1);
        assertEquals(testCard1.getPointsWorth(), 50);
        assertEquals(testCard1.getDifficulty(), "Easy");
        testCard1.setPointsFromCode(3);
        assertEquals(testCard1.getPointsWorth(), 200);
        assertEquals(testCard1.getDifficulty(), "Hard");
        testCard1.setPointsFromCode(2);
        assertEquals(testCard1.getPointsWorth(), 100);
        assertEquals(testCard1.getDifficulty(), "Medium");
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

    @Test
    public void testLogEditDescription() {
        el.clear();
        testCard1.setDescription("Hello");

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.get(1).toString().contains("Updated Description of 'title1' to 'Hello'"));
    }


    @Test
    public void testLogSetPointsFromCode() {
        el.clear();

        testCard1.setPointsFromCode(1);
        testCard1.setPointsFromCode(2);
        testCard1.setPointsFromCode(3);

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.get(1).toString().contains("Set difficulty of 'title1' to 'Easy'"));
        assertTrue(l.get(2).toString().contains("Set difficulty of 'title1' to 'Medium'"));
        assertTrue(l.get(3).toString().contains("Set difficulty of 'title1' to 'Hard'"));
    }

    @Test
    public void testLogSetFavourite() {
        el.clear();

        testCard1.setIsFavourite(true);

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.get(1).toString().contains("Set favourite status of 'title1' to 'true'"));
    }

    @Test
    public void testLogToggleFavourite() {
        el.clear();

        testCard1.setIsFavourite(true);
        testCard1.toggleFavourite();

        List<Event> l = new ArrayList<Event>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.get(2).toString().contains("Toggled favourite status of 'title1' to 'false'"));
    }
}