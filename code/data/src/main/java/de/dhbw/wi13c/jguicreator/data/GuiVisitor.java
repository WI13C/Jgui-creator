package de.dhbw.wi13c.jguicreator.data;

import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;


public abstract class GuiVisitor
{
	public abstract void visit(TextfieldData textfield);
	public abstract void visit(DomainObject dependentObject);
	public abstract void visit(DatepickerData datepicker);
	public abstract void visit(ComboBoxData comboBox);
	public abstract void visit(BarChartData chart);
	public abstract void visit(PieChartData chart);
}