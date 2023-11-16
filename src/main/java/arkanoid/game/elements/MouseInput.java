package arkanoid.game.elements;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static arkanoid.game.elements.Constants.*;

public class MouseInput implements MouseListener, MouseMotionListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Arkanoid.state == State.MENU) {
            if (PLAY_BUTTON.buttonEntered(mx, my)) {
                    Arkanoid.state = State.LEVEL_SELECTION_MENU;
            }
            if (QUIT_BUTTON.buttonEntered(mx, my)) {
                System.exit(1);
            }
        } else if (Arkanoid.state == State.LEVEL_ONE || Arkanoid.state == State.LEVEL_TWO || Arkanoid.state == State.LEVEL_THREE) {
            if (MENU_BUTTON.buttonEntered(mx, my)) {
                Arkanoid.state = State.MENU;
            }
        } else if (Arkanoid.state == State.LEVEL_SELECTION_MENU) {
            if (LEVEL_ONE_BUTTON.buttonEntered(mx, my)) {
                Arkanoid.state = State.LEVEL_ONE;
            }
            if (LEVEL_THREE_BUTTON.buttonEntered(mx, my)) {
                Arkanoid.state = State.LEVEL_THREE;
            }
            if (LEVEL_TWO_BUTTON.buttonEntered(mx, my)) {
                Arkanoid.state = State.LEVEL_TWO;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        PLAY_BUTTON.mouseX = mx;
        PLAY_BUTTON.mouseY = my;
    }
}
