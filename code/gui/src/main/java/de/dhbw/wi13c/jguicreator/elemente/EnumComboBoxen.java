package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.ComboBoxData;

/**
 * Klasse für die GUIKomponente ComboBox zur Darstellung von Enumerationen mit beschreibendem Label.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class EnumComboBoxen extends InputGuiKomponente
{

	private String labelValue;

	private JComboBox<Object> comboboxObject;

	private JLabel labelObject;

	private ComboBoxData comboBoxData;

	/**
	 *  Konstruktor zur Erstellung der EnumComboBoxen-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pValueLabel
	 * @param pListEnum
	 * @param pFinal
	 * @param pSettings
	 */
	public EnumComboBoxen(String pValueLabel, List<String> pListKeys, String pSelecetedKey, boolean pFinal, ComboBoxData comboBoxData, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;
		this.comboBoxData = comboBoxData;

		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.92), (int) (size.getHeight() * 0.1));
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
		comboboxObject.setSelectedItem(pSelecetedKey);
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

	@SuppressWarnings("unchecked")
	@Override
	public void reflectData()
	{
		//Dirty workaround ....
		if(comboBoxData.getValue().getClass().getEnumConstants().length > 0)
		{
			Class<? extends Enum> e = comboBoxData.getValue().getClass().getEnumConstants()[0].getClass();
			comboBoxData.setValue(Enum.valueOf(e, (String) comboboxObject.getSelectedItem()));
		}
	}

	@Override
	public boolean validateContent()
	{
		// Cannot be wrong, because only enum-items are choosable.
		return true;
	}
}
