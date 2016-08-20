package de.dhbw.wi13c.jguicreator.gui;

import java.util.EnumMap;

/**
 * Klasse um Einstellungen für alle Komponenten der GUI zu bestimmen. Bsp: Maße des Fensters
 * 
 * @author Tim Bayer
 *
 */
public class Settings
{
	private static final int DEFAULTWINDOWHEIGHT = 500;

	private static final int DEFAULTWINDOWWIDTH = 700;

	private EnumMap<Setting, String> mapSettings = new EnumMap<>(Setting.class);

	/**
	 * Konstruktor zur Initialisierung der Settings. Default-Werte für Fensterbreite & -höhe werden gesetzt.
	 */
	public Settings()
	{
		mapSettings.put(Setting.WINDOWHEIGHT, Integer.toString(DEFAULTWINDOWHEIGHT));
		mapSettings.put(Setting.WINDOWWIDTH, Integer.toString(DEFAULTWINDOWWIDTH));

	}

	public String getSetting(Setting pSetting)
	{
		if(!mapSettings.containsKey(pSetting))
		{
			return "";
		}
		else
		{
			return mapSettings.get(pSetting);
		}
	}

	public void setSetting(Setting pSetting, String pValue)
	{
		mapSettings.put(pSetting, pValue);
	}
/**
 * Enumerationen die die möglichen zu setzenden Settings beschreiben
 * 
 * @author Tim Bayer
 *
 */
	public enum Setting
	{
		WINDOWHEIGHT, WINDOWWIDTH;
	}
}
