package de.dhbw.wi13c.jguicreator.data;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

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
