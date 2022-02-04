package xyz.marshalldev.GameHandler;

import lombok.Data;
import xyz.marshalldev.CardHandler.Card;
import xyz.marshalldev.CardHandler.Deck;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.*;

@Data
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

        deck = new Deck();
        pot = new Pot();

        createPlayers();
        addStartingBalances();
        startGame();
    }

    private void createPlayers() {
        for (int i = 0; i < numPlayers; i++) {
            players.put(i, new Player());
        }
    }

    private void addStartingBalances() {
        for (Map.Entry<Integer, Player> element : players.entrySet()) {
            element.getValue().setBalance(startingBalance);
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
                    // player wins hand
                    // give pot
                    // increment blinds and button
                    // reset hands
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
            // find winner
            // handEnded()
        }
    }

    private void dealCommunityCards(int amount) {
        for (int i = 0; i < amount; i++) {
            this.communityCards.add(deck.deal());
        }
    }

    private void handEnded(Player player, Pot pot) {
        // Give money from pot to winner
        player.addBalance(pot.getAmountPerPlayer());
        // Increment blinds and button
        incrementPositions();
        // Clear CommunityCards
        this.communityCards.clear();
        // Reset cards of active players
        // Reset player currentBet
        // Reset player rank
    }

    // TODO add some sort of map to keep track of active players and their position to make it easier for incrementation
    private void incrementPositions() {

    }
}
