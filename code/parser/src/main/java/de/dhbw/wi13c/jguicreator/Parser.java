package de.dhbw.wi13c.jguicreator;

import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;

public interface Parser
{

	/**
	 * This method will parse the given Object to the own Datatype defined in the data module.
	 * 
	 * The following Datatypes must be handled
	 * - String/Integer/Double
	 * - Date
	 * - Enum
	 * - Object as new Form
	 * 
	 * - List<Object> as Dataset
	 * - Map<String, ? extends Number> as Chart (Pie / Bar)
	 * 
	 * The following modifiers must be handled
	 * - final as read only field
	 * 
	 * The following annotations must be handled
	 * @NotNull
	 * @Size
	 * @Pattern
	 * 
	 * 
	 * @param object 
	 * @return {@link DomainObject}
	 */
	public DomainObject parseObject(Object object);

	public static Parser getDefaultParser()
	{
		return new DomainObjectParser();
	}

}
