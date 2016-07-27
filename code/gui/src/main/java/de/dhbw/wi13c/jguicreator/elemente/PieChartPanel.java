package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Graphics;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.Settings;

public class PieChartPanel extends ChartPanel
{
	public PieChartPanel(String description, Map<String, ? extends Number> keyValues, Settings pSettings){
		super(description, keyValues, pSettings);
	}

	@Override
	public void drawStep(Graphics g)
	{
	}
}
