package de.dhbw.vs.jprakt.exam.testclasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.DomainObjectParser;
import de.dhbw.wi13c.jguicreator.impl.GuiCreator;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

public class Showcase
{

	public static void main(String[] args)
	{

		Adresse adresse = new Adresse("Hauptstrasse", 30);
		Kontakt privat = new Kontakt("privat", "0160123456", "ferdi@prov.de");
		Kontakt business = new Kontakt("business", "0173555666", "ferdi@busy.com");

		List<Kontakt> kontakte = new ArrayList<Kontakt>();
		kontakte.add(privat);
		kontakte.add(business);

		Map<String, Integer> einkommensZusammensetzung = new HashMap<String, Integer>();
		einkommensZusammensetzung.put("Miete", 20000);
		einkommensZusammensetzung.put("Gehalt", 35000);
		einkommensZusammensetzung.put("Zinsen", 15000);

		Map<String, Double> einkommensEntwicklung = new HashMap<String, Double>();
		einkommensEntwicklung.put("2010", 27323.54);
		einkommensEntwicklung.put("2011", 29263.12);
		einkommensEntwicklung.put("2012", 33131.64);
		einkommensEntwicklung.put("2013", 34123.12);

		Person aPerson = new Person("Hans", "Maier", new Integer(3), new Date(1980, 3, 24), adresse, kontakte, einkommensZusammensetzung, einkommensEntwicklung, 1.23);

		System.out.println(aPerson.getGeburtsdatum().getTime());
		aPerson.setGeburtsdatum(new Date(80, 3, 24));
		System.out.println(aPerson.getGeburtsdatum().getTime());
		System.out.println(new Date(-99999999999L));
		// Dear students,
		// this shut work in your Framework, while the class "GuiGenerator" is a
		// class of you
		// GuiGenerator.createView(aPerson, new PersonGespeichertListener() {
		// @Override
		// public void gespeichert(Person changedPerson) {
		// // ...changedPerson ist das in ihren Masken ver�nderte Person
		// }
		// });

		System.out.println(aPerson.toString());

		GuiCreator.createView(aPerson, new SavedListener<Person>()
		{
			public void saved(Person object)
			{
				System.out.println(object.toString());

			}
		}, new DomainObjectParser());

		// good luck
	}
}
