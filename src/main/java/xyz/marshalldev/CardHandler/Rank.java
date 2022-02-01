package xyz.marshalldev.CardHandler;

public enum Rank {

    ROYAL_FLUSH(9),
    STRAIGHT_FLUSH(8),
    QUADS(7),
    FULL_HOUSE(6),
    FLUSH(5),
    STRAIGHT(4),
    TRIPS(3),
    TWO_PAIR(2),
    PAIR(1),
    HIGH_CARD(0);

    public final int weight;

    Rank(int weight) {
        this.weight = weight;
    }
}
