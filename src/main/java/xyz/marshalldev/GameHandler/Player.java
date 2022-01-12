package xyz.marshalldev.GameHandler;

import lombok.Data;
import xyz.marshalldev.CardHandler.Hand;

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

    boolean isAllIn() {
        return this.balance == 0 && status != Action.FOLD;
    }

    boolean isActive() {
        return this.balance != 0 && status != Action.FOLD;
    }

    char getAction(int playerNumber) {
        System.out.println("Player " + (playerNumber+1) + ", these are your cards: " + hand.toString() + "\nWould you like to bet, check, or fold? ");
        String option = scan.nextLine();
        return Character.toLowerCase(option.charAt(0));
    }

    void updateBalance(int amount) {
        setBalance(getBalance() + amount);
    }
}
