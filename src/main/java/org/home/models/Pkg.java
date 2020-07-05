package org.home.models;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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
    //test_func_rc->TestEntity,test_func_rc->TestEntity2
    public String toJava(){
        return "public class "+ getNameCalmelL()+" {\n"+
                methods.stream().map(m->"   "+m.toJava()).collect(Collectors.joining("\n"))+
                "\n}";


    }

}
