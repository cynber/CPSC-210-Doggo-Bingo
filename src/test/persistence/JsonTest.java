package persistence;

// Created with assistance from TellerApp and JsonSerializationDemo:
//   https://github.students.cs.ubc.ca/CPSC210/TellerApp
//   https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Card;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkCard(int cardId, String title, String description, int usedCount,
                             int pointsWorth, Boolean isFavourite, Card card) {
        assertEquals(cardId, card.getId());
        assertEquals(title, card.getTitle());
        assertEquals(description, card.getDescription());
        assertEquals(usedCount, card.getUsedCount());
        assertEquals(pointsWorth, card.getPointsWorth());
        assertEquals(isFavourite, card.isFavourite());
    }
}
