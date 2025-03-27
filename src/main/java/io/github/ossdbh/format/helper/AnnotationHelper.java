package io.github.ossdbh.format.helper;

import io.github.ossdbh.format.enums.FixedWidthAnnotationEnum;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/*
 * Helper class for annotation processing
 *
 */
public class AnnotationHelper {

    /*
     * checks if a class is annotated as FwfInstance
     *
     * @param clazz class instance that needs to be checked
     * @return boolean true if the class was annotated as FwfInstance
     *
     */
    public static boolean isFwfInstance(Class clazz) {
        return AnnotationHelper.isFwfInstance(clazz, FixedWidthAnnotationEnum.FWFINSTANCE.getAnnotationLongName());
    }

    /*
     * checks if a class is annotated as FwfInstance
     *
     * @param clazz class instance that needs to be checked
     * @param annotationCheck string representation of the annotation to check
     * @return boolean true if the class was annotated as FwfInstance
     *
     */
    public static boolean isFwfInstance(Class clazz, String annotationCheck) {
        // List class-level annotations
        Annotation[] cl_annotations = clazz.getAnnotations();

        // Lets create a map of annotation name to annotation type
        Map<String, Annotation> classAnnotationMap = new HashMap<>();
        for (int i = 0; i < cl_annotations.length; i++) {
            classAnnotationMap.put(cl_annotations[i].annotationType().getName(), cl_annotations[i]);
        }

        if (classAnnotationMap.containsKey(annotationCheck)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * infers getter method name
     *
     * @param fieldName field whose getter method needs to be inferred
     * @return getter method name of the field
     *
     */
    public static String inferGetter(String fieldName) {
        String prefix = "get";
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        builder.append(fieldName.substring(0,1).toUpperCase());
        builder.append(fieldName.substring(1));
        return builder.toString();
    }
}
