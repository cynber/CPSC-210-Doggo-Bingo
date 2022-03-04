package model;

import java.util.List;

public class BoardSpace {

    private static int nextSpaceId = 1;  // tracks id of the next card item created

    int spaceId;
    Card card;
    boolean isFound;

    /*
    * REQUIRES: no other board spaces have the same spaceID
    * EFFECTS:  space id is a positive integer not yet assigned to any other board space;
                card is a Card;
     */
    public BoardSpace() {
        spaceId = nextSpaceId++;
        card = new Card("");
        isFound = false;
    }

    // EFFECTS: returns the spaceID
    public int getSpaceId() {
        return spaceId;
    }

    // EFFECTS: returns the Card
    public Card getCard() {
        return card;
    }

    // EFFECTS: returns the found status
    public boolean isFound() {
        return isFound;
    }

    // EFFECTS: sets the spaceID
    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    // EFFECTS: sets the Card
    public void setCard(Card card) {
        this.card = card;
    }

    // EFFECTS: sets the found status
    public void setFound(boolean found) {
        isFound = found;
    }
}
