package de.dhbw.wi13c.jguicreator.impl;

import de.dhbw.wi13c.jguicreator.Parser;
import de.dhbw.wi13c.jguicreator.ParserImpl;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

public class GuiCreatorImpl<T> implements GuiCreator<T> {
	
	Parser objectParser;
	
	public GuiCreatorImpl()
	{
		super();
		objectParser = new ParserImpl();
	}

	@Override
	public void createView(T object, SavedListener<T> listener) {
		DomainObject domainObject = objectParser.parseObject(object);
		
		//generatedForm.
	}

}
