package model;

// Represents an item having a unique id, title, description, and play-count.
// created with assistance from TellerApp:
//      https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class Item {
    private static final int HARD_WORTH = 200;
    private static final int MED_WORTH = 100;
    private static final int EASY_WORTH = 50;

    private static int nextItemId = 1;  // tracks id of the next item created
    //TODO: Check how nextItemId works

    private final int id;                 // item id
    private String title;           // the title of the item card
    private String description;     // the item's description field value (optional)
    private int usedCount;          // counts # of times item was used, starts at 0
//    private int foundCount;          // counts # of times item was played, starts at 0
    private int pointsWorth;
    private Boolean isFavourite;      // toggle to select item as a favourite

    /*
    * REQUIRES: no other items have the same title
    * EFFECTS:  item id is a positive integer not yet assigned to any other item;
                title of item is set to itemTitle;
                description is set to the BLANK_PHRASE
                favourite is set to FALSE;
     */
    public Item(String itemTitle) {
        id = nextItemId++;
        title = itemTitle;
        description = "";
        usedCount = 0;
//        foundCount = 0;
        pointsWorth = MED_WORTH;
        isFavourite = false;
    }

    // EFFECTS: returns the id
    public int getId() {
        return id;
    }

    // EFFECTS: returns the title
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns the description
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns the count of how many times the item has been used
    public int getUsedCount() {
        return usedCount;
    }

//    // EFFECTS: returns the count of how many times the item has been found
//    public int getFoundCount() {
//        return foundCount;
//    }

    // EFFECTS: returns the number of points that an item is worth
    public int getPointsWorth() {
        return pointsWorth;
    }

    // EFFECTS: returns true if the item is marked as a favourite, else false
    public Boolean isFavourite() {
        return isFavourite;
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
     * REQUIRES: points must be an integer 1, 2, or 3
     * MODIFIES: this
     * EFFECTS: changes the pointsWorth value to EASY_WORTH,
       MED_WORTH, or HARD_WORTH for 1, 2, or 3 respectively
     */
    public void editPoints(int points) {
        if (points == 1) {
            pointsWorth = EASY_WORTH;
        } else {
            if (points == 2) {
                pointsWorth = MED_WORTH;
            } else {
                pointsWorth = HARD_WORTH;
            }
        }
    }

    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }
}