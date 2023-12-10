package arkanoid.game.gui;

import arkanoid.game.fonts.MenuFont;

import java.awt.*;

import static arkanoid.game.consts.Constants.*;

public class Menu {

    public static arkanoid.game.gui.Button quitButton = new arkanoid.game.gui.Button(
            RESOLUTION_WIDTH / 2 - 60,
            400, 100, 40, "QUIT", 25);
    public static arkanoid.game.gui.Button playButton = new arkanoid.game.gui.Button(
            RESOLUTION_WIDTH / 2 - 60,
            200, 100, 40, "PLAY", 25);
    public static arkanoid.game.gui.Button levelOneButton = new arkanoid.game.gui.Button(
            RESOLUTION_WIDTH / 2 - 60,
            200, 100, 40,"LEVEL 1",23);
    public static arkanoid.game.gui.Button levelTwoButton = new arkanoid.game.gui.Button(
            RESOLUTION_WIDTH / 2 - 60,
            300, 100, 40,"LEVEL 2", 23);
    public static arkanoid.game.gui.Button levelThreeButton = new arkanoid.game.gui.Button(
            RESOLUTION_WIDTH / 2 - 60,
            400, 100, 40,"LEVEL 3",23);
    public static arkanoid.game.gui.Button menuButton = new Button(
            10,5,85,30, "MENU", 19);

    private final Font menuFont = new MenuFont().getFont();

    public void draw(Graphics2D g) {
        drawBackground(g);
        playButton.drawButton(g, 27, 27);
        quitButton.drawButton(g, 29, 27);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
        g.drawString("ARKANOID", RESOLUTION_WIDTH / 2 - 85, RESOLUTION_HEIGHT - 500);
    }

    public void drawLevelSelection(Graphics2D g) {
        drawBackground(g);
        levelOneButton.drawButton(g, 19, 27);
        levelTwoButton.drawButton(g, 19, 27);
        levelThreeButton.drawButton(g, 19, 27);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 34));
        g.drawString("CHOOSE  THE  LEVEL", RESOLUTION_WIDTH / 2 - 135, RESOLUTION_HEIGHT - 500);
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
