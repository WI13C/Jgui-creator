package de.dhbw.wi13c.jguicreator.data.validator;

/**
 * Base class for every validator.
 * @author lukashessenthaler
 *
 * @param <T>
 */
public abstract class Validator<T> implements Comparable<Validator<T>>
{

	public abstract boolean validate();

	public abstract String getMessage();
}
