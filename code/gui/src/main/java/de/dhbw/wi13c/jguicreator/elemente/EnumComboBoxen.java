package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

/**
 * Klasse für die GUIKomponente ComboBox zur Darstellung von Enumerationen mit beschreibendem Label.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class EnumComboBoxen extends GUIKomponente
{

	private String labelValue;

	private JComboBox<Object> comboboxObject;

	private JLabel labelObject;

	/**
	 *  Konstruktor zur Erstellung der EnumComboBoxen-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pValueLabel
	 * @param pListEnum
	 * @param pFinal
	 * @param pSettings
	 */
	public EnumComboBoxen(String pValueLabel, List<String> pListKeys, boolean pFinal, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;

		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		this.setLayout(new BorderLayout());
		checkLabelValue();
		labelObject = new JLabel(labelValue);
		labelObject.setFont(textfont);
		labelObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(labelObject, BorderLayout.WEST);

		String[] keys = new String[pListKeys.size()];
		keys = pListKeys.toArray(keys);
		comboboxObject = new JComboBox<>(keys);
		comboboxObject.setFont(textfont);

		comboboxObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));

		if(pFinal)
		{
			comboboxObject.setEnabled(false);
		}
		this.add(comboboxObject, BorderLayout.EAST);
	}

	/**
	 * Methode die den Text des Labels um ":" erweitert.
	 */
	private void checkLabelValue()
	{
		if(labelValue != null && !"".equals(labelValue))
		{
			labelValue += ": ";
		}
	}

	public String getSelectedItem()
	{
		return (String) comboboxObject.getSelectedItem();
	}

	@Override
	public void reflectData()
	{
		// TODO Auto-generated method stub
		
	}
}
