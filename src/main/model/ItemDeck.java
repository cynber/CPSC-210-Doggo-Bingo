package model;

import java.util.HashSet;
import java.util.ArrayList;

public class ItemDeck {
    ArrayList<Item> itemList;

    private static final int REQ_TEST_BOARD = 5;
    private static final int REQ_SIZE_5_5_YES_FREE = 24;
    private static final int REQ_SIZE_5_5_NO_FREE = 25;

    //TODO: find a way to make one method to test any of the preset board sizes at top


    public ItemDeck() {
        itemList = new ArrayList<Item>();
    }

    //REQUIRES: item with matching title must not already be in list
    //MODIFIES: this
    //EFFECTS: add the item into the deck
    public void addItem(Item i) {
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
    public int getSize() {
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

    //EFFECTS: checks if the item is in the deck based on title input
    public boolean containsItemTitle(String title) {
        for (Item i: itemList) {
            if (i.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: if item with title exists, removes the item, else do nothing
    public void removeItemTitle(String title) {
        for (Item i: itemList) {
            if (i.getTitle().equals(title)) {
                removeItem(i);
            } else {
                ;
            }
        }
    }

    //REQUIRES: there are one or more items in the deck
    //EFFECTS: returns a string representation of item titles, separated by commas.
    public String getItemTitles() {
        String titles = "";
        int count = itemList.size();
        if (itemList.size() == 1) {
            for (Item i: itemList) {
                titles += "\"" + i.getTitle() + "\"" + ".";
            }
        } else {
            for (Item i : itemList) {
                if (count > 1) {
                    titles += "\"" + i.getTitle() + "\"" + ", ";
                    count -= 1;
                } else {
                    titles += "and \"" + i.getTitle() + "\"" + ".";
                }
            }
        }
        return titles;
    }

    //REQUIRES: item must exist in list
    //EFFECTS: returns the item with the matching title
    public Item getItemFromTitle(String title) {
        for (Item i : itemList) {
            if (i.getTitle().equals(title)) {
                return i;
            } else {
                return null;
            }
        }
        return null;
    }

    public void renameTitleFromTitle(String oldTitle, String newTitle) {
        Item i = getItemFromTitle(oldTitle);
        i.renameTitle(newTitle);
    }

    public void renameDescriptionFromTitle(String title, String description) {
        Item i = getItemFromTitle(title);
        i.renameDescription(description);
    }

    public void editPointsFromTitle(String title, int points) {
        Item i = getItemFromTitle(title);
        i.editPoints(points);
    }

    public void toggleFavouriteFromTitle(String title) {
        Item i = getItemFromTitle(title);
        i.toggleFavourite();
    }

    public Boolean getFavouriteFromTitle(String title) {
        Item i = getItemFromTitle(title);
        return i.isFavourite();
    }

    //TODO: find a way to measure how well built a deck is, possible metrics:
    //      - number of items
    //      - number of items with descriptions
    //      - number of items with images (if we add images)


}