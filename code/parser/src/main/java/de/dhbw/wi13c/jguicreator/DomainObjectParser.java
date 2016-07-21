package de.dhbw.wi13c.jguicreator;

import de.dhbw.wi13c.jguicreator.data.ComboBox;
import de.dhbw.wi13c.jguicreator.data.Dataset;
import de.dhbw.wi13c.jguicreator.data.DomainObject;
import de.dhbw.wi13c.jguicreator.data.Textfield;
import de.dhbw.wi13c.jguicreator.data.UiElement;

public class DomainObjectParser implements Parser
{
	
	/**
	 * @return very simple mock data for now
	 * @author Eric
	 */
	@Override
	public DomainObject parseObject(Object object)
	{
		DomainObject rootObject = new DomainObject();
		Dataset rootDataset = new Dataset();
		
		UiElement textfield = new Textfield();
		rootDataset.getElements().add(textfield);
		
		UiElement textfield1 = new Textfield();
		rootDataset.getElements().add(textfield1);
		
		UiElement combobox = new ComboBox();
		rootDataset.getElements().add(combobox);
		
		DomainObject dependentObject = new DomainObject();
		rootDataset.getElements().add(dependentObject);
		
		
		Dataset dependentDataset = new Dataset();
		dependentObject.setDataset(dependentDataset);
		
		UiElement dependentTextfield = new Textfield();
		dependentDataset.getElements().add(dependentTextfield);
		
		UiElement dependentTextfield1 = new Textfield();
		dependentDataset.getElements().add(dependentTextfield1);
		
		
		rootObject.setDataset(rootDataset);
		return rootObject;
	}

}
