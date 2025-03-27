package io.github.ossdbh.format.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Used to annotate a field in a java class that
 * itself is a java object. Subsequently that nested object
 * should also be annotated with FwfInstance
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FwfNestedAttribute {}
