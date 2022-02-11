package model;

import java.util.HashSet;

public class ItemDeck {
    HashSet<Item> itemList;

    private static final int REQ_TEST_BOARD = 5;
    private static final int REQ_SIZE_5_5_YES_FREE = 24;
    private static final int REQ_SIZE_5_5_NO_FREE = 25;



    public ItemDeck() {
        itemList = new HashSet<Item>();
    }

    //MODIFIES: this
    //EFFECTS: inserts the item into the deck
    public void insertItem(Item i) {
        itemList.add(i);
    }

    //MODIFIES: this
    //EFFECTS: removes the item from the deck
    public void removeItem(Item i) {
        itemList.remove(i);
    }

    //EFFECTS: checks if the item is in the deck
    public boolean containsItem(Item i) {
        return itemList.contains(i);
    }

    //EFFECTS: checks the size of the deck (size of itemList)
    public int checkSize() {
        return itemList.size();
    }

    //EFFECTS: checks the deck is empty
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    //EFFECTS: checks if deck has enough items for board of inputted size
    public boolean enoughItems(int i) {
        return itemList.size() >= i;
    }

    //TODO: find a way to make one method to test any of the preset board sizes at top

    //TODO: find a way to measure how well built a deck is, possible metrics:
    //      - number of items
    //      - number of items with descriptions
    //      - number of items with images (if we add images)


}
