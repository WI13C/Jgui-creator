package de.dhbw.wi13c.jguicreator.data;


public class SwingVisitor extends GuiVisitor
{

	@Override
	public void visit(Textfield textfield)
	{
		System.out.println("Textfeld");		
	}

	@Override
	public void visit(DomainObject dependentObject)
	{
		System.out.println("Abhängiges Object");
	}

	@Override
	public void visit(Datepicker datepicker)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ComboBox comboBox)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Chart chart)
	{
		// TODO Auto-generated method stub
		
	}

}
