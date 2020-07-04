package org.home.models;

import org.home.dbreader.OraFieldDefRow;
import org.home.dbreader.TypeMap;

/**
 * Created by oleg on 2017-07-10.
 */
public class Field implements INamed {

    public static final String INOUT_NONE = "NONE";
    public static final String INOUT_IN = "IN";
    public static final String INOUT_OUT = "OUT";
    public static final String INOUT_INOUT = "INOUT";
    public static final String INOUT_UNDEFINED = "UNDEFINED";


    public Field(String name, Type type) {
        this.name = name;
        this.type = type;
        inOut = INOUT_UNDEFINED;
    }

    public Field(String name, Type type, String inOut) {
        this.name = name;
        this.type = type;
        this.inOut = inOut;
    }

    private String name;
    private Type type;
    private String inOut;

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getInOut() {
        return inOut;
    }

    public boolean getIsIn() {
        return inOut == INOUT_IN || inOut == INOUT_INOUT;
    }

    public boolean getIsOut() {
        return inOut == INOUT_OUT || inOut == INOUT_INOUT;
    }

    public static Field FromFieldDefSimpleType(OraFieldDefRow fd) {
        String javaType = null;
        String inout = INOUT_UNDEFINED;

        javaType= TypeMap.getJavaType(fd.data_type,fd.data_precision,fd.data_scale);

        if (fd.IN_OUT.equalsIgnoreCase("IN")) inout = INOUT_IN;
        else if (fd.IN_OUT.equalsIgnoreCase("OUT")) inout = INOUT_OUT;
        else if (fd.IN_OUT.equalsIgnoreCase("INOUT")) inout = INOUT_INOUT;
        return new Field(fd.getArgument_name(), new Type(javaType), inout);

    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", inOut=" + inOut +
                '}';
    }
}
