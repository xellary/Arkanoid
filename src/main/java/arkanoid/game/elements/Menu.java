package arkanoid.game.elements;

import arkanoid.game.fonts.MenuFont;

import java.awt.*;

import static arkanoid.game.elements.Constants.*;

public class Menu{

    private Font menuFont = new MenuFont().getFont();

    public void draw(Graphics2D g) {
        drawBackground(g);
        PLAY_BUTTON.drawButton(g, 27);
        PLAY_BUTTON.drawButtonText(g, "PLAY", 20, 27);
        QUIT_BUTTON.drawButton(g, 27);
        QUIT_BUTTON.drawButtonText(g, "QUIT", 22, 27);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
        g.drawString("ARKANOID", RESOLUTION_WIDTH / 2 - 102, RESOLUTION_HEIGHT - 500);
    }

    public void drawLevelSelection(Graphics2D g) {
        drawBackground(g);
        LEVEL_ONE_BUTTON.drawButton(g, 23);
        LEVEL_ONE_BUTTON.drawButtonText(g, "LEVEL 1", 14, 26);
        LEVEL_TWO_BUTTON.drawButton(g, 23);
        LEVEL_TWO_BUTTON.drawButtonText(g, "LEVEL 2", 14, 26);
        LEVEL_THREE_BUTTON.drawButton(g, 23);
        LEVEL_THREE_BUTTON.drawButtonText(g, "LEVEL 3", 14, 26);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 34));
        g.drawString("CHOOSE  THE  LEVEL", RESOLUTION_WIDTH / 2 - 155, RESOLUTION_HEIGHT - 500);
    }

    private void drawBackground(Graphics2D g) {
        Rectangle gradient = new Rectangle(0, 0, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        GradientPaint gp = new GradientPaint(RESOLUTION_WIDTH, RESOLUTION_HEIGHT, MY_PURPLE,
                RESOLUTION_WIDTH, 150, Color.black, false);
        g.setPaint(gp);
        g.fill(gradient);
        g.setColor(MY_GREY);
        g.fillRect(RESOLUTION_WIDTH / 2 - 175, 0, 320, RESOLUTION_HEIGHT);
        g.setColor(Color.white);
        g.setFont(menuFont);
    }

}
