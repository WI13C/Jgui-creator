package de.dhbw.wi13c.jguicreator.listener;



public interface SaveListener
{

	/**
	 * Called when the Save button on the Gui is pressed.
	 * @param <T>
	 * @param form The updated {@link Form} object
	 */
	public <T> T guiSave();
}
