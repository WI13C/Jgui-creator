package de.dhbw.wi13c.jguicreator.data;

public class Textfield extends UiElement
{

	@Override
	public void accept(GuiVisitor visitor)
	{
		visitor.visit(this);
	}

}
