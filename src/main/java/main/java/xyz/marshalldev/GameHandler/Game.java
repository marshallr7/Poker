package main.java.xyz.marshalldev.GameHandler;

import lombok.Data;
import main.java.xyz.marshalldev.CardHandler.Card;
import main.java.xyz.marshalldev.CardHandler.Deck;

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
    private final Map<Integer, Player> players = new HashMap<>();

    private int startingBalance;

    private int bigBlindValue;
    private int littleBlindValue;
    private int bigBlindIndex;
    private int littleBlindIndex;
    private int buttonIndex;

    public Game() {
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
        System.out.println(deck);
        for (int i = 0; i < numPlayers*2; i++, j++) {
            if (j == numPlayers) {
                j = 0;
            }

            Player player = players.get(j);

            if (player.isActive()) {
                player.getHand().addCard(deck.deal());
            }
        }
        System.out.println(deck.toString());
        System.out.println(players);
    }

    private void betting() {
        int j = littleBlindIndex;
        for (int i = 0; i < numPlayers; i++, j++) {
            if (j == numPlayers) {
                j = 0;
            }

            Player player = players.get(j);

            if (player.isActive()) {
                player.getAction(j);

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

                }
                break;
            case FOLD:
                break;
            case CHECK:
                break;
        }
    }
}
