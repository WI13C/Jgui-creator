package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class NumberTextFieldData extends UiElementData<Number>
{
	public NumberTextFieldData()
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
		setDatafield(new Datafield<Number>());
	}

	@Override
	public Number getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void setValue(Number value)
	{
		getDatafield().setValue(value);
	}

}
