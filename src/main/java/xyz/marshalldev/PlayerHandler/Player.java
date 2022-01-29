package xyz.marshalldev.PlayerHandler;

import lombok.Data;
import xyz.marshalldev.CardHandler.Hand;
import xyz.marshalldev.GameHandler.Action;

import java.util.Scanner;

@Data
public class Player {

    private Scanner scan = new Scanner(System.in);

    // Base info
    private int balance;
    private final Hand hand = new Hand();

    // Current pot info
    private int currentBet;
    private Action status;

    public boolean isAllIn() {
        return this.balance == 0 && status != Action.FOLD;
    }

    public boolean isActive() {
        return this.balance != 0 && status != Action.FOLD;
    }

    public void updateBalance(int amount, boolean add) {
        if (add) {
            setBalance(getBalance() + amount);
        } else {
            setBalance(getBalance() - amount);
        }
    }
}
