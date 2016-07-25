package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import javax.swing.Timer;
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
	private Timer timer;
	
	public void animate(){
		//this.animating = true;
		/*
		for (this.i = 1; this.i <= 100; this.i++){
			this.repaint();			
			try
			{
				Thread.sleep(25);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		*/
		
		int delay = 25; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (i <= 100) {
					i++;
					repaint();
				} else {
					timer.stop();
				}
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
		

		//this.animating = false;
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
	
	private void drawYAxisSteps(Graphics g, int x, int y, int depth) {
		//Axis Description
		int axisSteps = 50; // new "step-line" every 50px
		int notationLineLength = 10;
		for(int i = depth - axisSteps; i >= 0; i -= axisSteps)
		{
			g.drawLine(x, y + i, x - notationLineLength, y + i);
		}
	}
	
	@Override // Animation 2
	public void drawStep(Graphics g)
	{
		//Axis
		int length = this.getWidth() / 3 * 2;
		// height of the y axis
		int depth = this.getHeight() / 3 * 2;
		int chartleftx = (this.getWidth() - length) / 2;
		int charttopy = (this.getHeight() - depth) / 2;
		
		// draw the axis
		g.drawLine(chartleftx, charttopy + depth, chartleftx + length, charttopy + depth);
		g.drawLine(chartleftx, charttopy, chartleftx, charttopy + depth);

		drawYAxisSteps(g, chartleftx, charttopy, depth);
		
		// BarTiles
		int barspace = (length / this.values.size());
		// x value of the left side of a bar
		int currentposition = chartleftx + barspace / 4;
		int barwidth = barspace / 4 * 3;
		double stepHeight = ((double)depth)/100;
		
		for(String key : this.values.keySet())
		{
			Number value = this.values.get(key);
			// Maximal height of the current bar
			int currentmaxheight = (int) (depth * value.doubleValue() / this.maxValue);
			
			
			int drawHeight = (int)stepHeight * this.i > currentmaxheight ? currentmaxheight : (int)stepHeight * this.i;
			int bartopY = charttopy + depth - drawHeight;
			
			g.fillRect(currentposition, bartopY, barwidth, drawHeight);
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
