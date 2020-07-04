package org.home.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 2017-07-11.
 */
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

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String toString(){
        String res=name+"\n";
        for (Field field : fields) {
            res=res+ "  "+field.getType() +" "+  field.getName()+";\n";
        }
        return res;

    }
}
