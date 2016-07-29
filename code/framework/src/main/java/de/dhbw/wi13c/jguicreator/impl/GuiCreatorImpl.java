package de.dhbw.wi13c.jguicreator.impl;

import java.util.List;

import de.dhbw.wi13c.jguicreator.MockParser;
import de.dhbw.wi13c.jguicreator.Parser;
import de.dhbw.wi13c.jguicreator.ParserImpl;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.listener.GuiListener;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

public class GuiCreatorImpl<T> implements GuiCreator<T>
{

	Parser objectParser;

	public GuiCreatorImpl()
	{
		super();
		objectParser = new MockParser();
	}

	@Override
	public void createView(T object, SavedListener<T> listener)
	{
		DomainObject domainObject = objectParser.parseObject(object);
		buildView(domainObject);

	}
	
	@Override
	public void createView(T object, SavedListener<T> listener, Parser parser)
	{
		DomainObject domainObject = parser.parseObject(object);
		buildView(domainObject);

	}

	public void buildView(DomainObject domainObject)
	{
		MyGui gui = new MyGui(domainObject, new GuiListener[]{new GuiListener()
		{

			@Override
			public void guiSaved(DomainObject o)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void guiCanceled(DomainObject o)
			{
				// TODO Auto-generated method stub

			}
		}});
		
	}

}
