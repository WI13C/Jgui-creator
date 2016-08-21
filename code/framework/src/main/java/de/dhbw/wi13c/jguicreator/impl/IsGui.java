package de.dhbw.wi13c.jguicreator.impl;

import javax.swing.JFrame;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

/**
 * IsGui defines the behavior for a gui like a JDialog or a JFrame
 * and is handled by {@link SwingVisitor}.
 * 
 * @author Lukas Hessenthaler
 *
 */
public interface IsGui
{
	public JFrame getFrame();
	public void addElement(GUIKomponente elem);
	public void save();
	public Settings getSettings();
}
