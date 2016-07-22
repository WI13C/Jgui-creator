package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public abstract class UiElementData
{
	private Datafield datafield;
	
	public abstract void accept(GuiVisitor visitor);

	public Datafield getDatafield()
	{
		return datafield;
	}

	public void setDatafield(Datafield datafield)
	{
		this.datafield = datafield;
	}
}
