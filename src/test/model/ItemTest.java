package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    Item testItem;

    @BeforeEach
    public void setup() {
        testItem = new Item("title1");
    }

    @Test
    public void testRenameItem() {
        assertEquals(testItem.getTitle(),"title1");
        testItem.renameTitle("New Title 1");
        assertEquals(testItem.getTitle(),"New Title 1");
    }

    @Test
    public void testRenameDescription() {
        assertEquals(testItem.getDescription(),"There is no description yet, but you may enter one.");
        testItem.renameDescription("New description.");
        assertEquals(testItem.getDescription(),"New description.");
    }

    @Test
    public void testUseItem() {
        assertEquals(testItem.getUsedCount(),0);
        testItem.useItem(1);
        assertEquals(testItem.getUsedCount(),1);
        testItem.useItem(3);
        assertEquals(testItem.getUsedCount(),4);
    }

    @Test
    public void testEditPoints() {
        assertEquals(testItem.getPointsWorth(),100);
        testItem.editPoints(1);
        assertEquals(testItem.getPointsWorth(),50);
        testItem.editPoints(3);
        assertEquals(testItem.getPointsWorth(),200);
        testItem.editPoints(2);
        assertEquals(testItem.getPointsWorth(),100);
    }

    @Test
    public void testToggleFavourite() {
        assertFalse(testItem.isFavourite());
        testItem.toggleFavourite();
        assertTrue(testItem.isFavourite());
        testItem.toggleFavourite();
        assertFalse(testItem.isFavourite());
    }

}