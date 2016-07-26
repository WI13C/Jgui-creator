package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Graphics;
import java.awt.event.MouseMotionListener;

import javax.swing.border.TitledBorder;

import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public abstract class ChartPanel extends GUIKomponente implements MouseMotionListener
{
	
	private String description;

	protected ChartPanel(String description)
	{	
		super();
		this.setDescription(description);
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
		this.setBorder(new TitledBorder(description + ": "));
	}
	
	public abstract void drawStep(Graphics g);
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);		
	}

}
