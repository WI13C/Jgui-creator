package de.dhbw.wi13c.jguicreator.elemente;

import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public abstract class InputGuiKomponente extends GUIKomponente
{
	
	public abstract void reflectData();
	public abstract boolean validateContent();
}
