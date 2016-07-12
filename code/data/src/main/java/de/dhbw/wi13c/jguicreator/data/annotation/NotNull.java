package de.dhbw.wi13c.jguicreator.data.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * Size-Annotation:
 * Set field as a not nullable field.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface NotNull {
	String message() default "Field cannot be empty.";

}
