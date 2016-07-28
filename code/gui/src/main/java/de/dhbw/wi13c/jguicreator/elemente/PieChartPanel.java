package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Graphics;
import java.util.Map;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.listener.BarChartMouseMotionListener;
import de.dhbw.wi13c.jguicreator.listener.ChartComponentAdapter;

public class PieChartPanel extends ChartPanel
{
	private double totalValue;
	private double radius;
	
	public PieChartPanel(String description, Map<String, ? extends Number> keyValues,Settings pSettings){
		super(description, keyValues,pSettings);
		
		sumValues();
		this.addListeners();	
	}
	
	private void addListeners()
	{
		this.addComponentListener(new ChartComponentAdapter(this));
	}
	
	private void sumValues() {
		for(int i = 0; i < this.getCount(); i++)
		{
			this.totalValue += this.getValue(i);
		}
	}

	@Override
	public void RefreshValues()
	{
		super.RefreshValues();
		this.radius = (this.getHeight() > this.getWidth()?  this.getWidth():this.getHeight())/2;
		
	}
	
	@Override
	public void drawStep(Graphics g)
	{
		
		double curValue = 0;
		double startAngle = 0;
		double maxValue =  this.totalValue * this.getPercent() / 100.0D;
		double maxAngle = maxValue * 360 / this.totalValue;
		
		for (int i = 0; i < this.getCount(); i++) {
			
			startAngle =  (curValue * 360 / this.totalValue);
		    double drawAngle =  this.getValue(i) * 360 / this.totalValue;
			
			if (drawAngle >= maxAngle - startAngle) {
				drawSlice(g, startAngle, maxAngle - startAngle);
				break;
			} else {
			    drawSlice(g, startAngle, drawAngle);
			}
			

		    
		    curValue += this.getValue(i);
		}
	}
	
	private void drawSlice(Graphics g, double startAngle, double drawAngle) {
		g.fillArc(
	    		(int) this.getChartLeftX(),
	    		(int) this.getChartTopY(),
	    		(int) this.radius,
	    		(int) this.radius,
	    		(int) Math.round(startAngle),
	    		(int) Math.round(drawAngle)
	    		);
	}
}
