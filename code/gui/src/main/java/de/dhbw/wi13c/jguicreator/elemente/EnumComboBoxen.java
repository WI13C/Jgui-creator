package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public class EnumComboBoxen extends GUIKomponente
{

	private String labelValue;

	private JComboBox<Object> comboEnumField;

	private JLabel label;

	private Settings settings;

	public EnumComboBoxen(String pValueLabel, List pListEnum, boolean pFinal, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;

		settings = pSettings;
		Dimension size = new Dimension(Integer.parseInt(settings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(settings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initFont(15);

		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		label.setFont(font);
		label.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(label, BorderLayout.WEST);

		comboEnumField = new JComboBox<>(pListEnum.getItems());
		comboEnumField.setFont(font);

		comboEnumField.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));

		if(pFinal)
		{
			comboEnumField.setEnabled(false);
		}
		this.add(comboEnumField, BorderLayout.EAST);
	}

	private void checkLabelValue()
	{
		if(labelValue != null && !labelValue.equals(""))
		{
			labelValue += ": ";
		}
	}

	public String getSelectedItem()
	{
		return (String) comboEnumField.getSelectedItem();
	}
}
