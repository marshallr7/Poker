package xyz.marshalldev.CardHandler;

import lombok.Getter;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class Ranking {

    private static List<Card> sortedByRank = null;

    public Ranking(Player player, List<Card> communityCards) {
        List<Card> allCards = Stream.concat(player.getHand().getCards().stream(), communityCards.stream()).toList();
        for (Card card : allCards) {
            if (card.getValue() == 14) {
                allCards.add(new Card(1, card.getSuit()));
            }
        }
        sortedByRank = sortByRank(allCards);
    }

    public Rank getHandValue() {
        if (isRoyalFlush()) {
            return Rank.ROYAL_FLUSH;
        } else if (isStraightFlush()) {
            return Rank.STRAIGHT_FLUSH;
        } else if (isQuads()) {
            return Rank.QUADS;
        } else if (isFullHouse()) {
            return Rank.FULL_HOUSE;
        } else if (isFlush()) {
            return Rank.FLUSH;
        } else if (isStraight()) {
            return Rank.STRAIGHT;
        } else if (isTrips()) {
            return Rank.TRIPS;
        } else if (isTwoPair()) {
            return Rank.TWO_PAIR;
        } else if (isPair()) {
            return Rank.PAIR;
        } else {
            return Rank.HIGH_CARD;
        }
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

    // Ace King Queen Jack 10 Suited
    private boolean isRoyalFlush() {
        for (int i = sortedByRank.size()-1; i > 4; i--) {
            Suit suit = sortedByRank.get(i).getSuit();

            if (sortedByRank.get(i).getValue() == 14) {
                if (sortedByRank.get(i-1).getValue() == 13 && sortedByRank.get(i-1).getSuit() == suit) {
                    if (sortedByRank.get(i-2).getValue() == 12 && sortedByRank.get(i-2).getSuit() == suit) {
                        if (sortedByRank.get(i-3).getValue() == 11 && sortedByRank.get(i-3).getSuit() == suit) {
                            if (sortedByRank.get(i-4).getValue() == 10 && sortedByRank.get(i-4).getSuit() == suit) {
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
    private boolean isStraightFlush() {
        for (int i = 0; i < sortedByRank.size()-4; i++) {
            int value = sortedByRank.get(i).getValue();
            Suit suit = sortedByRank.get(i).getSuit();

            if (i != sortedByRank.size()-1 && sortedByRank.get(i+1).getValue() == value +1 && sortedByRank.get(i+1).getSuit() == suit) {
                if (i != sortedByRank.size()-2 && sortedByRank.get(i+2).getValue() == value +2 && sortedByRank.get(i+2).getSuit() == suit) {
                    if (i != sortedByRank.size()-3 && sortedByRank.get(i+3).getValue() == value +3 && sortedByRank.get(i+3).getSuit() == suit) {
                        if (i != sortedByRank.size()-4 && sortedByRank.get(i+4).getValue() == value +4 && sortedByRank.get(i+4).getSuit() == suit) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Ace Ace Ace 10 10
    private boolean isFullHouse() {
        boolean set = false;
        boolean pair = false;

        for (int i = 0; i < sortedByRank.size()-1; i++) {
            int value = sortedByRank.get(i).getValue();

            // Trips
            if (value == sortedByRank.get(i+1).getValue() && value == sortedByRank.get(i+2).getValue()) {
                set = true;
            } else if (value == sortedByRank.get(i+1).getValue()) {
                pair = true;
            }
        }
        return set && pair;
    }

    // 5 cards of the same suit
    private boolean isFlush() {
        long spade = sortedByRank.stream().filter(card -> card.getSuit().equals(Suit.SPADE)).count();
        long heart = sortedByRank.stream().filter(card -> card.getSuit().equals(Suit.HEART)).count();
        long diamond = sortedByRank.stream().filter(card -> card.getSuit().equals(Suit.DIAMOND)).count();
        long club = sortedByRank.stream().filter(card -> card.getSuit().equals(Suit.CLUB)).count();

        return spade >= 5 || heart >= 5 || diamond >= 5 || club >= 5;
    }

    // Straight: 10, Jack, Queen, King, Ace
    private boolean isStraight() {
        for (int i = 0; i < Ranking.sortedByRank.size()-4; i++) {
            int value = Ranking.sortedByRank.get(i).getValue();

            if (Ranking.sortedByRank.get(i+1).getValue() == value +1) {
                if (Ranking.sortedByRank.get(i+2).getValue() == value +2) {
                    if (Ranking.sortedByRank.get(i+3).getValue() == value +3) {
                        if (Ranking.sortedByRank.get(i+4).getValue() == value +4) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Four of a kind: Ace, Ace, Ace, Ace
    private boolean isQuads() {
        for (int i = 0; i < sortedByRank.size()-3; i++) {
            int value = sortedByRank.get(i).getValue();

            if (value == sortedByRank.get(i+1).getValue() && value == sortedByRank.get(i+2).getValue() && value == sortedByRank.get(i+3).getValue()) {
                return true;
            }
        }
        return false;
    }

    // Three of a kind: Ace, Ace, Ace
    private boolean isTrips() {
        for (int i = 0; i < sortedByRank.size()-2; i++) {
            int value = sortedByRank.get(i).getValue();

            if (value == sortedByRank.get(i+1).getValue() && value == sortedByRank.get(i+2).getValue()) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoPair() {
        int pairValue = 0;
        for (int i = 0; i < sortedByRank.size()-1; i++) {
            int value = sortedByRank.get(i).getValue();

            if (value == sortedByRank.get(i+1).getValue() && pairValue == 0) {
                pairValue = value;
                i++;
            } else if (value == sortedByRank.get(i+1).getValue() && value != pairValue && pairValue != 0) {
                return true;
            }
        }
        return false;
    }

    // Pair: Ace, Ace
    private boolean isPair() {
        for (int i = 0; i < sortedByRank.size()-1; i++) {
            int value = sortedByRank.get(i).getValue();

            if (value == sortedByRank.get(i+1).getValue()) {
                return true;
            }
        }
        return false;
    }

    // Highest card in hand
    public int getHighCard() {
        return sortedByRank.get(sortedByRank.size()-1).getValue();
    }
}
