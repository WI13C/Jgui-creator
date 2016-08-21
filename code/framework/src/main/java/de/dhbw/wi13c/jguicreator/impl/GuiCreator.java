package de.dhbw.wi13c.jguicreator.impl;

import de.dhbw.wi13c.jguicreator.Parser;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;


/**
 * GuiCreator is the static entry-point to create a gui for a given business-object.
 * @author Lukas Hessenthaler
 *
 * @param <T> 
 */
public class GuiCreator<T>
{
	
	public static <T> void createView(T object, SavedListener<T> listener, Parser parser)
	{
		DomainObject domainObject = parser.parseObject(object);
		new RootGui(domainObject, new SavedListener<DomainObject>()
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
