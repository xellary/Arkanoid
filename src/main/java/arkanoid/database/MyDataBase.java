package arkanoid.database;

import arkanoid.config.DataBaseProperties;
import arkanoid.config.PropertiesFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyDataBase {
    private static MyDataBase instance;
    private final DataBaseProperties properties = PropertiesFactory.getProperties();

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(
                properties.getUrl(),
                properties.getLogin(),
                properties.getPassword()
        );
    }

    public synchronized static MyDataBase getInstance() {
        if (instance == null) {
            instance = new MyDataBase();
        }
        return instance;
    }

    private MyDataBase() {
        init();
    }

    private void init() {
        createSchema();
        createTableLevels();
    }

    private void createSchema() {
        String sql = """
                create schema if not exists game.levels;
                """;
        execute(sql);
    }

    public void execute(String sql) {
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createTableLevels() {
        String sql = """
                create table if not exists levels (
                    id serial primary key,
                    name varchar,
                    pattern varchar[][]
                );
                """;
        execute(sql);

    }

    public Map<String, String> selectLevelById(int id, String table, String... columnNames) {
        Map<String, String> result = new HashMap<>();
        String sql = """
                select id, %s
                from game.%s
                where id = %d
                """;
        String names = Stream.of(columnNames).collect(Collectors.joining(", "));
        String select = String.format(sql, names, table, id);

        try (Connection connection = connect();
            Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(select);

            while (set.next()) {
                for (String columnName : columnNames) {
                    result.put(columnName, set.getString(columnName));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }
}