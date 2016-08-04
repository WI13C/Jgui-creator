package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.listener.SaveListener;

/**
 * Klasse für die GUIKomponente ComboBox zur Darstellung von einem Datum mit beschreibendem Label.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class DatumComboBoxen extends GUIKomponente implements SaveListener
{
	private String labelValue;

	private String comboDayValue;

	private String comboMonthValue;

	private String comboYearValue;

	private JComboBox<String> comboboxDayObject;

	private JComboBox<String> comboboxMonthObject;

	private JComboBox<String> comboboxYearObject;

	private JLabel label;

	private String[] possibledays = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

	private String[] possiblemonths = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

	private String[] possibleyears;

	public DatumComboBoxen(String pValueLabel, Calendar pDate, boolean pFinal, Settings pSettings)
	{
		this(pValueLabel, Integer.toString(pDate.get(Calendar.DAY_OF_MONTH)), Integer.toString(pDate.get(Calendar.MONTH) + 1), Integer.toString(pDate.get(Calendar.YEAR)), pFinal, pSettings);
	}

	/**
	 * Konstruktor zur Erstellung der DatumComboBoxen-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pValueLabel
	 * @param pValueDay
	 * @param pValueMonth
	 * @param pValueYear
	 * @param pFinal
	 * @param pSettings
	 * @deprecated
	 */
	public DatumComboBoxen(String pValueLabel, String pValueDay, String pValueMonth, String pValueYear, boolean pFinal, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;
		this.comboDayValue = pValueDay;
		this.comboMonthValue = pValueMonth;
		this.comboYearValue = pValueYear;

		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initYears();

		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		label.setFont(textfont);
		label.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(label, BorderLayout.WEST);

		comboboxDayObject = new JComboBox<>(possibledays);
		comboboxMonthObject = new JComboBox<>(possiblemonths);
		comboboxYearObject = new JComboBox<>(possibleyears);

		comboboxDayObject.setFont(textfont);
		comboboxMonthObject.setFont(textfont);
		comboboxYearObject.setFont(textfont);

		comboDayValue = dayMapper(comboDayValue);
		comboMonthValue = monthMapper(comboMonthValue);
		comboYearValue = yearMapper(comboYearValue);

		comboboxDayObject.setSelectedItem(comboDayValue);
		comboboxMonthObject.setSelectedItem(comboMonthValue);
		comboboxYearObject.setSelectedItem(comboYearValue);

		comboboxDayObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.01)));
		comboboxMonthObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.01), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.01)));
		comboboxYearObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.01), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));

		if(pFinal)
		{
			comboboxDayObject.setEnabled(false);
			comboboxMonthObject.setEnabled(false);
			comboboxYearObject.setEnabled(false);
		}
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.add(comboboxDayObject);
		p.add(comboboxMonthObject);
		p.add(comboboxYearObject);

		this.add(p, BorderLayout.EAST);
	}

	/**
	 * Methode zur Initialisierung des Arrays mit den Jahren die in der Combobox auszuwählen sind.
	 */
	private void initYears()
	{
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		possibleyears = new String[year * 2];
		int end = year * 2;
		for(int i = end - 1; i >= 0; i--)
		{
			possibleyears[i] = Integer.toString(i);
		}
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

	/**
	 * Methode die den Zahlenwert eines Monats in den Text des Monats umwandelt.
	 * 
	 * @param pNumberOfMonth
	 * @return
	 */
	private String monthMapper(String pNumberOfMonth)
	{
		int numberOfMonth = 0;
		try
		{
			numberOfMonth = Integer.parseInt(pNumberOfMonth);
		}
		catch(Exception e)
		{
			numberOfMonth = 1;
		}
		return possiblemonths[numberOfMonth - 1];
	}

	/**
	 * Methode die prüft ob der Wert ein valider Tag sein kann.
	 * 
	 * @param pDay
	 * @return
	 */
	private String dayMapper(String pDay)
	{
		for(String day : possibledays)
		{
			if(day.equals(pDay))
			{
				return day;
			}
		}
		return "1";

	}

	/**
	 * Methode die prüft ob das Jahr in der Combobox angezeigt werden kann.
	 * 
	 * @param pYear
	 * @return
	 */
	private String yearMapper(String pYear)
	{
		for(String year : possibleyears)
		{
			if(year.equals(pYear))
			{
				return year;
			}
		}
		return "2000";
	}

	public void setDay(String pDay)
	{
		comboDayValue = dayMapper(pDay);
		comboboxDayObject.setSelectedItem(comboDayValue);
	}

	public void setMonth(String pMonth)
	{
		comboMonthValue = monthMapper(pMonth);
		comboboxMonthObject.setSelectedItem(comboMonthValue);
	}

	public void setYear(String pYear)
	{
		comboYearValue = yearMapper(pYear);
		comboboxYearObject.setSelectedItem(comboYearValue);
	}

	public String getDay()
	{
		return (String) comboboxDayObject.getSelectedItem();
	}

	public String getMonth()
	{
		return (String) comboboxMonthObject.getSelectedItem();
	}

	public String getYear()
	{
		return (String) comboboxYearObject.getSelectedItem();
	}

	public Calendar getCalendarItem()
	{
		return new GregorianCalendar(Integer.parseInt((String) comboboxYearObject.getSelectedItem()), Integer.parseInt((String) comboboxMonthObject.getSelectedItem()), Integer.parseInt((String) comboboxDayObject.getSelectedItem()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T guiSave()
	{
		return (T) new GregorianCalendar(Integer.parseInt((String) comboboxYearObject.getSelectedItem()), Integer.parseInt((String) comboboxMonthObject.getSelectedItem()), Integer.parseInt((String) comboboxDayObject.getSelectedItem()));
	}
}
