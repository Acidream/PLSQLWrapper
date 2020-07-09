package org.home.dbreader;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.home.models.Field;
import org.home.models.Method;
import org.home.models.Pkg;
import org.home.models.TableType;
import org.home.settings.DBConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 2017-07-15.
 */
public class DBReader {


    public static List<OraMethodDef> getOraMethodDefs(String owner, String objectNameCriteria) throws SQLException {
        QueryRunner run = new QueryRunner(DBConnection.getDS());
        ResultSetHandler<List<OraMethodDef>> h = new BeanListHandler<OraMethodDef>(OraMethodDef.class);
        return  run.query("SELECT object_name,procedure_name,object_id,subprogram_id,object_type FROM SYS.DBA_PROCEDURES where owner=? and PROCEDURE_NAME is null and "+objectNameCriteria, h,owner);
    }

    public static List<OraMethodDef> getOraPackageMethodDefs(String owner, String packageName) throws SQLException {
        QueryRunner run = new QueryRunner(DBConnection.getDS());
        ResultSetHandler<List<OraMethodDef>> h = new BeanListHandler<OraMethodDef>(OraMethodDef.class);
        return  run.query("SELECT object_name,procedure_name,object_id,subprogram_id,object_type FROM SYS.DBA_PROCEDURES where owner=? and not PROCEDURE_NAME is null and object_name='"+packageName+"'", h,owner);
    }

    public static List<OraFieldDefRow> getFieldDefs(Long objId, Long subprogramId) throws SQLException {
        QueryRunner run = new QueryRunner(DBConnection.getDS());
        ResultSetHandler<List<OraFieldDefRow>> h = new BeanListHandler<OraFieldDefRow>(OraFieldDefRow.class);
        return  run.query("SELECT package_name, object_name,  argument_name,position,sequence,data_level ,data_type ,data_Length ,data_precision,data_scale ,IN_OUT,type_subname FROM SYS.ALL_ARGUMENTS where owner='ABS' and object_id=? and subprogram_id=? order by sequence", h,objId,subprogramId);
    }

    public static List<Method> getMethodsByObjNameCriteria(String owner, String objectNameCriteria ) throws SQLException {
        List<Method> res=new ArrayList<Method>();
        List<OraMethodDef>  methodIds= getOraMethodDefs(owner, objectNameCriteria);
        for (OraMethodDef oraMethodDef : methodIds) {
            res.add( Method.FromFieldDef(oraMethodDef.getObject_name(), getFieldDefs(oraMethodDef.getObject_id(),oraMethodDef.subprogram_id) ));
        }
        return res;
    }

    public static Pkg getPkg(String owner, String packageName ) throws SQLException {

        List<Method> methods=new ArrayList<Method>();
        Set<TableType> tableTypes =new HashSet<TableType>();
        List<OraMethodDef>  methodIds= getOraPackageMethodDefs(owner, packageName);
        for (OraMethodDef oraMethodDef : methodIds) {
            methods.add( Method.FromFieldDef(oraMethodDef.getProcedure_name(), getFieldDefs(oraMethodDef.getObject_id(),oraMethodDef.subprogram_id) ));
        }
        //Collect table types
        for (Method m : methods) {
            if (!m.getIsProcedure() && m.getResult().getType() instanceof TableType) tableTypes.add((TableType)m.getResult().getType());
            for (Field field : m.getParams()) {
                if (field.getType() instanceof TableType ) tableTypes.add((TableType)field.getType());
            }
        }
        ArrayList<TableType> typesList= new ArrayList<TableType>();
        typesList.addAll(tableTypes);
        return new Pkg(packageName, typesList, methods);
    }
}
