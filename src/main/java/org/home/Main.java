package org.home;

/**
 * Created by oleg on 2017-07-10.
 */


import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.home.dbreader.DBReader;
import org.home.models.*;
import org.home.utils.StringUtils;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.home.settings.Utils.printList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, SQLException {

        //test();
        //Settings.generateSettingsExample();

        //  System.out.println(Settings.get().getGroups().get(0).getServiceFolder());
        //DBReader.selectRecordsFromDbUserTable();

        //TableType t=  Select2Class.getType("Test_Entity","select * from sys.all_objects");
        //System.out.println(genEntityFromType(t) );


        //printList(DBReader.getFieldDefs(79545L,1L));
        //printList(DBReader.getMethodsByObjNameCriteria("ABS","object_name like ('%FUN%')"));
        printList(DBReader.getMethodsByObjNameCriteria("ABS", "object_name like ('%PROC%')"));

        //System.out.println(genServiceFromMethodList(DBReader.getMethodsByObjNameCriteria("ABS","object_name like ('%FUN%')")));


        //String grandParent = Main.class.getResource("../../Service.stg").getContent();
        //new FileReader("../../Service.stg");

        //System.out.println(grandParent);
        System.out.println("------------------");
        Pkg pkg = DBReader.getPkg("ABS", "PKG1");

        System.out.println(genServiceFromPackage(pkg));
        printList(genEntitiesForPackage(pkg));

    }

    public static void test() throws FileNotFoundException {


        ArrayList<Field> fields = new ArrayList<Field>();
        fields.add(new Field("id", new Type("Long")));
        fields.add(new Field("name", new Type("String")));
        fields.add(new Field("status_desc", new Type("String")));
        TableType testOraTableType = new TableType("test_type_name", fields);

        System.out.println(genEntityFromType(testOraTableType));

        ArrayList<Field> args = new ArrayList<Field>();
        args.add(new Field("doc_id", new Type("Long")));
        args.add(new Field("doc_type", new Type("Long")));
        Method m1 = new Method(new Field(null, new Type("Long")), "get_status_id", args);
        Method m2 = new Method(new Field(null, testOraTableType), "get_status_id_typed", args);
        List<TableType> tableTypes = Arrays.asList(testOraTableType);
        List<Method> methods = Arrays.asList(m1, m2);
        Pkg p1 = new Pkg("first_test_package", tableTypes, methods);
        System.out.println(genServiceFromPackage(p1));

    }


    public static String genServiceFromPackage(Pkg pkg) throws FileNotFoundException {
        StringTemplateGroup stg = new StringTemplateGroup( StringUtils.getResourceReader("Service.stg")  ,DefaultTemplateLexer.class);
        StringTemplate service = stg.getInstanceOf("serviceroot");
        service.setAttribute("pkg", pkg);
        return service.toString();
    }

    public static String genServiceFromMethodList(List<Method> mlist) throws FileNotFoundException {
        StringTemplateGroup stg = new StringTemplateGroup(StringUtils.getResourceReader("Methods.stg"), DefaultTemplateLexer.class);
        StringTemplate service = stg.getInstanceOf("methodsroot");
        service.setAttribute("mlist", mlist);
        return service.toString();
    }


    public static List<String> genEntitiesForPackage(Pkg pkg) throws FileNotFoundException {
        List<String> l = new ArrayList<>();
        for (TableType tableType : pkg.getTableTypes()) {
            l.add(genEntityFromType(tableType));
        }
        return l;
    }

    public static String genEntityFromType(TableType inpTableType) throws FileNotFoundException {
        StringTemplateGroup stg = new StringTemplateGroup(StringUtils.getResourceReader("Entity.stg"), DefaultTemplateLexer.class);
        StringTemplate entity = stg.getInstanceOf("entityroot");
        entity.setAttribute("type", inpTableType);
        return entity.toString();
    }


}
