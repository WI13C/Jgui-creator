package de.dhbw.wi13c.jguicreator.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import de.dhbw.wi13c.jguicreator.elemente.BarChartPanel;

public class BarChartMouseMotionListener implements MouseMotionListener
{
	private BarChartPanel chart;
	public BarChartMouseMotionListener(BarChartPanel chart)
	{
		this.chart = chart;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		if (this.isValidBounds(e.getX(), e.getY()))
		{
			double mouseX = (double)e.getX();
			double barwidth = this.chart.getBarSpace() * 0.75;
			int index = (int)((mouseX - this.chart.getChartLeftX()) / this.chart.getBarSpace());
			this.chart.setIndex(index);
			this.chart.setCenterOfSignY(this.chart.getChartHeight() - this.chart.getChartHeight() * this.chart.getValue() * 0.5 / this.chart.getMaxValue()+ this.chart.getChartTopY());
			this.chart.setCenterOfSignX(this.chart.getChartLeftX() + this.chart.getBarSpace() * 0.25 + this.chart.getBarSpace() * index + barwidth * 0.5);
			this.chart.setDrawSign(true);
		}else{
			this.chart.setDrawSign(false);
		}
		
		this.chart.repaint();
	}
	
	private boolean isValidBounds(int mouseX, int mouseY){
		return mouseX - this.chart.getChartLeftX() >= 0 && mouseX - this.chart.getChartLeftX() <= this.chart.getChartLength()
			&& mouseY - this.chart.getChartTopY() >= 0 && mouseY - this.chart.getChartTopY() <= this.chart.getChartHeight();
	}
}
