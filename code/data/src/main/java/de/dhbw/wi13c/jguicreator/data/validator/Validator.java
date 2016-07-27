package de.dhbw.wi13c.jguicreator.data.validator;

public abstract class Validator<T>
{

	public abstract boolean validate(T object);

	public abstract String getMessage();
}