package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.NumberTextFieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

/**
 * Klasse für die GUIKomponente Textfeld mit beschreibendem Label. 
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class NumberTextFieldMitLabel extends InputGuiKomponente
{
	private String labelValue;

	private String textfieldValue;

	private JTextField textfieldObject;

	private JLabel labelObject;
	
	private JLabel errorLabel;
	
	private JPanel errorPanel;

	private Settings settings;

	private NumberTextFieldData textfieldData;


	/**
	 * Konstruktor zur Erstellung der TextFieldMitLabel-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pLabelValue
	 * @param pTextFieldValue
	 * @param pFinal
	 * @param pSettings
	 */
	public NumberTextFieldMitLabel(String pLabelValue, Number pTextFieldValue, boolean pFinal, Settings pSettings, NumberTextFieldData textfieldData)
	{
		super();
		this.settings = pSettings;
		this.textfieldData = textfieldData;
		Dimension size = new Dimension(Integer.parseInt(settings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(settings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.92), (int) (size.getHeight() * 0.1));
		setPanelSize(size);

		this.labelValue = pLabelValue;
		this.textfieldValue = pTextFieldValue.toString();
		this.setLayout(new BorderLayout());
		checkLabelValue();
		labelObject = new JLabel(labelValue);
		labelObject.setFont(textfont);
		labelObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(labelObject, BorderLayout.WEST);
		textfieldObject = new JTextField(50);
		textfieldObject.setFont(textfont);
		textfieldObject.setText(textfieldValue);
		errorPanel = new JPanel();
		BufferedImage myPicture;
		try
		{
			InputStream input = getClass().getClassLoader().getResourceAsStream("error_Icon.png");
			if(input != null)
			{
				myPicture = ImageIO.read(input);
				ImageIcon errorImg = new ImageIcon(myPicture.getScaledInstance((int) (size.getHeight() * 0.7), (int) (size.getHeight() * 0.7), (int) (size.getHeight() * 0.7)));
			
				errorLabel = new JLabel(errorImg);
			}
			else
			{
				errorLabel = new JLabel();
			}
		}
		catch(IOException e)
		{
			errorLabel = new JLabel();
		}
		
		errorLabel.setToolTipText("ERROR! Der Inhalt kann so nicht gespeichert werden!");
		errorPanel.add(errorLabel);
		errorPanel.setOpaque(false);
		errorPanel.setVisible(false);
		if(pFinal)
		{
			textfieldObject.setEnabled(false);
		}
		this.add(textfieldObject, BorderLayout.CENTER);
		this.add(errorPanel, BorderLayout.EAST);
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

	public void showError(String pErrorMsg){
		errorLabel.setToolTipText(pErrorMsg);
		errorPanel.setVisible(true);
	}
	
	public void hideError(){
		errorPanel.setVisible(false);
	}
	
	@Override
	public void reflectData()
	{
		//TODO FIXME Diese Klasse repräsentiert momentan die UiElementData Klasse namens TextfieldData.
		//Diese repräsentiert Strings jedoch werden hier (TextFieldMitLabel) auch Number Typen verwendet.
		//Diese Number Typen werden von NumberTextfieldData repräsentiert.
		//=> anstatt nur TextFieldMitLabel zusätzlich noch NumberTextFieldMitLabel erstellen?
		//Copy that.
		
		Number num = null;
		try
		{
			num = NumberFormat.getInstance().parse(textfieldObject.getText());
		}
		catch(ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		textfieldData.setValue((t. num);
		System.out.println("F:" + this.textfieldObject.getText());
	}

	@Override
	public boolean validateContent()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
