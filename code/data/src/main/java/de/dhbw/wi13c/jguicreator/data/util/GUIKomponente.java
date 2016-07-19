package de.dhbw.wi13c.jguicreator.data.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

public abstract class GUIKomponente extends JPanel
{
	protected Font font;

	protected GUIKomponente()
	{
		this(700, 500);
	}

	protected GUIKomponente(int pWidth, int pHeigth)
	{
		this.setPreferredSize(new Dimension(pWidth, pHeigth));
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
		this.setPreferredSize(pDimension);
	}
}
