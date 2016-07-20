package framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.Parser;
import de.dhbw.wi13c.jguicreator.data.Datafield;
import de.dhbw.wi13c.jguicreator.data.Datafield.DatafieldType;
import de.dhbw.wi13c.jguicreator.data.Dataset;
import de.dhbw.wi13c.jguicreator.data.DomainObject;
import de.dhbw.wi13c.jguicreator.data.Form;
import de.dhbw.wi13c.jguicreator.data.GuiVisitor;
import de.dhbw.wi13c.jguicreator.data.SwingVisitor;
import de.dhbw.wi13c.jguicreator.data.Textfield;
import de.dhbw.wi13c.jguicreator.data.UiElement;

public class Test
{

	@org.junit.Test
	public void test()
	{
//		Map<String, Integer> einkommenszusammensetzung = new HashMap<>();
//		einkommenszusammensetzung.put("Gehalt", (3000 * 12));
//		einkommenszusammensetzung.put("Mieteinkommen", (300 * 12));
//
//		Map<String, Integer> einkommensentwicklung = new HashMap<>();
//		einkommensentwicklung.put("Januar", (3000));
//		einkommensentwicklung.put("Februar", (3000));
//
//		Person p1 = new Person("gustav", "ganz", 3, new Date(), new Adresse("entenweg", 13), Arrays.asList(new Kontakt[]{new Kontakt("privat", "9837249", "asd@asd")}), einkommenszusammensetzung, einkommensentwicklung);
//
//		Form rootForm = Parser.getDefaultParser().parseObject(p1);
//
//		Datafield<String> ndf = new Datafield<>();
//
//		try
//		{
//			ndf.setField(p1.getClass().getField("vorname"));
//		}
//		catch(NoSuchFieldException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch(SecurityException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ndf.setInstance(p1);
//		ndf.setType(DatafieldType.TEXT);
//
//		Datafield<String> df = new Datafield<>();
//
//		try
//		{
//			df.setField(p1.getClass().getField("vorname"));
//		}
//		catch(NoSuchFieldException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch(SecurityException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		df.setInstance(p1);
//		df.setType(DatafieldType.TEXT);

		
		DomainObject startObject = new DomainObject();
		Dataset startDataset = new Dataset();
		List<UiElement> startElements = new ArrayList<>();
		startElements.add(new Textfield());
		startElements.add(new Textfield());
		startElements.add(new Textfield());
		
		DomainObject dependentObject = new DomainObject();
		
		
		startElements.add(dependentObject);
		startDataset.setElements(startElements);
		startObject.setDataset(startDataset);
		
		GuiVisitor interpreter = new SwingVisitor();
		
		for(UiElement uiElement : startElements)
		{
			uiElement.accept(interpreter);
		}
	}
	
	

}
