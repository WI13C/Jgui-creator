package de.dhbw.wi13c.jguicreator.data.uielements;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.dhbw.wi13c.jguicreator.data.validator.Validator;

public class Datafield<T>
{
	/**
	 * Constructor can only be called by classes of the package to deny the construction of Datafields with a wrong
	 * Type. Instead the Subclasses of UiElementData should specify the Type.
	 */
	Datafield()
	{

	}

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

		try
		{
			if(this.field != null && value != null)
			{
				boolean isAccessible = this.field.isAccessible();
				this.field.setAccessible(true);
				this.field.set(instance, value);
				this.field.setAccessible(isAccessible);
			}
		}
		catch(IllegalArgumentException e)
		{
			//			 TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DatafieldType getType()
	{
		return type;
	}

	public void setType(DatafieldType type)
	{
		this.type = type;
	}

	public List<Validator> getValidators()
	{
		return validators;
	}

	public void setValidators(List<Validator> validators)
	{
		if(validators != null)
			this.validators = validators;
	}

	/**
	 * Type indicating which Gui Component will be used
	 */
	private DatafieldType type;

	/**
	 * Set of Validators
	 */
	private List<Validator> validators = new ArrayList<>();

	public static enum DatafieldType
	{
		TEXT, NUMBER, INTEGER, DOUBLE, DATE, LIST;
		//TODO complete
	}

}
