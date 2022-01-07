package main.java.xyz.marshalldev.GameHandler;

import lombok.Data;
import main.java.xyz.marshalldev.CardHandler.Hand;

@Data
public class Player {
    // Base info
    private int balance;
    private final Hand hand = new Hand();

    // Current pot info
    private int currentBet;
    private boolean folded;

    private boolean isAllIn() {
        return this.balance == 0 && !folded;
    }
}
