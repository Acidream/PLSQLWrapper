package org.home.utils;

import com.google.common.base.CaseFormat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static <T> String join(List<T> list, Function<? super T, String> f) {
        return join(list, f, ", ");
    }

    public static <T> String join(List<T> list, Function<? super T, String> f, String delim) {
        return list.stream().map(f).collect(Collectors.joining(delim));
    }

    public static String flatJoin(Object... objs) {
        ArrayList<String> res = new ArrayList<>();
        for (Object obj : objs) {
            if (obj instanceof String) {
                res.add((String) obj);
                continue;
            }
            if (obj instanceof List) {
                res.addAll((Collection<? extends String>) obj);
                continue;
            }
        }
       return String.join(", ",res);
    }


}
