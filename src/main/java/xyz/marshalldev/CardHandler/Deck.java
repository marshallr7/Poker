package main.java.xyz.marshalldev.CardHandler;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
        generate();
        shuffle();
    }

    public void generate() {
        for (int i = 1; i < 14; i++) {
            int finalI = i;
            Suit.stream().forEach(suit -> deck.add(new Card(String.valueOf(finalI), suit)));
        }
    }

    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    @Override
    public String toString() {
        final String[] cards = {"Deck: "};
        deck.forEach(card -> cards[0] = cards[0] + card.toString() + " ");
        return cards[0];
    }
}
