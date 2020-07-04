package org.home.dbreader;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg on 2017-07-15.
 */
public class TypeMap {
    private static final Map<String, String> oracleToJava;
    static {
        Map<String, String> map = new HashMap<String, String>();
        map.put("varchar", "String");
        map.put("varchar2", "String");
        map.put("date", "Date");
        map.put("timestamp", "Date");
        map.put("BINARY_INTEGER".toLowerCase(), "Long");
        map.put("float", "Double");
        oracleToJava = Collections.unmodifiableMap(map);
    }

    public static String getJavaType(String dbTypeString,Integer dataPrecision,Integer dataScale){
        if (oracleToJava.containsKey(dbTypeString.toLowerCase())) return oracleToJava.get(dbTypeString.toLowerCase());
        if (dbTypeString.equalsIgnoreCase("NUMBER")) {
            if (dataPrecision!=null && dataPrecision.equals(38) && dataScale == null) return "Long";
            return "Double";
        }
        System.out.println("Oracle type:"+dbTypeString +" in not mapped!");
        return "UNKNOWN";


    }

}
