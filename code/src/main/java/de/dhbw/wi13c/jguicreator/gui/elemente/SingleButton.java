package de.dhbw.wi13c.jguicreator.gui.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.gui.Settings;
import de.dhbw.wi13c.jguicreator.gui.Settings.Setting;

/**
 * Klasse für die GUIKomponente Einzel-Button.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class SingleButton extends GUIKomponente
{
	private JButton btnObject;

	/**
	 * Konstruktor zur Erstellung der SingleButton-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pButtonText
	 * @param eventListener
	 * @param pSettings
	 */
	public SingleButton(String pButtonText, ActionListener eventListener, Settings pSettings)
	{
		super();
		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		this.setLayout(new BorderLayout());

		JPanel pnlAccept = new JPanel();
		btnObject = new JButton(pButtonText);
		btnObject.addActionListener(eventListener);
		btnObject.setFont(textfont);
		pnlAccept.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.2), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		pnlAccept.add(btnObject);
		pnlAccept.setOpaque(false);

		this.add(pnlAccept, BorderLayout.WEST);
	}

	public JButton getBtn()
	{
		return btnObject;
	}

	@Override
	public void reflectData()
	{
		// TODO Auto-generated method stub
		
	}

}
