package model;

public class BoardSpace {

    private static int nextSpaceId = 1;  // tracks id of the next card item created

    int spaceId;
    Card card;
    boolean foundStatus;

    /*
    * REQUIRES: no other board spaces have the same spaceID
    * EFFECTS:  space id is a positive integer not yet assigned to any other board space;
                card is a Card;
     */
    public BoardSpace() {
        spaceId = nextSpaceId++;
        card = new Card("");
        foundStatus = false;
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
        return foundStatus;
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
    public void setFoundStatus(boolean foundStatus) {
        this.foundStatus = foundStatus;
    }

    // EFFECTS: sets all fields
    public void setAllFields(int newSpaceId, Card newCard, Boolean newIsFound) {
        spaceId = newSpaceId;
        card = newCard;
        foundStatus = newIsFound;
    }
}
