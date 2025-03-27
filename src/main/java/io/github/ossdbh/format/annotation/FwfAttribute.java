package io.github.ossdbh.format.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Used to annotate a field in specifying that its to be formatted
 * as fixed width using the format string passed as parameter to the
 * annotation
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FwfAttribute {
    String format() default "";
}
