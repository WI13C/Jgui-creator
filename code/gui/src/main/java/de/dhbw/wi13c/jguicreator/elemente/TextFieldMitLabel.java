package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
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
public class TextFieldMitLabel extends GUIKomponente
{
	private String labelValue;

	private String textfieldValue;

	private JTextField textfieldObject;

	private JLabel labelObject;

	private Settings settings;

	private TextfieldData textfieldData;

	/**
	 * Konstruktor zur Erstellung der TextFieldMitLabel-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pLabelValue
	 * @param pTextFieldValue
	 * @param pFinal
	 * @param pSettings
	 */
	public TextFieldMitLabel(String pLabelValue, String pTextFieldValue, boolean pFinal, Settings pSettings, TextfieldData textfieldData)
	{
		super();
		this.settings = pSettings;
		this.textfieldData = textfieldData;
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

	@Override
	public void reflectData()
	{
		//TODO FIXME Diese Klasse repräsentiert momentan die UiElementData Klasse namens TextfieldData.
		//Diese repräsentiert Strings jedoch werden hier (TextFieldMitLabel) auch Number Typen verwendet.
		//Diese Number Typen werden von NumberTextfieldData repräsentiert.
		//=> anstatt nur TextFieldMitLabel zusätzlich noch NumberTextFieldMitLabel erstellen?
		//Copy that.
		textfieldData.setValue(this.textfieldObject.getText());		
		System.out.println("F:" + this.textfieldObject.getText());
	}
}
