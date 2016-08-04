package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class TextfieldData extends UiElementData<String>
{
	public TextfieldData()
	{
		
	}

	public void setValue(String value)
	{
		getDatafield().setValue(value);
	}
	
	public String getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	@Override
	protected void initDatafield()
	{
		setDatafield(new Datafield<String>());
	}


}
