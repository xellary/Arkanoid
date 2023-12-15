package arkanoid.game;

import arkanoid.game.entities.Ball;
import arkanoid.game.entities.BricksGenerator;
import arkanoid.game.entities.Paddle;
import arkanoid.game.enums.State;
import arkanoid.game.gui.Menu;
import arkanoid.game.levels.Level;
import arkanoid.game.listeners.MouseInput;
import arkanoid.game.fonts.GameFont;
import arkanoid.persistence.LevelPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static arkanoid.game.consts.Constants.*;
import static arkanoid.game.levels.LevelPictures.LEVEL_THREE;
import static arkanoid.game.levels.LevelPictures.PYRAMID;

public class Arkanoid extends JPanel implements ActionListener, KeyListener {

    public static State state = State.MENU;
    private final Font font = new GameFont().getFont();
    private boolean play = false;
    private int score = 0;
    private BricksGenerator pattern1;
    private BricksGenerator pattern2;
    private BricksGenerator pattern3;
    private int totalBricks;
    private Ball ball = new Ball(
            START_X_LOCATION_0F_BALL,
            START_Y_LOCATION_0F_BALL,
            X_DIRECTION, Y_DIRECTION, BALL_SIZE);
    private Paddle paddle = new Paddle(
            START_X_LOCATION_0F_PAD, START_Y_LOCATION_0F_PAD, 100, 10);
    private final Menu menu = new Menu();
    private Level secondLevel;
    private Level thirdLevel;

    public Arkanoid() {
        addKeyListener(this);
        addMouseListener(new MouseInput());
        addMouseMotionListener(new MouseInput());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        createLevelsFromDB();
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
                if (play) {
                    paddle.moveRight();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (play) {
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

    private void createLevelsFromDB() {
        LevelPersistence levelPersistence = new LevelPersistence();
        levelPersistence.createLevel("Pyramid", PYRAMID);
        levelPersistence.createLevel("Crab", LEVEL_THREE);
        secondLevel = levelPersistence.getById(1);
        thirdLevel = levelPersistence.getById(2);
    }

    private void restartGame() {
        ball.setX(START_X_LOCATION_0F_BALL);
        ball.setY(START_Y_LOCATION_0F_BALL);
        ball.setXDir(X_DIRECTION);
        ball.setYDir(Y_DIRECTION);
        paddle.setX(START_X_LOCATION_0F_PAD);
        score = 0;
        totalBricks = 1;

        pattern1 = new BricksGenerator(5, 10);
        pattern2 = new BricksGenerator(secondLevel.getPattern(), 88, 350);
        pattern3 = new BricksGenerator(thirdLevel.getPattern(), 88, 280);
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
        Menu.menuButton.drawButton(gr,23, 20);

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
        gr.setFont(gr.getFont().deriveFont(Font.PLAIN, 18));
        gr.drawString("Score ", 330, 30);
        gr.drawString(" " + score, 435, 30);

        // paddle
        paddle.draw(gr);

        // ball
        ball.draw(gr);

        if (totalBricks <= 0) {
            play = false;
            ball.setXDir(0);
            ball.setYDir(0);
            drawVictoryMessage(gr);
        } else if (ball.getY() > RESOLUTION_HEIGHT - 30) {
            play = false;
            ball.setXDir(0);
            ball.setYDir(0);
            drawDefeatMessage(gr);
        } else if (!play) {
            drawStartMessage(gr);
        }
    }

    private void actGame(BricksGenerator pattern) {
        if (play) {
            if (new Rectangle(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius()).intersects(
                    new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight()))) {
                ball.setYDir(-ball.getYDir());
            }

            pattern.intersectWithBall(ball);
            score = pattern.getScore();
            totalBricks = pattern.getAmountOfBricks();

            ball.setX(ball.getX() + ball.getXDir());
            ball.setY(ball.getY() + ball.getYDir());
            if (ball.getX() < 0) {
                ball.setXDir(-ball.getXDir());
            }
            if (ball.getY() < SCORE_PANEL_HEIGHT) {
                ball.setYDir(-ball.getYDir());
            }
            if (ball.getX() > RESOLUTION_WIDTH - 38) {
                ball.setXDir(-ball.getXDir());
            }
        }
    }

    private void drawMessageBack(Graphics2D gr) {
        gr.setStroke(new BasicStroke(3));
        gr.setColor(Color.gray);
        gr.drawRect(RESOLUTION_WIDTH / 2 - 230, RESOLUTION_HEIGHT / 2 - 100, 450, 150);
        gr.setColor(MY_GREY);
        gr.fillRect(RESOLUTION_WIDTH / 2 - 230, RESOLUTION_HEIGHT / 2 - 100, 450, 150);
    }

    private void drawVictoryMessage(Graphics2D gr) {
        drawMessageBack(gr);
        gr.setColor(Color.white);
        gr.setFont(gr.getFont().deriveFont(Font.PLAIN, 24));
        gr.drawString("You won", RESOLUTION_WIDTH / 2 - 90, RESOLUTION_HEIGHT / 2 - 10);
    }

    private void drawDefeatMessage(Graphics2D gr) {
        drawMessageBack(gr);
        gr.setColor(Color.red);
        gr.setFont(gr.getFont().deriveFont(Font.PLAIN, 16));
        gr.drawString("Game Over", RESOLUTION_WIDTH / 2 - 85, RESOLUTION_HEIGHT / 2 - 40);
        gr.setColor(Color.white);
        gr.drawString("Press Enter to Restart", RESOLUTION_WIDTH / 2 - 190, RESOLUTION_HEIGHT / 2);
    }

    private void drawStartMessage(Graphics2D gr) {
        drawMessageBack(gr);
        gr.setColor(Color.white);
        gr.setFont(gr.getFont().deriveFont(Font.PLAIN, 14));
        gr.drawString("To move paddle press", RESOLUTION_WIDTH / 2 - 210, RESOLUTION_HEIGHT / 2 - 60);
        gr.drawString("Left and right arrows", RESOLUTION_WIDTH / 2 - 210, RESOLUTION_HEIGHT / 2 - 30);
        gr.drawString("To win break all the bricks", RESOLUTION_WIDTH / 2 - 210, RESOLUTION_HEIGHT / 2);
        gr.drawString("To start game press", RESOLUTION_WIDTH / 2 - 210, RESOLUTION_HEIGHT / 2 + 30);
        gr.setColor(Color.green);
        gr.drawString("Enter", RESOLUTION_WIDTH / 2 + 90, RESOLUTION_HEIGHT / 2 + 30);
    }

}