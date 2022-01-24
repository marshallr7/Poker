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

    Action getAction(int playerNumber) {
        System.out.println("Player " + (playerNumber+1) + ", these are your cards: " + hand + "\nWould you like to check, bet, call, or fold? ");
        String option = scan.nextLine();

        switch (Character.toLowerCase(option.charAt(0))) {
            case 'b':
                return Action.BET;
            case 'c':
                return Action.CHECK;
            case 'f':
                return Action.FOLD;
            default:
                System.out.println("Player " + (playerNumber+1) + ", please enter a valid action. (Check, Bet, Call, Fold)");
                return getAction(playerNumber);
        }
    }

    void updateBalance(int amount) {
        setBalance(getBalance() + amount);
    }
}
