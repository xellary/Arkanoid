package elements;

import fonts.GameFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static elements.Constants.*;
import static elements.LevelPictures.*;

public class Arkanoid extends JPanel implements ActionListener, KeyListener {

    private Font font = new GameFont().getFont();
    private boolean play = false;
    private int score = 0;
    private BricksGenerator pattern1;
    private BricksGenerator pattern2;
    private BricksGenerator pattern3;
    private int totalBricks;
    private Ball ball = new Ball(START_X_LOCATION_0F_BALL, START_Y_LOCATION_0F_BALL, X_DIRECTION, Y_DIRECTION, BALL_SIZE);
    private Paddle paddle = new Paddle(START_X_LOCATION_0F_PAD, START_Y_LOCATION_0F_PAD, 100, 10);
    public static State state = State.MENU;
    private final Menu menu = new Menu();

    public Arkanoid() {
        addKeyListener(this);
        addMouseListener(new MouseInput());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 0;
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D gr = (Graphics2D)g;
        gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (state == State.LEVEL_ONE) {
            drawGame(pattern1, gr);
        } else if (state == State.LEVEL_TWO) {
            drawGame(pattern2, gr);
        } else if (state == State.LEVEL_THREE) {
            drawGame(pattern3, gr);
        } else if (state == State.MENU) {
            play = false;
            restartGame();
            menu.draw(gr);
        } else if (state == State.LEVEL_SELECTION_MENU) {
            menu.drawLevelSelection(gr);
        }
        gr.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (state == State.LEVEL_ONE) {
            actGame(pattern1);
        } else if (state == State.LEVEL_TWO) {
            actGame(pattern2);
        } else if (state == State.LEVEL_THREE) {
            actGame(pattern3);
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (state == State.LEVEL_ONE || state == State.LEVEL_TWO || state == State.LEVEL_THREE) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (paddle.x >= RESOLUTION_WIDTH - 120) {
                    paddle.x = RESOLUTION_WIDTH - 120;
                } else {
                    play = true;
                    paddle.moveRight();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (paddle.x <= 10) {
                    paddle.x = 10;
                } else {
                    play = true;
                    paddle.moveLeft();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!play) {
                    play = true;
                    restartGame();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void restartGame() {
        ball.x = START_X_LOCATION_0F_BALL;
        ball.y = START_Y_LOCATION_0F_BALL;
        ball.xDir = X_DIRECTION;
        ball.yDir = Y_DIRECTION;
        paddle.x = START_X_LOCATION_0F_PAD;
        score = 0;
        totalBricks = 1;
        pattern1 = new BricksGenerator(5, 10);
        pattern2 = new BricksGenerator(LEVEL2_PICTURE, 88, 350);
        pattern3 = new BricksGenerator(LEVEL3_PICTURE, 88, 280);
        repaint();
    }

    private void drawGame(BricksGenerator pattern, Graphics2D gr) {

        Rectangle gradient = new Rectangle(0, 0, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
        GradientPaint gp = new GradientPaint(RESOLUTION_WIDTH, RESOLUTION_HEIGHT, MY_PURPLE,
                RESOLUTION_WIDTH, 150, Color.black, false);
        gr.setPaint(gp);
        gr.fill(gradient);

        // score panel
        gr.setColor(MY_GREY);
        gr.fillRect(0, 0, RESOLUTION_WIDTH, SCORE_PANEL_HEIGHT);

        // menu button
        MENU_BUTTON.drawButton(gr, 24);
        MENU_BUTTON.drawButtonText(gr, "MENU", 18, 20);

        // map
        pattern.draw(gr);

        // borders
        gr.setColor(Color.black);
        gr.fillRect(0, SCORE_PANEL_HEIGHT, 2, RESOLUTION_HEIGHT - 8);
        gr.fillRect(0, SCORE_PANEL_HEIGHT, RESOLUTION_WIDTH - 16, 3);
        gr.fillRect(RESOLUTION_WIDTH - 16, SCORE_PANEL_HEIGHT, 3, RESOLUTION_HEIGHT - 8);

        // score
        gr.setColor(Color.white);
        gr.setFont(font);
        gr.setFont(gr.getFont().deriveFont(Font.PLAIN, 35));
        gr.drawString("Score ", 335, 32);
        gr.drawString("" + score, 445, 32);

        // paddle
        paddle.draw(gr);

        // ball
        ball.draw(gr);

        if (totalBricks <= 0) {
            play = false;
            ball.xDir = 0;
            ball.yDir = 0;
            gr.setColor(Color.white);
            gr.setFont(gr.getFont().deriveFont(Font.PLAIN, 40));
            gr.drawString("You won", RESOLUTION_WIDTH / 2 - 60, RESOLUTION_HEIGHT / 2);
        }

        if (ball.y > RESOLUTION_HEIGHT - 30) {
            play = false;
            ball.xDir = 0;
            ball.yDir = 0;
            gr.setColor(Color.white);
            gr.drawString("Game  Over", RESOLUTION_WIDTH / 2 - 90, RESOLUTION_HEIGHT / 2);
            gr.drawString("Press  Enter  to  Restart", RESOLUTION_WIDTH / 2 - 200, RESOLUTION_HEIGHT / 2 + 40);
        }
    }

    private void actGame(BricksGenerator pattern) {
        if (play) {
            if (new Rectangle(ball.x, ball.y, ball.radius, ball.radius).intersects(new Rectangle(paddle.x, paddle.y, paddle.width, paddle.height))) {
                ball.yDir = -ball.yDir;
            }

            pattern.intersectWithBall(ball);
            score = pattern.score;
            totalBricks = pattern.amountOfBricks;

            ball.x += ball.xDir;
            ball.y += ball.yDir;
            if (ball.x < 0) {
                ball.xDir = -ball.xDir;
            }
            if (ball.y < SCORE_PANEL_HEIGHT) {
                ball.yDir = -ball.yDir;
            }
            if (ball.x > RESOLUTION_WIDTH - 38) {
                ball.xDir = -ball.xDir;
            }
        }
        repaint();
    }

}