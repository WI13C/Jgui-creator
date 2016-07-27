package de.dhbw.wi13c.jguicreator.data.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

/**
 * Basisklasse für alle GUIKomponenten
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public abstract class GUIKomponente extends JPanel
{
	protected Font textfont;

	private Dimension bounds = new Dimension(700, 500);

	/**
	 * Konstruktor um die grundsätzlichen Settings des JPanels einer GUIKomponente zu setzen.
	 * 
	 */
	protected GUIKomponente()
	{
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setVisible(true);

		initFont(15);
	}

	/**
	 * Methode zur Änderung der Default-Schriftgröße für die GUIKomponente
	 * 
	 * @param pFontSize
	 */
	protected void initFont(int pFontSize)
	{
		textfont = new Font("Monaco", Font.PLAIN, pFontSize);
		this.repaint();
	}

	/**
	 * Methode zur Bestimmung der Größe der JPanels der GUIKomponente
	 * 
	 * @param pDimension
	 */
	protected void setPanelSize(Dimension pDimension)
	{
		this.bounds = pDimension;
		this.setPreferredSize(bounds);
	}

	public Dimension getKomponentenBounds()
	{
		return bounds;
	}

}
