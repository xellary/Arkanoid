package arkanoid.game.entities;

import java.awt.*;

public class Ball {

    private int x;
    private int y;
    private int xDir;
    private int yDir;
    private int radius;

    public Ball(int x, int y, int xDir, int yDir, int radius) {
        this.x = x;
        this.y = y;
        this.xDir = xDir;
        this.yDir = yDir;
        this.radius = radius;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(getX(), getY(), getRadius(), getRadius());
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

    public void setY(int y) {
        this.y = y;
    }

    public int getXDir() {
        return xDir;
    }

    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public void setYDir(int yDir) {
        this.yDir = yDir;
    }

    public int getRadius() {
        return radius;
    }
}
