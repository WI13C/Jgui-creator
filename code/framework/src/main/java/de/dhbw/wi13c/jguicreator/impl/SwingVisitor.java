package de.dhbw.wi13c.jguicreator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.GuiVisitor;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.elemente.BarChartPanel;
import de.dhbw.wi13c.jguicreator.elemente.PieChartPanel;
import de.dhbw.wi13c.jguicreator.elemente.SingleButton;
import de.dhbw.wi13c.jguicreator.elemente.TextFieldMitLabel;
import de.dhbw.wi13c.jguicreator.listener.GuiListener;

public class SwingVisitor extends GuiVisitor
{

	private MyGui myGui;

	public SwingVisitor(MyGui myGui)
	{
		this.myGui = myGui;
	}

	@Override
	public void visit(TextfieldData textfield)
	{

		if(textfield.getDatafield() != null)
		{

			GUIKomponente elem = new TextFieldMitLabel(textfield.getName(), (String) textfield.getDatafield().getValue(), textfield.getDatafield().isReadOnly(), myGui.getSettings(), textfield.getDatafield().getType());

			myGui.addElement(elem);
			System.out.println("Textfield: " + textfield.getName() + " | Value: " + textfield.getDatafield().getValue().toString());

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
				MyGui tempGui = new MyGui(dependentObject, new GuiListener[]{new GuiListener()
				{

					@Override
					public void guiSaved(DomainObject form)
					{
						// TODO Auto-generated method stub

					}

					@Override
					public void guiCanceled(DomainObject form)
					{
						// TODO Auto-generated method stub

					}
				}});
			}
		}, myGui.getSettings());
		myGui.addElement(sb);
		System.out.println("Abhängiges Object: " + dependentObject.getName());

	}

	@Override
	public void visit(DatepickerData datepicker)
	{
		//		JPanel p = new JPanel();
		//		p.setSize(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)),Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT)));
		//		GUIKomponente elem = new DatumComboBoxen("foo", "12", "12", "2012", false, settings);
		//		p.add(elem);
		//		myGui.addElement(p);
		//		System.out.println("Date");

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

}
