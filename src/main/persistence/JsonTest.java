package persistence;

import model.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
