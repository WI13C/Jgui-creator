package de.dhbw.wi13c.jguicreator.listener;



public interface GuiListener
{

	/**
	 * Called when the Save button on the Gui is pressed.
	 * @param form The updated {@link Form} object
	 */
	public void guiSaved(Object form);
	
	/**
	 * Called when the cancel Button on the Gui is pressed.
	 * @param form The original unchanged {@link Form} object
	 */
	public void guiCanceled(Object form);
}
