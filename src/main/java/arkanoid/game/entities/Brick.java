package arkanoid.game.entities;

import java.awt.*;

public class Brick {
    private int width;
    private int height;
    private Color color;
    private int x;
    private int y;

    public Brick(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics2D g) {
        drawBrick(g, 2, Color.black);
    }

    public void draw(Graphics2D g, int stroke, Color colorOfStroke) {
        drawBrick(g, stroke, colorOfStroke);
    }

    private void drawBrick(Graphics2D g, int stroke, Color colorOfStroke) {
        int sizeOfShadow = height / 4;
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(color.darker());
        g.fillRect(x, y + height - sizeOfShadow, width - sizeOfShadow + 1, sizeOfShadow);
        g.fillRect(x + width - sizeOfShadow, y, sizeOfShadow, height);
        g.setColor(color.brighter());
        g.fillRect(x, y, width - sizeOfShadow, sizeOfShadow);
        g.fillRect(x, y, sizeOfShadow, height - sizeOfShadow);
        g.fillPolygon(new int[] {x, x + sizeOfShadow, x},
                new int[] {y + height - sizeOfShadow - 1, y + height - sizeOfShadow - 1, y + height},3);
        g.fillPolygon(new int[] {x + width - sizeOfShadow - 1, x + width, x + width - sizeOfShadow},
                new int[] {y, y, y + sizeOfShadow},3);

        g.setStroke(new BasicStroke(stroke));
        g.setColor(colorOfStroke);
        g.drawRect(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}