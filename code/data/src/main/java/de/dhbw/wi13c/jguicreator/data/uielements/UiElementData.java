package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public abstract class UiElementData
{
	private Datafield datafield;
	
	private String name;

	
	public UiElementData()
	{
		//TODO das Erzeugen des Datafields an dieser Stelle soll temporär sein und deshalb entfernt werden
		datafield = new Datafield();
		
	}
	
	public abstract void accept(GuiVisitor visitor);

	public Datafield getDatafield()
	{
		return datafield;
	}

	public void setDatafield(Datafield datafield)
	{
		this.datafield = datafield;
	}
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
