package de.dhbw.wi13c.jguicreator;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import javax.swing.border.TitledBorder;

public class BarChart extends Chart
{
	
	private Map<String, ? extends Number> values;
	private double maxValue;

	public BarChart(String description, Map<String, ? extends Number> values)
	{
		super(description);
		this.values = values;
		this.RenderMaxValue();		
	}
	
	private void RenderMaxValue(){
		for (Number value : this.values.values()){
			if (this.maxValue < value.doubleValue()){
				this.maxValue = value.doubleValue();
			}
		}
	}

	@Override // TODO Will be refactored
	public void paint(Graphics g)
	{
		super.paint(g);
		//Axis
		int length = this.getWidth() / 3 * 2; 
		int depth = this.getHeight() / 3 * 2;
		int x = (this.getWidth() - length) / 2;
		int y = (this.getHeight() - depth) / 2;
		g.drawLine(x, y + depth, x + length, y + depth);
		g.drawLine(x, y, x, y + depth);
		
		//Axis Description
		int axisSteps = 50;
		int notationLineLength = 10;
		for (int i = depth - axisSteps ;i >= 0; i -= axisSteps){
			g.drawLine(x, y + i, x-notationLineLength, y + i);
		}
		
		// BarTiles
		int barspace = (length / this.values.size());
		int currentposition = x + barspace / 4;
		int barwidth = barspace / 4 * 3;
		for (String key : this.values.keySet()){
			Number value = this.values.get(key);
			int currentheigth = (int)(depth * value.doubleValue() / this.maxValue);
			g.fillRect(currentposition, y+depth - currentheigth, barwidth, currentheigth);
			currentposition += barspace;
		}
	}
}
