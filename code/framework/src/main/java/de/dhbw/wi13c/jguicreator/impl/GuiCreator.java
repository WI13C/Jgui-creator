package de.dhbw.wi13c.jguicreator.impl;

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
}
