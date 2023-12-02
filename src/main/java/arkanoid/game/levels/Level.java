package arkanoid.game.levels;

public class Level {
    private final int id;
    private String name;
    private int[][] pattern;

    public Level(int id, String name, int[][] pattern) {
        this.id = id;
        this.name = name;
        this.pattern = pattern;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int[][] getPattern() {
        return pattern;
    }
}
