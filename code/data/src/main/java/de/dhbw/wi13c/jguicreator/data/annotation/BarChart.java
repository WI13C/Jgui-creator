package de.dhbw.wi13c.jguicreator.data.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author lukashessenthaler
 * BarChart:
 * An annotated map-field will be displayed as a bar-chart.
 *
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface BarChart {

}
