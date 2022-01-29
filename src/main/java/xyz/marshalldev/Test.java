package xyz.marshalldev;

import xyz.marshalldev.CardHandler.Card;
import xyz.marshalldev.CardHandler.Rankings;
import xyz.marshalldev.CardHandler.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

    private static List<Card> sortByRank(List<Card> communityCards) {
        List<Card> communityCardsCopy = new ArrayList<>(communityCards);
        int min;

        for (int i = 0; i < communityCardsCopy.size(); i++) {
            min = i;
            for (int j = i+1; j < communityCardsCopy.size(); j++) {
                if (communityCardsCopy.get(j).getValue() < communityCardsCopy.get(min).getValue()) {
                    min = j;
                }
            }
            Collections.swap(communityCardsCopy, i, min);
        }
        return communityCardsCopy;
    }

    public static List<Card> sortBySuit(List<Card> communityCards) {
        List<Card> communityCardsCopy = new ArrayList<>(communityCards);
        int min;

        for (int i = 0; i < communityCards.size(); i++) {
            min = i;

            for (int j = i+1; j <communityCards.size(); j++) {
                if (communityCards.get(j).getSuit().getRank() < communityCards.get(min).getSuit().getRank()) {
                    min = j;
                }
            }
            Collections.swap(communityCardsCopy, i, min);
        }
        System.out.println(communityCardsCopy);
        return communityCardsCopy;
    }

    Test() {
        System.out.println("Royal Flush: " + Rankings.isRoyalFlush(royalFlushTest()));
        System.out.println("Straight Flush: " + Rankings.isStraightFlush(straightTest()));
        System.out.println("Quads: " + Rankings.isQuads(quadsTest()));
        System.out.println("Full House Test: " + Rankings.isFullHouse(fullHouseTest()));
        System.out.println("Flush: " + Rankings.isFlush(flushTest()));
        System.out.println("Straight: " + Rankings.isStraight(straightTest()));
        System.out.println("Trips: " + Rankings.isTrips(tripsTest()));
        System.out.println("Two Pair: " + Rankings.isTwoPair(twoPairTest()));
        System.out.println("Pair: " + Rankings.isPair(pairTest()));
        System.out.println("High Card: " + Rankings.getHighCard(highCardTest()));
    }

    private static List<Card> royalFlushTest() {
        List<Card> royalFlushTest = new ArrayList<>();

        royalFlushTest.add(new Card(1, Suit.CLUB));
        royalFlushTest.add(new Card(6, Suit.CLUB));
        royalFlushTest.add(new Card(10, Suit.CLUB));
        royalFlushTest.add(new Card(11, Suit.CLUB));
        royalFlushTest.add(new Card(12, Suit.CLUB));
        royalFlushTest.add(new Card(13, Suit.CLUB));
        royalFlushTest.add(new Card(14, Suit.CLUB));

        royalFlushTest = sortByRank(royalFlushTest);
        return royalFlushTest;
    }

    private static List<Card> straightTest() {
        List<Card> straightTest = new ArrayList<>();

        straightTest.add(new Card(1, Suit.CLUB));
        straightTest.add(new Card(6, Suit.CLUB));
        straightTest.add(new Card(10, Suit.CLUB));
        straightTest.add(new Card(11, Suit.CLUB));
        straightTest.add(new Card(12, Suit.CLUB));
        straightTest.add(new Card(13, Suit.CLUB));
        straightTest.add(new Card(14, Suit.CLUB));

        straightTest = sortByRank(straightTest);
        return straightTest;
    }

    private static List<Card> quadsTest() {
        List<Card> quads = new ArrayList<>();
        quads.add(new Card(14, Suit.HEART));
        quads.add(new Card(6, Suit.CLUB));
        quads.add(new Card(10, Suit.SPADE));
        quads.add(new Card(14, Suit.DIAMOND));
        quads.add(new Card(14, Suit.SPADE));
        quads.add(new Card(14, Suit.CLUB));
        quads.add(new Card(11, Suit.HEART));

        quads = sortByRank(quads);
        return quads;
    }

    private static List<Card> fullHouseTest() {
        List<Card> fullHouse = new ArrayList<>();

        fullHouse.add(new Card(6, Suit.SPADE));
        fullHouse.add(new Card(6, Suit.HEART));
        fullHouse.add(new Card(14, Suit.SPADE));
        fullHouse.add(new Card(10, Suit.DIAMOND));
        fullHouse.add(new Card(10, Suit.SPADE));
        fullHouse.add(new Card(10, Suit.SPADE));
        fullHouse.add(new Card(2, Suit.SPADE));

        sortByRank(fullHouse);
        return fullHouse;
    }

    private static List<Card> flushTest() {
        List<Card> flush = new ArrayList<>();

        flush.add(new Card(6, Suit.SPADE));
        flush.add(new Card(6, Suit.HEART));
        flush.add(new Card(14, Suit.SPADE));
        flush.add(new Card(10, Suit.DIAMOND));
        flush.add(new Card(10, Suit.SPADE));
        flush.add(new Card(11, Suit.SPADE));
        flush.add(new Card(2, Suit.SPADE));

        return flush;
    }

    private static List<Card> tripsTest() {
        List<Card> trips = new ArrayList<>();

        trips.add(new Card(5, Suit.HEART));
        trips.add(new Card(4, Suit.CLUB));
        trips.add(new Card(3, Suit.SPADE));
        trips.add(new Card(4, Suit.DIAMOND));
        trips.add(new Card(4, Suit.SPADE));
        trips.add(new Card(14, Suit.CLUB));
        trips.add(new Card(11, Suit.HEART));

        trips = sortByRank(trips);
        return trips;
    }

    public static List<Card> pairTest() {
        List<Card> pair = new ArrayList<>();

        pair.add(new Card(5, Suit.HEART));
        pair.add(new Card(6, Suit.CLUB));
        pair.add(new Card(3, Suit.SPADE));
        pair.add(new Card(4, Suit.DIAMOND));
        pair.add(new Card(4, Suit.SPADE));
        pair.add(new Card(14, Suit.CLUB));
        pair.add(new Card(11, Suit.HEART));

        pair = sortByRank(pair);
        return pair;
    }

    public static List<Card> twoPairTest() {
        List<Card> twoPair = new ArrayList<>();

        twoPair.add(new Card(5, Suit.HEART));
        twoPair.add(new Card(6, Suit.CLUB));
        twoPair.add(new Card(6, Suit.SPADE));
        twoPair.add(new Card(4, Suit.DIAMOND));
        twoPair.add(new Card(4, Suit.SPADE));
        twoPair.add(new Card(14, Suit.CLUB));
        twoPair.add(new Card(11, Suit.HEART));

        twoPair = sortByRank(twoPair);
        return twoPair;
    }

    public static List<Card> highCardTest() {
        List<Card> highCard = new ArrayList<>();

        highCard.add(new Card(5, Suit.HEART));
        highCard.add(new Card(6, Suit.CLUB));
        highCard.add(new Card(6, Suit.SPADE));
        highCard.add(new Card(4, Suit.DIAMOND));
        highCard.add(new Card(4, Suit.SPADE));
        highCard.add(new Card(14, Suit.CLUB));
        highCard.add(new Card(11, Suit.HEART));

        highCard = sortByRank(highCard);
        return highCard;
    }

}
