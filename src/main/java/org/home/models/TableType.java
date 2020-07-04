package org.home.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 2017-07-11.
 */
@Data
public class TableType extends Type {
    private String name;
    List<Field> fields= new ArrayList<Field>();



    public TableType(String name) {
        super(name);
    }


    public TableType(String name, List<Field> fields) {
        super(name);
        this.fields = fields;
    }

}
