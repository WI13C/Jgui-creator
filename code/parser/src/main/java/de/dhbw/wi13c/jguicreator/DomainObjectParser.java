package de.dhbw.wi13c.jguicreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.validator.PatternValidator;
import de.dhbw.wi13c.jguicreator.data.validator.Validator;

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
		for(Field field : fields)
		{
			UiElementData uiElementData = null;

			if(isStringTextField(field))
			{
				uiElementData = createStringTextfield(field, object);
			}
			else
			{
				if(isNumberTextField(field))
				{
					uiElementData = createNumberTextfield(field, object);
				}
				else
				{
					if(isDate(field))
					{
						uiElementData = createDatePickerData(field, object);
					}
					else
					{
						if(isComboBox(field))
						{
							uiElementData = createComboBox(field, object);
						}
						else
						{
							boolean isAnnotation = false;
							for(Annotation annotation : field.getAnnotations())
							{
								isAnnotation = false;
								if(isBarChart(annotation))
								{
									uiElementData = createBarChartData(field, object);
									isAnnotation = true;
								}

								if(isPieChart(annotation))
								{
									uiElementData = createPieChartData(field, object);
									isAnnotation = true;
								}
							}

							if(isDataset(field/*, isAnnotation*/))
							{
								uiElementData = createDataset(field, object);
							}
							else
							{
								if(isDomainObject(field))
								{
									uiElementData = createDomainObject(field, object);
								}
							}
							
						}
					}
				}
			}

			if(uiElementData != null)
			{
				domainObject.getUiElementContainer().addElement(uiElementData);
				setUiElementName(field, uiElementData);

				//Setting the read only boolean
				boolean isFinal = Modifier.isFinal(field.getModifiers());
				uiElementData.getDatafield().setReadOnly(isFinal);

				parseValidators(uiElementData, field);
			}

		}
	}

	private void parseValidators(UiElementData uiElementData, Field field)
	{
		Set validators = uiElementData.getDatafield().getValidators();
		for(Annotation annotation : field.getDeclaredAnnotations())
		{
			if(annotation.annotationType().getTypeName()
				.equals(Pattern.class.getTypeName()))
			{
				validators.add(new PatternValidator(uiElementData));
			}

			if(annotation.annotationType().getTypeName()
				.equals(NotNull.class.getTypeName()))
			{
				//TODO Validator erstellen und zum datenobjekt hinzufügen
			}
			
			if(annotation.annotationType().getTypeName()
				.equals(Size.class.getTypeName()))
			{
				//TODO Validator erstellen und zum datenobjekt hinzufügen
			}
		}
		uiElementData.getDatafield().getValidators();
	}

	//TODO kann erweitert werden um bessere namen zu generieren
	//bspw. könnten wörter getrennt werden
	private void setUiElementName(Field field, UiElementData uiElementData)
	{
		String output = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
		uiElementData.setName(output);
	}

	private UiElementData createComboBox(Field field, Object object)
	{
		ComboBoxData comboBoxdata = new ComboBoxData();
		return comboBoxdata;
	}

	private Dataset createDataset(Field field, Object object)
	{
		//		System.out.println("dataset: " + field.getName() + " " + " class: "
		//			+ object.getClass().getSimpleName());

		//TODO Es wird eine Liste mit DomainObjects erstellt. Die Liste kann aber trotzdem "einfache" Klassen wie Integer enthalten,
		//die dann wie DomainObjects behandelt werden. Verursacht das Probleme?
		Dataset dataset = new Dataset();

		try
		{
			boolean isAccessible = field.isAccessible();
			field.setAccessible(true);
			Collection<?> col = (Collection<?>) field.get(object);
			for(Object colObj : col)
			{
				try
				{
					Object obj = colObj;
					//					System.out.println("------------ " + obj.getClass().getTypeName());
					DomainObject domainobject = new DomainObject();
					parseFields(obj.getClass().getDeclaredFields(), obj, domainobject);

					dataset.getElements().add(domainobject);
				}
				catch(SecurityException | IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			field.setAccessible(isAccessible);
		}
		catch(IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataset;
	}

	private DomainObject createDomainObject(Field field, Object object)
	{
		//		System.out.println("domainObj: " + field.getName());
		DomainObject domainObject = new DomainObject();

		try
		{
			field.setAccessible(true);
			Object obj = field.get(object);
			//			System.out.println("------------ " + obj.getClass().getTypeName());
			parseFields(field.getType().getDeclaredFields(), obj, domainObject);
		}
		catch(SecurityException | IllegalArgumentException
			| IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return domainObject;
	}

	private TextfieldData createStringTextfield(Field field, Object object)
	{
		//		System.out.println("stringtextfield: " + field.getName() + " class: "
		//			+ object.getClass().getSimpleName());

		TextfieldData textfieldData = new TextfieldData();
		return textfieldData;
	}

	private TextfieldData createNumberTextfield(Field field, Object object)
	{
		//		System.out.println("numbertextfield: " + field.getName() + " class: "
		//			+ object.getClass().getSimpleName());
		TextfieldData textfieldData = new TextfieldData();
		return textfieldData;
	}

	private DatepickerData createDatePickerData(Field field, Object object)
	{
		//		System.out.println("datepicker : " + field.getName() + " class: "
		//			+ object.getClass().getSimpleName());
		DatepickerData datepickerData = new DatepickerData();
		return datepickerData;
	}

	private BarChartData createBarChartData(Field field, Object object)
	{
		//		System.out.println("barchart: " + field.getName() + " class: "
		//			+ object.getClass().getSimpleName());
		BarChartData barChartData = new BarChartData();
		Datafield datafield = new Datafield<Map<String, ? extends Number>>(); //TODO es gibt auch andere maps als string-number
		try
		{
			field.setAccessible(true);
			datafield.setValue(field.get(object));
		}
		catch(IllegalArgumentException | IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		barChartData.setDatafield(datafield);
		return barChartData;
	}

	private PieChartData createPieChartData(Field field, Object object)
	{
		//		System.out.println("piechart: " + field.getName() + " class: "
		//			+ object.getClass().getSimpleName());
		PieChartData pieChartData = new PieChartData();
		return pieChartData;
	}

	private boolean isComboBox(Field field)
	{
		boolean isEnum = field.getType().isEnum();
		return isEnum;
	}

	private boolean isDate(Field field)
	{
		if(field.getType().equals(Date.class))
		{
			return true;
		}
		return false;
	}

	private boolean isDataset(Field field/*, boolean isAnnotation*/)
	{
		boolean isList = Collection.class.isAssignableFrom(field.getType());
		return /*!isAnnotation &&*/isList;
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

}
