package de.dhbw.wi13c.jguicreator.helper;

import java.awt.Color;

public class ColorWheel
{
	private Color[] colors;

	public ColorWheel(int colorCount)
	{
		this.colors = new Color[colorCount];
		int half = colorCount / 2 + colorCount % 2;
		for(int i = 0; i < colorCount; i++)
		{
			int varietyNumber = i/2 + i%2 + (i%2) * (half - 1);
			this.colors[i] = Color.getHSBColor(1.0f / colorCount * varietyNumber, 0.8f, 0.8f);
		}
	}

	public Color getColor(int index)
	{
		return this.colors[index];
	}
}
