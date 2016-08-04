package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.Datafield.DatafieldType;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.listener.SaveListener;

/**
 * Klasse für die GUIKomponente Textfeld mit beschreibendem Label. 
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class TextFieldMitLabel extends GUIKomponente implements SaveListener
{
	private String labelValue;

	private String textfieldValue;

	private JTextField textfieldObject;

	private JLabel labelObject;

	private Settings settings;

	private DatafieldType datatype;

	/**
	 * Konstruktor zur Erstellung der TextFieldMitLabel-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pLabelValue
	 * @param pTextFieldValue
	 * @param pFinal
	 * @param pSettings
	 */
	public TextFieldMitLabel(String pLabelValue, String pTextFieldValue, boolean pFinal, Settings pSettings, DatafieldType datafieldType)
	{
		super();
		this.settings = pSettings;
		this.datatype = datafieldType;
		Dimension size = new Dimension(Integer.parseInt(settings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(settings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		this.labelValue = pLabelValue;
		this.textfieldValue = pTextFieldValue;
		this.setLayout(new BorderLayout());
		checkLabelValue();
		labelObject = new JLabel(labelValue);
		labelObject.setFont(textfont);
		labelObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(labelObject, BorderLayout.WEST);
		textfieldObject = new JTextField(50);
		textfieldObject.setFont(textfont);
		textfieldObject.setText(textfieldValue);
		if(pFinal)
		{
			textfieldObject.setEnabled(false);
		}
		this.add(textfieldObject, BorderLayout.EAST);
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

	public String getTextfieldValue()
	{
		return textfieldObject.getText();
	}

	public void setTextOfTextfield(String pValue)
	{
		textfieldObject.setText(pValue);
	}

	private String mapTextFieldValueToString()
	{
		return textfieldObject.getText();
	}

	private Integer mapTextFieldValueToInteger()
	{

		Integer value = new Integer(5);
		try
		{
			value = Integer.parseInt(textfieldObject.getText());
		}
		catch(Exception e)
		{
		}
		return value;
	}

	private Double mapTextFieldValueToDouble()
	{

		Double value = new Double(5);
		try
		{
			value = Double.parseDouble(textfieldObject.getText());
		}
		catch(Exception e)
		{
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T guiSave()
	{
		switch(datatype)
		{
			case TEXT:
				return (T) mapTextFieldValueToString();
			case DOUBLE:
				return (T) mapTextFieldValueToDouble();
			case INTEGER:
				return (T) mapTextFieldValueToInteger();
			default:
				return (T) mapTextFieldValueToString();
		}
	}
}
