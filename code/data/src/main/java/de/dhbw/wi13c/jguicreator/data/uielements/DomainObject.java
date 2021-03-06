package de.dhbw.wi13c.jguicreator.data.uielements;

import de.dhbw.wi13c.jguicreator.data.UiElementContainer;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;

public class DomainObject extends UiElementData<Object>
{
	private UiElementContainer dataset;
	
	private Class<?> type;

	public DomainObject()
	{
		super();
		this.dataset = new UiElementContainer();
	}

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

	public UiElementContainer getUiElementContainer()
	{
		return dataset;
	}

	public void setUiElementContainer(UiElementContainer container)
	{
		this.dataset = container;
	}

	@Override
	protected void initDatafield()
	{
		setDatafield(new Datafield<Object>());
	}

	@Override
	public Object getValue()
	{
		return getDatafield().getValue();
	}

	@Override
	public void setValue(Object value)
	{
		getDatafield().setValue(value);		
	}

	public Class<?> getType()
	{
		return type;
	}

	public void setType(Class<?> type)
	{
		this.type = type;
	}

}
