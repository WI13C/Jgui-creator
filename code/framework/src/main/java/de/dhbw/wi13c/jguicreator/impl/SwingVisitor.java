package de.dhbw.wi13c.jguicreator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.elemente.BarChartPanel;
import de.dhbw.wi13c.jguicreator.elemente.DatumComboBoxen;
import de.dhbw.wi13c.jguicreator.elemente.PieChartPanel;
import de.dhbw.wi13c.jguicreator.elemente.SingleButton;
import de.dhbw.wi13c.jguicreator.elemente.TextFieldMitLabel;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

public class SwingVisitor extends GuiVisitor
{

	private IsGui myGui;

	private Map<UiElementData, GUIKomponente> elementConnector;

	public SwingVisitor(IsGui myGui)
	{
		this.myGui = myGui;
		elementConnector = new HashMap<>();
	}

	@Override
	public void visit(TextfieldData textfield)
	{

		if(textfield.getDatafield() != null)
		{

			GUIKomponente elem = new TextFieldMitLabel(textfield.getName(), (String) textfield.getDatafield().getValue(), textfield.getDatafield().isReadOnly(), myGui.getSettings(), textfield.getDatafield().getType());

			myGui.addElement(elem);
			System.out.println("Textfield: " + textfield.getName() + " | Value: " + textfield.getDatafield().getValue().toString());
			saveConnectorState(textfield, elem);
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

				Popup p = new Popup("Fooo", myGui.getFrame(), dependentObject, new SavedListener<DomainObject>()
				{
					@Override
					public void saved(DomainObject object)
					{
						System.out.println("saved");
					}
				});

				p.setVisible(true);
			}
		}, myGui.getSettings());
		myGui.addElement(sb);
		System.out.println("Abhängiges Object: " + dependentObject.getName());

	}

	@Override
	public void visit(DatepickerData datepicker)
	{
		
		DatumComboBoxen dcb = new DatumComboBoxen("Datum", "12", "12", "2342", false, myGui.getSettings());
		myGui.addElement(dcb);

	}

	@Override
	public void visit(ComboBoxData comboBox)
	{
		System.out.println("ComboBox");
	}

	@Override
	public void visit(BarChartData chart)
	{
		if(chart.getDatafield() != null)
		{
			BarChartPanel elem = new BarChartPanel(chart.getName(), (Map<String, ? extends Number>) chart.getDatafield().getValue(), myGui.getSettings());
			myGui.addElement(elem);
			elem.animate();
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
			elem.animate();
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
		System.out.println("Dataset");

	}

	private void saveConnectorState(UiElementData uiElementData, GUIKomponente guiKomponente)
	{
		elementConnector.put(uiElementData, guiKomponente);
	}

}
