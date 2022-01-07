package main.java.xyz.marshalldev.GameHandler;

import java.util.HashMap;
import java.util.Map;

public class Game {

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
}
