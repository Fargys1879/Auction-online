package dao;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionBuilderDAOTest implements ConnectionBuilder{
    private static final String DB_CONFIG_FILE = "src/main/resources/db.properties";
    private final Properties props = new Properties();

    private void getConfigDataBase() {
        File file = new File(ConnectionBuilderDAOTest.DB_CONFIG_FILE);
        try {
            props.load(new FileReader(file));
        } catch (Exception e) {
            throw new RuntimeException("Properties file is missing", e);
        }
    }

    @Override
    public Connection getConnection() {
        getConfigDataBase();
        Connection connection;
        try {
            Class.forName(props.getProperty("db.driver"));
            connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"),
                    props.getProperty("db.password"));
            System.out.println("DataBase Connection - OK");
        } catch (ClassNotFoundException e) {
            throw new DAOException("Driver missing", e);
        } catch (SQLException e) {
            throw new DAOException("No connection", e);
        }
        return connection;
    }
}
