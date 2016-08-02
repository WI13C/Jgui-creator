package de.dhbw.wi13c.jguicreator.impl;

import de.dhbw.wi13c.jguicreator.MockParser;
import de.dhbw.wi13c.jguicreator.Parser;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

public class GuiCreator<T>
{

	Parser objectParser;
	private SavedListener<DomainObject> domainObjectSavedListener;

	public GuiCreator(SavedListener<DomainObject> domainObjectSavedListener)
	{
		super();
		this.domainObjectSavedListener = domainObjectSavedListener;
		objectParser = new MockParser();
	}
	
	public static <T> void createView(T object, SavedListener<T> listener, Parser parser)
	{
		DomainObject domainObject = parser.parseObject(object);
		MyGui gui = new MyGui(domainObject, new SavedListener<DomainObject>()
		{
			@Override
			public void saved(DomainObject dom)
			{
				listener.saved((T)dom.getDatafield().getInstance());
			}
		});
		

	}
}
