package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.helper.ColorWheel;
import de.dhbw.wi13c.jguicreator.listener.BarChartMouseMotionListener;
import de.dhbw.wi13c.jguicreator.listener.ChartComponentAdapter;
import de.dhbw.wi13c.jguicreator.listener.PieChartMouseMotionListener;

public class PieChartPanel extends ChartPanel
{
	private static final int StartAngle = 90;

	private double totalValue;

	private double diameter;

	private ColorWheel colorWheel;

	private double centerOfCircleX;

	private double centerOfCircleY;

	public double getCenterOfCircleX()
	{
		return centerOfCircleX;
	}

	public double getCenterOfCircleY()
	{
		return centerOfCircleY;
	}

	public void setIndexFromDegree(double angle)
	{
		double currentAngle = 0;
		for(int i = 0; i < this.getCount(); i++)
		{
			currentAngle += this.getArcAngle(i);
			if(currentAngle >= angle)
			{
				this.setIndex(i);
				break;
			}
		}
	}

	public double getDegreeFromPoint(double x, double y)
	{
		double arcTang = Math.atan2(y - this.getCenterOfCircleY(), x - this.getCenterOfCircleX());
		double degree = (arcTang * 180 / Math.PI + 450) % 360;
		return degree;
	}
	
	private double getXPositionFromDegree(double degree){
		double xPosition = this.diameter / 2 * Math.cos(((degree) % 360) * Math.PI / 180) + this.getCenterOfCircleX();
		return xPosition;
	}
	
	private double getYPositionFromDegree(double degree){
		double yPosition = this.getCenterOfCircleY() - (this.diameter / 2 * Math.sin(((degree) % 360) * Math.PI / 180));
		return yPosition;
	}

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
		this.addMouseMotionListener(new PieChartMouseMotionListener(this));
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
		this.centerOfCircleX = this.getChartLeftX() + this.getChartLength() * 0.5;
		this.centerOfCircleY = this.getChartTopY() + this.getChartHeight() * 0.5;
	}

	@Override
	public void drawStep(Graphics g)
	{
		this.drawSlices(g);
	}

	private void drawLines(Graphics g, double degree)
	{
		g.setColor(Color.lightGray);
		g.drawLine((int) this.getCenterOfCircleX(), (int) this.getCenterOfCircleY(), (int) this.getCenterOfCircleX(), (int) (this.getCenterOfCircleY() - this.diameter * 0.5));
		
		for(int i = 0; i < this.getCount() - 1; i++)
		{			
			g.drawLine((int) this.getCenterOfCircleX(), (int) this.getCenterOfCircleY(), (int) this.getXPositionFromDegree(degree), (int) this.getYPositionFromDegree(degree));
		}
	}

	private void drawSlices(Graphics g)
	{
		double currentAngle = StartAngle; // 90 Grad ist 12 Uhr
		double currentMax = currentAngle - this.getPercent() * 3.6;
		for(int i = 0; i < this.getCount(); i++)
		{
			g.setColor(this.colorWheel.getColor(i));
			
			if(currentAngle - this.getArcAngle(i) <= currentMax)
			{
				drawSlice(g, currentAngle, currentAngle - currentMax);
				drawLines(g, currentAngle);
				break;
			}
			else
			{
				drawSlice(g, currentAngle, this.getArcAngle(i));
				drawLines(g, currentAngle);
				currentAngle -= this.getArcAngle(i);
			}			
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
