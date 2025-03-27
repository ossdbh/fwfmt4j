package io.github.ossdbh.format.constants;

import java.util.HashMap;
import java.util.Map;

/*
 * Global constants
 *
 */
public interface Constants {
    public static final String FIXED_WIDTH_FORMAT_INSTANCE_ANNOTATION_SHORT_NAME = "FwfInstance";
    public static final String FIXED_WIDTH_FORMAT_INSTANCE_ANNOTATION_LONG_NAME = "io.github.ossdbh.format.annotation.FwfInstance";


    public static final String FIXED_WIDTH_FORMAT_ATTRIBUTE_ANNOTATION_SHORT_NAME = "FwfAttribute";
    public static final String FIXED_WIDTH_FORMAT_ATTRIBUTE_ANNOTATION_LONG_NAME = "io.github.ossdbh.format.annotation.FwfAttribute";

    public static final String FIXED_WIDTH_FORMAT_NESTED_ATTRIBUTE_ANNOTATION_SHORT_NAME = "FwfNestedAttribute";
    public static final String FIXED_WIDTH_FORMAT_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME = "io.github.ossdbh.format.annotation.FwfNestedAttribute";

    public static final String FIXED_WIDTH_FORMAT_ATTRIBUTE_METHOD_FORMAT_KEY = "FORMAT";
    public static final String FIXED_WIDTH_FORMAT_ATTRIBUTE_METHOD_FORMAT_VALUE = "format";

    public static final String JAVA_LANG_OBJECT_CLASSNAME = "java.lang.Object";
    public static final String JAVA_LANG_OBJECT_SIMPLECLASSNAME = "Object";

    public static final Map<String, String> attributeKV = new HashMap<String, String>() {{
        put(Constants.FIXED_WIDTH_FORMAT_ATTRIBUTE_METHOD_FORMAT_KEY, Constants.FIXED_WIDTH_FORMAT_ATTRIBUTE_METHOD_FORMAT_VALUE);
    }};


}
