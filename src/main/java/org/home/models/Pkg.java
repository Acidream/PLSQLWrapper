package org.home.models;

import lombok.Data;

import java.util.List;

/**
 * Created by oleg on 2017-07-11.
 */
@Data
public class Pkg implements INamed{
    List<TableType> tableTypes;
    String name;
    List<Method> methods;

    public Pkg(String name, List<TableType> tableTypes, List<Method> methods) {
        this.name=name;
        this.tableTypes = tableTypes;
        this.methods = methods;
    }

}
