package io.github.ossdbh.format.dto;

/*
 * Each java attribute in a class that is annotated with
 * FwfInstance, FwfAttribute and FwfNestedAttribute that subsequently
 * has to be converted to a fixed width string is internally represented
 * as an FWValue instance
 *
 */
public class FWValue {
    private String fmt = "";


    private String method = "";


    private Object data = null;

    private Class attributeClass;

    /*
     * gets attribute fmt
     *
     */
    public String getFmt() {
        return fmt;
    }

    /*
     * sets attribute fmt
     *
     * @param fmt string representation of fixed width format
     *
     */
    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    /*
     * gets attribute method
     *
     */
    public String getMethod() {
        return method;
    }

    /*
     * sets attribute method
     *
     * @param method string representation of method to be invoked
     *
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /*
     * gets attribute data
     *
     */
    public Object getData() {
        return data;
    }

    /*
     * sets attribute data
     *
     * @param data string representation of data
     *
     */
    public void setData(Object data) {
        this.data = data;
    }

    /*
     * gets attribute attributeClass
     *
     */
    public Class getAttributeClass() {
        return attributeClass;
    }

    /*
     * sets attribute attributeClass
     *
     * @param attributeClass string representation of attribute class
     *
     */
    public void setAttributeClass(Class attributeClass) {
        this.attributeClass = attributeClass;
    }
}
