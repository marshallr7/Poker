package xyz.marshalldev.GameHandler;

import lombok.Getter;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.HashMap;
import java.util.Map;

public class Pot {

    @Getter
    int value;                                      // Total pot value
    @Getter
    int amountPerPlayer;                            // Amount each player has bet

    Map<Player, Integer> bets = new HashMap<>();    // Player - Bet amount, used to manage payouts

    // Constructor to fill bets with players, 0

    void updatePot(int amount) {
        this.value = amount + this.value;
    }

    void updateAmountPerPlayer(int amount) {
        this.amountPerPlayer = this.amountPerPlayer + amount;
    }

    private void payPot(Player player) {
        player.addBalance(value);
        this.value = 0;
    }

    public void reset() {
        this.value = 0;
        this.amountPerPlayer = 0;
        bets.values().stream().forEach(i -> System.out.println(i));
    }
}
