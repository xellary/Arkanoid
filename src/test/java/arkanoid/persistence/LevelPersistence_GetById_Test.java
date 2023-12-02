package arkanoid.persistence;

import arkanoid.game.levels.Level;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class LevelPersistence_GetById_Test {
    private final LevelPersistence levelPersistence = new LevelPersistence();
    @Test
    @DisplayName("Get id and name from db")
    public void getIdAndNameFromDB() {
        Level level = levelPersistence.getById(1);

        then(level).isNotNull();
        then(level.getId()).isEqualTo(1);
        then(level.getName()).isEqualTo("Pyramid");
    }
}
