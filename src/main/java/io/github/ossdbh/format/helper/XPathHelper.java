package io.github.ossdbh.format.helper;

import java.util.Map;
import java.util.Stack;

/*
 * Helper class for XPath related support
 *
 */
public class XPathHelper {

    /*
     * Generates an xPath structure from the recursion stack
     * @param attrXPath field tokens discovered during depth first traversal across a java class
     *
     */
    public static String getXPath(Stack<String> attrXPath) {
        StringBuilder builder = new StringBuilder();
        if (attrXPath != null && !attrXPath.isEmpty()) {
            attrXPath.stream().forEach(e -> {
                builder.append(e);
                builder.append(".");
            });
        }

        String xPath = builder.toString();
        if (xPath.charAt(xPath.length() - 1) == '.') {
            xPath = xPath.substring(0, xPath.length() - 1);
        }
        return xPath;
    }

    /*
     * checks if field name tokens held in a dft stack is present in a user configured fields to skip map
     * @param attrXPath field tokens discovered during depth first traversal across a java class
     * @param fieldsToSkipMap hashmap that has keys as XPath representation of field names to be skipped
     */
    public static boolean skipField(Stack<String> attrXPath, Map<String, Integer> fieldsToSkipMap) {
        String xP = ".";
        if (fieldsToSkipMap != null && fieldsToSkipMap.entrySet() != null && fieldsToSkipMap.entrySet().size() > 0) {
            // If we have fields to skip generate an xPath
            xP = XPathHelper.getXPath(attrXPath);
            if (fieldsToSkipMap.containsKey(xP)) {
                // If the xpath matches any of the fields to skip return true to skip the field
                return true;
            } else {
                // If the xpath does not match any of the fields to skip return false to process the field
                return false;
            }
        } else {
            return false;
        }
    }
}
