package de.dhbw.wi13c.jguicreator.data;


public abstract class GuiVisitor
{
	public abstract void visit(Textfield textfield);
	public abstract void visit(DomainObject dependentObject);
}
