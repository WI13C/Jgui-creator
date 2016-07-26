package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

/**
 * Provides Data for Collections like Lists e.g. List of Kontaktdaten of Person
 * @author Eric Schuh
 *
 */
public class Dataset extends UiElementData
{

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

}
