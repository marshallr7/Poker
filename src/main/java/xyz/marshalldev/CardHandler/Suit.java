package xyz.marshalldev.CardHandler;

import java.util.stream.Stream;

public enum Suit {
    SPADE("♠"),
    HEART("♥"),
    DIAMOND("♦"),
    CLUB("♣");

    public final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public static Stream<Suit> stream() {
        return Stream.of(Suit.values());
    }

}
