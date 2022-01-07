package main.java.xyz.marshalldev.CardHandler;

public class Card {

    String value;
    Suit suit;

    public Card(String value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        if (this.value.equals("1")) {
            return "A" + suit;
        }

        if (!isFaceCard(this.value)) {
            return this.value + suit;
        }

        if (this.value.equals("11")) {
            return "J" + suit;
        }

        if (this.value.equals("12")) {
            return "Q" + suit;
        }

        if (this.value.equals("13")) {
            return "K" + suit;
        }
        return null;
    }

    private boolean isFaceCard(String value) {
        return value.equals("11") || value.equals("12") || value.equals("13");
    }
}
