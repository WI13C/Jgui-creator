package de.dhbw.wi13c.jguicreator.data.validator;

public abstract class Validator<T> implements Comparable<Validator<T>>
{

	public abstract boolean validate();

	public abstract String getMessage();
}
