package io.github.ossdbh.format.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Used to annotate a java class specifying that the
 * class has attributes annotated with FwfAttribute for
 * generating fixed width string
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface FwfInstance {
    String value() default "";
}
