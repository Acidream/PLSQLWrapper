package org.home;


import org.home.dbreader.DBReader;
import org.home.models.*;
import org.home.settings.DBConnection;
import org.home.sourcereaderwriter.AnnotationsReader;
import org.home.sourcereaderwriter.ContentWriter;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        if (args.length < 4) {
            System.out.println("Not enough parameters, must be - connStr user pass filename");
            return;
        }
        DBConnection.settings.setDB_CONNECTION_URL(args[0]);
        DBConnection.settings.setDB_USER(args[1]);
        DBConnection.settings.setDB_PASSWORD(args[2]);
        String fileName = args[3];

        AnnotationsReader ar = new AnnotationsReader(fileName);
        ar.read();
        String packageName = ar.getAnnotations().getPackageName();
        Pkg pkg = DBReader.getPkg("ABS", packageName);//TEST_PKG

        ContentWriter cv = new ContentWriter(fileName, pkg.toJava());
        cv.write();
    }

    public static void test() throws FileNotFoundException {
        ArrayList<Field> fields = new ArrayList<Field>();
        fields.add(new Field("id", new Type("Long")));
        fields.add(new Field("name", new Type("String")));
        fields.add(new Field("status_desc", new Type("String")));
        TableType testOraTableType = new TableType("test_type_name", fields);
        ArrayList<Field> args = new ArrayList<Field>();
        args.add(new Field("doc_id", new Type("Long")));
        args.add(new Field("doc_type", new Type("Long")));
        Method m1 = new Method(new Field(null, new Type("Long")), "get_status_id", args);
        Method m2 = new Method(new Field(null, testOraTableType), "get_status_id_typed", args);
        List<TableType> tableTypes = Arrays.asList(testOraTableType);
        List<Method> methods = Arrays.asList(m1, m2);
        Pkg p1 = new Pkg("first_test_package", tableTypes, methods);
    }


}
