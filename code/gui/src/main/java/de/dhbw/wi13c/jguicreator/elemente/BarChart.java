package de.dhbw.wi13c.jguicreator.elemente;

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

	private void RenderMaxValue()
	{
		for(Number value : this.values.values())
		{
			if(this.maxValue < value.doubleValue())
			{
				this.maxValue = value.doubleValue();
			}
		}
	}
	
	private int i;
	private boolean animating;
	
	public void animate(){
		this.animating = true;
		for (this.i = 1; this.i <= 100; this.i++){
			this.repaint();			
			try
			{
				Thread.sleep(500);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		this.animating = false;
	}

//	@Override // Animation 1
//	public void drawStep(Graphics g)
//	{
//		//Axis
//		int length = this.getWidth() / 3 * 2;
//		int depth = this.getHeight() / 3 * 2;
//		int x = (this.getWidth() - length) / 2;
//		int y = (this.getHeight() - depth) / 2;
//		g.drawLine(x, y + depth, x + length, y + depth);
//		g.drawLine(x, y, x, y + depth);
//
//		//Axis Description
//		int axisSteps = 50;
//		int notationLineLength = 10;
//		for(int i = depth - axisSteps; i >= 0; i -= axisSteps)
//		{
//			g.drawLine(x, y + i, x - notationLineLength, y + i);
//		}
//		
//		// BarTiles
//		int barspace = (length / this.values.size());
//		int currentposition = x + barspace / 4;
//		int barwidth = barspace / 4 * 3;
//		for(String key : this.values.keySet())
//		{
//			Number value = this.values.get(key);
//			int currentheigth = (int) (depth * value.doubleValue() / this.maxValue);
//			double stepHeight = ((double)currentheigth)/100;
//			int drawHeight = (int)stepHeight * this.i;
//			int startY = y + depth - drawHeight;
//			g.fillRect(currentposition, startY, barwidth, drawHeight);
//			currentposition += barspace;
//		}
//	}
	
	@Override // Animation 2
	public void drawStep(Graphics g)
	{
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
		for(int i = depth - axisSteps; i >= 0; i -= axisSteps)
		{
			g.drawLine(x, y + i, x - notationLineLength, y + i);
		}
		
		// BarTiles
		int barspace = (length / this.values.size());
		int currentposition = x + barspace / 4;
		int barwidth = barspace / 4 * 3;
		for(String key : this.values.keySet())
		{
			Number value = this.values.get(key);
			int currentheigth = (int) (depth * value.doubleValue() / this.maxValue);
			double stepHeight = ((double)depth)/100;
			int drawHeight = (int)stepHeight * this.i > currentheigth ? currentheigth : (int)stepHeight * this.i;
			int startY = y + depth - drawHeight;
			g.fillRect(currentposition, startY, barwidth, drawHeight);
			currentposition += barspace;
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		this.drawStep(g);	
	}
}
