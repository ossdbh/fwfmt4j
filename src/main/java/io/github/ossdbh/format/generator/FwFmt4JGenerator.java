package io.github.ossdbh.format.generator;

import io.github.ossdbh.format.constants.Constants;
import io.github.ossdbh.format.dto.FWValue;
import io.github.ossdbh.format.enums.FixedWidthAnnotationEnum;
import io.github.ossdbh.format.exception.FwFmt4jException;
import io.github.ossdbh.format.helper.AnnotationHelper;
import io.github.ossdbh.format.helper.GetterMethodDiscovererHelper;
import io.github.ossdbh.format.helper.XPathHelper;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Generates fixed width format String from any java bean
 * Supports, flat, nested, inherited, lombok and non-lombok java beans
 *
 */
public class FwFmt4JGenerator {

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @return returns fixed width formatted String. Record attributes are ordered implicitly based on the order in which they appear in the instances
     *
     */
    public static String generateFW(Object instance) {
        // Use default lombok based getter method discoverer
        return FwFmt4JGenerator.generateFW(instance, null, GetterMethodDiscovererHelper.defaultLombokBasedGetterMethod);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param getterMethod The getter method discovery function that would be used by the generator
     * @return returns fixed width formatted String. Record attributes are ordered implicitly based on the order in which they appear in the instances
     *
     */
    public static String generateFW(Object instance, Function<String, String> getterMethod) {
        return FwFmt4JGenerator.generateFW(instance, null, getterMethod);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip
     * @return returns fixed width formatted String. Record attributes are ordered implicitly based on the order in which they appear in the instances
     *
     */
    public static String generateFW(Object instance, List<String> fieldsToSkipList){
        return FwFmt4JGenerator.generateFW(instance, fieldsToSkipList, GetterMethodDiscovererHelper.defaultLombokBasedGetterMethod);
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have used to annotate an instance for easy fixed width record generation
     *
     * @param instance The object instance that is to be converted to fixed width format
     * @param fieldsToSkipList list of dot separated xPath style attributes to skip
     * @param getterMethod The getter method discovery function that would be used by the generator
     * @return returns fixed width formatted String. Record attributes are ordered implicitly based on the order in which they appear in the instances
     *
     */
    public static String generateFW(Object instance, List<String> fieldsToSkipList, Function<String,String> getterMethodDiscoverer) {
        Map<String, Integer> fieldsToSkipMap = new HashMap<>();

        if (fieldsToSkipList != null && fieldsToSkipList.size() > 0) {
            fieldsToSkipMap = fieldsToSkipList.stream().collect(Collectors.toMap(Function.identity(), String::length));
        }

        Stack<String> attrXPath = new Stack<>();
        List<FWValue> fwAttrList = new ArrayList<>();

        FwFmt4JGenerator.generateFWRecord(instance.getClass(), instance, fwAttrList, attrXPath, fieldsToSkipMap, getterMethodDiscoverer);

        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        fwAttrList.stream().forEach(e -> {
            if(e.getData() instanceof String) {
                formatter.format(e.getFmt(), StringUtils.defaultIfBlank((String) e.getData(), ""));
            } else {
                formatter.format(e.getFmt(), e.getData());
            }
        });

        return builder.toString();
    }

    /*
     * Depth First Traverses an instance using its type and builds a
     * Depth First Stack that holds Fixed Width Annotations that a user
     * may have added to annotate an instance for fixed width record generation
     *
     * @param clazz The class type that is being inspected
     * @param instance The actual instance that needs to be written in fixed width
     * @param fwAttrList Depth First Stack that holds the discovered field attribute values
     * @return none
     */
    private static void generateFWRecord(Class clazz, Object instance,
                                         List<FWValue> fwAttrList,
                                         Stack<String> attrXPath,
                                         Map<String, Integer> fieldsToSkipMap,
                                         Function<String,String> getterMethodDiscoverer) {
        if (AnnotationHelper.isFwfInstance(clazz)) {
            // Push the current class instance into XPath
            attrXPath.push(instance.getClass().getSimpleName());

            if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                // Start discovering class fields
                ClassFieldResolver classFieldResolver = new ClassFieldResolver();
                List<Field> declaredFields = new ArrayList<>();

                // Build a list of declared fields that the class passed in as param has declared
                classFieldResolver.getClassFields(clazz, new Stack<>(), new Stack<String>(), declaredFields, fieldsToSkipMap);

                for(int i = 0; i < declaredFields.size(); i++) {
                    Field f = declaredFields.get(i);
                    // Discover annotations on the field
                    Annotation[] fieldAnnotations = f.getDeclaredAnnotations();
                    if (fieldAnnotations != null && fieldAnnotations.length > 0) {
                        // Iterate over all field annotations
                        for (int j = 0; j < fieldAnnotations.length; j++) {
                            // Check if we have a field annotated as FwfAttribute
                            if (fieldAnnotations[j].annotationType().getName().equals(
                                    // This means this is a leaf node and is a candidate to be pushed onto
                                    // the depth first traversal stack (fwAttrList)
                                    FixedWidthAnnotationEnum.FWFATTRIBUTE.getAnnotationLongName())) {

                                attrXPath.push(f.getName());

                                if (!XPathHelper.skipField(attrXPath, fieldsToSkipMap)) {
                                    try {
                                        String formatStringDiscovered = (String)fieldAnnotations[j].annotationType()
                                                .getMethod(FixedWidthAnnotationEnum.FWFATTRIBUTE.getMethod(
                                                        Constants.FIXED_WIDTH_FORMAT_ATTRIBUTE_METHOD_FORMAT_KEY))
                                                .invoke(fieldAnnotations[j]);

                                        // The format String that we discovered, we would want to apply
                                        // on an actual value that would be provided by a lombok getter
                                        // Infer the getter name from the fieldname
                                        String getterMethodInferred = getterMethodDiscoverer.apply(f.getName());

                                        // Fetch the Method object from the current instance
                                        Method getter = clazz.getMethod(getterMethodInferred);
                                        //System.out.println("Calling getter " + getter.getName());

                                        // Invoke the method here
                                        Object methodReturned = getter.invoke(instance, null);

                                        // TODO: no default values


                                        //Infer data type
                                        Class methodClass = getter.getReturnType();
                                        // Build a FWValue instance that we would use later
                                        // to construct the record
                                        FWValue fwValue = new FWValue();
                                        fwValue.setFmt(formatStringDiscovered);
                                        fwValue.setMethod(getterMethodInferred);
                                        fwValue.setData(methodReturned);
                                        fwValue.setAttributeClass(methodClass);
                                        fwAttrList.add(fwValue);
                                    } catch (IllegalAccessException e) {
                                        throw new FwFmt4jException(e.getMessage(), e);
                                    } catch (InvocationTargetException e) {
                                        throw new FwFmt4jException(e.getMessage(), e);
                                    } catch (NoSuchMethodException e) {
                                        throw new FwFmt4jException(e.getMessage(), e);
                                    }
                                }

                                attrXPath.pop();
                            } else if (fieldAnnotations[j].annotationType().getName().equals(
                                    Constants.FIXED_WIDTH_FORMAT_NESTED_ATTRIBUTE_ANNOTATION_LONG_NAME)) {
                                // else if the field is marked as nested it means is has an FwfInstance annotation
                                // on the field
                                Class nestedType = f.getType();
                                // Check if this nested type was annotated with FwInstance annotation
                                if (AnnotationHelper.isFwfInstance(nestedType)) {
                                    // This means the nested type has an FwInstance annotation
                                    // We need to depth first travserse into the field
                                    // with the type of the nested field and the actual nested field instance

                                    // Lets infer the lombok getter method for this field
                                    String getterMethodInferred = getterMethodDiscoverer.apply(f.getName());

                                    try {
                                        // Fetch the Method object from the current instance
                                        Method getter = clazz.getMethod(getterMethodInferred);

                                        // Invoke the method, this will give us the actual instance
                                        // of the nested type
                                        Object nestedInstance = getter.invoke(instance, null);

                                        if (nestedInstance != null) {
                                            // Depth first traverse with the nested type and the actual
                                            // nested instance
                                            FwFmt4JGenerator.generateFWRecord(nestedType,
                                                    nestedInstance,
                                                    fwAttrList,
                                                    attrXPath,
                                                    fieldsToSkipMap,
                                                    getterMethodDiscoverer);
                                        }
                                    } catch (NoSuchMethodException e) {
                                        throw new FwFmt4jException(e.getMessage(), e);
                                    } catch (InvocationTargetException e) {
                                        throw new FwFmt4jException(e.getMessage(), e);
                                    } catch (IllegalAccessException e) {
                                        throw new FwFmt4jException(e.getMessage(), e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            attrXPath.pop();
        }
    }
}
