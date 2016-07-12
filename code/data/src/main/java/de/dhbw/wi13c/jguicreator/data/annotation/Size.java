package de.dhbw.wi13c.jguicreator.data.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/*
 * Size-Annotation:
 * Set minmum and maximum content-length of a target.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Size
{
	String message() default "Size of field doesn't match min and max value.";

	/**
	 * @return size the element must be higher or equal to
	 */
	int min() default 0;

	/**
	 * @return size the element must be lower or equal to
	 */
	int max() default Integer.MAX_VALUE;

}
