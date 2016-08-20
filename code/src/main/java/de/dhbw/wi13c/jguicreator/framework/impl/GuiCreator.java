package de.dhbw.wi13c.jguicreator.framework.impl;

import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.framework.listener.SavedListener;
import de.dhbw.wi13c.jguicreator.parser.Parser;

public class GuiCreator<T>
{

	private SavedListener<DomainObject> domainObjectSavedListener;

	public GuiCreator(SavedListener<DomainObject> domainObjectSavedListener)
	{
		super();
		this.domainObjectSavedListener = domainObjectSavedListener;
	}
	
	public static <T> void createView(T object, SavedListener<T> listener, Parser parser)
	{
		DomainObject domainObject = parser.parseObject(object);
		MyGui gui = new MyGui(domainObject, new SavedListener<DomainObject>()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void saved(DomainObject dom)
			{
				listener.saved((T)dom.getDatafield().getInstance());
			}
		});
		

	}
}
