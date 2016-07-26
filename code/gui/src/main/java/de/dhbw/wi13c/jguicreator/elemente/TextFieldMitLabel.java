package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public class TextFieldMitLabel extends GUIKomponente
{
	private String labelValue;

	private String textfieldValue;

	private JTextField field;

	private JLabel label;

	private Settings settings;

	public TextFieldMitLabel()
	{
		this("", "", false, new Settings());

	}

	public TextFieldMitLabel(String pLabelValue, String pTextFieldValue)
	{
		this(pLabelValue, pTextFieldValue, false, new Settings());
	}

	public TextFieldMitLabel(String pLabelValue, String pTextFieldValue, boolean pFinal, Settings pSettings)
	{
		super();
		settings = pSettings;
		Dimension size = new Dimension(Integer.parseInt(settings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(settings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initFont(15);

		this.labelValue = pLabelValue;
		this.textfieldValue = pTextFieldValue;
		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		label.setFont(font);
		label.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(label, BorderLayout.WEST);
		field = new JTextField(50);
		field.setFont(font);
		field.setText(textfieldValue);
		if(pFinal)
		{
			field.setEnabled(false);
		}
		this.add(field, BorderLayout.EAST);
	}

	private void checkLabelValue()
	{
		if(labelValue != null && !"".equals(labelValue))
		{
			labelValue += ": ";
		}
	}

	public String getTextfieldValue()
	{
		return field.getText();
	}

	public void setTextOfTextfield(String pValue)
	{
		field.setText(pValue);
	}
}
