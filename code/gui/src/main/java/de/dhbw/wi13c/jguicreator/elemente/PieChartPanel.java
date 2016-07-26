package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Graphics;
import java.util.Map;

public class PieChartPanel extends ChartPanel
{
	public PieChartPanel(String description, Map<String, ? extends Number> keyValues){
		super(description, keyValues);
	}

	@Override
	public void drawStep(Graphics g)
	{
	}
}
