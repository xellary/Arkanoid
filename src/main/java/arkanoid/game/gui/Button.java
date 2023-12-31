package arkanoid.game.gui;

import arkanoid.game.entities.Brick;
import arkanoid.game.fonts.MenuFont;

import java.awt.*;

public class Button {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    public boolean enter;
    private final int sizeOfFont;
    private final String text;

    public Button(int x, int y, int width, int height, String text, int sizeOfFont) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.sizeOfFont = sizeOfFont;
    }

    public void drawButton(Graphics2D g, int posX, int posY) {
        Font font = new MenuFont().getFont();
        Brick brick = new Brick(width, height);
        if (enter) {
            brick.setColor(Color.gray);
        } else {
            brick.setColor(Color.darkGray);
        }
        brick.setX(x);
        brick.setY(y);
        brick.draw(g, 1, Color.black);

        g.setColor(Color.white);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, sizeOfFont));
        g.drawString(text, x + posX, y + posY);
    }

    public boolean buttonEntered(int mx, int my) {
        if (mx >= x && mx <= x + width) {
            return my >= y && my <= y + height;
        }
        return false;
    }
}
