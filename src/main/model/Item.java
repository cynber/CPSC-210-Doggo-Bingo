package model;

// Represents an item having a unique id, title, description, and play-count.

import java.util.Objects;

public class Item {
    private static final String BLANK_PHRASE = "There is no description yet, but you may enter one.";
    private static final int STARTING_WORTH = 200;
    private static final int DIMINISH_BY = 10;
    private static final int MIN_WORTH = (STARTING_WORTH /2);

    private static int nextItemId = 1;  // tracks id of the next item created
    //TODO: Check how nextItemId works

    private final int id;                 // item id
    private String title;           // the title of the item card
    private String description;     // the item's description field value (optional)
    private int usedCount;          // counts # of times item was used, starts at 0
    private int foundCount;          // counts # of times item was played, starts at 0
    private int pointsWorth;
    private Boolean favourite;      // toggle to select item as a favourite

    /*
    * REQUIRES: no other items have the same title
    * EFFECTS:  item id is a positive integer not yet assigned to any other item;
                title of item is set to itemTitle;
                if description has a non-zero length, then description is set to
                  itemDescription, otherwise itemDescription is set to blankPhrase;
                playCount is set to 0;
                favourite is set to FALSE;
     */
    public Item(String itemTitle, String itemDescription) {
        id = nextItemId++;
        title = itemTitle;
        if (Objects.equals(itemDescription, "")) {

            description = BLANK_PHRASE;
        } else {
            description = itemDescription;
        }
        usedCount = 0;
        foundCount = 0;
        pointsWorth = STARTING_WORTH;
        favourite = false;

        //TODO: find out how to properly check a string's length
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public int getFoundCount() {
        return foundCount;
    }

    public int getPointsWorth() {
        return pointsWorth;
    }

    public Boolean getFavourite() {
        return favourite;
    }



    /*
     * REQUIRES: newTitle has length > 0
     * MODIFIES: this
     * EFFECTS: title is set to newTitle
     */
    public void renameTitle(String newTitle) {
        title = newTitle;
    }

    /*
     * REQUIRES: newDescription has length > 0
     * MODIFIES: this
     * EFFECTS: title is set to newDescription
     */
    public void renameDescription(String newDescription) {
        description = newDescription;
    }

    /*
     * REQUIRES: timesUsed >= 1
     * MODIFIES: this
     * EFFECTS: increments usedCount by timesUsed
     */
    public void useItem(int timesUsed) {
        usedCount += timesUsed;
    }

    /*
     * REQUIRES: timesFound >= 1
     * MODIFIES: this
     * EFFECTS: increments foundCount by timesFound;
                decreases pointsWorth by (diminishBy * timesFound),
                  doesn't decrease past minWorth
     */
    public void findItem(int timesFound) {
        foundCount += timesFound;
        if (pointsWorth >= (MIN_WORTH + (DIMINISH_BY * timesFound))) {
            pointsWorth -= (DIMINISH_BY * timesFound);
        } else {
            pointsWorth = MIN_WORTH;
        }
    }

    public void toggleFavourite() {
        favourite = !favourite;
    }

    //TODO: method to return a string representation of item?
}