package de.dhbw.wi13c.jguicreator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.Dataset;
import de.dhbw.wi13c.jguicreator.data.annotation.PieChart;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.test.Adresse;
import de.dhbw.wi13c.jguicreator.data.annotation.BarChart;
import de.dhbw.wi13c.jguicreator.test.Kontakt;
import de.dhbw.wi13c.jguicreator.test.Person;

public class DomainObjectParser implements Parser
{

	/**
	 * @return simple mock data for now
	 * @author Eric
	 */
	@Override
	public DomainObject parseObject(Object object)
	{
		DomainObject rootObject = new DomainObject();

		parseFields(object.getClass().getDeclaredFields(), object); // doesn't
																	// return
																	// inherited
																	// fields

		return rootObject;
		// return getMockData(object);
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

						if (isDomainObject(field, isAnnotation))
						{
							createDomainObject(field, object);
						}
					}
				}
			}
		}
	}

	/*
	 * TODO If no previous condition matched and the name of the field type does
	 * not start with java.lang, the field type is assumed to be a complex
	 * domain object. This may not be a correct assumption in every case.
	 */
	private boolean isDomainObject(Field field, boolean isCustomAnnotation)
	{
		// System.out.println("------- " +field.getType().getName());
		boolean isJavaStandard = field.getType().getName().startsWith("java")
				|| field.getType().getName().startsWith("sun")
				|| field.getType().getName().startsWith("[B");
		boolean isPrimitive = field.getType().isPrimitive();
		return !isCustomAnnotation && !isJavaStandard && !isPrimitive;
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
		System.out.println("stringtextfield: " + field.getName() +" class: "+object.getClass().getSimpleName());
	}

	private void createNumberTextfield(Field field, Object object)
	{
		System.out.println("numbertextfield: " + field.getName() +" class: "+object.getClass().getSimpleName());
	}

	private void createDatePickerData(Field field, Object object)
	{
		System.out.println("datepicker : " + field.getName() +" class: "+object.getClass().getSimpleName());
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
		System.out.println("barchart: " + field.getName()  +" class: "+object.getClass().getSimpleName());
	}

	private void createPieChartData(Field field, Object object)
	{
		System.out.println("piechart: " + field.getName() +" class: "+object.getClass().getSimpleName());
	}

	private DomainObject getMockData(Object object)
	{
		Adresse adresse = new Adresse("Freiburgerstraße", 4);

		List<Kontakt> kontaktdaten = new ArrayList<>();
		kontaktdaten.add(new Kontakt("privat", "016326632",
				"jens.mueller@pom.de"));

		Map<String, Integer> einkommen = new HashMap<>();
		einkommen.put("Zinsen", new Integer(5000));
		einkommen.put("Gehalt", new Integer(4000));

		Map<String, Integer> einkommensentwicklung = new HashMap<>();
		einkommensentwicklung.put("Januar", new Integer(2000));
		einkommensentwicklung.put("Februar", new Integer(2300));
		einkommensentwicklung.put("März", new Integer(2700));

		Person person = new Person("Jens", "Müller", 5, new Date(1994, 5, 15),
				adresse, kontaktdaten, einkommen, einkommensentwicklung);

		DomainObject rootObject = new DomainObject();
		Datafield<Person> datafield = new Datafield<>();
		datafield.setInstance(person);
		rootObject.setDatafield(datafield);
		Dataset rootDataset = new Dataset();

		try
		{
			TextfieldData textfield = new TextfieldData();
			Datafield<String> datafieldVorname = new Datafield<>();
			datafieldVorname.setField(person.getClass().getField("vorname"));
			textfield.setDatafield(datafieldVorname);
			textfield.setName("Vorname");
			rootDataset.getElements().add(textfield);

			TextfieldData textfield1 = new TextfieldData();
			Datafield<String> datafieldNachname = new Datafield<>();
			datafieldNachname.setField(person.getClass().getField("nachname"));
			textfield.setDatafield(datafieldNachname);
			textfield.setName("Nachname");
			rootDataset.getElements().add(textfield1);

			BarChartData einkommenBarChart = new BarChartData();
			rootDataset.getElements().add(einkommenBarChart);

			PieChartData entwicklungBarChart = new PieChartData();
			rootDataset.getElements().add(entwicklungBarChart);

			DomainObject adresseDependentObject = new DomainObject();
			rootDataset.getElements().add(adresseDependentObject);

			DomainObject kontaktPrivatDependentObject = new DomainObject();
			rootDataset.getElements().add(kontaktPrivatDependentObject);

			Dataset kontaktDataset = new Dataset();
			kontaktPrivatDependentObject.setDataset(kontaktDataset);

			TextfieldData typTextfield = new TextfieldData();
			Datafield<String> typDatafield = new Datafield<>();
			typDatafield.setField(person.getClass().getField("typ"));
			textfield.setDatafield(typDatafield);
			typTextfield.setName("Typ");
			kontaktDataset.getElements().add(typTextfield);

			TextfieldData telTextfield = new TextfieldData();
			Datafield<String> telDatafield = new Datafield<>();
			telDatafield.setField(person.getClass().getField("telefon"));
			textfield.setDatafield(telDatafield);
			telTextfield.setName("Telefonnummer");
			kontaktDataset.getElements().add(telTextfield);

			TextfieldData emailTextfield = new TextfieldData();
			Datafield<String> emailDatafield = new Datafield<>();
			emailDatafield.setField(person.getClass().getField("email"));
			textfield.setDatafield(emailDatafield);
			emailTextfield.setName("Email");
			kontaktDataset.getElements().add(emailTextfield);

			Dataset dependentDataset = new Dataset();
			adresseDependentObject.setDataset(dependentDataset);

			TextfieldData strasseTextfield = new TextfieldData();
			strasseTextfield.setName("Straße");
			Datafield<String> strasseDatafield = new Datafield<>();
			strasseDatafield.setField(person.getClass().getField("strasse"));
			textfield.setDatafield(strasseDatafield);
			dependentDataset.getElements().add(strasseTextfield);

			TextfieldData hausnummerTextfield = new TextfieldData();
			hausnummerTextfield.setName("Hausnummer");
			Datafield<String> hausnummerDatafield = new Datafield<>();
			hausnummerDatafield.setField(person.getClass().getField(
					"Hausnummer"));
			textfield.setDatafield(hausnummerDatafield);
			dependentDataset.getElements().add(hausnummerTextfield);

			rootObject.setDataset(rootDataset);

		}
		catch (NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootObject;
	}

}
