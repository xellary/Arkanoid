package arkanoid;

import arkanoid.game.elements.Arkanoid;

import javax.swing.*;

import static arkanoid.game.elements.Constants.RESOLUTION_HEIGHT;
import static arkanoid.game.elements.Constants.RESOLUTION_WIDTH;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Arkanoid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        Arkanoid game = new Arkanoid();
        frame.add(game);

        frame.setVisible(true);
    }
}
