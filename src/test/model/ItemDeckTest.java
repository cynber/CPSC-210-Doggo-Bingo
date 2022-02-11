package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemDeckTest {

    Item testItem1;
    Item testItem2;
    Item testItem3;
    Item testItem4;

    ItemDeck testDeck1;
    ItemDeck testDeck2;

    @BeforeEach
    public void setup() {
        testItem1 = new Item("title1");
        testItem2 = new Item("title2");
        testItem3 = new Item("title3");
        testItem4 = new Item("title4");
        testDeck1 = new ItemDeck();

        testDeck2 = new ItemDeck();
        testDeck2.addItem(testItem1);
        testDeck2.addItem(testItem2);
        testDeck2.addItem(testItem3);
    }

    @Test
    public void testAddItem() {
        assertTrue(testDeck1.isEmpty());
        testDeck1.addItem(testItem1);
        assertTrue(testDeck1.containsItem(testItem1));
        assertEquals(testDeck1.getSize(),1);
        testDeck1.addItem(testItem2);
        assertTrue(testDeck1.containsItem(testItem1));
        assertTrue(testDeck1.containsItem(testItem2));
        assertEquals(testDeck1.getSize(),2);
    }

    @Test
    public void testRemoveItem() {
        assertEquals(testDeck2.getSize(),3);

        testDeck2.removeItem(testItem2);  // second element added
        assertEquals(testDeck2.getSize(),2);
        assertFalse(testDeck2.containsItem(testItem2));

        testDeck2.removeItem(testItem1);  // first element added
        assertEquals(testDeck2.getSize(),1);
        assertFalse(testDeck2.containsItem(testItem1));

        testDeck2.removeItem(testItem3);  // last element added
        assertEquals(testDeck2.getSize(),0);
        assertFalse(testDeck2.containsItem(testItem1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(testDeck2.getSize(),3);
        testDeck2.removeItem(testItem1);
        testDeck2.removeItem(testItem2);
        testDeck2.removeItem(testItem3);
        assertTrue(testDeck2.isEmpty());
    }

    @Test
    public void testEnoughItems() {
        assertFalse(testDeck2.enoughItems(4));
        assertTrue(testDeck2.enoughItems(3));
        assertTrue(testDeck2.enoughItems(2));
    }

    @Test
    public void testGetContainsFirstAdded() {
        assertTrue(testDeck2.containsItemTitle("title1"));
        assertFalse(testDeck2.containsItemTitle("hello"));
    }

    @Test
    public void testGetContainsMiddleAdded() {
        assertTrue(testDeck2.containsItemTitle("title2"));
        assertFalse(testDeck2.containsItemTitle("hello"));
    }

    @Test
    public void testGetContainsItemLastAdded() {
        assertTrue(testDeck2.containsItemTitle("title3"));
        assertFalse(testDeck2.containsItemTitle("hello"));
    }

    @Test
    public void testRemoveItemTitle() {
        assertTrue(testDeck2.containsItemTitle("title1"));
        assertTrue(testDeck2.containsItemTitle("title2"));
        assertTrue(testDeck2.containsItemTitle("title3"));
        testDeck2.removeItemTitle("title4");
        assertEquals(testDeck2.getSize(),3);
        testDeck2.removeItemTitle("title2");
        assertEquals(testDeck2.getSize(),2);
        assertFalse(testDeck2.containsItemTitle("title2"));
    }

    //TODO had error, can't replicate anymore


    @Test
    public void testGetItemTitles() {
        assertEquals(testDeck2.getItemTitles(),"\"title1\", \"title2\", and \"title3\".");
        testDeck2.removeItemTitle("title2");
        assertEquals(testDeck2.getItemTitles(),"\"title1\", and \"title3\".");
        testDeck2.removeItemTitle("title1");
        assertEquals(testDeck2.getItemTitles(),"\"title3\".");

    }



}