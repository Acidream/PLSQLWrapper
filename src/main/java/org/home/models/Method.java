package org.home.models;

import org.home.dbreader.OraFieldDefRow;

import java.util.ArrayList;
import java.util.List;

import static org.home.settings.Utils.listToString;

/**
 * Created by oleg on 2017-07-11.
 */
public class Method implements INamed {
    Field result;
    private String name;
    List<Field> params;

    public Method() {
    }

    public Method(Field result, String name, List<Field> params) {
        this.result = result;
        this.name = name;
        this.params = params;
    }

    public void setResult(Field result) {
        this.result = result;
    }

    public void setParams(List<Field> params) {
        this.params = params;
    }

    public Field getResult() {
        return result;
    }

    public String getName() {
        return name;
    }

    public List<Field> getParams() {
        return params;
    }


    public boolean getIsProcedure(){return result==null;}

    public static Method FromFieldDef(String name, List<OraFieldDefRow> fds) {
        Method res = new Method();
        res.setParams(new ArrayList<Field>());

        Field currTableField = null;
        //TableType currTableType = null;

        List<Field> args = new ArrayList<Field>();
        Integer prev_level = -1;
        for (OraFieldDefRow fd : fds) {

            if (prev_level > fd.data_level) args.add(currTableField);//закончился сложный тип

            if (fd.data_level == 0 && fd.data_type.equalsIgnoreCase("PL/SQL RECORD")) { //начался сложный тип
                currTableField = new Field( fd.getArgument_name(), new TableType( fd.type_subname != null ? fd.type_subname + "_rec" : fd.package_name + "_rec" + fd.position),fd.getIN_OUT());
            }

            if (fd.data_level > 0) {//элемент сложного типа
                ((TableType)currTableField.getType()).getFields().add(Field.FromFieldDefSimpleType(fd));
            }
            if (fd.data_level == 0 && !fd.data_type.equalsIgnoreCase("PL/SQL RECORD")){//корневрой простой тип
                args.add(Field.FromFieldDefSimpleType(fd));}


            prev_level = fd.data_level;
        }
        if (currTableField !=null && ((TableType)currTableField.getType()).getFields()!=null && ((TableType)currTableField.getType()).getFields().size() > 0) args.add(currTableField);

        for (Field arg : args) {
            if ( arg.getName()==null )
                res.setResult(arg);
            else res.getParams().add(arg);
        }

        res.name = name;
        return res;
    }

    @Override
    public String toString() {
        return "Method  "+ name +"   {" +
                "result=" + result +
                ", params=\n" + listToString(params,"  ","\n") +
                '}';
    }
}
