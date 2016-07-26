package de.dhbw.wi13c.jguicreator;

import java.util.EnumMap;

public class Settings
{
	private static final int DEFAULTWINDOWHEIGHT = 500;

	private static final int DEFAULTWINDOWWIDTH = 700;

	private EnumMap<Setting, String> mapSettings = new EnumMap<>(Setting.class);

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

	public enum Setting
	{
		WINDOWHEIGHT, WINDOWWIDTH;
	}
}
