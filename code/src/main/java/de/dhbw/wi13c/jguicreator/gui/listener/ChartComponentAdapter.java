package de.dhbw.wi13c.jguicreator.gui.listener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import de.dhbw.wi13c.jguicreator.gui.elemente.ChartPanel;


public class ChartComponentAdapter extends ComponentAdapter
{
	private ChartPanel chart;
	
	public ChartComponentAdapter(ChartPanel chart)
	{
		this.chart = chart;
	}
	
	@Override
	public void componentResized(ComponentEvent e)
	{
		super.componentResized(e);
		this.chart.RefreshValues();
	}
}
