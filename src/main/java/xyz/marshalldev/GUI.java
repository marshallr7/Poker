package xyz.marshalldev;

import javax.swing.*;

public class GUI {

    public static int template(String message, String title, Object[] options) {
        return JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
    }
}
