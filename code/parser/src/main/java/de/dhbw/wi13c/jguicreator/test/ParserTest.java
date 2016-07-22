package de.dhbw.wi13c.jguicreator.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.dhbw.wi13c.jguicreator.Parser;

public class ParserTest
{

	@Test
	public void test()
	{
		Adresse adresse = new Adresse("Hauptstraße", 30);
		Kontakt privat = new Kontakt("privat", "0160123456", "ferdi@prov.de");
		Kontakt business = new Kontakt("business", "0173555666",
				"ferdi@busy.com");

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

		Person aPerson = new Person("Hans", "Maier", new Integer(3), new Date(
				1980, 3, 24), adresse, kontakte, einkommensZusammensetzung,
				einkommensEntwicklung);
		
		
		Parser parser = Parser.getDefaultParser();
		parser.parseObject(aPerson);
//		parser.parseObject(object)
	}

}
