package arkanoid.game.entities;

import java.awt.*;

import static arkanoid.game.consts.Constants.PADDLE_SPEED;
import static arkanoid.game.consts.Constants.RESOLUTION_WIDTH;

public class Paddle {

    private int x;
    private final int y;
    private final int height;
    private final int width;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRoundRect(x, y, width, height, 5, 5);
    }

    public void moveRight() {
        if (x + width  >= RESOLUTION_WIDTH - 50) {
            x = RESOLUTION_WIDTH - 120;
        } else {
            x += PADDLE_SPEED;
        }
    }

    public void moveLeft() {
        if (x <= 40) {
            x = 10;
        } else {
            x -= PADDLE_SPEED;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
