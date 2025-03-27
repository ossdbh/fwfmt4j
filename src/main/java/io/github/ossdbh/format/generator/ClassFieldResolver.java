package io.github.ossdbh.format.generator;

import io.github.ossdbh.format.constants.Constants;
import io.github.ossdbh.format.helper.XPathHelper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
 * This class walks up the inheritance tree discovering fields
 * It skips java.lang.object class as far as attribute discovery is concerned
 *
 */
public class ClassFieldResolver {

    /*
     * Accepts a class and walks the inheritance tree discovering fields
     * Skips fields that the caller wants to skip.
     * Skip fields follow a "." separated XPath like syntax
     * @param clazz the class instance to walk the inheritance tree
     * @param classFieldResolverStack depth first stack that holds each class instance visited during traversal
     * @param xAttrPath depth first stack that holds XPath for fields and classes discovered during traversal
     * @param fieldsToSkipMap fields to skip specified as a String key in the map. Nested fields are specified using XPath like syntax with "." as delimiter
     */
    public void getClassFields(Class clazz,
                               Stack<Class> classFieldResolverStack,
                               Stack<String> xAttrPath,
                               List<Field> classFieldsList,
                               Map<String, Integer> fieldsToSkipMap) {
        classFieldResolverStack.push(clazz);
        xAttrPath.push(clazz.getSimpleName());

        if (clazz.getSuperclass() != null) {
            this.getClassFields(clazz.getSuperclass(),
                    classFieldResolverStack,
                    xAttrPath,
                    classFieldsList,
                    fieldsToSkipMap);
        }

        // Process the current class at top of stack
        // and only process the class if its not java.lang.Object
        if (!clazz.getSimpleName().equals(Constants.JAVA_LANG_OBJECT_SIMPLECLASSNAME)) {
            // peek the class
            Class peekedClazz = classFieldResolverStack.peek();

            // fetch all fields of the class
            Field[] poppedClazzFields = peekedClazz.getDeclaredFields();

            for (int i = 0; i < poppedClazzFields.length; i++) {
                // Push the field name into xAttrPath
                xAttrPath.push(poppedClazzFields[i].getName());

                // check if the xAttrPath to this field is part of fieldsToSkipMap or not
                if (!XPathHelper.skipField(xAttrPath, fieldsToSkipMap)) {
                    // Only add this field if it is not in fieldsToSkipMap
                    classFieldsList.add(poppedClazzFields[i]);

                    // System.out.println("Field to be processed: " + poppedClazzFields[i].getName());
                } else {
                    // System.out.println("Skipping Field: " + poppedClazzFields[i].getName());
                }

                // pop the field after it has been processes
                if (!xAttrPath.empty()) {
                    xAttrPath.pop();
                }
            }
        }
        if (!xAttrPath.empty()) {
            xAttrPath.pop();
        }
        if (!classFieldResolverStack.empty()) {
            classFieldResolverStack.pop();
        }
    }
}
