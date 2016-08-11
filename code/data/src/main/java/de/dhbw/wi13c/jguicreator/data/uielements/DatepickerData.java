package de.dhbw.wi13c.jguicreator.data.uielements;

import java.util.Date;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class DatepickerData extends UiElementData<Date>
{
	public DatepickerData()
	{
		super();
	}
	
	public Date getDate()
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
		setDatafield(new Datafield<Date>());
	}

	@Override
	public Date getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void setValue(Date value)
	{
		getDatafield().setValue(value);
	}

}
