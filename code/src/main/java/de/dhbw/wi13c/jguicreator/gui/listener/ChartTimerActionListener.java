package de.dhbw.wi13c.jguicreator.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.dhbw.wi13c.jguicreator.gui.elemente.ChartPanel;


public class ChartTimerActionListener implements ActionListener
{
	private ChartPanel chart;
	private int percent;
	
	
	public ChartTimerActionListener(ChartPanel chart)
	{
		this.chart = chart;
		this.percent = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(this.percent <= 100)
		{
			this.percent++;
			this.chart.setPercent(this.percent);
			this.chart.repaint();
		}
		else
		{
			this.chart.stopTimer();
		}		
	}
}
