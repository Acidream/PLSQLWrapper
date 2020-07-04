package org.home.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 2017-07-11.
 */
public class Type implements INamed  {


    private String name;
    public Type(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
