package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class BarChartData extends UiElementData
{

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

}