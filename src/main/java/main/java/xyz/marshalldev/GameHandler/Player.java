package main.java.xyz.marshalldev.GameHandler;

import lombok.Data;
import main.java.xyz.marshalldev.CardHandler.Hand;

@Data
public class Player {
    private int balance;
    private Hand hand;

}
