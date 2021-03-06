package de.dhbw.wi13c.jguicreator.data.uielements;

import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class PieChartData extends UiElementData<Map<String, ? extends Number>>
{
	public PieChartData()
	{
		super();
	}

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	@Override
	protected void initDatafield()
	{
		setDatafield(new Datafield<Map<String, ? extends Number>>());
	}

	@Override
	public Map<String, ? extends Number> getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void setValue(Map<String, ? extends Number> value)
	{
		getDatafield().setValue(value);
	}

}
