package io.github.ossdbh.format.exception;

/*
 * Checked Exception that the caller needs to catch
 *
 */
public class FwFmt4jException extends RuntimeException {

    /*
     * no argument constructor
     *
     */
    public FwFmt4jException() { super(); }

    /*
     * constructor that accepts exception message
     * @param message string representation of exception message
     *
     */
    public FwFmt4jException(String message) { super(message);}

    /*
     * constructor that accepts instance of Throwable
     * @param t Throwable instance of an exception that the library catches and rethrows as FwFmt4jException
     *
     */
    public FwFmt4jException(Throwable t) { super(t);}

    /*
     * constructor that accepts instance of Throwable
     * @param message string representation of exception message
     * @param t Throwable instance of an exception that the library catches and rethrows as FwFmt4jException
     *
     */
    public FwFmt4jException(String message, Throwable t) { super(message, t); }
}
