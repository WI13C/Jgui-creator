package de.dhbw.wi13c.jguicreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.UiElementContainer;
import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.test.Adresse;
import de.dhbw.wi13c.jguicreator.test.Kontakt;
import de.dhbw.wi13c.jguicreator.test.Person;

public class DomainObjectParser implements Parser
{

	/**
	 * @return incomplete {@link DomainObject} for now
	 * @author Eric
	 */
	@Override
	public DomainObject parseObject(Object object)
	{
		DomainObject rootObject = new DomainObject();

		parseFields(object.getClass().getDeclaredFields(), object); // TODO
																	// getDeclaredFields()
																	// doesn't
																	// consider
																	// inherited
																	// fields

		return rootObject;
	}

	// TODO gibts da eine schönere lösung als 100 if/else?
	private void parseFields(Field[] fields, Object object)
	{
		for (Field field : fields)
		{
			if (isStringTextField(field))
			{
				createStringTextfield(field, object);
			}
			else
			{
				if (isNumberTextField(field))
				{
					createNumberTextfield(field, object);
				}
				else
				{
					if (isDate(field))
					{
						createDatePickerData(field, object);
					}
					else
					{
						boolean isAnnotation = false;
						for (Annotation annotation : field.getAnnotations())
						{
							isAnnotation = false;
							if (isBarChart(annotation))
							{
								createBarChartData(field, object);
								isAnnotation = true;
							}

							if (isPieChart(annotation))
							{
								createPieChartData(field, object);
								isAnnotation = true;
							}
						}
						
						if(isDataset(field/*, isAnnotation*/))
						{
							createDataset(field, object);
						}
						else
						{
							if (isDomainObject(field))
							{
								createDomainObject(field, object);
							}
						}
					}
				}
			}
		}
	}

	private void createDataset(Field field, Object object)
	{
		System.out.println("dataset: " + field.getName() + " " + " class: "
				+ object.getClass().getSimpleName());
		
		//TODO Elemente der collection parsen. Für diese muss wiederum geprüft werden, ob es sich um textfelder, domainobjects etc. handelt
	}

	private boolean isDataset(Field field/*, boolean isAnnotation*/)
	{
		boolean isList = Collection.class.isAssignableFrom(field.getType());
		return /*!isAnnotation &&*/ isList;
	}

	/*
	 * TODO If no previous condition matched and the name of the field type does
	 * not start with java, sun or [B, the field type is assumed to be a complex
	 * domain object. This may not be a correct assumption in every case.
	 */
	private boolean isDomainObject(Field field)
	{
		// System.out.println("------- " +field.getType().getName());
		boolean isJavaStandard = field.getType().getName().startsWith("java")
				|| field.getType().getName().startsWith("sun")
				|| field.getType().getName().startsWith("[B");
		boolean isPrimitive = field.getType().isPrimitive();
		return !isJavaStandard && !isPrimitive;
	}

	private void createDomainObject(Field field, Object object)
	{
		System.out.println("domainObj: " + field.getName());

		try
		{
			field.setAccessible(true);
			Object obj = field.get(object);
			System.out.println("------------ " + obj.getClass().getTypeName());
			parseFields(field.getClass().getDeclaredFields(), obj);
		}
		catch (SecurityException | IllegalArgumentException
				| IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isPieChart(Annotation annotation)
	{
		return annotation.annotationType().getTypeName()
				.equals(PieChart.class.getTypeName());
	}

	private boolean isBarChart(Annotation annotation)
	{
		return annotation.annotationType().getTypeName()
				.equals(BarChart.class.getTypeName());
	}

	private boolean isStringTextField(Field field)
	{
		boolean equals = field.getType().getName()
				.equals(String.class.getName());
		boolean isSignature = field.getName().equals("signature");

		return equals && !isSignature;
	}

	private boolean isNumberTextField(Field field)
	{
		boolean instance = field.getType().isInstance(Number.class);
		boolean isClass = field.getName().equals("clazz");
		return instance && !isClass;
	}

	private void createStringTextfield(Field field, Object object)
	{
		System.out.println("stringtextfield: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
	}

	private void createNumberTextfield(Field field, Object object)
	{
		System.out.println("numbertextfield: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
	}

	private void createDatePickerData(Field field, Object object)
	{
		System.out.println("datepicker : " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
	}

	private boolean isDate(Field field)
	{
		if (field.getType().equals(Date.class))
		{
			return true;
		}
		return false;
	}

	private void createBarChartData(Field field, Object object)
	{
		System.out.println("barchart: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
	}

	private void createPieChartData(Field field, Object object)
	{
		System.out.println("piechart: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
	}


}
