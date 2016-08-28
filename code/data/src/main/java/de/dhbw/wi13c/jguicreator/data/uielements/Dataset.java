package de.dhbw.wi13c.jguicreator.data.uielements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

/**
 * Provides Data for Collections like Lists e.g. List of Kontaktdaten of Person
 * @author Eric Schuh
 *
 */
public class Dataset extends UiElementData<List<DomainObject>>
{
	private Map<String, DomainObject> elements;

	private Class<?> parameterizedType;

	public Dataset()
	{
		super();
		elements = new HashMap<String, DomainObject>();
	}

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	public Map<String, DomainObject> getElements()
	{
		return elements;
	}

	public void setElements(Map<String, DomainObject> elements)
	{
		this.elements = elements;
	}

	public void addElement(String key, DomainObject element)
	{
		this.elements.put(key, element);
	}

	@Override
	protected void initDatafield()
	{
		setDatafield(new Datafield<Map<String, DomainObject>>());
	}

	//	@Override
	//	public Map<String, DomainObject> getValue()
	//	{
	//		return getDatafield().getValue();
	//	}
	//
	//	@Override
	//	public void setValue(Map<String, DomainObject> value)
	//	{
	//		getDatafield().setValue(value);
	//	}

	public Class<?> getParameterizedType()
	{
		return parameterizedType;
	}

	public void setParameterizedType(Class<?> parameterizedType)
	{
		this.parameterizedType = parameterizedType;
	}

	@Override
	public List<DomainObject> getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void setValue(List<DomainObject> value)
	{
		getDatafield().setValue(value);
	}

}
