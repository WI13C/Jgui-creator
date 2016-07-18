package de.dhbw.wi13c.jguicreator.data;

public class ComboBox extends UiElement
{

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

}
