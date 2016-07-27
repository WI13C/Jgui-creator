package de.dhbw.wi13c.jguicreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;

import de.dhbw.wi13c.jguicreator.data.UiElementContainer;
import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;

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

		parseFields(object.getClass().getDeclaredFields(), object, rootObject); // TODO
																	// getDeclaredFields()
																	// doesn't
																	// consider
																	// inherited
																	// fields

		return rootObject;
	}

	// TODO gibts da eine schönere lösung als 100 if/else?
	// TODO bei der implementierung der Methoden, die in dieser Methode aufgerufen werden, wird der Kontext des Aufrufes in dieser Methode
	// als gegeben betrachtet. Beispielsweise wird bei isNumberTextField() angenommen, dass es sich nicht um einen String handelt,
	// da sich der Aufruf im else-Block von isStringTextField() befindet. Die Methoden sollten also in keinem anderen Kontext verwendet werden.
	private void parseFields(Field[] fields, Object object, DomainObject domainObject)
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

	private Dataset createDataset(Field field, Object object)
	{
		System.out.println("dataset: " + field.getName() + " " + " class: "
				+ object.getClass().getSimpleName());
		
		//TODO Es wird eine Liste mit DomainObjects erstellt. Die Liste kann aber trotzdem "einfache" Klassen wie Integer enthalten,
		//die dann wie DomainObjects behandelt werden. Verursacht das Probleme?
		Dataset dataset = new Dataset();

		try
		{
			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);
			Collection<?> col = (Collection<?>) field.get(object);
			for (Object colObj : col)
			{
				try
				{
					Object obj = colObj;
//					System.out.println("------------ " + obj.getClass().getTypeName());
					DomainObject domainobject = new DomainObject();
					parseFields(obj.getClass().getDeclaredFields(), obj, domainobject);

					dataset.getElements().add(domainobject);
				}
				catch (SecurityException | IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			field.setAccessible(isAccessible);
		}
		catch (IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataset;
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

	private DomainObject createDomainObject(Field field, Object object)
	{
		System.out.println("domainObj: " + field.getName());
		DomainObject domainObject = new DomainObject();
		
		try
		{
			field.setAccessible(true);
			Object obj = field.get(object);
//			System.out.println("------------ " + obj.getClass().getTypeName());
			parseFields(field.getType().getDeclaredFields(), obj, domainObject);
		}
		catch (SecurityException | IllegalArgumentException
				| IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return domainObject;
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
//		Class<?> c = field.getType();
//		boolean instance = c.isInstance(Number.class);
		Class<?> classNumber = Number.class;
		Class<?> classField = field.getType();
		boolean instance = classNumber.isAssignableFrom(classField);
		boolean isClass = field.getName().equals("clazz");
		return instance && !isClass;
	}

	private TextfieldData createStringTextfield(Field field, Object object)
	{
		System.out.println("stringtextfield: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
		
		TextfieldData textfieldData = new TextfieldData();
		return textfieldData;
		
		
	}

	private TextfieldData createNumberTextfield(Field field, Object object)
	{
		System.out.println("numbertextfield: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
		TextfieldData textfieldData = new TextfieldData();
		return textfieldData;
	}

	private DatepickerData createDatePickerData(Field field, Object object)
	{
		System.out.println("datepicker : " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
		DatepickerData datepickerData = new DatepickerData();
		return datepickerData;
	}

	private boolean isDate(Field field)
	{
		if (field.getType().equals(Date.class))
		{
			return true;
		}
		return false;
	}

	private BarChartData createBarChartData(Field field, Object object)
	{
		System.out.println("barchart: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
		BarChartData barChartData = new BarChartData();
		return barChartData;
	}

	private PieChartData createPieChartData(Field field, Object object)
	{
		System.out.println("piechart: " + field.getName() + " class: "
				+ object.getClass().getSimpleName());
		PieChartData pieChartData = new PieChartData();
		return pieChartData;
	}


}
