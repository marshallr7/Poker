package xyz.marshalldev.CardHandler;

import lombok.Getter;
import lombok.NonNull;
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

    public static boolean isRoyalFlush(List<Card> cards) {
        for (int i = cards.size()-1; i > 4; i--) {
            Suit suit = cards.get(i).getSuit();

            if (cards.get(i).getValue() == 14) {
                if (cards.get(i-1).getValue() == 13 && cards.get(i-1).getSuit() == suit) {
                    if (cards.get(i-2).getValue() == 12 && cards.get(i-2).getSuit() == suit) {
                        if (cards.get(i-3).getValue() == 11 && cards.get(i-3).getSuit() == suit) {
                            if (cards.get(i-4).getValue() == 10 && cards.get(i-4).getSuit() == suit) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

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

    public static boolean isFullHouse(List<Card> cards) {
        boolean set = false;
        boolean pair = false;

        for (int i = 0; i < cards.size()-1; i++) {
            int value = cards.get(i).getValue();

            // Trips
            if (value == cards.get(i+1).getValue() && value == cards.get(i+2).getValue()) {
                set = true;
            } else if (value == cards.get(i+1).getValue()) {
                pair = true;
            }
        }
        return set && pair;
    }

    public static boolean isFlush(List<Card> cards) {
        // spade, heart, diamond, club
        long spade = cards.stream().filter(card -> card.getSuit().equals(Suit.SPADE)).count();
        long heart = cards.stream().filter(card -> card.getSuit().equals(Suit.HEART)).count();
        long diamond = cards.stream().filter(card -> card.getSuit().equals(Suit.DIAMOND)).count();
        long club = cards.stream().filter(card -> card.getSuit().equals(Suit.CLUB)).count();

        return spade >= 5 || heart >= 5 || diamond >= 5 || club >= 5;
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

    public static boolean isTwoPair(List<Card> cards) {
        int pairValue = 0;
        for (int i = 0; i < cards.size() -1; i++) {
            int value = cards.get(i).getValue();

            if (value == cards.get(i+1).getValue() && pairValue == 0) {
                pairValue = value;
                i++;
            } else if (value == cards.get(i+1).getValue() && value != pairValue && pairValue != 0) {
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

    public static int getHighCard(@NonNull List<Card> cards) {
        return cards.get(-1).getValue();
    }
}
