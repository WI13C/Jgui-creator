package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.listener.ChartTimerActionListener;

public abstract class ChartPanel extends GUIKomponente
{
	private String[] keys;

	private double[] values;

	private TitledBorder border;

	private int percent;

	private Timer timer;

	private int index;

	private double centerOfSignY;

	private double centerOfSignX;

	private boolean drawSign;

	private double depth;

	private double chartLeftX;

	private double chartTopY;

	private double xAxisLength;

	public void setPercent(int percent)
	{
		this.percent = percent;
	}

	public void setDescription(String description)
	{
		this.border = new TitledBorder(description + ": ");
		this.setBorder(this.border);
	}

	public void setCenterOfSignY(double centerOfSignY)
	{
		this.centerOfSignY = centerOfSignY;
	}

	public void setDrawSign(boolean drawSign)
	{
		this.drawSign = drawSign;
	}

	public void setCenterOfSignX(double centerOfSignX)
	{
		this.centerOfSignX = centerOfSignX;
	}
	
	public int getPercent(){
		return this.percent;
	}

	public double getValue()
	{
		return this.values[index];
	}

	public double getValue(int index)
	{
		return this.values[index];
	}

	public String getKey()
	{
		return this.keys[index];
	}

	public int getCount()
	{
		return this.values.length;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public double getChartLeftX()
	{
		return this.chartLeftX;
	}

	public double getChartTopY()
	{
		return this.chartTopY;
	}

	public double getXAxisLength()
	{
		return this.xAxisLength;
	}

	public double getDepth()
	{
		return this.depth;
	}

	protected ChartPanel(String description, Map<String, ? extends Number> keyValues, Settings pSettings)
	{
		super();
		
		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.6));
		setPanelSize(size);

		initFont(15);
		this.setDescription(description);
		this.fillArrays(keyValues);
	}

	public abstract void drawStep(Graphics g);

	private void fillArrays(Map<String, ? extends Number> keyValues)
	{
		this.keys = new String[keyValues.size()];
		this.values = new double[keyValues.size()];
		int i = 0;
		for(String key : keyValues.keySet())
		{
			this.keys[i] = key;
			this.values[i] = keyValues.get(key).doubleValue();
			i++;
		}
	}

	public void animate()
	{
		int delay = 25; //milliseconds		
		this.timer = new Timer(delay, new ChartTimerActionListener(this));
		this.timer.start();
	}

	public void stopTimer()
	{
		this.timer.stop();
	}

	public void RefreshValues()
	{
		this.xAxisLength = this.getWidth() * 0.67;
		this.depth = this.getHeight() * 0.67;
		this.chartLeftX = (this.getWidth() - this.xAxisLength) * 0.5;
		this.chartTopY = (this.getHeight() - depth) * 0.5;
	}

	public void drawSign(Graphics g)
	{
		if(this.drawSign)
		{
			g.setColor(Color.red);
			double value = this.getValue();
			String displayText = this.getKey() + ": " + String.valueOf(value);
			Rectangle2D fontRect = g.getFontMetrics().getStringBounds(displayText, g);
			g.fillRect((int) (this.centerOfSignX - fontRect.getWidth() * 0.5 - 5), (int) (this.centerOfSignY - fontRect.getHeight() * 0.5 - 5), (int) fontRect.getWidth() + 10, (int) fontRect.getHeight() + 10);
			g.setColor(Color.white);
			g.drawString(displayText, (int) (this.centerOfSignX - fontRect.getWidth() * 0.5), (int) (this.centerOfSignY + fontRect.getHeight() * 0.5 - 5));
			g.setColor(Color.black);
		}
	}

	@Override
	public void setFont(Font font)
	{
		super.setFont(font);
		if(null != font && null != this.border)
		{
			this.border.setTitleFont(font);
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		this.drawStep(g);
		this.drawSign(g);
	}
}
