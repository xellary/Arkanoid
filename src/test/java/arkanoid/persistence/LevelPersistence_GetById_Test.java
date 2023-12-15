package arkanoid.persistence;

import arkanoid.game.levels.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static arkanoid.game.levels.LevelPictures.LEVEL_THREE;
import static arkanoid.game.levels.LevelPictures.PYRAMID;
import static org.assertj.core.api.BDDAssertions.then;

public class LevelPersistence_GetById_Test {

    @Test
    @DisplayName("Get id and name from db")
    public void getIdAndNameFromDB() {
        LevelPersistence levelPersistence = new LevelPersistence();

        levelPersistence.createLevel("Pyramid", PYRAMID);
        levelPersistence.createLevel("Crab", LEVEL_THREE);
        Level level = levelPersistence.getById(1);

        then(level).isNotNull();
        then(level.getId()).isEqualTo(1);
        then(level.getName()).isEqualTo("Pyramid");
    }
}
