package de.dhbw.wi13c.jguicreator;

import java.util.HashMap;
import java.util.Map;

public class Settings
{
	private static final int DEFAULTWINDOWHEIGHT = 500;

	private static final int DEFAULTWINDOWWIDTH = 700;

	private Map<Setting, String> settings = new HashMap<>();

	public Settings()
	{
		settings.put(Setting.WINDOWHEIGHT, DEFAULTWINDOWHEIGHT + "");
		settings.put(Setting.WINDOWWIDTH, DEFAULTWINDOWWIDTH + "");

	}

	public String getSetting(Setting pSetting)
	{
		if(!settings.containsKey(pSetting))
		{
			return "";
		}
		else
		{
			return settings.get(pSetting);
		}
	}

	public void setSetting(Setting pSetting, String pValue)
	{
		settings.put(pSetting, pValue);
	}

	public enum Setting
	{
		WINDOWHEIGHT, WINDOWWIDTH;
	}
}
