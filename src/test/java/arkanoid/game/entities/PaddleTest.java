package arkanoid.game.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static arkanoid.game.consts.Constants.RESOLUTION_WIDTH;
import static arkanoid.game.consts.Constants.START_X_LOCATION_0F_PAD;
import static org.assertj.core.api.BDDAssertions.then;

public class PaddleTest {
    @Test
    @DisplayName("Paddle moveRight and moveLeft test")
    public void PaddleMoveRightAndMoveLeftTest() {
        Paddle paddle = new Paddle(START_X_LOCATION_0F_PAD, 0, 100, 10);
        for (int i = 0; i < 9; i++) {
            paddle.moveLeft();
        }
        then(paddle.x).isEqualTo(10);

        for (int i = 0; i < 17; i++) {
            paddle.moveRight();
        }
        then(paddle.x).isEqualTo(RESOLUTION_WIDTH - 120);
    }
}
