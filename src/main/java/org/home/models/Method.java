package org.home.models;

import lombok.Data;
import org.home.dbreader.OraFieldDefRow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.home.utils.StringUtils.flatJoin;

/**
 * Created by oleg on 2017-07-11.
 */
@Data
public class Method implements INamed {
    Field result;
    List<Field> params;
    private String name;
    private String packageName = "";

    public Method() {
    }

    public Method(Field result, String name, List<Field> params) {
        this.result = result;
        this.name = name;
        this.params = params;
    }

    public static Method FromFieldDef(String name, List<OraFieldDefRow> fds) {
        Method res = new Method();
        res.setParams(new ArrayList<Field>());

        Field currTableField = null;
        //TableType currTableType = null;

        List<Field> args = new ArrayList<Field>();
        Integer prev_level = -1;
        for (OraFieldDefRow fd : fds) {

            if (prev_level > fd.data_level) {
                args.add(currTableField);//закончился сложный тип
                currTableField = null;
            }


            if (fd.data_type.equalsIgnoreCase("PL/SQL RECORD")) { //начался сложный тип
                if (currTableField == null)
                    currTableField = new Field(fd.getArgument_name(), new TableType(fd.type_subname != null ? fd.type_subname + "_rec" : fd.package_name + "_rec" + fd.position), fd.getIN_OUT());
                continue;
            }

            if (fd.data_type.equalsIgnoreCase("REF CURSOR")) { //начался сложный тип
                if (currTableField == null)
                    currTableField = new Field(fd.getArgument_name(), new TableType(fd.type_subname != null ? fd.type_subname + "_rec" : fd.package_name + "_rec" + fd.position), fd.getIN_OUT());

                continue;
            }

            if (fd.data_level > 0) {//элемент сложного типа
                ((TableType) currTableField.getType()).getFields().add(Field.FromFieldDefSimpleType(fd));
            }
            if (fd.data_level == 0) {//корневрой простой тип
                args.add(Field.FromFieldDefSimpleType(fd));
            }


            prev_level = fd.data_level;
        }
        if (currTableField != null && ((TableType) currTableField.getType()).getFields() != null && ((TableType) currTableField.getType()).getFields().size() > 0)
            args.add(currTableField);

        for (Field arg : args) {
            if (arg.getName() == null)
                res.setResult(arg);
            else res.getParams().add(arg);
        }

        res.name = name;
        return res;
    }

    public boolean getIsProcedure() {
        return result == null;
    }

    private List<String> paramsName() {
        return params.stream().map(p -> p.getNameCalmelL()).collect(Collectors.toList());
    }

    private List<String> paramsTypeName() {
        return params.stream().map(p -> p.getType().getName() + " " + p.getNameCalmelL()).collect(Collectors.toList());
    }

    private boolean isResultTableType() {
        return (result.getType() instanceof TableType && !((TableType) result.getType()).isRefCursor);
    }

    private boolean isResultRC() {
        return (result.getType() instanceof TableType && ((TableType) result.getType()).isRefCursor);
    }

    private String getOutTypeName() {
        if (getResult().getType().getName() != null)
            return getResult().getType().getName();
        return getNameCalmelU() + "Res";
    }

    public String toJava() {

        if (getIsProcedure()) {
            return
                    "public void " + getNameCalmelL() + "(" + flatJoin(paramsTypeName()) + ")" +
                            "{" +
                            "proc(" + flatJoin("\"" + name + "\"", paramsName()) + ");" +
                            "}";
        } else {
            return
                    "public " + getOutTypeName() + " " + getNameCalmelL() + "(" + flatJoin(paramsTypeName()) + ")" +
                            "{" +
                            "return " + (isResultRC() ? "func_cursor" : "func") + "(" + flatJoin("\"" + name + "\"", getOutTypeName() + ".class", paramsName()) + ");" +
                            "} \n" + renderComplexType();


        }
    }

    private String renderComplexType() {
        if (!(isResultRC() || isResultTableType()))
            return "";
        TableType tt = (TableType) result.getType();
        if (tt.fields.size() == 0)
            return "";
        return " @Data\n" +
                "class " + getOutTypeName() + "{ \n" +
                tt.fields.stream().map(p -> "   " + p.getType().getName() + " " + p.getNameCalmelL() + ";\n").collect(Collectors.joining()) +
                "}";


    }


}
