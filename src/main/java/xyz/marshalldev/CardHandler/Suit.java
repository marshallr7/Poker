package xyz.marshalldev.CardHandler;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Suit {
    SPADE("♠", 0),
    HEART("♥", 1),
    DIAMOND("♦", 2),
    CLUB("♣", 3);

    public final String symbol;
    public final int rank;

    Suit(String symbol, int rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public static Stream<Suit> stream() {
        return Stream.of(Suit.values());
    }

}
