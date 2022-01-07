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

    List<Card> communityCards;

    private final int MAX_PLAYERS = 9;
    private int numPlayers;
    private final Map<Integer, Player> players = new HashMap<>();

    private int startingBalance;

    private int bigBlind;
    private int littleBlind;
    private int bigBlindIndex;
    private int littleBlindIndex;
    private int buttonIndex;

    private int potValue;

    public void start() {

    }
}
