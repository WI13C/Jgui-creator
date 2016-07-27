package de.dhbw.wi13c.jguicreator.data.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class GUIKomponente extends JPanel
{
	protected Font font;
	private Dimension bounds;

	
	protected GUIKomponente()
	{
		this(new Dimension(700, 500));
	}

	protected GUIKomponente(Dimension pDimension)
	{
		this.bounds =pDimension;
		this.setPreferredSize(bounds);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setVisible(true);
	}

	protected void initFont(int pFontSize)
	{
		font = new Font("Monaco", Font.PLAIN, pFontSize);
		this.repaint();
	}

	protected void setPanelSize(Dimension pDimension)
	{
		this.bounds=pDimension;
		this.setPreferredSize(bounds);
	}

	public Dimension getKomponentenBounds()
	{
		return bounds;
	}
	
}
