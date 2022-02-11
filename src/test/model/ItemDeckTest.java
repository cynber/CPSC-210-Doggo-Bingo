package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemDeckTest {

    Item testItem1;
    Item testItem2;
    Item testItem3;
    Item testItem4;

    ItemDeck testItemList1;
    ItemDeck testItemList2;

    @BeforeEach
    public void setup() {
        testItem1 = new Item("title1");
        testItem2 = new Item("title2");
        testItem3 = new Item("title3");
        testItem4 = new Item("title4");
        testItemList1 = new ItemDeck();

        testItemList2 = new ItemDeck();
        testItemList1.insertItem(testItem1);
        testItemList1.insertItem(testItem2);
        testItemList1.insertItem(testItem3);
    }

    @Test
    public void testInsertItem() {
        assertTrue(testItemList1.isEmpty());
        testItemList1.insertItem(testItem1);
        assertTrue(testItemList1.containsItem(testItem1));
        assertEquals(testItemList1.checkSize(),1);
        testItemList1.insertItem(testItem2);
        assertTrue(testItemList1.containsItem(testItem1));
        assertTrue(testItemList1.containsItem(testItem2));
        assertEquals(testItemList1.checkSize(),2);
    }

    @Test
    public void testRemoveItem() {
        assertEquals(testItemList2.checkSize(),3);

        testItemList2.removeItem(testItem2);  // second element added
        assertEquals(testItemList2.checkSize(),2);
        assertFalse(testItemList2.containsItem(testItem2));

        testItemList2.removeItem(testItem1);  // first element added
        assertEquals(testItemList2.checkSize(),1);
        assertFalse(testItemList2.containsItem(testItem1));

        testItemList2.removeItem(testItem3);  // last element added
        assertEquals(testItemList2.checkSize(),1);
        assertFalse(testItemList2.containsItem(testItem1));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(testItemList2.checkSize(),3);
        testItemList2.removeItem(testItem1);
        testItemList2.removeItem(testItem2);
        testItemList2.removeItem(testItem3);
        assertTrue(testItemList2.isEmpty());
    }


//
//    //EFFECTS: checks if enough items for inputted size
//    public boolean enoughItems(int i) {
//        return itemList.size() >= i;
//    }



}