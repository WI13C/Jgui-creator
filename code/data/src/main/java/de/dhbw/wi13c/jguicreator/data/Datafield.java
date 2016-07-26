package de.dhbw.wi13c.jguicreator.data;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

import de.dhbw.wi13c.jguicreator.data.validator.Validator;

public class Datafield<T>
{
	
	/**
	 * Field to Reflect Data back to the original object
	 */
	private Field field;
	
	/**
	 * Instance of the original Object
	 */
	private Object instance;
	
	/**
	 * Flag indicating read only
	 */
	private boolean readOnly;
	
	/**
	 * current Value
	 */
	private T value;
	
	public Field getField()
	{
		return field;
	}

	public void setField(Field field)
	{
		this.field = field;
	}

	public Object getInstance()
	{
		return instance;
	}

	public void setInstance(Object instance)
	{
		this.instance = instance;
	}

	public boolean isReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(boolean readOnly)
	{
		this.readOnly = readOnly;
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public DatafieldType getType()
	{
		return type;
	}

	public void setType(DatafieldType type)
	{
		this.type = type;
	}

	public Set<Validator> getValidators()
	{
		return validators;
	}

	/**
	 * Type indicating which Gui Component will be used
	 */
	private DatafieldType type;
	
	/**
	 * Set of Validators
	 */
	private final Set<Validator> validators = new TreeSet<>();
	
	public static enum DatafieldType
	{
		TEXT,
		NUMBER,
		DATE,
		LIST;
		//TODO complete
	}

}
