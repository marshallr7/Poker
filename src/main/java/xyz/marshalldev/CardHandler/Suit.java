package main.java.xyz.marshalldev.CardHandler;

public enum Suit {
    SPADE("♠"),
    CLUB("♣"),
    HEART("♥"),
    DIAMOND("♦");

    public final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

}
