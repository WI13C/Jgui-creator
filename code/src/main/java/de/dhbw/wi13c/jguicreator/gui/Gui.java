package de.dhbw.wi13c.jguicreator.gui;

import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
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
 *
 */
public abstract class Gui
{


	public Gui(DomainObject domainObject)
	{
		
	}
	
	/**
	 * Called to show the Gui
	 */
	public abstract void show();
	
	
}
