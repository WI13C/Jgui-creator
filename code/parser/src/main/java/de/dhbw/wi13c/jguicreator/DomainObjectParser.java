package de.dhbw.wi13c.jguicreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.dhbw.wi13c.jguicreator.data.ErrorHandler;
import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.data.annotation.FieldLabel;
import de.dhbw.wi13c.jguicreator.data.annotation.Id;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.Datafield;
import de.dhbw.wi13c.jguicreator.data.uielements.Dataset;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.NumberTextFieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.uielements.Datafield.DatafieldType;
import de.dhbw.wi13c.jguicreator.data.validator.NotNullValidator;
import de.dhbw.wi13c.jguicreator.data.validator.PatternValidator;
import de.dhbw.wi13c.jguicreator.data.validator.SizeValidator;
import de.dhbw.wi13c.jguicreator.data.validator.Validator;

public class DomainObjectParser implements Parser
{

	/**
	 * @author Eric
	 */
	@Override
	public DomainObject parseObject(Object object)
	{
		DomainObject rootObject = new DomainObject();
		rootObject.getDatafield().setInstance(object); //The instance of the Datafield of the rootObject is the rootObject itself

		parseFields(object.getClass().getDeclaredFields(), object, rootObject); // TODO
		// getDeclaredFields()
		// doesn't
		// consider
		// inherited
		// fields

		return rootObject;
	}

	/**
	 * Parses the fields of the class of the object parameter. 
	 * @param fields the fields which are parsed 
	 * @param object the instance which is parsed
	 * @param domainObject the DomainObject which the parsed data is added to
	 */
	private void parseFields(Field[] fields, Object object, DomainObject domainObject)
	{
		for(Field field : fields)
		{
			boolean foundOne = false;
			UiElementData uiElementData = null;
			
			boolean fieldAccessible = true;
			
			try
			{
				if(object != null && field != null)
				{
					field.setAccessible(true);
					Object o = field.get(object);
				}
				
			} catch (IllegalArgumentException | IllegalAccessException e)
			{
				ErrorHandler.showError("field "+field.getName()+" is not accessible."
						+ "The code 'field.get(object)' failed");
				fieldAccessible = false;
			}
			
			if(fieldAccessible)
			{
				try
				{
					
					if(!foundOne && isStringTextField(field))
					{
						uiElementData = createStringTextfield(field, object);
						foundOne = true;
					}
		
					if(!foundOne && isNumberTextField(field))
					{
						uiElementData = createNumberTextfield(field, object);
						foundOne = true;
					}
		
					if(!foundOne && isDate(field))
					{
						uiElementData = createDatePickerData(field, object);
						foundOne = true;
					}
		
					if(!foundOne && isComboBox(field))
					{
						uiElementData = createComboBox(field, object);
						foundOne = true;
		
					}
		
					if(!foundOne && !isDataset(field) && !isDomainObject(field))
					{
						for(Annotation annotation : field.getAnnotations())
						{
							if(isBarChart(annotation))
							{
								uiElementData = createBarChartData(field, object);
								foundOne = true;
							}
		
							if(isPieChart(annotation))
							{
								uiElementData = createPieChartData(field, object);
								foundOne = true;
							}
						}
		
					}
		
					if(!foundOne && isDataset(field))
					{
						uiElementData = createDataset(field, object);
						foundOne = true;
					}
		
					if(!foundOne && isDomainObject(field))
					{
						uiElementData = createDomainObject(field, object);
						foundOne = true;
					}
		
					if(uiElementData != null)
					{
						setupUiElementData(object, domainObject, field, uiElementData);
					}
					else
					{
						ErrorHandler.showError("field "+field.getName()+" in class "+object.getClass().getName()+" was not parsed.");
						if(!foundOne)
						{
							ErrorHandler.showError("the datatype "+field.getType().getSimpleName()+" is not supported by the framework." );
						}
								
					}
				}
				catch(IllegalArgumentException e)
				{
					ErrorHandler.showError("illegal argument provided for field "+field.getName());
				}
				catch(IllegalAccessException e)
				{
					ErrorHandler.showError("field "+field.getName()+" is not accessible.");
				}
				catch(SecurityException e)
				{
					ErrorHandler.showError("Security Exception for field "+field.getName());
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Initializes a UiElementData instance by:
	 * <ol>
	 * <li>Adding it to its DomainObject</li>
	 * <li>Setting its name</li>
	 * <li>Setting the read only flag</li>
	 * <li>Setting the 'field', 'value' and 'instance' values to its Datafield</li>
	 * <li>Parsing and setting the validators</li>
	 * </ol>
	 * 
	 * @param object
	 * @param domainObject
	 * @param field
	 * @param uiElementData
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void setupUiElementData(Object object, DomainObject domainObject, Field field, UiElementData uiElementData) throws IllegalArgumentException, IllegalAccessException
	{
		domainObject.getUiElementContainer().addElement(uiElementData);
		setUiElementName(field, uiElementData);

		// Setting the read only boolean
		boolean isFinal = Modifier.isFinal(field.getModifiers());
		uiElementData.getDatafield().setReadOnly(isFinal);

		uiElementData.getDatafield().setField(field);
		uiElementData.getDatafield().setInstance(object);

		field.setAccessible(true);

		if(object != null)
		{
			uiElementData.getDatafield().setValue(field.get(object));	
		}

		parseValidators(uiElementData, field);
	}

	private void parseValidators(UiElementData uiElementData, Field field)
	{
		List<Validator> validators = uiElementData.getDatafield().getValidators();
		for(Annotation annotation : field.getDeclaredAnnotations())
		{
			if(annotation.annotationType().getTypeName().equals(Pattern.class.getTypeName()))
			{
				Pattern pattern = (Pattern) annotation;
				validators.add(new PatternValidator(uiElementData, pattern.regexp()));
			}

			if(annotation.annotationType().getTypeName().equals(NotNull.class.getTypeName()))
			{
				validators.add(new NotNullValidator(uiElementData));
			}

			if(annotation.annotationType().getTypeName().equals(Size.class.getTypeName()))
			{
				Size sizeAnno = (Size) annotation;
				validators.add(new SizeValidator(uiElementData, sizeAnno.min(), sizeAnno.max()));
			}
		}

		// set Validators to datafield
		uiElementData.getDatafield().setValidators(validators);
	}

	/**
	 * If a FieldLabel annotation was set to the field, 
	 * the name of that annotation is set as name for the UiElement instance.
	 * If that annotation was not set, a name is made up from the name of the field.   
	 * @param field
	 * @param uiElementData
	 */
	private void setUiElementName(Field field, UiElementData uiElementData)
	{
		FieldLabel declaredAnnotation = field.getDeclaredAnnotation(FieldLabel.class);
		if(declaredAnnotation != null)
		{
			uiElementData.setName(declaredAnnotation.name());
		}
		else
		{
			//Can be extended to generate better Names e.g. by splitting words
			String output = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			uiElementData.setName(output);
		}
	}

	private UiElementData createComboBox(Field field, Object object)
	{
		ComboBoxData comboBoxdata = new ComboBoxData();
		return comboBoxdata;
	}

	private Dataset createDataset(Field field, Object object) throws IllegalAccessException, IllegalArgumentException, SecurityException
	{
		// System.out.println("dataset: " + field.getName() + " " + " class: "
		// + object.getClass().getSimpleName());

		// Es wird eine Liste mit DomainObjects erstellt. Die Liste kann
		// aber trotzdem "einfache" Klassen wie Integer enthalten,
		// die dann wie DomainObjects behandelt werden.
		Dataset dataset = new Dataset();

		boolean isAccessible = field.isAccessible();
		field.setAccessible(true);
		Collection<?> collection = (Collection<?>) field.get(object);
		
		//Get the parameterized type of the collection
		//e.g. Person in case of List<Person>
		//The type can't be abstract. This implementation doesn't support inheritance.
		ParameterizedType collectionType = (ParameterizedType) field.getGenericType();
		Class<?> collectionClass = (Class<?>) collectionType.getActualTypeArguments()[0];
		
		if(Modifier.isAbstract( collectionClass.getModifiers() ))
		{
			ErrorHandler.showError("type of collection "+field.getName()+" can't be abstract.");
			return null;
		}
		
		dataset.setParameterizedType(collectionClass);
		
		if(collection == null)
		{
			ErrorHandler.showError("collection "+field.getName()+" was not initialized.");
			return null;
		}
		else
		{
			int i = 0;
			for(Object colObj : collection)
			{
				i++;
				Object obj = colObj;
				// System.out.println("------------ " +
				// obj.getClass().getTypeName());
				DomainObject domainobject = new DomainObject();
				
				
				parseFields(obj.getClass().getDeclaredFields(), obj, domainobject);

				String key = "";
				for(Field subField : obj.getClass().getDeclaredFields())
				{
					Id declaredIdAnnotation = subField.getDeclaredAnnotation(Id.class);
					if(declaredIdAnnotation != null)
					{
						subField.setAccessible(true);
						Object value = subField.get(colObj);
						key = value.toString();
					}
				}

				if(key.equals(""))
				{
					dataset.getElements().put(i + "", domainobject);
				}
				else
				{
					dataset.getElements().put(key, domainobject);
				}
			}

			field.setAccessible(isAccessible);
		}

		return dataset;
	}

	private DomainObject createDomainObject(Field field, Object object) throws SecurityException, IllegalArgumentException, IllegalAccessException
	{
		// System.out.println("domainObj: " + field.getName());
		DomainObject domainObject = new DomainObject();
		


		field.setAccessible(true);
		Object obj = field.get(object);
		
		if(obj == null)
		{
			ErrorHandler.showError("domain object in field "+field.getName()+" was not initialized.");
			return null;
		}
		else
		{
			parseFields(field.getType().getDeclaredFields(), obj, domainObject);
		}

		return domainObject;
	}

	private TextfieldData createStringTextfield(Field field, Object object)
	{
		// System.out.println("stringtextfield: " + field.getName() + " class: "
		// + object.getClass().getSimpleName());

		TextfieldData textfieldData = new TextfieldData();
		textfieldData.getDatafield().setType(DatafieldType.TEXT);
		return textfieldData;
	}

	private NumberTextFieldData createNumberTextfield(Field field, Object object)
	{
		// System.out.println("numbertextfield: " + field.getName() + " class: "
		// + object.getClass().getSimpleName());
		
		NumberTextFieldData textfieldData = new NumberTextFieldData();
		if(field.getType().equals(Integer.class) || field.getType().equals(int.class))
		{
			textfieldData.getDatafield().setType(DatafieldType.INTEGER);
		}
		
		if(field.getType().equals(Double.class) || field.getType().equals(double.class))
		{
			textfieldData.getDatafield().setType(DatafieldType.DOUBLE);
		}
		
		return textfieldData;
	}

	private DatepickerData createDatePickerData(Field field, Object object)
	{
		// System.out.println("datepicker : " + field.getName() + " class: "
		// + object.getClass().getSimpleName());
		DatepickerData datepickerData = new DatepickerData();
		return datepickerData;
	}

	private BarChartData createBarChartData(Field field, Object object) throws IllegalArgumentException, IllegalAccessException
	{
		// System.out.println("barchart: " + field.getName() + " class: "
		// + object.getClass().getSimpleName());
		BarChartData barChartData = new BarChartData();

		Datafield datafield = barChartData.getDatafield();
		field.setAccessible(true);
		datafield.setValue(field.get(object));
		barChartData.setDatafield(datafield);
		return barChartData;
	}

	private PieChartData createPieChartData(Field field, Object object)
	{
		// System.out.println("piechart: " + field.getName() + " class: "
		// + object.getClass().getSimpleName());
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

	private boolean isDataset(Field field)
	{
		boolean isList = Collection.class.isAssignableFrom(field.getType());
		return isList;
	}

	/*
	 * If no previous condition matched and the name of the field type does
	 * not start with java, sun or [B, the field type is assumed to be a complex
	 * domain object. This may not be a correct assumption in every case.
	 */
	private boolean isDomainObject(Field field)
	{
		// System.out.println("------- " +field.getType().getName());
		boolean isArray = field.getType().isArray();
		boolean isJavaStandard = field.getType().getName().startsWith("java") || field.getType().getName().startsWith("sun") || field.getType().getName().startsWith("[B");
		boolean isPrimitive = field.getType().isPrimitive();
		return !isJavaStandard && !isPrimitive && !isArray;
	}

	private boolean isPieChart(Annotation annotation)
	{
		return annotation.annotationType().getTypeName().equals(PieChart.class.getTypeName());
	}

	private boolean isBarChart(Annotation annotation)
	{
		return annotation.annotationType().getTypeName().equals(BarChart.class.getTypeName());
	}

	private boolean isStringTextField(Field field)
	{
		boolean equals = field.getType().getName().equals(String.class.getName());
		boolean isSignature = field.getName().equals("signature");

		return equals && !isSignature;
	}

	private boolean isNumberTextField(Field field)
	{
		Class<?> classNumber = Number.class;
		Class<?> classField = field.getType();
		boolean assignablePrimitiveInt = int.class.isAssignableFrom(classField);
		boolean assignablePrimitiveDouble = double.class.isAssignableFrom(classField);
		boolean assignableNumber = classNumber.isAssignableFrom(classField);
		boolean isAssignable = assignableNumber || assignablePrimitiveInt || assignablePrimitiveDouble;
		boolean isClass = field.getName().equals("clazz");
		return isAssignable && !isClass;
	}

}
