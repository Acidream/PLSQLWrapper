package org.home.settings;

import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by oleg on 2017-07-26.
 */
public class DBConnection {

    public static ConnSettings settings = new ConnSettings();


    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";

    //todo:rewrite using settings
    public static Connection get() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(settings.DB_CONNECTION_URL, settings.DB_USER, settings.DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    public static DataSource getDS() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(settings.DB_CONNECTION_URL);
        ds.setUser(settings.DB_USER);
        ds.setPassword(settings.DB_PASSWORD);
        return ds;
    }

}
