package xyz.marshalldev.GameHandler;

import lombok.Getter;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.HashMap;
import java.util.Map;

public class Pot {

    @Getter
    int value;                                          // Total pot value
    @Getter
    int amountPerPlayer;                                // Amount each player has bet

    Map<Integer, Integer> bets = new HashMap<>();       // Seat - Bet amount, used to manage payouts

    public Pot(Map<Integer, Player> players) {
        for (Player player : players.values()) {
            bets.putIfAbsent(player.getSeat(), 0);
        }
    }

    void updatePot(int amount) {
        this.value = amount + this.value;
    }

    void updateAmountPerPlayer(Player player, int amount) {
        this.amountPerPlayer = this.amountPerPlayer + amount;
        bets.replace(player.getSeat(), bets.get(player.getSeat()) + amount);
    }

    private void payPot(Player player) {
        player.addBalance(value);
        this.value = 0;
    }

    public void reset() {
        this.value = 0;
        this.amountPerPlayer = 0;
        // reset bets hashmap
        bets.replaceAll((k,v) -> v = 0);
    }
}
