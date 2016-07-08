package de.dhbw.wi13c.jguicreator.impl;

/**
 * Factory to create a type save instance of gui creator.
 * 
 * @author Robin Sadlo
 *
 */
public class GuiCreatorFactory {

	/**
	 * Creates a new Gui Creator
	 * 
	 * @param type
	 *            Type of the parsable class
	 * @return A GuiCreator capable to handle instances of the class type
	 */
	public static <T> GuiCreator<T> newGuiCreator(Class<T> type) {
		return new GuiCreatorImpl<T>();
	}
}
