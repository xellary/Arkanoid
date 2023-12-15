package arkanoid.persistence;

import arkanoid.game.levels.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static arkanoid.game.levels.LevelPictures.LEVEL_THREE;
import static arkanoid.game.levels.LevelPictures.PYRAMID;
import static org.assertj.core.api.BDDAssertions.then;

public class LevelPersistence_UpdateNameById_Test {
    @Test
    @DisplayName("Update level name in db")
    public void updateLevelNameInDB() {
        LevelPersistence levelPersistence = new LevelPersistence();
        levelPersistence.createLevel("Pyramid", PYRAMID);
        levelPersistence.createLevel("Crab", LEVEL_THREE);

        Level testLevel = levelPersistence.getById(1);
        then(testLevel.getName()).isEqualTo("Pyramid");

        levelPersistence.updateNameById("TEST", 1);
        testLevel = levelPersistence.getById(1);
        then(testLevel.getName()).isEqualTo("TEST");

        levelPersistence.updateNameById("Pyramid", 1);
    }
}
