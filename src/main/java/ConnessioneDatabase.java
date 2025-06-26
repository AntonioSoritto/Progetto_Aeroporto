import java.sql.*;

public class ConnessioneDatabase {
    private static ConnessioneDatabase instance;
    public Connection connection = null;
    private String nome = "postgres";
    private String password = "Giovanna.1991!";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String driver = "org.postgresql.Driver";

    private ConnessioneDatabase() throws SQLException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, nome, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static ConnessioneDatabase getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new ConnessioneDatabase();
        }
        return instance;
    }
}