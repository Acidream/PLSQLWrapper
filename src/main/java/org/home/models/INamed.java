package org.home.models;

import org.home.utils.StringUtils;

/**
 * Created by oleg on 2017-10-07.
 */
public interface INamed {
    String getName();

    default String getNameCalmelL() {
        return StringUtils.FirstToLower( StringUtils.UnderScoreToCamel(getName())) ;
    }

    default String getNameCalmelU() {
        return StringUtils.FirstToUpper( StringUtils.UnderScoreToCamel(getName())) ;
    }
}
