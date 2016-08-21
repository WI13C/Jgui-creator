package de.dhbw.wi13c.jguicreator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.NumberTextFieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.elemente.BarChartPanel;
import de.dhbw.wi13c.jguicreator.elemente.DatumComboBoxen;
import de.dhbw.wi13c.jguicreator.elemente.ListCombo;
import de.dhbw.wi13c.jguicreator.elemente.PieChartPanel;
import de.dhbw.wi13c.jguicreator.elemente.SingleButton;
import de.dhbw.wi13c.jguicreator.elemente.TextFieldMitLabel;
import de.dhbw.wi13c.jguicreator.listener.AddEditRemoveListener;

/**
 * SwingVisitor is part of the visitor-pattern and decides which gui-element should get added to the gui.
 * 
 * @author Lukas Hessenthaler, Eric Schuh
 *
 */
public class SwingVisitor extends GuiVisitor
{

	private IsGui myGui;

	public SwingVisitor(IsGui myGui)
	{
		this.myGui = myGui;
	}

	@Override
	public void visit(TextfieldData textfield)
	{

		if(textfield.getDatafield() != null)
		{

			GUIKomponente elem = new TextFieldMitLabel(textfield.getName(), (String) textfield.getValue(), textfield.getDatafield().isReadOnly(), myGui.getSettings(), textfield);

			myGui.addElement(elem);
			//			System.out.println("Textfield: " + textfield.getName() + " | Value: " + textfield.getDatafield().getValue().toString());
			System.out.println("Textfield: " + textfield.getName() + " | Value: " + textfield.getValue());
		}
		else
		{
			//TODO nullpointer behandeln
		}

	}

	@Override
	public void visit(DomainObject dependentObject)
	{
		SingleButton sb = new SingleButton(dependentObject.getName(), new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				Popup p = new Popup("Fooo", myGui.getFrame(), dependentObject);

				p.setVisible(true);
			}
		}, myGui.getSettings());
		myGui.addElement(sb);
		System.out.println("Abhängiges Object: " + dependentObject.getName());

	}

	@Override
	public void visit(DatepickerData datepicker)
	{
		System.out.println("Date: " + datepicker.getName());
		DatumComboBoxen dcb = new DatumComboBoxen(datepicker.getName(), new GregorianCalendar(1994, 5, 5), false, myGui.getSettings());
		myGui.addElement(dcb);

	}

	@Override
	public void visit(ComboBoxData comboBox)
	{
		System.out.println("ComboBox: " + comboBox.getName());
		//		EnumComboBoxen ecb = new EnumComboBoxen(comboBox.getName(), comboBox.getDatafield().getType(), false, myGui.getSettings());
	}

	@Override
	public void visit(BarChartData chart)
	{
		if(chart.getDatafield() != null)
		{
			BarChartPanel elem = new BarChartPanel(chart.getName(), (Map<String, ? extends Number>) chart.getDatafield().getValue(), myGui.getSettings());
			myGui.addElement(elem);
			System.out.println("Barchart: " + chart.getName());
		}
		else
		{
			//TODO nullpointer behandeln
		}
	}

	@Override
	public void visit(PieChartData chart)
	{
		if(chart.getDatafield() != null)
		{
			PieChartPanel elem = new PieChartPanel(chart.getName(), (Map<String, ? extends Number>) chart.getDatafield().getValue(), myGui.getSettings());
			myGui.addElement(elem);
			System.out.println("Piechart: " + chart.getName());
		}
		else
		{
			//TODO nullpointer behandeln
		}

	}

	@Override
	public void visit(Dataset dataset)
	{
		System.out.println("Dataset: " + dataset.getName());

		List<String> keys = new ArrayList<>();
		for(String key : dataset.getElements().keySet())
		{
			keys.add(key);
		}
		ListCombo lc = new ListCombo(dataset.getName(), keys, new AddEditRemoveListener()
		{

			@Override
			public void remove(String key)
			{
				System.out.println("remove: " + key);
			}

			@Override
			public void edit(String key)
			{
				System.out.println("edit: " + key);
				Popup p = new Popup(key, myGui.getFrame(), dataset.getElements().get(key));
				p.setVisible(true);
			}

			@Override
			public void add()
			{
				System.out.println("add");

			}
		}, myGui.getSettings());
		myGui.addElement(lc);

	}

	@Override
	public void visit(NumberTextFieldData numberTextFieldData)
	{
		// TODO Auto-generated method stub

	}

}
