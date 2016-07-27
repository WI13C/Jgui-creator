package de.dhbw.wi13c.jguicreator.data.uielements;

import java.util.Collection;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

/**
 * Provides Data for Collections like Lists e.g. List of Kontaktdaten of Person
 * @author Eric Schuh
 *
 */
public class Dataset extends UiElementData
{
	private Collection<DomainObject> elements;

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	public Collection<DomainObject> getElements()
	{
		return elements;
	}

	public void setElements(Collection<DomainObject> elements)
	{
		this.elements = elements;
	}

}
