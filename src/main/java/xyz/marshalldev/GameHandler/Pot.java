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
}
