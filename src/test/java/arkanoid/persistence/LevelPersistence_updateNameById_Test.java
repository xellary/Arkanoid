package arkanoid.persistence;

import arkanoid.game.levels.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class LevelPersistence_updateNameById_Test {
    private final LevelPersistence levelPersistence = new LevelPersistence();
    @Test
    @DisplayName("Update level name in db")
    public void updateLevelNameInDB() {
        levelPersistence.updateNameById("Pyramid", 1);

        Level testLevel = levelPersistence.getById(1);
        then(testLevel.getName()).isEqualTo("Pyramid");

        levelPersistence.updateNameById("TEST", 1);
        testLevel = levelPersistence.getById(1);
        then(testLevel.getName()).isEqualTo("TEST");

        levelPersistence.updateNameById("Pyramid", 1);
    }
}
