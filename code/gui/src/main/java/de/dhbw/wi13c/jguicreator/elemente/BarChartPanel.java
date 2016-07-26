package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Map;

import javax.swing.Timer;

public class BarChartPanel extends ChartPanel
{
	private String[] keys;
	private double[] values;

	private double maxValue;
	private double centerOfSignY;
	private double centerOfSignX;
	private int index;
	private boolean drawSign;

	public BarChartPanel(String description, Map<String, ? extends Number> keyValues)
	{
		super(description);
		this.FillArrays(keyValues);
		this.RenderMaxValue();
		this.addMouseMotionListener(this);
	}

	private void FillArrays(Map<String, ? extends Number> keyValues)
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

	private void RenderMaxValue()
	{
		for(Number value : this.values)
		{
			if(this.maxValue < value.doubleValue())
			{
				this.maxValue = value.doubleValue();
			}
		}
	}

	private int percent;

	private Timer timer;

	public void animate()
	{
		int delay = 25; //milliseconds
		this.percent = 0;
		ActionListener taskPerformer = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				if(percent <= 100)
				{
					percent++;
					repaint();
				}
				else
				{
					timer.stop();
				}
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.start();
	}

	private void drawYAxisSteps(Graphics g, int x, int y, int depth)
	{
		//Axis Description
		int axisSteps = 50; // new "step-line" every 50px
		int notationLineLength = 10;
		for(int i = depth - axisSteps; i >= 0; i -= axisSteps)
		{
			g.drawLine(x, y + i, x - notationLineLength, y + i);
		}
	}

	@Override // Animation 2
	public void drawStep(Graphics g)
	{
		// TODO check which double is needed

		//Axis
		double length = this.getWidth() * 0.67;
		// height of the y axis
		double depth = this.getHeight() * 0.67;
		double chartleftx = (this.getWidth() - length) * 0.5;
		double charttopy = (this.getHeight() - depth) * 0.5;

		// draw the axis
		g.drawLine((int) chartleftx, (int) (charttopy + depth), (int) (chartleftx + length), (int) (charttopy + depth));
		g.drawLine((int) chartleftx, (int) charttopy, (int) chartleftx, (int) (charttopy + depth));

		drawYAxisSteps(g, (int) chartleftx, (int) charttopy, (int) depth);

		// BarTiles
		double barspace = (length / this.keys.length);
		// x value of the left side of a bar
		double currentposition = chartleftx + barspace * 0.25;
		double barwidth = barspace * 0.75;

		for(int i = 0; i < this.keys.length; i++)
		{
			// Maximal height of the current bar
			double currentmaxheight = (depth * this.values[i] / this.maxValue);
			double stepHeight = currentmaxheight * 0.01;

			double drawHeight = stepHeight * this.percent;
			double bartopY = charttopy + depth - Math.floor(drawHeight);
			g.fillRect((int) currentposition, (int) bartopY, (int) barwidth, (int) drawHeight);
			currentposition += barspace;
		}
		
		// draw Description Sign
		if ( this.drawSign){
			g.setColor(Color.red);
			double value = this.values[this.index];
			String displayText = keys[this.index] + ":" + String.valueOf(value);
			double fontWidth = g.getFontMetrics().stringWidth(displayText);
			Rectangle2D fontRect = g.getFontMetrics().getStringBounds(displayText, g);
			g.fillRect((int)(this.centerOfSignX - fontRect.getWidth() * 0.5 - 5)
				, (int)(this.centerOfSignY - fontRect.getHeight() * 0.5 - 5)
				, (int)fontRect.getWidth() + 10, (int)fontRect.getHeight() + 10);
			g.setColor(Color.white);
			g.drawString(displayText, (int)(this.centerOfSignX - fontRect.getWidth() * 0.5)
				, (int)(this.centerOfSignY + fontRect.getHeight()*0.5));
			g.setColor(Color.black);
		}
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		this.drawStep(g);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		double mouseX = (double)e.getX();
		int mouseY = e.getY();
		double length = this.getWidth() *0.67;
		double chartleftx = (this.getWidth() - length) *0.5;
		double depth = this.getHeight() * 0.67;
		double charttopy = (this.getHeight() - depth) * 0.5;
		if (mouseX - chartleftx >= 0 && mouseX - chartleftx <= length
			&& mouseY - charttopy >= 0 && mouseY - charttopy <= depth)
		{
			double barspace = (length / this.values.length);
			double barwidth = barspace * 0.75;
			this.index = (int)((mouseX - chartleftx) / barspace);
			this.centerOfSignY = depth - depth * this.values[this.index] / this.maxValue + charttopy;
			this.centerOfSignX = chartleftx + barspace * 0.25 + barspace * this.index + barwidth * 0.5;
			this.drawSign = true;
		}else{
			this.drawSign = false;
		}
		
		this.repaint();
	}
}
