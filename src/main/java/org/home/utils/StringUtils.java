package org.home.utils;

import com.google.common.base.CaseFormat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by oleg on 2017-07-12.
 */
public class StringUtils {

    public static String FirstToUpper(String inp) {
        return inp.substring(0, 1).toUpperCase() + inp.substring(1);
    }

    public static String FirstToLower(String inp) {
        return inp.substring(0, 1).toLowerCase() + inp.substring(1);
    }

    public static String UnderScoreToCamel(String inp) {
        if (inp.contains("_"))
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, inp);
        if (inp.toUpperCase().equals(inp)) return inp.toLowerCase();
        else return inp;
    }


    public static Reader getResourceReader(String fileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        return new InputStreamReader(is);

    }


}
