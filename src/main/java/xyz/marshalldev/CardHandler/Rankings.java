package xyz.marshalldev.CardHandler;

import lombok.Getter;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class Rankings {

    private final List<Card> sortedByRank;

    public Rankings(Player player, List<Card> communityCards) {
        List<Card> allCards = Stream.concat(player.getHand().getCards().stream(), communityCards.stream()).toList();
//        for (Card card : allCards) {
//        TODO: Make aces count high and low
//        }
        this.sortedByRank = sortByRank(allCards);
    }

//    public static ArrayList<Card> sortBySuit(ArrayList<Card> communityCards) {
//        int min;
//
//        for (int i = 0; i < communityCards.size(); i++) {
//            min = i;
//
//            for (int j = i+1; j <communityCards.size(); j++) {
//                if (communityCards.get(j).getSuit().getRank() < communityCards.get(min).getSuit().getRank()) {
//                }
//            }
//        }
//    }


    private List<Card> sortByRank(List<Card> communityCards) {
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

//    public static boolean isRoyalFlush(List<Card> cards) {
//    }

    // Straight Flush: 10Clubs, JackClubs, QueenClubs, KingClubs, AceClubs
    public static boolean isStraightFlush(List<Card> cards) {
        for (int i = 0; i < cards.size()-4; i++) {
            int value = cards.get(i).getValue();
            Suit suit = cards.get(i).getSuit();

            if (i != cards.size()-1 && cards.get(i+1).getValue() == value +1 && cards.get(i+1).getSuit() == suit) {
                if (i != cards.size()-2 && cards.get(i+2).getValue() == value +2 && cards.get(i+2).getSuit() == suit) {
                    if (i != cards.size()-3 && cards.get(i+3).getValue() == value +3 && cards.get(i+3).getSuit() == suit) {
                        if (i != cards.size()-4 && cards.get(i+4).getValue() == value +4 && cards.get(i+4).getSuit() == suit) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Straight: 10, Jack, Queen, King, Ace
    public static boolean isStraight(List<Card> cards) {
        for (int i = 0; i < cards.size()-4; i++) {
            int value = cards.get(i).getValue();

            if (cards.get(i+1).getValue() == value +1) {
                if (cards.get(i+2).getValue() == value +2) {
                    if (cards.get(i+3).getValue() == value +3) {
                        if (cards.get(i+4).getValue() == value +4) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Four of a kind: Ace, Ace, Ace, Ace
    public static boolean isQuads(List<Card> cards) {
        for (int i = 0; i < cards.size()-3; i++) {
            int value = cards.get(i).getValue();

            if (value == cards.get(i+1).getValue() && value == cards.get(i+2).getValue() && value == cards.get(i+3).getValue()) {
                return true;
            }
        }
        return false;
    }

    // Three of a kind: Ace, Ace, Ace
    public static boolean isTrips(List<Card> cards) {
        for (int i = 0; i < cards.size()-2; i++) {
            int value = cards.get(i).getValue();

            if (value == cards.get(i+1).getValue() && value == cards.get(i+2).getValue()) {
                return true;
            }
        }
        return false;
    }

    // Pair: Ace, Ace
    public static boolean isPair(List<Card> cards) {
        for (int i = 0; i < cards.size()-1; i++) {
            int value = cards.get(i).getValue();

            if (value == cards.get(i+1).getValue()) {
                return true;
            }
        }
        return false;
    }
}
