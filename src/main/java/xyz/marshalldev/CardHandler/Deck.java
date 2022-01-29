package xyz.marshalldev.CardHandler;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
        generate();
        shuffle();
    }

    public void generate() {
        for (int i = 2; i < 15; i++) {
            int finalI = i;
            Suit.stream().forEach(suit -> deck.add(new Card(finalI, suit)));
        }
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    public Card deal() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public int size() {
        return deck.size();
    }

    @Override
    public String toString() {
        final String[] cards = {"Deck: "};
        deck.forEach(card -> cards[0] = cards[0] + card.toString() + " ");
        return cards[0];
    }
}
