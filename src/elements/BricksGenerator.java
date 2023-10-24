package elements;

import java.awt.*;



import static elements.Constants.*;
public class BricksGenerator {

    public int[][] map;
    private final Color[] rowColors = new Color[]{MY_RED, MY_PINK, MY_ORANGE, MY_BLUE, MY_GREEN};
    private Brick brick = new Brick(RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
    public int amountOfBricks;
    public int score;

    public BricksGenerator(int row, int col) {
        map = new int[row][col];
        amountOfBricks = row * col;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        brick.height = (brick.height - 490) / row;
        brick.width = (brick.width - 88) / col;
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
        brick.height = (brick.height - heightOfArea) / map.length;
        brick.width = (brick.width - widthOfArea) / map[0].length;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    brick.color = rowColors[i % rowColors.length];
                    g.setColor(brick.color);
                    brick.x = j * brick.width + 40;
                    brick.y = i * brick.height + 100;
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

    public void intersectWithBall(Ball ball) {
        A:
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int brickX = j * brick.width + 40;
                    int brickY = i * brick.height + 100;

                    Rectangle brickRect = new Rectangle(brickX, brickY, brick.width, brick.height);
                    Rectangle ballRect = new Rectangle(ball.x, ball.y, ball.radius, ball.radius);

                    if (ballRect.intersects(brickRect)) {
                        setBrickValue(0, i, j);
                        score += 5;

                        if (ball.x + 19 <= brickRect.x || ball.x + 1 >= brickRect.x + brickRect.width) {
                            ball.xDir = -ball.xDir;
                        } else {
                            ball.yDir = -ball.yDir;
                        }
                        break A;
                    }
                }
            }
        }
    }

}
