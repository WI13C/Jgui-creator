package de.dhbw.wi13c.jguicreator.impl;

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
import de.dhbw.wi13c.jguicreator.elemente.TextFieldMitLabel;


public class SwingVisitor extends GuiVisitor
{
	
	private MyGui myGui;
	Settings settings;
	

	public SwingVisitor(MyGui myGui)
	{
		this.myGui = myGui;
		settings = new Settings();
		settings.setSetting(Setting.WINDOWHEIGHT, "300");
	}

	@Override
	public void visit(TextfieldData textfield)
	{
		JPanel p = new JPanel();
		p.setSize(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)),Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT)));
		GUIKomponente elem = new TextFieldMitLabel(textfield.getName(), "VALUE", textfield.getDatafield().isReadOnly(), settings);
		p.add(elem);
		myGui.addElement(p);
		System.out.print("Textfeld: ");		
		System.out.println(textfield.getName());
		
	}

	@Override
	public void visit(DomainObject dependentObject)
	{
		System.out.println("Abhängiges Object");
	}

	@Override
	public void visit(DatepickerData datepicker)
	{
		System.out.println("Date");
		
	}

	@Override
	public void visit(ComboBoxData comboBox)
	{
		System.out.println("ComboBox");
		
	}

	@Override
	public void visit(BarChartData chart)
	{
		System.out.println("BarChart");
		
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
