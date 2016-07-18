package de.dhbw.wi13c.jguicreator.data;

import javafx.scene.control.TextField;

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

}
