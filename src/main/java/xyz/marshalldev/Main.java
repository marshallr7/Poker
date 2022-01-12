package xyz.marshalldev;

import xyz.marshalldev.GameHandler.Game;


public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.setStartingBalance(1000);
        game.setNumPlayers(5);
        game.setButtonIndex(0);
        game.setBigBlindIndex(1);
        game.setLittleBlindIndex(2);
    }
}
