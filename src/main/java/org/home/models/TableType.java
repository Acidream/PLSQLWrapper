package org.home.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 2017-07-11.
 */
@Data
public class TableType extends Type {
    boolean isRefCursor = false;
    List<Field> fields = new ArrayList<Field>();


    public TableType(String name) {
        super(name);
    }

    public TableType(String name, boolean isRefCursor) {
        super(name);
        this.isRefCursor = isRefCursor;
    }


    public TableType(String name, List<Field> fields) {
        super(name);
        this.fields = fields;
    }

}
