package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class ComboBoxData extends UiElementData<Enum>
{
	public ComboBoxData()
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
		setDatafield(new Datafield<Enum>());		
	}

	@Override
	public Enum getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void setValue(Enum value)
	{
		getDatafield().setValue(value);
	}

}
