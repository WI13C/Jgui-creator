package de.dhbw.wi13c.jguicreator.data.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class GUIKomponente extends JPanel
{
	protected GUIKomponente()
	{
		this(350, 40);
	}

	protected GUIKomponente(int pWidth, int pHeigth)
	{
		this.setPreferredSize(new Dimension(pWidth, pHeigth));
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setVisible(true);
	}
}
