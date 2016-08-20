package de.dhbw.wi13c.jguicreator.framework.impl;

import javax.swing.JFrame;

import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.gui.Settings;

public interface IsGui
{
	public JFrame getFrame();
	public void addElement(GUIKomponente elem);
	public void save();
	public Settings getSettings();
}
