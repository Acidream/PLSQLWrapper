package org.home.dbreader;

/**
 * Created by oleg on 2017-07-28.
 */
public class OraFieldDefRow {
    public OraFieldDefRow() {
    }

   public  String argument_name;
    public  String data_type;
    public  String IN_OUT;
    public Integer data_length;
    public Integer data_precision;


    public  String package_name;
    public String object_name;
    public Integer  position;
    public Integer   sequence;
    public Integer  data_level;
    public Integer  data_scale;
    public String type_subname;


    public String getArgument_name() {
        return argument_name;
    }

    public void setArgument_name(String argument_name) {
        this.argument_name = argument_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public Integer getData_length() {
        return data_length;
    }

    public void setData_length(Integer data_length) {
        this.data_length = data_length;
    }

    public Integer getData_precision() {
        return data_precision;
    }

    public void setData_precision(Integer data_precision) {
        this.data_precision = data_precision;
    }

    public String getIN_OUT() {
        return IN_OUT;
    }

    public void setIN_OUT(String IN_OUT) {
        this.IN_OUT = IN_OUT;
    }


    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getData_level() {
        return data_level;
    }

    public void setData_level(Integer data_level) {
        this.data_level = data_level;
    }

    public Integer getData_scale() {
        return data_scale;
    }

    public void setData_scale(Integer data_scale) {
        this.data_scale = data_scale;
    }

    public String getType_subname() {
        return type_subname;
    }

    public void setType_subname(String type_subname) {
        this.type_subname = type_subname;
    }

    public OraFieldDefRow(String argumentName, String dataType, Integer dataLength, Integer dataPrecision) {
        this.argument_name = argumentName;
        this.data_type = dataType;
        this.data_length = dataLength;
        this.data_precision = dataPrecision;


    }

    @Override
    public String toString() {
        return "OraFieldDefRow{" +
                "argument_name='" + argument_name + '\'' +
                ", data_type='" + data_type + '\'' +
                ", IN_OUT='" + IN_OUT + '\'' +
                ", data_length=" + data_length +
                ", data_precision=" + data_precision +
                ", package_name='" + package_name + '\'' +
                ", object_name='" + object_name + '\'' +
                ", position=" + position +
                ", sequence=" + sequence +
                ", data_level=" + data_level +
                ", data_scale=" + data_scale +
                ", type_subname='" + type_subname + '\'' +
                '}';
    }
}
