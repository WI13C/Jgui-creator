package de.dhbw.wi13c.jguicreator.data.uielements;

import java.util.ArrayList;
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
	
	public Dataset()
	{
		//TODO muss hier statt einer ArrayList der Typ einer Collection erzeugt werden,
		//der im geparsten Objekt für den Datensatz verwendet wird?
		elements = new ArrayList<DomainObject>();
	}

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
