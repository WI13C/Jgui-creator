package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public class SingleButton extends GUIKomponente
{
	private JButton btn;

	private Settings settings;

	public SingleButton(ActionListener pActionListener)
	{
		this("Klicken", pActionListener, new Settings());
	}

	public SingleButton(String pButtonText, ActionListener pActionListener, Settings pSettings)
	{
		super();
		settings = pSettings;
		Dimension size = new Dimension(Integer.parseInt(settings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(settings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initFont(15);

		this.setLayout(new BorderLayout());

		JPanel pnlAccept = new JPanel();
		btn = new JButton(pButtonText);
		btn.addActionListener(pActionListener);
		btn.setFont(font);
		pnlAccept.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.2), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		pnlAccept.add(btn);
		pnlAccept.setOpaque(false);

		this.add(pnlAccept, BorderLayout.WEST);
	}

	public JButton getBtn()
	{
		return btn;
	}

}
