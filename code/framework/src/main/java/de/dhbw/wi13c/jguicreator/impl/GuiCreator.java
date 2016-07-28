package de.dhbw.wi13c.jguicreator.impl;

import de.dhbw.wi13c.jguicreator.Parser;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;

/**
 * Interface of GuiCreator
 * 
 * @author Robin Sadlo
 *
 * @param <T>
 *            The type the GuiCreator will be able to handle
 */
public interface GuiCreator<T> {

	public void createView(T object, SavedListener<T> event);

	/**
	 * Created for testing different parsers.
	 * @author Eric Schuh
	 * @param object
	 * @param listener
	 * @param parser
	 */
	public void createView(T object, SavedListener<T> listener, Parser parser);
}
