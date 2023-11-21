package arkanoid.game.elements;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class MouseInput implements MouseListener, MouseMotionListener {

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Arkanoid.state == State.MENU) {
            if (Menu.playButton.buttonEntered(mx, my)) {
                    Arkanoid.state = State.LEVEL_SELECTION_MENU;
            }
            if (Menu.quitButton.buttonEntered(mx, my)) {
                System.exit(1);
            }
        } else if (Arkanoid.state == State.LEVEL_ONE || Arkanoid.state == State.LEVEL_TWO || Arkanoid.state == State.LEVEL_THREE) {
            if (Menu.menuButton.buttonEntered(mx, my)) {
                Arkanoid.state = State.MENU;
            }
        } else if (Arkanoid.state == State.LEVEL_SELECTION_MENU) {
            if (Menu.levelOneButton.buttonEntered(mx, my)) {
                Arkanoid.state = State.LEVEL_ONE;
            }
            if (Menu.levelThreeButton.buttonEntered(mx, my)) {
                Arkanoid.state = State.LEVEL_THREE;
            }
            if (Menu.levelTwoButton.buttonEntered(mx, my)) {
                Arkanoid.state = State.LEVEL_TWO;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        List<Button> buttons = List.of(Menu.playButton, Menu.quitButton,
                Menu.menuButton, Menu.levelOneButton,
                Menu.levelTwoButton, Menu.levelThreeButton);

        for (Button button : buttons) {
            button.enter = button.buttonEntered(mx, my);
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
    public void mouseClicked(MouseEvent e) {

    }
}
