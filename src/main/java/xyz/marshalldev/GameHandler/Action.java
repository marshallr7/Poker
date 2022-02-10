package xyz.marshalldev.GameHandler;

import xyz.marshalldev.CardHandler.Card;
import xyz.marshalldev.GUI;
import xyz.marshalldev.PlayerHandler.Player;

import java.util.List;

public enum Action {
    BET,
    CHECK,
    FOLD,
    CALL,
    ALL_IN;

    static final String[] betActions = {
            "Check",
            "Bet",
            "Fold",
            "Call",
            "All In"
    };

    // TODO: modify GUI options based on user met requirements
    public static Action getAction(Player player, List<Card> communityCards) {
        Integer result;
        if (communityCards.size() == 0) {
            result = GUI.buttonTemplate(
                    "What would you like to do?",
                    "Cards: " + player.getHand().toString() + " - Balance: $" + player.getBalance(),
                    betActions);
        } else {
            result = GUI.buttonTemplate(
                    "What would you like to do?\nCommunity cards: " + communityCards,
                    "Cards: " + player.getHand().toString() + " - Balance: $" + player.getBalance(),
                    betActions);
        }

        return switch (result) {
            case 0 -> Action.CHECK;
            case 1 -> Action.BET;
            case 2 -> Action.FOLD;
            case 3 -> Action.CALL;
            case 4 -> Action.ALL_IN;
            default -> getAction(player, communityCards);
        };
    }

    public static void betAction(Player player, Action action, Pot pot, int activePlayers) {
        switch (action) {
            case BET -> {
                int amount = 0;

                // If player doesn't enter a proper integer
                try {
                    amount = Integer.parseInt(GUI.dialogTemplate("How much would you like to bet?", "Cards: " + player.getHand().toString() + " - Balance: $" + player.getBalance() + " - Current Pot Size: $" + pot.getValue()));
                } catch (NumberFormatException e) {
                    betAction(player, action, pot, activePlayers);
                }

                // If player attempts to bet more than they have
                if (!(player.getBalance() >= amount)) {
                    betAction(player, action, pot, activePlayers);
                    return;
                }
                // if player bets full balance
                if (amount == player.getBalance()) {
                    player.setBalance(0);
                    player.setStatus(Action.ALL_IN);
                }
                pot.updatePot(amount);
                pot.updateAmountPerPlayer(amount);
                player.addBet(amount);
            }
            case FOLD -> {
                player.setStatus(Action.FOLD);
                activePlayers -= 1;
            }
            case CALL -> {
                int amountToCall = pot.getAmountPerPlayer() - player.getCurrentBet();
                player.addBet(amountToCall);
            }
            case ALL_IN -> {
                player.addBet(player.getBalance());
                player.setStatus(Action.ALL_IN);
            }
            default -> {
            }
        }
    }
}


