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

    public void addBalance(int amount) {
        setBalance(getBalance() + amount);
    }

    public void removeBalance(int amount) {
        setBalance(getBalance() - amount);
    }

    public void addBetAmount(int amount) {
        setCurrentBet(getCurrentBet() + amount);
    }
}
