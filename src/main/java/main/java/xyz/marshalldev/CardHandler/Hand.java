package main.java.xyz.marshalldev.CardHandler;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Hand {

    List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }
}
