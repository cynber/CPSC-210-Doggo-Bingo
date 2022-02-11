package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item testItem1;
    Item testItem2;
    ItemDeck testDeck1;

    @BeforeEach
    public void setup() {
        testItem1 = new Item("title1");
        testItem2 = new Item("title2");

        testDeck1 = new ItemDeck();
        testDeck1.addItem(testItem1);
        testDeck1.addItem(testItem2);
    }

    @Test
    public void testRenameItem() {
        assertEquals(testItem1.getTitle(),"title1");
        testItem1.renameTitle("New Title 1");
        assertEquals(testItem1.getTitle(),"New Title 1");
    }

    @Test
    public void testRenameDescription() {
        assertEquals(testItem1.getDescription(),"There is no description yet, but you may enter one.");
        testItem1.renameDescription("New description.");
        assertEquals(testItem1.getDescription(),"New description.");
    }

    @Test
    public void testUseItem() {
        assertEquals(testItem1.getUsedCount(),0);
        testItem1.useItem(1);
        assertEquals(testItem1.getUsedCount(),1);
        testItem1.useItem(3);
        assertEquals(testItem1.getUsedCount(),4);
    }

    @Test
    public void testEditPoints() {
        assertEquals(testItem1.getPointsWorth(),100);
        testItem1.editPoints(1);
        assertEquals(testItem1.getPointsWorth(),50);
        testItem1.editPoints(3);
        assertEquals(testItem1.getPointsWorth(),200);
        testItem1.editPoints(2);
        assertEquals(testItem1.getPointsWorth(),100);
    }

    @Test
    public void testToggleFavourite() {
        assertFalse(testItem1.isFavourite());
        testItem1.toggleFavourite();
        assertTrue(testItem1.isFavourite());
        testItem1.toggleFavourite();
        assertFalse(testItem1.isFavourite());
    }

    @Test
    public void testGetId() {
        assertEquals(testItem1.getId(),1);
        assertEquals(testItem2.getId(),2);
    }

}