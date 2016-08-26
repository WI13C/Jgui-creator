package de.dhbw.wi13c.jguicreator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.DomainObjectParser;
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
import de.dhbw.wi13c.jguicreator.listener.ObjectSavedListener;

/**
 * SwingVisitor is part of the visitor-pattern and decides which gui-element should get added to the gui.
 * 
 * @author Lukas Hessenthaler, Eric Schuh
 *
 */
public class SwingVisitor extends GuiVisitor
{

	private IsGui myGui;
	
	/**
	 * Temp storage for keys belonging to the shown keys and the domain-object-dataset ...
	 */
	private Map<String, String> tempKeys;

	public SwingVisitor(IsGui myGui)
	{
		this.myGui = myGui;
		tempKeys = new HashMap<>();
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

		for(String key : dataset.getElements().keySet())
		{
			tempKeys.put(key, key);
		}
		ListCombo lc = new ListCombo(dataset.getName(), tempKeys.keySet(), dataset, myGui.getSettings());
		lc.AddAddEditRemoveListener(new AddEditRemoveListener()
		{

			@Override
			public void remove(String key)
			{
				System.out.println("remove: " + key);
				dataset.getElements().remove(key);
				tempKeys.remove(key);
				lc.updateListValue(tempKeys.keySet());
				lc.reflectData(dataset);
			}

			@Override
			public void edit(String key)
			{
				System.out.println("edit: " + key);
				Popup p = new Popup(key, myGui.getFrame(), dataset.getElements().get(key));
				
				p.setVisible(true);
				System.out.println("popup shown");
				lc.updateListValue(tempKeys.keySet());
			}

			@Override
			public void add()
			{
				System.out.println("add");

				/*
				 * I have yet to write a piece of code that i am more proud of than the following one.
				 * This code uses the first constructor which has the matching number of arguments
				 * to create a new instance. 
				 * The constructor is found in a brute force way by trying all number of arguments
				 * up to the number of the tries variable.
				 * @author totally not Eric
				 */
				boolean foundOne = false;
				int k = 0;
				Object createdObject = null;
				while(!foundOne && k < dataset.getParameterizedType().getConstructors().length)
				{
					Constructor constructor = dataset.getParameterizedType().getConstructors()[k];
					int tries = 100;
					int i = 0;
					while(!foundOne && i < tries)
					{
						Object[] objects = new Object[i];
						try
						{
							createdObject = constructor.newInstance(objects);
							foundOne = true;
						}
						catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
						{
							//nothing should happen here
						}
						i++;
					}
					k++;
				}

				if(createdObject != null)
				{
					DomainObjectParser dop = new DomainObjectParser();
					DomainObject dom = dop.parseObject(createdObject);
					Popup p = new Popup(dom.getName(), myGui.getFrame(), dom);
					p.addObjectSavedListener(new ObjectSavedListener()
					{
						
						@Override
						public void saved(DomainObject o)
						{
							//dirty, but works....
							dataset.getElements().put("new", o);
						}
					});
					p.setVisible(true);
				}
			}
		});
		myGui.addElement(lc);

	}

	@Override
	public void visit(NumberTextFieldData numberTextFieldData)
	{
		// TODO Auto-generated method stub

	}

}
