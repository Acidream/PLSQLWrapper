package org.home.settings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 2017-07-12.
 */
public class Utils {

    public static String getValueForKey(String key, String line) {
        line = line.trim();
        String lineLC = line.toLowerCase();
        key = key.toLowerCase();
        if (lineLC.startsWith(key + "=")) return line.substring((key + "=").length());
        if (lineLC.startsWith(key + " =")) return line.substring((key + " =").length());
        return null;
    }

    public static String[] readTextFile(String pathFile) {
        ArrayList<String> lst = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                lst.add(line);
                line = br.readLine();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (Exception e) {
            }

        }

        return lst.toArray(new String[lst.size()]);
    }


    public static void printList(List list){
        for (Object o : list) {
            System.out.println(o);
        }
    }


    public static String listToString(List list,String prefix,String suffix){
        StringBuilder stb=new StringBuilder();
        for (Object o : list) {
            stb.append(prefix+ o+suffix);
        }
        return stb.toString();
    }

}
