package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class TextfieldData extends UiElementData
{
	private String name;

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
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
