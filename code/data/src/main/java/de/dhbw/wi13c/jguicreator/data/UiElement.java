package de.dhbw.wi13c.jguicreator.data;

public abstract class UiElement
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
