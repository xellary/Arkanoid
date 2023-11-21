package arkanoid.game.elements;

import java.awt.*;

import static arkanoid.game.elements.Constants.PADDLE_SPEED;
import static arkanoid.game.elements.Constants.RESOLUTION_WIDTH;

public class Paddle {

    public int x;
    public int y;
    public int height;
    public int width;
    public Color color = Color.white;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
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
}
