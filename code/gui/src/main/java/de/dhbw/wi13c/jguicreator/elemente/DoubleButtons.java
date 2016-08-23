package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.listener.SavedCanceledListener;

/**
 * Klasse für die GUIKomponente Doppel-Button.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class DoubleButtons extends GUIKomponente
{
	private JButton btnAcceptObject;

	private JButton btnAbortObject;

	/**
	 * Konstruktor zur Erstellung der DoubleButton-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pActionListener
	 * @param pSettings
	 */
	public DoubleButtons(SavedCanceledListener pActionListener, Settings pSettings)
	{
		super();
		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.92), (int) (size.getHeight() * 0.09));
		setPanelSize(size);

		this.setLayout(new BorderLayout());
		JPanel pnlAbort = new JPanel();
		btnAbortObject = new JButton("Abbrechen");
		btnAbortObject.addActionListener(e -> pActionListener.canceled());
		initFont(20);
		btnAbortObject.setFont(textfont);
		pnlAbort.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.2)));
		pnlAbort.add(btnAbortObject);
		pnlAbort.setOpaque(false);

		JPanel pnlAccept = new JPanel();
		btnAcceptObject = new JButton("Speichern");
		btnAcceptObject.addActionListener(e -> pActionListener.saved());
		btnAcceptObject.setFont(textfont);
		pnlAccept.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.2), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		pnlAccept.add(btnAcceptObject);
		pnlAccept.setOpaque(false);

		this.add(pnlAccept, BorderLayout.WEST);
		this.add(pnlAbort, BorderLayout.EAST);
	}

	public JButton getBtnAccept()
	{
		return btnAcceptObject;
	}

	public JButton getBtnAbort()
	{
		return btnAbortObject;
	}

	@Override
	public void reflectData()
	{
	}
}
