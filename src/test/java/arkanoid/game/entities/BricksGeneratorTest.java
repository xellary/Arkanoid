package arkanoid.game.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class BricksGeneratorTest {
    @Test
    @DisplayName("Brick manipulation methods")
    public void BrickManipulationMethods() {
        BricksGenerator bricksGenerator = new BricksGenerator(5, 5);
        then(bricksGenerator.getAmountOfBricks()).isEqualTo(25);

        bricksGenerator.setBrickValue(0, 2, 3);

        then(bricksGenerator.getAmountOfBricks()).isEqualTo(24);
        then(bricksGenerator.getBrickValue(2, 3)).isEqualTo(0);
        then(bricksGenerator.getBrickValue(1,1)).isEqualTo(1);
    }
}
