package de.dhbw.wi13c.jguicreator.data;

import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.NumberTextFieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;

/**
 * Base class for classes which generate GUIs by using the 
 * <a href='https://de.wikipedia.org/wiki/Besucher_(Entwurfsmuster)'>visitor pattern</a>
 * @author Eric Schuh, Doku Lukas Hessenthaler
 *
 */
public abstract class GuiVisitor
{
	/**
	 * Creates the Element for displaying and reflecting simple data like strings
	 * @param textfield
	 */
	public abstract void visit(TextfieldData textfield);

	/**
	 * Creates the Element for displaying and reflecting complex objects which contains simple data/other objects
	 * @param dependentObject
	 */
	public abstract void visit(DomainObject dependentObject);

	/**
	 * Creates the Element for displaying and reflecting dates
	 * @param datepicker
	 */
	public abstract void visit(DatepickerData datepicker);

	/**
	 * Creates the Element for displaying and reflecting enum fields
	 * @param comboBox
	 */
	public abstract void visit(ComboBoxData comboBox);

	/**
	 * Creates the Element for displaying Bar-Charts
	 * @param chart
	 */
	public abstract void visit(BarChartData chart);

	/**
	 * Creates the Element for displaying Pie-Charts
	 * @param chart
	 */
	public abstract void visit(PieChartData chart);

	/**
	 * Creates the Element for displaying and reflecting complex lists containing objects
	 * @param dataset
	 */
	public abstract void visit(Dataset dataset);

	/**
	 * Creates the Element for displaying and reflecting Number-Data
	 * @param numberTextFieldData
	 */
	public abstract void visit(NumberTextFieldData numberTextFieldData);
}
