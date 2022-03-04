package model;

// Represents a deck of items that can be used to build a Bingo board
// created with assistance from TellerApp:
//      https://github.students.cs.ubc.ca/CPSC210/TellerApp

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

public class CardDeck implements Writable {
    String deckName;
    ArrayList<Card> cardList;

//    private static final int REQ_TEST_BOARD = 5;
//    private static final int REQ_SIZE_5_5_YES_FREE = 24;
//    private static final int REQ_SIZE_5_5_NO_FREE = 25;
//  TODO: find a way to make one method to test any of the preset board sizes at top


    public CardDeck() {
        cardList = new ArrayList<>();
        deckName = "Deck Progress";
    }

    //REQUIRES: card with matching title must not already be in list
    //MODIFIES: this
    //EFFECTS: add the card into the deck
    public void addCard(Card i) {
        cardList.add(i);
    }

    //MODIFIES: this
    //EFFECTS: removes the card from the deck
    public void deleteCard(Card i) {
        cardList.remove(i);
    }

    //EFFECTS: checks if the card is in the deck
    public boolean containsCard(Card i) {
        return cardList.contains(i);
    }

    //EFFECTS: checks the size of the deck (size of cardList)
    public int getNumberOfCards() {
        return cardList.size();
    }

    //EFFECTS: checks the deck is empty
    public boolean isEmpty() {
        return cardList.isEmpty();
    }

    //EFFECTS: checks if deck has enough cards for board of inputted size
    public boolean enoughCards(int i) {
        return cardList.size() >= i;
    }

    //EFFECTS: checks if the card is in the deck based on title input
    public boolean containsCardFromTitle(String title) {
        for (Card i : cardList) {
            if (i.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: there are 1+ items in the deck
    //EFFECTS: returns a string representation of item titles, separated by commas.
    public String getCardFromTitle() {
        String titles = "";
        int count = cardList.size();
        if (cardList.size() == 1) {
            for (Card i : cardList) {
                titles += "\"" + i.getTitle() + "\"" + ".";
            }
        } else {
            for (Card i : cardList) {
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

    //REQUIRES: card must exist in list
    //EFFECTS: returns the card with the matching title
    public Card getCardFromTitle(String title) {
        Card card = new Card("");
        for (Card i : cardList) {
            if (i.getTitle().equals(title)) {
                card = i;
            }
        }
        return card;
    }

    public ArrayList<Card> getCardDetails() {
        return new ArrayList<Card>(cardList);
    }

    //REQUIRES: card must exist in list
    //MODIFIES: card in this with matching title
    //EFFECTS: renames the card in this with matching title, with inputted description
    public void renameDescriptionFromTitle(String title, String description) {
        Card i = getCardFromTitle(title);
        i.setDescription(description);
    }

    //REQUIRES: card must exist in list
    //MODIFIES: card in this with matching title
    //EFFECTS: returns the description of the card in this with matching title
    public String getDescriptionFromTitle(String title) {
        Card i = getCardFromTitle(title);
        String description = i.getDescription();
        if (Objects.equals(description, "")) {
            return "This card does not have a description.";
        } else {
            return "\"" + title + "\" currently has the following description:\n \"" + description + "\"";
        }
    }

    //REQUIRES: card must exist in list
    //MODIFIES: card in this with matching title
    //EFFECTS: edits the pointsWorth value of the card in this with matching title, with inputted points
    public void editPointsFromTitle(String title, int points) {
        Card i = getCardFromTitle(title);
        i.setPointsFromCode(points);
    }

    //REQUIRES: card must exist in list
    //MODIFIES: card in this with matching title
    //EFFECTS: toggles the favourite status of the card in this with matching title
    public void toggleFavouriteFromTitle(String title) {
        Card i = getCardFromTitle(title);
        i.toggleFavourite();
    }

    //REQUIRES: card must exist in list
    //MODIFIES: card in this with matching title
    //EFFECTS: gets the favourite status of the card in this with matching title
    public Boolean getFavouriteFromTitle(String title) {
        Card i = getCardFromTitle(title);
        return i.isFavourite();
    }

    //EFFECTS: gets the deck name
    public String getDeckName() {
        return deckName;
    }

    //EFFECTS: sets the deck name
    public void setDeckName(String s) {
        deckName = s;
    }

    //EFFECTS: gets the cards in deck
    public ArrayList<Card> getCards() {
        return cardList;
    }

    //EFFECTS: sets the cards in deck
    public void setCards(ArrayList<Card> cl) {
        cardList = cl;
    }

    //EFFECTS: gets the size of the deck
    public int getDeckSize() {
        return cardList.size();
    }

    //EFFECTS: gets the card at index
    public Card getCardIndex(int i) {
        return cardList.get(i);
    }


    // EFFECTS: creates a Json Object for the CardDeck
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("deck name", deckName);
        json.put("cards", cardsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : cardList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }



    //TODO: find a way to measure how "well built" a deck is, possible metrics:
    //      - number of cards
    //      - number of cards with descriptions
    //      - number of cards with images (if we add images)
}
