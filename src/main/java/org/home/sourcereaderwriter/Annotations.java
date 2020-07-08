package org.home.sourcereaderwriter;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Annotations {
    public static String PROC_ANNOTATION = "@GenDbProc";
    public static String FUNCRESP_ANNOTATION = "@GenDbFuncResp";
    public static String GENSTART_DIVIDER = "//-------------------GENSTART-------------------";
    public static String GENEND_DIVIDER = "//-------------------GENEND-------------------";

    String packageName;
    Map<String, String> functionType = new HashMap<>();


}
