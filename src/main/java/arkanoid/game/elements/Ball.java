package arkanoid.game.elements;

import java.awt.*;

public class Ball {

    public int x;
    public int y;
    public int xDir;
    public int yDir;
    public Color color = Color.white;
    public int radius;

    public Ball(int posX, int posY, int xDir, int yDir, int radius) {
        this.x = posX;
        this.y = posY;
        this.xDir = xDir;
        this.yDir = yDir;
        this.radius = radius;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x, y, radius, radius);
    }
}
