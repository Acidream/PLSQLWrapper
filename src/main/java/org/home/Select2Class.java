package org.home;

import org.home.models.Field;
import org.home.models.TableType;
import org.home.models.Type;
import org.home.settings.DBConnection;

import java.sql.*;

/**
 * Created by oleg on 2017-07-16.
 */
public class Select2Class {


    public static TableType getType(String name, String sql) throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        TableType res = null;
        try {
            dbConnection = DBConnection.get();
            statement = dbConnection.createStatement();
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            res = getType(name, rs);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return res;
    }

    public static TableType getType(String name, ResultSet rs) throws SQLException {
        TableType res = new TableType(name);
        ResultSetMetaData md = rs.getMetaData();
        for (int i = 1; i <= md.getColumnCount(); i++) {
            String typeStr = md.getColumnClassName(i);
            System.out.println(md.getColumnName(i));
            if (md.getColumnType(i) == 12) typeStr = "String";
            else if (md.getColumnType(i) == 93) typeStr = "Timestamp";
            else if (md.getColumnType(i) == 2)
                if (md.getScale(i) > 0) typeStr = "Double";
                else typeStr = "Long";
            res.getFields().add(new Field(md.getColumnName(i), new Type(typeStr)));

//
//                System.out.println(md.getColumnLabel(i));
//                System.out.println(md.getCatalogName(i));//empty
//
//                System.out.println(md.getColumnType(i));
//                System.out.println(md.getColumnTypeName(i));
//                System.out.println(md.getPrecision(i));
//                System.out.println(md.getScale(i));
//                System.out.println("----------------------");

        }

        return res;
    }


}
