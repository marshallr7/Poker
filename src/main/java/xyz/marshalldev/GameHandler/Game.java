package xyz.marshalldev.GameHandler;

import lombok.Data;
import xyz.marshalldev.CardHandler.Card;
import xyz.marshalldev.CardHandler.Deck;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.*;

public class Game {

    private Deck deck;                                              // Deck of cards
    private Pot pot;                                                // Pot for the game

    List<Card> communityCards = new ArrayList<>();                  // Flop, turn card, and river

    private final int MAX_PLAYERS = 9;                              // The max number of players allowed in a single game
    private int numPlayers;                                         // The total number of players
    private int activePlayers;                                      // The number of players that are active in the pot
    private Map<Integer, Player> players = new LinkedHashMap<>();   // Player information storage

    private int startingBalance;                                    // The starting balance for all players

    private int bigBlindAmount;                                     // Amount of money the big blind pays per hand
    private int littleBlindAmount;                                  // Amount of money the little blind pays per hand
    private int bigBlindIndex;                                      // The seat the big blind is located in
    private int littleBlindIndex;                                   // The seat the little blind is located in
    private int buttonIndex;                                        // The seat the button is located in

    private int lastBetIndex;                                       // The seat that the last player to bet is located in

    public Game(int numPlayers, int startingBalance, int buttonIndex, int littleBlindIndex, int bigBlindIndex) {
        this.numPlayers = numPlayers;
        this.activePlayers = numPlayers;
        this.startingBalance = startingBalance;
        this.buttonIndex = buttonIndex;
        this.littleBlindIndex = littleBlindIndex;
        this.bigBlindIndex = bigBlindIndex;
        this.lastBetIndex = littleBlindIndex;

        createPlayers();

        deck = new Deck();
        pot = new Pot(players);

        startGame();
    }

    private void createPlayers() {
        for (int i = 0; i < numPlayers; i++) {
            players.put(i, new Player(startingBalance, i));
        }
    }

    private void startGame() {
        while (players.size() > 1) {
            dealToPlayers();
            betting();
            dealCommunityCards(3);
            betting();
            dealCommunityCards(1);
            betting();
            dealCommunityCards(1);
            betting();
        }
    }

    private void dealToPlayers() {
        // start at little blind -> big blind -> regular players -> button
        int j = littleBlindIndex;
        for (int i = 0; i < numPlayers*2; i++, j++) {
            if (j == numPlayers) {
                j = 0;
            }

            Player player = players.get(j);

            if (player.isActive()) {
                player.getHand().addCard(deck.deal());
            }
        }
    }

    private void betting() {
        int j = littleBlindIndex;
        // This for loop isn't going to work, if a player raises, it needs to go around for everyone to call
        // Going to need to use lastBetIndex, which is updated on bet, and have it set i back to zero
        for (int i = 0; i < numPlayers; i++, j++) {
            Action action = null;
            if (j == numPlayers) {
                j = 0;
            }

            Player player = players.get(j);

            if (player.isActive()) {
                if (activePlayers > 1) {
                    if (!player.isAllIn()) {
                        action = Action.getAction(player, communityCards);
                        Action.betAction(player, action, pot, activePlayers);
                    }
                } else if (activePlayers == 1) {
                    handEnded(player, pot);
                    return;
                }
            }

            if (action == Action.BET) {
                // j is set to seat after player who bet
                j = i+1;
                // set i to zero so betting makes its way around again
                i = 0;
            }
        }

        if (communityCards.size() == 5) {
            List<Player> winners = new ArrayList<>();
            // find winner
            handEnded(players.get(1), pot);
        }
    }

    private void dealCommunityCards(int amount) {
        for (int i = 0; i < amount; i++) {
            this.communityCards.add(deck.deal());
        }
    }

    private void handEnded(Player winner , Pot pot) {
        // Give money from pot to winner
        winner.addBalance(pot.getAmountPerPlayer());
        // Increment blinds and button
        incrementPositions();
        // Clear CommunityCards
        this.communityCards.clear();
        // Reset players
        for (Player player : players.values()) {
            player.reset();
        }

        pot.reset();
    }

    private void incrementPositions() {
        int playersSize = players.size();
        // check if i+1 = players.size, if so, wrap
        if (bigBlindIndex+1 > playersSize) {
            /* utilize hashmap instead of directly assigning
            because we will modify the hashmap when a player is out of the pot */
            bigBlindIndex = players.get(0).getSeat();
        } else if (littleBlindIndex+1 > playersSize) {
            littleBlindIndex = players.get(0).getSeat();
        } else if (buttonIndex+1 > playersSize) {
            buttonIndex = players.get(0).getSeat();
        } else {
            bigBlindIndex = players.get(bigBlindIndex+1).getSeat();
            littleBlindIndex = players.get(littleBlindIndex+1).getSeat();
            buttonIndex = players.get(buttonIndex+1).getSeat();
        }
    }
}
