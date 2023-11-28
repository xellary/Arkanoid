package arkanoid.persistence;

import arkanoid.game.levels.Level;
import arkanoid.database.MyDataBase;

import java.util.Map;

public class LevelPersistence {
    private static final String TABLE_NAME = "levels";
    private static final String ID_NAME = "id";
    private static final String LEVEL_NAME = "name";
    private static final String PATTERN_NAME = "pattern";
    private final MyDataBase db = MyDataBase.getInstance();

    public void createLevel(String name, String[] pattern) {
        String sql = """
                insert into game.levels
                (name, pattern)
                values ('%s', '%s')
                ON CONFLICT (name) DO NOTHING
                """;
        String patternPic = "{" + String.join(",", pattern) + "}";
        db.execute(String.format(sql, name, patternPic));
    }

    public void deleteById(int id) {
        String sql = """
                delete from game.levels where id = %d;
                """;
        db.execute(String.format(sql, id));
    }

    public void updateNameById(String name, int id) {
        String sql = """
                UPDATE game.levels SET name = '%s' 
                WHERE id = %d;
                """;
        db.execute(String.format(sql, name, id));
    }

    public void updatePatternById(String[] pattern, int id) {
        String sql = """
                UPDATE game.levels SET pattern = '%s' 
                WHERE id = %d;
                """;
        String patternPic = "{" + String.join(",", pattern) + "}";
        db.execute(String.format(sql, patternPic, id));
    }

    public Level getById(int id) {
        Map<String, String> fromDB = db.selectById(
                id,
                TABLE_NAME,
                ID_NAME,
                LEVEL_NAME,
                PATTERN_NAME
        );
        if (fromDB == null && fromDB.isEmpty()) {
            return null;
        }
        return convertLevel(fromDB);
    }

    public Level convertLevel(Map<String, String> fromDB) {
        return new Level(
                Integer.parseInt(String.valueOf(fromDB.get(ID_NAME))),
                fromDB.get(LEVEL_NAME),
                convertString(fromDB.get(PATTERN_NAME))
        );
    }

    private int[][] convertString(String inputString) {

        String[] elements = inputString.replaceAll("[{}]", "").split(",");
        int[][] result = new int[elements.length][elements[0].length()];

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[i].length(); j++) {
                result[i][j] = Character.getNumericValue(elements[i].charAt(j));
            }
        }
        return result;
    }
}
