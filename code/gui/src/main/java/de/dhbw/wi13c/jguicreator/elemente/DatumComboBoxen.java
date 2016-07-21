package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public class DatumComboBoxen extends GUIKomponente
{
	private String labelValue;

	private String comboDayValue;

	private String comboMonthValue;

	private String comboYearValue;

	private JComboBox<String> comboDayField;

	private JComboBox<String> comboMonthField;

	private JComboBox<String> comboYearField;

	private JLabel label;

	private Settings settings;

	private String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

	private String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};

	private String years[];

	public DatumComboBoxen()
	{
		this("", "1", "1", "2000", false, new Settings());
	}

	public DatumComboBoxen(String pValueLabel, String pValueDay, String pValueMonth, String pValueYear)
	{
		this(pValueLabel, pValueDay, pValueMonth, pValueYear, false, new Settings());
	}

	public DatumComboBoxen(String pValueLabel, String pValueDay, String pValueMonth, String pValueYear, boolean pFinal, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;
		this.comboDayValue = pValueDay;
		this.comboMonthValue = pValueMonth;
		this.comboYearValue = pValueYear;

		settings = pSettings;
		Dimension size = new Dimension(Integer.parseInt(settings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(settings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initFont(15);
		initYears();

		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		label.setFont(font);
		label.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(label, BorderLayout.WEST);

		comboDayField = new JComboBox<>(days);
		comboMonthField = new JComboBox<>(months);
		comboYearField = new JComboBox<>(years);

		comboDayField.setFont(font);
		comboMonthField.setFont(font);
		comboYearField.setFont(font);

		comboDayValue = dayMapper(comboDayValue);
		comboMonthValue = monthMapper(comboMonthValue);
		comboYearValue = yearMapper(comboYearValue);

		comboDayField.setSelectedItem(comboDayValue);
		comboMonthField.setSelectedItem(comboMonthValue);
		comboYearField.setSelectedItem(comboYearValue);

		comboDayField.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.01)));
		comboMonthField.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.01), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.01)));
		comboYearField.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.01), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));

		if(pFinal)
		{
			comboDayField.setEnabled(false);
			comboMonthField.setEnabled(false);
			comboYearField.setEnabled(false);
		}
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.add(comboDayField);
		p.add(comboMonthField);
		p.add(comboYearField);

		this.add(p, BorderLayout.EAST);
	}

	private void initYears()
	{
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		years = new String[year * 2];
		int end = year * 2;
		for(int i = end - 1; i >= 0; i--)
		{
			years[i] = Integer.toString(i);
		}
	}

	private void checkLabelValue()
	{
		if(labelValue != null && !labelValue.equals(""))
		{
			labelValue += ": ";
		}
	}

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
		return months[numberOfMonth - 1];
	}

	private String dayMapper(String pDay)
	{
		for(String day : days)
		{
			if(day.equals(pDay))
			{
				return day;
			}
		}
		return "1";

	}

	private String yearMapper(String pYear)
	{
		for(String year : years)
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
		comboDayField.setSelectedItem(comboDayValue);
	}

	public void setMonth(String pMonth)
	{
		comboMonthValue = monthMapper(pMonth);
		comboMonthField.setSelectedItem(comboMonthValue);
	}

	public void setYear(String pYear)
	{
		comboYearValue = yearMapper(pYear);
		comboYearField.setSelectedItem(comboYearValue);
	}

	public String getDay()
	{
		return (String) comboDayField.getSelectedItem();
	}

	public String getMonth()
	{
		return (String) comboMonthField.getSelectedItem();
	}

	public String getYear()
	{
		return (String) comboYearField.getSelectedItem();
	}
}
