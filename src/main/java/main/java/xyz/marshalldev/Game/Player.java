package main.java.xyz.marshalldev.Game;

import main.java.xyz.marshalldev.CardHandler.Hand;

public class Player {
    private int balance;
    private Hand hand;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }
}
