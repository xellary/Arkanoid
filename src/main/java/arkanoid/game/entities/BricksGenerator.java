package arkanoid.game.entities;

import java.awt.*;

import static arkanoid.game.consts.Constants.*;
public class BricksGenerator {

    private final int[][] map;
    private int amountOfBricks;
    private int score;

    private final Color[] rowColors = new Color[]{MY_RED, MY_PINK, MY_ORANGE, MY_BLUE, MY_GREEN};

    private final Brick brick = new Brick(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);

    public BricksGenerator(int row, int col) {
        map = new int[row][col];
        amountOfBricks = row * col;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        brick.setHeight((brick.getHeight() - 490) / row);
        brick.setWidth((brick.getWidth() - 88) / col);
    }

    public BricksGenerator(int[][] picture, int widthOfArea, int heightOfArea) {
        map = new int[picture.length][picture[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (picture[i][j] == 1) {
                    map[i][j] = 1;
                    amountOfBricks++;
                }
            }
        }
        brick.setHeight((brick.getHeight() - heightOfArea) / map.length);
        brick.setWidth((brick.getWidth() - widthOfArea) / map[0].length);
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    brick.setColor(rowColors[i % rowColors.length]);
                    g.setColor(brick.getColor());
                    brick.setX(j * brick.getWidth() + 40);
                    brick.setY(i * brick.getHeight() + 100);
                    brick.draw(g);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
        if (value == 0) {
            amountOfBricks--;
        }
    }

    public int getBrickValue(int row, int col) {
        return map[row][col];
    }

    public void intersectWithBall(Ball ball) {
        A:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int brickX = j * brick.getWidth() + 40;
                    int brickY = i * brick.getHeight() + 100;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brick.getWidth(), brick.getHeight());
                    Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());

                    if (ballRect.intersects(brickRect)) {
                        setBrickValue(0, i, j);
                        score += 5;

                        if (ball.getX() + 19 <= brickRect.x || ball.getX() + 1 >= brickRect.x + brickRect.width) {
                            ball.setXDir(-ball.getXDir());
                        } else {
                            ball.setYDir(-ball.getYDir());
                        }
                        break A;
                    }
                }
            }
        }
    }

    public int getAmountOfBricks() {
        return amountOfBricks;
    }

    public int getScore() {
        return score;
    }
}
