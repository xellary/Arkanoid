import javax.swing.*;

import elements.*;

import static elements.Constants.*;

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
