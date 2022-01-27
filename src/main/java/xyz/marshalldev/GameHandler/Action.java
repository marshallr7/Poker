package xyz.marshalldev.GameHandler;

import xyz.marshalldev.GUI;

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

    public static Action getAction(Player player) {
        int result = GUI.buttonTemplate(
                "What would you like to do?",
                "Cards: " + player.getHand().toString() + " - Balance: $" + player.getBalance(),
                betActions);

        return switch (result) {
            case 0 -> Action.CHECK;
            case 1 -> Action.BET;
            case 2 -> Action.FOLD;
            case 3 -> Action.CALL;
            case 4 -> Action.ALL_IN;
            default -> getAction(player);
        };

    }
}


