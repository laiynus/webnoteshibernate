package by.khrapovitsky.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionDB {

    public Connection createConnection() throws IOException, ClassNotFoundException, SQLException {

        Connection connection;

        ResourceBundle rb = ResourceBundle.getBundle("db");
        String host = rb.getString("host");
        String username = rb.getString("username");
        String password = rb.getString("password");
        String driver = rb.getString("driver");

        Class.forName(driver);
        connection = DriverManager.getConnection(host, username, password);
        System.out.println("CONNECTION: " + connection);

        return connection;
    }
}
