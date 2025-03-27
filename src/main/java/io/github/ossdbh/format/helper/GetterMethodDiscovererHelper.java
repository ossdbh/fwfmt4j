package io.github.ossdbh.format.helper;

import java.util.function.Function;

/*
 * Helper class for annotation processing
 *
 */
public class GetterMethodDiscovererHelper {

    /*
     * default method to discover getter method for lombok based DTOs
     * @param str the fieldname whose getter method needs to be discovered
     * @return the getter method for the fieldname returned as a string
     *
     */
    public static Function<String, String> defaultLombokBasedGetterMethod = str -> AnnotationHelper.inferGetter(str);

}
