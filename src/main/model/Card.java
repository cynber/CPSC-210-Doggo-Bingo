package model;

// Represents a card item having a unique id, title,
//   description, use-count, points value, and favourite status;
// Created with assistance from TellerApp:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp

public class Card {
    private static final int HARD_WORTH = 200;
    private static final int MED_WORTH = 100;
    private static final int EASY_WORTH = 50;

    private static int nextCardId = 1;  // tracks id of the next card item created
    //TODO: Check how nextCardId works, possibly use length of deck

    private final int id;               // card id
    private String title;               // the title of the card
    private String description;         // the card's description field value (optional)
    private int usedCount;              // counts # of times card was used, starts at 0
//    private int foundCount;           // counts # of times card was found, starts at 0
    private int pointsWorth;            // points (score) that the card is worth
    private Boolean isFavourite;        // toggle to select card as a favourite

    /*
    * REQUIRES: no other cards have the same title
    * EFFECTS:  card id is a positive integer not yet assigned to any other card;
                title of card is set to cardTitle;
                description is set to the BLANK_PHRASE
                favourite is set to FALSE;
     */
    public Card(String cardTitle) {
        id = nextCardId++;
        title = cardTitle;
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

    // EFFECTS: returns the count of how many times the card has been used
    public int getUsedCount() {
        return usedCount;
    }

//    // EFFECTS: returns the count of how many times the card has been found
//    public int getFoundCount() {
//        return foundCount;
//    }

    // EFFECTS: returns the number of points that a card is worth
    public int getPointsWorth() {
        return pointsWorth;
    }

    // EFFECTS: returns true if the card is marked as a favourite, else false
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