package xyz.marshalldev.GameHandler;

import lombok.Getter;
import xyz.marshalldev.PlayerHandler.Player;

public class Pot {

    int value;
    @Getter
    int amountPerPlayer;

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
