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

    Test() {
        System.out.println("Straight Flush: " + Rankings.isStraightFlush(straightTest()));
        System.out.println("Quads: " + Rankings.isQuads(quadsTest()));
        System.out.println("Straight: " + Rankings.isStraight(straightTest()));
        System.out.println("Trips: " + Rankings.isTrips(tripsTest()));
        System.out.println("Two Pair: " + Rankings.isTwoPair(twoPairTest()));
        System.out.println("Pair: " + Rankings.isPair(pairTest()));
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
        System.out.println("Straight: " + straightTest.toString());
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
        System.out.println("Quads: " + quads.toString());
        return quads;
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
        System.out.println("Trips: " + trips.toString());
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
        System.out.println("Pair: " + pair.toString());
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
        System.out.println("Two Pair: " + twoPair.toString());
        return twoPair;
    }



}
