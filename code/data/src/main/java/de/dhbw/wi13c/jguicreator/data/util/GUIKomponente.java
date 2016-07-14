package de.dhbw.wi13c.jguicreator.data.util;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class GUIKomponente extends JPanel
{
	protected GUIKomponente()
	{
		setPreferredSize(new Dimension(200, 200));
		setOpaque(true);
		setBackground(Color.RED);
		setVisible(true);
	}
}
