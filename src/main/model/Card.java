package model;

// Represents a card item having a unique id, title,
//   description, use-count, points value, and favourite status;

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


import org.json.JSONObject;
import persistence.Writable;

public class Card implements Writable {
    private static final int HARD_WORTH = 200;
    private static final int MED_WORTH = 100;
    private static final int EASY_WORTH = 50;

    private static int nextCardId = 1;  // tracks id of the next card item created

    private int id;                     // card id
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

    /*
     * REQUIRES: newID has value >= 1
     * MODIFIES: this
     * EFFECTS: id is set to newId
     */
    public void setId(int newId) {
        id = newId;
    }

    // EFFECTS: returns the title
    public String getTitle() {
        return title;
    }

    /*
     * REQUIRES: newTitle has length > 0
     * MODIFIES: this
     * EFFECTS: title is set to newTitle
     */
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    // EFFECTS: returns the description
    public String getDescription() {
        return description;
    }

    /*
     * REQUIRES: newDescription has length > 0
     * MODIFIES: this
     * EFFECTS: title is set to newDescription
     */
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    // EFFECTS: returns the count of how many times the card has been used
    public int getUsedCount() {
        return usedCount;
    }

    /*
     * REQUIRES: newUsedCount has value >= 0
     * MODIFIES: this
     * EFFECTS: usedCount is set to newUsedCount
     */
    public void setUsedCount(int newUsedCount) {
        usedCount = newUsedCount;
    }

//    // EFFECTS: returns the count of how many times the card has been found
//    public int getFoundCount() {
//        return foundCount;
//    }

    /*
     * REQUIRES: newFoundCount has value >= 0
     * MODIFIES: this
     * EFFECTS: foundCount is set to newFoundCount
     */
//  public void setFoundCount(int newFoundCount) {
//      foundCount = newFoundCount;
//  }

    // EFFECTS: returns the number of points that a card is worth
    public int getPointsWorth() {
        return pointsWorth;
    }

    // EFFECTS: returns the difficulty level
    public String getDifficulty() {
        if (pointsWorth == MED_WORTH) {
            return "Medium";
        } else if (pointsWorth == HARD_WORTH) {
            return "Hard";
        } else {
            return "Easy";
        }
    }

    /*
     * REQUIRES: points must be an integer 1, 2, or 3
     * MODIFIES: this
     * EFFECTS: changes the pointsWorth value to EASY_WORTH,
       MED_WORTH, or HARD_WORTH for 1, 2, or 3 respectively
     */
    public void setPointsFromCode(int points) {
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

    /*
     * MODIFIES: this
     * EFFECTS: changes the pointsWorth value to points
     */
    public void setPointsDirectly(int points) {
        pointsWorth = points;
    }

    // EFFECTS: returns true if the card is marked as a favourite, else false
    public Boolean isFavourite() {
        return isFavourite;
    }

    /*
     * MODIFIES: this
     * EFFECTS: isFavourite is set to newIsFavourite
     */
    public void setIsFavourite(Boolean newIsFavourite) {
        isFavourite = newIsFavourite;
    }

    /*
     * REQUIRES: timesUsed >= 1
     * MODIFIES: this
     * EFFECTS: increments usedCount by timesUsed
     */
    public void useItem(int timesUsed) {
        usedCount += timesUsed;
    }

    // MODIFIES: this
    // EFFECTS: toggle the favourite status
    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }

    // MODIFIES: this
    // EFFECTS: sets all the fields in Card
    public void setCardFields(int newID, String newTitle, String newDescription,
                              int newUsedCount, int newPointsWorth, Boolean newIsFavourite) {
        id = newID;
        title = newTitle;
        description = newDescription;
        usedCount = newUsedCount;
        pointsWorth = newPointsWorth;
        isFavourite = newIsFavourite;
    }


    // EFFECTS: creates a JSON object of the card
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cardID", id);
        json.put("Title", title);
        json.put("Description", description);
        json.put("Use Count", usedCount);
  //    json.put("Found Count", foundCount);
        json.put("Points", pointsWorth);
        json.put("Favourite", isFavourite);        // toggle to select card as a favourite
        return json;
    }
}