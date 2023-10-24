package elements;

import java.awt.*;

public interface Constants {

    int RESOLUTION_WIDTH = 800;
    int RESOLUTION_HEIGHT = 640;
    int START_X_LOCATION_0F_PAD = 350;
    int START_Y_LOCATION_0F_PAD = 590;
    int X_DIRECTION = -2;
    int Y_DIRECTION = -4;
    int START_X_LOCATION_0F_BALL = 395;
    int START_Y_LOCATION_0F_BALL = 575;
    int BALL_SIZE = 15;
    int PADDLE_SPEED = 30;
    int SCORE_PANEL_HEIGHT = 40;
    Color MY_GREY = new Color(30, 31, 34);
    Color MY_PURPLE = new Color(56, 0, 130);
    Color MY_GREEN = new Color(0, 230, 55);
    Color MY_PINK = new Color(255, 0, 200);
    Color MY_RED = new Color(236,48,11);
    Color MY_BLUE = new Color(6,232,239);
    Color MY_ORANGE = new Color(255, 153, 0);
    Button QUIT_BUTTON = new Button(RESOLUTION_WIDTH / 2 - 60, 400, 100, 40);
    Button PLAY_BUTTON = new Button(RESOLUTION_WIDTH / 2 - 60, 200, 100, 40);
    Button MENU_BUTTON = new Button(10,5,100,30);
    Button LEVEL_ONE_BUTTON = new Button(RESOLUTION_WIDTH / 2 - 60, 200, 100, 40);
    Button LEVEL_TWO_BUTTON = new Button(RESOLUTION_WIDTH / 2 - 60, 300, 100, 40);
    Button LEVEL_THREE_BUTTON = new Button(RESOLUTION_WIDTH / 2 - 60, 400, 100, 40);

}
