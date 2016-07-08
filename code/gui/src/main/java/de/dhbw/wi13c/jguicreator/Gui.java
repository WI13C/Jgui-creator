package de.dhbw.wi13c.jguicreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import de.dhbw.wi13c.jguicreator.data.Form;
import de.dhbw.wi13c.jguicreator.listener.GuiListener;

/**
 * 
 * Abstract Gui class.
 * Can be extended to provide the gui capability.
 * 
 * The Gui must represent the Forms and Fields given in the Form Object.
 * 
 * The changes on the gui must be reflected in the Forms Field objects only if the save button is pressed.
 * Otherwise the Form object should remain unchanged
 * 
 * @author Robin Sadlo
 *
 */
public abstract class Gui
{
	/**
	 * The Form object
	 */
	protected final Form form;
	
	private final Collection<GuiListener> listeners = new ArrayList<>();

	public Gui(Form form, GuiListener... guiListeners)
	{
		this.form = form;
		this.listeners.addAll(Arrays.asList(guiListeners));
		
	}
	
	/**
	 * Called to show the Gui
	 */
	public abstract void show();
	
	/**
	 * Called when the Gui is saved
	 */
	protected void onSave()
	{
		this.listeners.stream().forEach((l) -> l.guiSaved(this.form));
	}
	
	/**
	 * Called when the gui is canceled
	 */
	protected void onCancel()
	{
		this.listeners.stream().forEach((l)->l.guiCanceled(this.form) );
	}
}
