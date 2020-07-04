package org.home.models;

import java.util.List;

/**
 * Created by oleg on 2017-07-11.
 */
public class Pkg implements INamed{
    List<TableType> tableTypes;
    String name;
    List<Method> methods;

    public Pkg(String name, List<TableType> tableTypes, List<Method> methods) {
        this.name=name;
        this.tableTypes = tableTypes;
        this.methods = methods;
    }

    public List<TableType> getTableTypes() {
        return tableTypes;
    }

    public List<Method> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return "Pkg{" +
                "tableTypes=" + tableTypes +
                ", name='" + name + '\'' +
                ", methods=" + methods +
                '}';
    }

    @Override
    public String getName() {
        return name;
    }
}
