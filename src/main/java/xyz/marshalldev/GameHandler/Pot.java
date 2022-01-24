package xyz.marshalldev.GameHandler;

import lombok.Getter;
import lombok.Setter;

public class Pot {

    @Getter
    @Setter
    int value;
    int amountPerPlayer;

    void updatePot(int amount) {
        setValue(amount + getValue());
    }

    private void payPot(Player player) {
        player.updateBalance(value);
        setValue(0);
    }
}
