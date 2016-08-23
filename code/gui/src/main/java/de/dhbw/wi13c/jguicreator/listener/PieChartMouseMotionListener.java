package de.dhbw.wi13c.jguicreator.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import de.dhbw.wi13c.jguicreator.elemente.PieChartPanel;


public class PieChartMouseMotionListener implements MouseMotionListener
{
	private PieChartPanel chart;
	public PieChartMouseMotionListener(PieChartPanel chart)
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
			double mouseY = (double)e.getY();
			double degree = this.chart.getDegreeFromPoint(mouseX, mouseY);
			this.chart.setIndexFromDegree(degree);
			this.chart.setCenterOfSignY(mouseY-20);
			this.chart.setCenterOfSignX(mouseX);
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
