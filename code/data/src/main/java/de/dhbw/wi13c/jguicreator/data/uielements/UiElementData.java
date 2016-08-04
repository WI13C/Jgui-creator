package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public abstract class UiElementData<T>
{
	private Datafield<T> datafield;
	
	private String name;

	public UiElementData()
	{
		initDatafield();
	}
	
	protected abstract void initDatafield();
	
	public abstract void accept(GuiVisitor visitor);

	public Datafield<T> getDatafield()
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
