package xyz.marshalldev.CardHandler;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hand {

    private final List<Card> cards;

    public Hand() {
         cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        final String[] hand = {""};
        cards.forEach(card -> hand[0] = hand[0] + card.toString() + " ");
        return hand[0];
    }
}
