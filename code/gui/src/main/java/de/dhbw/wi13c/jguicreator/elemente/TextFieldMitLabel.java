package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public class TextFieldMitLabel extends GUIKomponente
{
	private String labelValue;

	private String textfieldValue;

	private JTextField field;

	private JLabel label;

	public TextFieldMitLabel()
	{
		this("", "", false);

	}

	public TextFieldMitLabel(String pLabelValue, String pTextFieldValue)
	{
		this(pLabelValue, pTextFieldValue, false);
	}

	public TextFieldMitLabel(String pLabelValue, String pTextFieldValue, boolean pFinal)
	{
		super();
		this.labelValue = pLabelValue;
		this.textfieldValue = pTextFieldValue;
		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		this.add(label, BorderLayout.WEST);
		field = new JTextField(15);
		field.setText(textfieldValue);
		if(pFinal)
		{
			field.setEnabled(false);
		}
		this.add(field, BorderLayout.EAST);
	}

	private void checkLabelValue()
	{
		if(labelValue != null && !labelValue.equals(""))
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
