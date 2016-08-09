package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Graphics;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.helper.ColorWheel;
import de.dhbw.wi13c.jguicreator.listener.BarChartMouseMotionListener;
import de.dhbw.wi13c.jguicreator.listener.ChartComponentAdapter;

public class PieChartPanel extends ChartPanel
{
	private double totalValue;

	private double radius;
	
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
		this.radius = (this.getHeight() > this.getWidth() ? this.getWidth() : this.getHeight()) / 2;
	}

	@Override
	public void drawStep(Graphics g)
	{
		double startAngle = 90; // 90 Grad ist 12 Uhr
		double currentMax = startAngle - this.getPercent() * 3.6;

		for(int i = 0; i < this.getCount(); i++)
		{
			g.setColor(this.colorWheel.getColor(i));
			double arcAngle = this.getValue(i) / this.totalValue * 360;
			if(startAngle - arcAngle <= currentMax)
			{
				drawSlice(g, startAngle, startAngle - currentMax);
				break;
			}
			else
			{
				drawSlice(g, startAngle, arcAngle);
			}

			startAngle -= arcAngle;
		}
	}

	private void drawSlice(Graphics g, double startAngle, double drawAngle)
	{
		// Negative Gradzahl --> Mit dem Uhrzeigersinn
		g.fillArc((int) this.getChartLeftX(), (int) this.getChartTopY(), (int) this.radius, (int) this.radius, (int) Math.round(startAngle), (int) -Math.round(drawAngle));
	}

	@Override
	public void reflectData()
	{
		// TODO Auto-generated method stub
		// hm..., naja...		
	}
}
