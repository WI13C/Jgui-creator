package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

@SuppressWarnings("serial")
public class ErrorPanel extends GUIKomponente
{

	public ErrorPanel(String pErrorText, Settings pSettings){
		super();
		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.92), (int) (size.getHeight() * 0.14));
		setPanelSize(size);
		initFont(22);
		this.setBorder(new TitledBorder("ERROR"));
		JLabel errorLabel = new JLabel(pErrorText);
		errorLabel.setFont(textfont);
		this.add(errorLabel);
		
		this.setBackground(new Color(0xD14F42));
	}
	
	@Override
	public void reflectData()
	{
	}

}
