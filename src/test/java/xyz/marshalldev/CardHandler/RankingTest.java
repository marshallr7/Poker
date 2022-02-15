package xyz.marshalldev.CardHandler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class RankingTest {

    private final List<Card> testCards = new ArrayList<>();

    @Test
    void getHighCardAceTest() {

    }

    @Test
    void isRoyalFlush() {
        testCards.clear();
        testCards.add(new Card(1, Suit.CLUB));
        testCards.add(new Card(6, Suit.CLUB));
        testCards.add(new Card(10, Suit.CLUB));
        testCards.add(new Card(11, Suit.CLUB));
        testCards.add(new Card(12, Suit.CLUB));
        testCards.add(new Card(13, Suit.CLUB));
        testCards.add(new Card(14, Suit.CLUB));

        Ranking rank = new Ranking(testCards);
        Assertions.assertEquals(Rank.ROYAL_FLUSH, rank.getHandValue());
    }
}