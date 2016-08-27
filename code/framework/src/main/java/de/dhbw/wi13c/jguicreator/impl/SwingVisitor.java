package de.dhbw.wi13c.jguicreator.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;

import de.dhbw.wi13c.jguicreator.DomainObjectParser;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;
import de.dhbw.wi13c.jguicreator.data.annotation.Id;
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
import de.dhbw.wi13c.jguicreator.elemente.EnumComboBoxen;
import de.dhbw.wi13c.jguicreator.elemente.ListCombo;
import de.dhbw.wi13c.jguicreator.elemente.NumberTextFieldMitLabel;
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

	public SwingVisitor(IsGui myGui)
	{
		this.myGui = myGui;
	}

	@Override
	public void visit(TextfieldData textfield)
	{

		GUIKomponente elem = new TextFieldMitLabel(textfield.getName(), (String) textfield.getValue(), textfield.getDatafield().isReadOnly(), myGui.getSettings(), textfield);

		myGui.addElement(elem);
		//			System.out.println("Textfield: " + textfield.getName() + " | Value: " + textfield.getDatafield().getValue().toString());
		System.out.println("Textfield: " + textfield.getName() + " | Value: " + textfield.getValue());

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
		Calendar tempCal = new GregorianCalendar();
		tempCal.setTime(datepicker.getValue());
		DatumComboBoxen dcb = new DatumComboBoxen(datepicker.getName(), tempCal, false, datepicker, myGui.getSettings());
		myGui.addElement(dcb);

	}

	@Override
	public void visit(ComboBoxData comboBox)
	{
		System.out.println("ComboBox: " + comboBox.getName());
		Field f;
		try
		{
			f = comboBox.getDatafield().getInstance().getClass().getField(comboBox.getName().toLowerCase());
			
		}
		catch(NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		//		List<T> list = Collections.list(comboBox.getDatafield().getInstance().getClass());
		EnumComboBoxen ecb = new EnumComboBoxen(comboBox.getName(), Arrays.asList("String A", "String B", "String C"), comboBox.getDatafield().isReadOnly(), myGui.getSettings());
		myGui.addElement(ecb);
	}

	@Override
	public void visit(BarChartData chart)
	{

		BarChartPanel elem = new BarChartPanel(chart.getName(), (Map<String, ? extends Number>) chart.getDatafield().getValue(), myGui.getSettings());
		myGui.addElement(elem);
		System.out.println("Barchart: " + chart.getName());

	}

	@Override
	public void visit(PieChartData chart)
	{

		PieChartPanel elem = new PieChartPanel(chart.getName(), (Map<String, ? extends Number>) chart.getDatafield().getValue(), myGui.getSettings());
		myGui.addElement(elem);
		System.out.println("Piechart: " + chart.getName());

	}

	@Override
	public void visit(Dataset dataset)
	{
		System.out.println("Dataset: " + dataset.getName());
		dataset.getElements().keySet();
		ListCombo lc = new ListCombo(dataset.getName(), dataset.getElements().keySet(), dataset, myGui.getSettings());
		lc.AddAddEditRemoveListener(new AddEditRemoveListener()
		{

			@Override
			public void remove(String key)
			{
				System.out.println("remove: " + key);
				dataset.getElements().remove(key);
				lc.updateListValue(dataset.getElements().keySet());
			}

			@Override
			public void edit(String key)
			{
				System.out.println("edit: " + key);
				Popup p = new Popup(key, myGui.getFrame(), dataset.getElements().get(key));

				p.setVisible(true);

				//Scanning for @Id Annotations to recreate the temp-datastructure
				DomainObject dom = dataset.getElements().get(key);

				Object o = dom.getDatafield().getInstance();
				String parsedKey = getParsedKey(o);

				DomainObject tmpDom = dataset.getElements().remove(key);
				dataset.getElements().put(parsedKey, tmpDom);
				lc.updateListValue(dataset.getElements().keySet());
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
						public void saved(DomainObject dom)
						{
							//Scanning for @Id Annotations to recreate the temp-datastructure
							Object o = dom.getDatafield().getInstance();
							String parsedKey = getParsedKey(o);

							dataset.getDatafield().getValue().add(dom);
							dataset.getElements().put(parsedKey, dom);
							lc.reflectData(dataset);
							lc.updateListValue(dataset.getElements().keySet());
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
		GUIKomponente elem = new NumberTextFieldMitLabel(numberTextFieldData.getName(), numberTextFieldData.getValue(), numberTextFieldData.getDatafield().isReadOnly(), myGui.getSettings(), numberTextFieldData);
		System.out.println("Type: " + numberTextFieldData.getDatafield().getType());
		myGui.addElement(elem);
		System.out.println("NumberTextfield: " + numberTextFieldData.getName() + " | Value: " + numberTextFieldData.getValue());

	}

	/**
	 * Returns the value of the {@link Id} annotated Object
	 * @param o the Object wich should be annotated with {@link Id}
	 * @return the parsed value of the {@link Id} annotated field
	 * Credits to erix parser
	 */
	private String getParsedKey(Object o)
	{

		Object value = new Object();

		for(Field subField : o.getClass().getDeclaredFields())
		{
			Id declaredIdAnnotation = subField.getDeclaredAnnotation(Id.class);
			if(declaredIdAnnotation != null)
			{
				subField.setAccessible(true);

				try
				{
					value = subField.get(o);

				}
				catch(IllegalArgumentException | IllegalAccessException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return value.toString();

	}

}
