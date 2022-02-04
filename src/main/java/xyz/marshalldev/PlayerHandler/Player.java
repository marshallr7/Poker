package xyz.marshalldev.PlayerHandler;

import lombok.Data;
import xyz.marshalldev.CardHandler.Hand;
import xyz.marshalldev.CardHandler.Rank;
import xyz.marshalldev.GameHandler.Action;

import java.util.Scanner;

@Data
public class Player {

    private Scanner scan = new Scanner(System.in);

    private int balance;                    // Money balance
    private Hand hand = new Hand();         // Hand

    // Current pot info
    private int currentBet;                 // Amount of money in current pot
    private Action status;                  // If player is folded or all in

    private Rank rank;                      // Player showdown value

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

    public void addBet(int amount) {
        setCurrentBet(getCurrentBet() + amount);
        setBalance(getBalance() - amount);
    }

    @Override
    public String toString() {
        return "Balance: $" + balance + "\nHand: " + hand.toString() + "\nCurrent bet: $" + currentBet + "\nStatus: " + status + "\nRank: " + rank;
    }
}
