package elements;

import fonts.MenuFont;

import java.awt.*;

public class Button {
    public int x;
    public int y;
    public int width;
    public int height;

    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawButtonText(Graphics2D g, String text, int posX, int posY) {
        g.drawString(text, x + posX, y + posY);
    }

    public void drawButton(Graphics2D g, int sizeOfFont) {
        Font font = new MenuFont().getFont();
        Brick brick = new Brick(width, height);
        brick.color = Color.darkGray;
        brick.x = x;
        brick.y = y;
        brick.draw(g, 1, Color.black);

        g.setColor(Color.white);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, sizeOfFont));
    }

    public boolean buttonPressed(int mx, int my) {
        if (mx >= x && mx <= x + width) {
            return my >= y && my <= y + height;
        }
        return false;
    }
}
