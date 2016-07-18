package de.dhbw.wi13c.jguicreator.data;


public abstract class GuiVisitor
{
	public abstract void visit(Textfield textfield);
	public abstract void visit(DomainObject dependentObject);
	public abstract void visit(Datepicker datepicker);
	public abstract void visit(ComboBox comboBox);
	public abstract void visit(Chart chart);
}
