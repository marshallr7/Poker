package xyz.marshalldev.CardHandler;

import lombok.Data;

@Data
public class Card {

    String value;
    Suit suit;

    public Card(String value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        if (this.value.equals("1")) {
            return "A" + suit.symbol;
        }

        if (!isFaceCard(this.value)) {
            return this.value + suit.symbol;
        }

        if (this.value.equals("11")) {
            return "J" + suit.symbol;
        }

        if (this.value.equals("12")) {
            return "Q" + suit.symbol;
        }

        if (this.value.equals("13")) {
            return "K" + suit.symbol;
        }
        return null;
    }

    private boolean isFaceCard(String value) {
        return value.equals("11") || value.equals("12") || value.equals("13");
    }
}
