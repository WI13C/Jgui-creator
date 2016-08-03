package de.dhbw.wi13c.jguicreator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.UiElementContainer;
import de.dhbw.wi13c.jguicreator.data.uielements.BarChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;
import de.dhbw.wi13c.jguicreator.data.uielements.DatepickerData;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.PieChartData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.test.Adresse;
import de.dhbw.wi13c.jguicreator.test.Kontakt;
import de.dhbw.wi13c.jguicreator.test.Person;

/**
 * Is used to generate mock data
 * @author Eric Schuh
 *
 */
public class MockParser implements Parser
{
	/**
	 * @return static Mock data
	 */
	public DomainObject parseObject(Object object)
	{
		return getMockData(object);
	}
	
	/**
	 * 
	 * @param object does not affect the returned data
	 * @return static mock data
	 */
	private DomainObject getMockData(Object object)
	{
		Adresse adresse = new Adresse("Freiburgerstraße", 4);

		List<Kontakt> kontaktdaten = new ArrayList<>();
		Kontakt privatKontakt = new Kontakt("privat", "016326632",
				"jens.mueller@pom.de");
		kontaktdaten.add(privatKontakt);

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
		UiElementContainer rootDataset = new UiElementContainer();
		
		try
		{
			TextfieldData textfield1 = new TextfieldData();
			Datafield<String> datafieldVorname = new Datafield<>();
			datafieldVorname.setField(person.getClass().getDeclaredField("vorname"));
			datafieldVorname.setValue(new String(person.getVorname()));
			textfield1.setDatafield(datafieldVorname);
			textfield1.setName("Vorname");
			rootDataset.getElements().add(textfield1);
			
			ComboBoxData geschlecht = new ComboBoxData();
			Datafield<Person.Geschlecht> datafieldGeschlecht = new Datafield<>();
			datafieldGeschlecht.setField(person.getClass().getDeclaredField("geschlecht"));
			datafieldGeschlecht.setValue(Person.Geschlecht.MANN);
			geschlecht.setDatafield(datafieldGeschlecht);
			geschlecht.setName("Gender");
			rootDataset.getElements().add(geschlecht);
			
			DatepickerData datum1 = new DatepickerData();
			Datafield<Date> datafieldGeburtstag = new Datafield<>();
			datafieldGeburtstag.setField(person.getClass().getDeclaredField("Geburtsdatum"));
			datafieldGeburtstag.setValue(person.getGeburtsdatum());
			datum1.setDatafield(datafieldGeburtstag);
			datum1.setName("Geburtstag");
			rootDataset.getElements().add(datum1);

			TextfieldData textfield2 = new TextfieldData();
			Datafield<String> datafieldNachname = new Datafield<>();
			datafieldNachname.setField(person.getClass().getDeclaredField("nachname"));
			datafieldNachname.setValue(person.getNachname());
			datafieldNachname.setReadOnly(true);
			textfield2.setDatafield(datafieldNachname);
			textfield2.setName("Nachname");
			rootDataset.getElements().add(textfield2);

			BarChartData einkommenBarChart = new BarChartData();
			einkommenBarChart.setName("Einkommen");
			Datafield<Map<String, ? extends Number>> einkommensData= new Datafield<Map<String, ? extends Number>>();
			einkommensData.setValue(einkommen);
			einkommenBarChart.setDatafield(einkommensData);
			rootDataset.getElements().add(einkommenBarChart);

			PieChartData entwicklungBarChart = new PieChartData();
			entwicklungBarChart.setName("Einkommensentwicklung");
			Datafield<Map<String, ? extends Number>>  entwicklungsData= new Datafield<Map<String, ? extends Number>>();
			entwicklungsData.setValue(einkommensentwicklung);
			entwicklungBarChart.setDatafield(entwicklungsData);
			rootDataset.getElements().add(entwicklungBarChart);

			DomainObject adresseDependentObject = new DomainObject();
			adresseDependentObject.setName("Adresse");
			rootDataset.getElements().add(adresseDependentObject);

			DomainObject kontaktPrivatDependentObject = new DomainObject();
			kontaktPrivatDependentObject.setName("Kontakte");
			rootDataset.getElements().add(kontaktPrivatDependentObject);

			UiElementContainer kontaktDataset = new UiElementContainer();
			kontaktPrivatDependentObject.setUiElementContainer(kontaktDataset);

			TextfieldData typTextfield = new TextfieldData();
			Datafield<String> typDatafield = new Datafield<>();
			typDatafield.setField(privatKontakt.getClass().getDeclaredField("typ"));
			typDatafield.setValue("wasmusshierrein");
			typTextfield.setDatafield(typDatafield);
			typTextfield.setName("Typ");
			kontaktDataset.getElements().add(typTextfield);

			TextfieldData telTextfield = new TextfieldData();
			Datafield<String> telDatafield = new Datafield<>();
			telDatafield.setField(privatKontakt.getClass().getDeclaredField("telefon"));
			telDatafield.setValue("09832");
			telTextfield.setDatafield(telDatafield);
			telTextfield.setName("Telefonnummer");
			kontaktDataset.getElements().add(telTextfield);

			TextfieldData emailTextfield = new TextfieldData();
			Datafield<String> emailDatafield = new Datafield<>();
			emailDatafield.setField(privatKontakt.getClass().getDeclaredField("email"));
			emailDatafield.setValue("mail@pornhub.com");
			emailTextfield.setDatafield(emailDatafield);
			emailTextfield.setName("Email");
			kontaktDataset.getElements().add(emailTextfield);

			UiElementContainer dependentDataset = new UiElementContainer();
			adresseDependentObject.setUiElementContainer(dependentDataset);

			TextfieldData strasseTextfield = new TextfieldData();
			strasseTextfield.setName("Straße");
			Datafield<String> strasseDatafield = new Datafield<>();
			strasseDatafield.setField(adresse.getClass().getDeclaredField("strasse"));
			strasseDatafield.setValue("Reperbahn");
			strasseTextfield.setDatafield(strasseDatafield);
			dependentDataset.getElements().add(strasseTextfield);

			TextfieldData hausnummerTextfield = new TextfieldData();
			hausnummerTextfield.setName("Hausnummer");
			Datafield<String> hausnummerDatafield = new Datafield<>();
			hausnummerDatafield.setField(adresse.getClass().getDeclaredField(
					"Hausnummer"));
			hausnummerDatafield.setValue("88");
			hausnummerTextfield.setDatafield(hausnummerDatafield);
			dependentDataset.getElements().add(hausnummerTextfield);

			rootObject.setUiElementContainer(rootDataset);

		}
		catch (NoSuchFieldException | SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rootObject;
	}

}
