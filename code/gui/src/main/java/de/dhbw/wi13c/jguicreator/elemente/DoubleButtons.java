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

public class DoubleButtons extends GUIKomponente
{
	private JButton btnAccept;

	private JButton btnAbort;

	public DoubleButtons(ActionListener pActionListener, Settings pSettings)
	{
		super();
		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initFont(15);

		this.setLayout(new BorderLayout());
		JPanel pnlAbort = new JPanel();
		btnAbort = new JButton("Abbrechen");
		btnAbort.addActionListener(pActionListener);
		btnAbort.setFont(font);
		pnlAbort.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.2)));
		pnlAbort.add(btnAbort);
		pnlAbort.setOpaque(false);

		JPanel pnlAccept = new JPanel();
		btnAccept = new JButton("Speichern");
		btnAccept.addActionListener(pActionListener);
		btnAccept.setFont(font);
		pnlAccept.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.2), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		pnlAccept.add(btnAccept);
		pnlAccept.setOpaque(false);

		this.add(pnlAccept, BorderLayout.WEST);
		this.add(pnlAbort, BorderLayout.EAST);
	}

	public JButton getBtnAccept()
	{
		return btnAccept;
	}

	public JButton getBtnAbort()
	{
		return btnAbort;
	}
}
