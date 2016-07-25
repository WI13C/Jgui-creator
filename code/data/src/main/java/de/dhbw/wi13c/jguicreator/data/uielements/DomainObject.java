package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.UiElementContainer;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class DomainObject extends UiElementData
{
	private UiElementContainer dataset;

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	public UiElementContainer getUiElementContainer()
	{
		return dataset;
	}

	public void setUiElementContainer(UiElementContainer dataset)
	{
		this.dataset = dataset;
	}

}
