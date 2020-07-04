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

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION_URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String DB_USER = "abs";
    private static final String DB_PASSWORD = "qaz";
//todo:rewrite using settings
    public static Connection get() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }


    public static DataSource getDS() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(DB_CONNECTION_URL);
        ds.setUser(DB_USER);
        ds.setPassword(DB_PASSWORD);
        return ds;
    }

}
