package xyz.marshalldev.GameHandler;

import lombok.Data;
import xyz.marshalldev.CardHandler.Card;
import xyz.marshalldev.CardHandler.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Game {

    private Deck deck;
    private Pot pot;

    List<Card> communityCards;

    private final int MAX_PLAYERS = 9;
    private int numPlayers;
    private int activePlayers;
    private final Map<Integer, Player> players = new HashMap<>();

    private int startingBalance;

    private int bigBlindValue;
    private int littleBlindValue;
    private int bigBlindIndex;
    private int littleBlindIndex;
    private int buttonIndex;

    public Game(int numPlayers, int startingBalance, int buttonIndex, int littleBlindIndex, int bigBlindIndex) {
        this.numPlayers = numPlayers;
        this.activePlayers = numPlayers;
        this.startingBalance = startingBalance;
        this.buttonIndex = buttonIndex;
        this.littleBlindIndex = littleBlindIndex;
        this.bigBlindIndex = bigBlindIndex;

        deck = new Deck();
        createPlayers();
        addStartingBalances();
        dealToPlayers();
        betting();
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
        for (int i = 0; i < numPlayers; i++, j++) {
            if (j == numPlayers) {
                j = 0;
            }

            Player player = players.get(j);

            if (player.isActive()) {
                if (activePlayers > 1) {
                    Action action = Action.getAction(player);
                    betAction(player, action);
                } else if (activePlayers == 1) {
                    // player wins hand
                    // give pot
                    // increment blinds and button
                    // reset hands
                }
            }
        }
    }

    private void betAction(Player player, Action action) {
        switch (action) {
            case BET:
                System.out.println("How much would you like to bet?");
                int amount = player.getScan().nextInt();
                if (!(player.getBalance() >= amount)) {
//                    player.getAction(player);
                }
                if (amount == player.getBalance()) {
                    player.setBalance(0);
                }
                break;
            case FOLD:
                break;
            case CHECK:
                break;
        }
    }
}
