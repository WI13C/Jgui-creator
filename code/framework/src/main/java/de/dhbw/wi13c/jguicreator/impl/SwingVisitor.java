package de.dhbw.wi13c.jguicreator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
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
import de.dhbw.wi13c.jguicreator.elemente.DatumComboBoxen;
import de.dhbw.wi13c.jguicreator.elemente.SingleButton;
import de.dhbw.wi13c.jguicreator.elemente.TextFieldMitLabel;

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
		//		JPanel p = new JPanel();
		//		p.setSize(Integer.valueOf(myGui.getSettings().getSetting(Setting.WINDOWWIDTH)),Integer.valueOf(myGui.getSettings().getSetting(Setting.WINDOWHEIGHT)));
		
		if(textfield.getDatafield() != null)
		{
			GUIKomponente elem = new TextFieldMitLabel(textfield.getName(), "VALUE", textfield.getDatafield().isReadOnly(), myGui.getSettings());
			//		p.add(elem);
			myGui.addElement(elem);
			System.out.print("Textfeld: ");
			System.out.println(textfield.getName());
	
		}
		else
		{
			//TODO nullpointer behandeln
		}
		
	}

	@Override
	public void visit(DomainObject dependentObject)
	{
		SingleButton sb = new SingleButton("dependentObject", new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
			}
		}, myGui.getSettings());
		myGui.addElement(sb);
		System.out.println("Abhängiges Object");
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
			BarChartPanel elem = new BarChartPanel("Name vom Chart", (Map<String, ? extends Number>) chart.getDatafield().getValue(), myGui.getSettings());
			myGui.addElement(elem);
			elem.animate();
			System.out.println("Barchart: ");	
		}
		else
		{
			//TODO nullpointer behandeln
		}
	}

	@Override
	public void visit(PieChartData chart)
	{
		System.out.println("PieChart");

	}

	@Override
	public void visit(Dataset dataset)
	{
		System.out.println("Dataset");

	}

}
