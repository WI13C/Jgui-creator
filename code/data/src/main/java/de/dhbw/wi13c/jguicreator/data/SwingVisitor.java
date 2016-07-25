package de.dhbw.wi13c.jguicreator.data;

import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;


public class SwingVisitor extends GuiVisitor
{

	@Override
	public void visit(TextfieldData textfield)
	{
		System.out.println("Textfeld");		
	}

	@Override
	public void visit(DomainObject dependentObject)
	{
		System.out.println("Abhängiges Object");
	}

	@Override
	public void visit(DatepickerData datepicker)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ComboBoxData comboBox)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BarChartData chart)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PieChartData chart)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Dataset dataset)
	{
		// TODO Auto-generated method stub
		
	}

}
