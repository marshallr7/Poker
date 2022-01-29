// TODO: MAKE ACE 14 INSTEAD OF 1

package xyz.marshalldev.CardHandler;

import lombok.Data;

@Data
public class Card {

    int value;
    Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        if (this.value == 14) {
            return "A" + suit.symbol;
        }

        if (!isFaceCard(this.value)) {
            return this.value + suit.symbol;
        }

        if (this.value == 11) {
            return "J" + suit.symbol;
        }

        if (this.value == 12) {
            return "Q" + suit.symbol;
        }

        if (this.value == 13) {
            return "K" + suit.symbol;
        }
        return null;
    }

    private boolean isFaceCard(int value) {
        return value == 11 || value == 12 || value == 13;
    }
}
