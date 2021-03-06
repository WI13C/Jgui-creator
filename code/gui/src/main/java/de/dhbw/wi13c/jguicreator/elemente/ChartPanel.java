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
import de.dhbw.wi13c.jguicreator.helper.KeyValue;
import de.dhbw.wi13c.jguicreator.listener.ChartTimerActionListener;

public abstract class ChartPanel extends GUIKomponente
{
	private KeyValue keyValues[];

	private TitledBorder border;

	private int percent;

	private Timer timer;

	private int index;

	private double centerOfSignY;

	private double centerOfSignX;

	private boolean drawSign;

	private double yAxisLength;

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
		return getValue(this.index);
	}

	public double getValue(int index)
	{
		return this.keyValues[index].getValue();
	}

	public String getKey()
	{
		return this.keyValues[index].getKey();
	}

	public int getCount()
	{
		return this.keyValues.length;
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

	public double getChartLength()
	{
		return this.xAxisLength;
	}

	public double getChartHeight()
	{
		return this.yAxisLength;
	}

	protected ChartPanel(String description, Map<String, ? extends Number> keyValues, Settings pSettings)
	{
		super();
		
		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.92), (int) (size.getHeight() * 0.6));
		setPanelSize(size);

		initFont(15);
		this.setDescription(description);
		this.fillArrays(keyValues);
		
		this.animate();
	}

	public abstract void drawStep(Graphics g);

	private void fillArrays(Map<String, ? extends Number> keyValues)
	{
		this.keyValues = new KeyValue[keyValues.size()];
		int i = 0;
		for(String key : keyValues.keySet())
		{
			this.keyValues[i] = new KeyValue(key, keyValues.get(key).doubleValue());
			i++;
		}
	}

	private void animate()
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
		this.yAxisLength = this.getHeight() * 0.67;
		this.chartLeftX = (this.getWidth() - this.xAxisLength) * 0.5;
		this.chartTopY = (this.getHeight() - yAxisLength) * 0.5;
	}

	public void drawSign(Graphics g)
	{
		if(this.drawSign)
		{
			g.setColor(Color.white);
			double value = this.getValue();
			String displayText = this.getKey() + ": " + String.valueOf(value);
			Rectangle2D fontRect = g.getFontMetrics().getStringBounds(displayText, g);
			int x = (int) (this.centerOfSignX - fontRect.getWidth() * 0.5 - 5);
			int y = (int) (this.centerOfSignY - fontRect.getHeight() * 0.5 - 5);
			int width = (int) fontRect.getWidth() + 10;
			int height = (int) fontRect.getHeight() + 10;
			g.fillRoundRect(x, y, width, height, 10, 10);
			g.setColor(new Color(55, 95, 174));
			g.drawRoundRect(x, y, width, height, 10, 10);
			g.setColor(Color.black);
			g.drawString(displayText, (int) (this.centerOfSignX - fontRect.getWidth() * 0.5), (int) (this.centerOfSignY + fontRect.getHeight() * 0.5 - 5));
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
