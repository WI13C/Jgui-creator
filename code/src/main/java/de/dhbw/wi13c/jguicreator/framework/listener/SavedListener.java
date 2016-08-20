package de.dhbw.wi13c.jguicreator.framework.listener;

/**
 * Listener to be called when the save Button on the gui is pressed
 * 
 * @author Robin Sadlo
 *
 * @param <T> Type of the Object
 */
public interface SavedListener<T> {

	/**
	 * Called when the save button was pressed.
	 * The Objects fields will be updated to match the gui
	 * 
	 * @param object
	 */
	public void saved(T object);
}
