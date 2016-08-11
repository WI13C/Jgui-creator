package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.listener.ChartComponentAdapter;
import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.listener.BarChartMouseMotionListener;

public class BarChartPanel extends ChartPanel
{
	private double maxValue;

	private double initialXPosition;

	private double barwidth;

	private double barSpace;

	private Color color;
	
	public double getBarSpace()
	{
		return this.barSpace;
	}

	public double getMaxValue()
	{
		return this.maxValue;
	}

	public BarChartPanel(String description, Map<String, ? extends Number> keyValues, Settings pSettings)
	{
		super(description, keyValues, pSettings);
		this.color = new Color(80, 120, 200);
		this.renderMaxValue();
		this.RefreshValues();
		this.addListeners();
	}

	private void addListeners()
	{
		this.addMouseMotionListener(new BarChartMouseMotionListener(this));
		this.addComponentListener(new ChartComponentAdapter(this));
	}

	private void renderMaxValue()
	{
		for(int i = 0; i < this.getCount(); i++)
		{
			if(this.maxValue < this.getValue(i))
			{
				this.maxValue = this.getValue(i);
			}
		}
	}

	private void drawYAxisSteps(Graphics g, int x, int y, int depth)
	{
		//Axis Description
		int axisSteps = 50; // new "step-line" every 50px
		int notationLineLength = (int) this.getChartLength();
		for(int i = depth - axisSteps; i >= 0; i -= axisSteps)
		{
			g.drawLine(x - 10, y + i, x + notationLineLength, y + i);
		}
	}

	@Override
	public void RefreshValues()
	{
		super.RefreshValues();

		this.barSpace = (this.getChartLength() / this.getCount());
		this.initialXPosition = this.getChartLeftX() + barSpace * 0.25;
		this.barwidth = barSpace * 0.75;
	}

	@Override
	public void drawStep(Graphics g)
	{
		// draw the axis
		g.drawLine((int) this.getChartLeftX(), (int) (this.getChartTopY() + this.getChartHeight()), (int) (this.getChartLeftX() + this.getChartLength()), (int) (this.getChartTopY() + this.getChartHeight()));
		g.drawLine((int) this.getChartLeftX(), (int) this.getChartTopY(), (int) this.getChartLeftX(), (int) (this.getChartTopY() + this.getChartHeight()));

		g.setColor(this.color);
		double currentXPosition = this.initialXPosition;
		for(int i = 0; i < this.getCount(); i++)
		{
			// Maximal height of the current bar
			double currentmaxheight = (this.getChartHeight() * this.getValue(i) * 0.9 / this.maxValue);
			double stepHeight = currentmaxheight * 0.01;

			double drawHeight = stepHeight * this.getPercent();
			double bartopY = this.getChartTopY() + getChartHeight() - Math.floor(drawHeight);
			g.fillRect((int) currentXPosition, (int) bartopY, (int) this.barwidth, (int) drawHeight);
			currentXPosition += this.barSpace;
		}

		g.setColor(Color.lightGray);
		drawYAxisSteps(g, (int) this.getChartLeftX(), (int) this.getChartTopY(), (int) this.getChartHeight());
	}

	@Override
	public void reflectData()
	{
		// TODO Auto-generated method stub

	}
}
