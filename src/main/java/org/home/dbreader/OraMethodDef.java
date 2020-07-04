package org.home.dbreader;

/**
 * Created by oleg on 2017-07-28.
 */
public class OraMethodDef {
    String object_name;
    String procedure_name;
    Long object_id;
    Long subprogram_id;
    String object_type;

    public OraMethodDef() {
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public Long getObject_id() {
        return object_id;
    }

    public void setObject_id(Long object_id) {
        this.object_id = object_id;
    }

    public Long getSubprogram_id() {
        return subprogram_id;
    }

    public void setSubprogram_id(Long subprogram_id) {
        this.subprogram_id = subprogram_id;
    }

    public String getObject_type() {
        return object_type;
    }

    public void setObject_type(String object_type) {
        this.object_type = object_type;
    }

    @Override
    public String toString() {
        return "OraMethodDef{" +
                "object_name='" + object_name + '\'' +
                ", procedure_name='" + procedure_name + '\'' +
                ", object_id=" + object_id +
                ", subprogram_id=" + subprogram_id +
                ", object_type='" + object_type + '\'' +
                '}';
    }
}
