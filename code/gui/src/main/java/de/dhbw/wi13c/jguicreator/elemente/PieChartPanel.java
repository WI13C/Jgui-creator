package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.helper.ColorWheel;
import de.dhbw.wi13c.jguicreator.listener.BarChartMouseMotionListener;
import de.dhbw.wi13c.jguicreator.listener.ChartComponentAdapter;

public class PieChartPanel extends ChartPanel
{
	private double totalValue;

	private double diameter;

	private ColorWheel colorWheel;

	public PieChartPanel(String description, Map<String, ? extends Number> keyValues, Settings pSettings)
	{
		super(description, keyValues, pSettings);

		sumValues();
		this.addListeners();
		this.colorWheel = new ColorWheel(this.getCount());
	}

	private void addListeners()
	{
		this.addComponentListener(new ChartComponentAdapter(this));
	}

	private void sumValues()
	{
		for(int i = 0; i < this.getCount(); i++)
		{
			this.totalValue += this.getValue(i);
		}
	}

	@Override
	public void RefreshValues()
	{
		super.RefreshValues();
		this.diameter = (this.getChartHeight() > this.getChartLength() ? this.getChartLength() : this.getChartHeight());
	}

	@Override
	public void drawStep(Graphics g)
	{
		this.drawSlices(g);
		this.drawLines(g);
	}

	private void drawLines(Graphics g)
	{
		g.setColor(Color.lightGray);
		double centerOfCircleX = this.getChartLeftX() + this.getChartLength() * 0.5;
		double centerOfCircleY = this.getChartTopY() + this.getChartHeight() * 0.5;
		g.drawLine((int) centerOfCircleX, (int) centerOfCircleY, (int) centerOfCircleX, (int) (centerOfCircleY - this.diameter * 0.5));
		for(int i = 0; i < this.getCount() - 1; i++)
		{
			// rechte Seite
			if(this.getArcAngle(i) < 90)
			{
				double slope = Math.tan(this.getArcAngle(i));
				//				double endX = 
				//				double endY = 
				//g.drawLine((int)centerOfCircleX, (int)centerOfCircleY, (int)endX, (int)endY);

			}
		}
	}

	private void drawSlices(Graphics g)
	{
		double startAngle = 90; // 90 Grad ist 12 Uhr
		double currentMax = startAngle - this.getPercent() * 3.6;
		for(int i = 0; i < this.getCount(); i++)
		{
			g.setColor(this.colorWheel.getColor(i));

			if(startAngle - this.getArcAngle(i) <= currentMax)
			{
				drawSlice(g, startAngle, startAngle - currentMax);
				break;
			}
			else
			{
				drawSlice(g, startAngle, this.getArcAngle(i));
			}

			startAngle -= this.getArcAngle(i);
		}
	}

	private double getArcAngle(int index)
	{
		return this.getValue(index) / this.totalValue * 360;
	}

	private void drawSlice(Graphics g, double startAngle, double drawAngle)
	{
		// Negative Gradzahl --> Mit dem Uhrzeigersinn
		g.fillArc((int) this.getChartLeftX(), (int) this.getChartTopY(), (int) this.diameter, (int) this.diameter, (int) Math.round(startAngle), (int) -Math.round(drawAngle));
	}

	@Override
	public void reflectData()
	{
		// TODO Auto-generated method stub
		// hm..., naja...		
	}
}
