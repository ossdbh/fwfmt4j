package io.github.ossdbh.format.enums;

import io.github.ossdbh.format.constants.Constants;

import java.util.HashMap;
import java.util.Map;

/*
 * enum constants
 *
 */
public enum FixedWidthAnnotationEnum {

    FWFINSTANCE(
            Constants.FIXED_WIDTH_FORMAT_INSTANCE_ANNOTATION_SHORT_NAME,
            Constants.FIXED_WIDTH_FORMAT_INSTANCE_ANNOTATION_LONG_NAME,
            null
    ),
    FWFATTRIBUTE(
            Constants.FIXED_WIDTH_FORMAT_ATTRIBUTE_ANNOTATION_SHORT_NAME,
            Constants.FIXED_WIDTH_FORMAT_ATTRIBUTE_ANNOTATION_LONG_NAME,
            Constants.attributeKV
    ),

    FWFNESTEDATTRIBUTE(
            Constants.FIXED_WIDTH_FORMAT_NESTED_ATTRIBUTE_ANNOTATION_SHORT_NAME,
            Constants.FIXED_WIDTH_FORMAT_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME,
            null
    );


    private String annotationShortName;
    private String annotationLongName;

    private Map<String, String> annotationMethodNamesMap = new HashMap<>();

    /*
     * constructor for each enum constant
     * @param annotationShortName annotation short name
     * @param annotationLongName annotation long name
     * @param annotationMethodNamesMap annotation method names map
     *
     */
    FixedWidthAnnotationEnum(String annotationShortName, String annotationLongName, Map<String, String> annotationMethodNamesMap) {
        this.annotationShortName = annotationShortName;
        this.annotationLongName = annotationLongName;
        this.annotationMethodNamesMap = annotationMethodNamesMap;
    }

    /*
     * gets attribute annotationShortName
     *
     */
    public String getAnnotationShortName() {
        return annotationShortName;
    }

    /*
     * gets attribute annotationLongName
     *
     */
    public String getAnnotationLongName() {
        return annotationLongName;
    }

    /*
     * gets attribute annotationMethodNamesMap
     *
     */
    public String getMethod(String key) {
        return this.annotationMethodNamesMap.get(key);
    }
}
