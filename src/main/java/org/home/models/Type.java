package org.home.models;

import lombok.Data;

/**
 * Created by oleg on 2017-07-11.
 */
@Data
public class Type implements INamed  {

    private String name;

    public Type(String name) {
        this.name = name;

    }

}
