package de.dhbw.wi13c.jguicreator.data.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author lukashessenthaler
 * Id:
 * An annotated field will be handled like a primary-key.
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface Id {

}
