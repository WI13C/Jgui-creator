package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

/**
 * Klasse für die GUIKomponente ComboBox zur Darstellung von einem Datum mit beschreibendem Label.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class DatumComboBoxen extends GUIKomponente
{
	private String labelValue;

	private String comboDayValue;

	private String comboMonthValue;

	private String comboYearValue;

	private JComboBox<String> comboboxDayObject;

	private JComboBox<String> comboboxMonthObject;

	private JComboBox<String> comboboxYearObject;

	private JLabel label;

	private List<String> listOfMonths;

	private String[] possibledays;

	//	private String[] possiblemonths = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
	private String[] possiblemonths;

	private String[] possibleyears;

	/**
	 * Konstruktor zur Erstellung der DatumComboBoxen-GUIKomponente. Größe wird anhand der Settings gesetzt.
	 * 
	 * @param pValueLabel
	 * @param pVDate
	 * @param pFinal
	 * @param pSettings
	 */
	public DatumComboBoxen(String pValueLabel, Calendar pDate, boolean pFinal, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;
		this.comboDayValue = Integer.toString(pDate.get(Calendar.DAY_OF_MONTH));
		this.comboMonthValue = Integer.toString(pDate.get(Calendar.MONTH) + 1);
		this.comboYearValue = Integer.toString(pDate.get(Calendar.YEAR));

		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initYears();
		initMonths();
		comboboxMonthObject = new JComboBox<>(possiblemonths);
		comboboxMonthObject.setSelectedItem(comboMonthValue);
		comboboxMonthObject.addActionListener(e -> updateDateComboBox());

		updateDays();

		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		label.setFont(textfont);
		label.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(label, BorderLayout.WEST);

		comboboxDayObject = new JComboBox<>(possibledays);

		comboboxYearObject = new JComboBox<>(possibleyears);
		comboboxYearObject.addActionListener(e -> updateDateComboBox());
		comboboxDayObject.setFont(textfont);
		comboboxMonthObject.setFont(textfont);
		comboboxYearObject.setFont(textfont);

		comboboxDayObject.setSelectedItem(comboDayValue);

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

	private void updateDays()
	{
		listOfMonths = range(1, Month.valueOfIgnoreCase((String) comboboxMonthObject.getSelectedItem()).getNumberOfDays());
		if(comboboxMonthObject.getSelectedIndex() == 1)
		{

			if(checkLeapYear())
			{
				listOfMonths.remove(listOfMonths.size() - 1);
			}
		}
		possibledays = listOfMonths.stream().toArray(String[]::new);
	}

	void updateDateComboBox()
	{
		int selected = comboboxDayObject.getSelectedIndex();
		updateDays();
		comboboxDayObject.removeAllItems();
		for(String item : possibledays)
		{
			comboboxDayObject.addItem(item);
		}
		if(selected < possibledays.length)
		{
			comboboxDayObject.setSelectedIndex(selected);
		}
		else
		{
			comboboxDayObject.setSelectedIndex(possibledays.length - 1);
		}

	}

	private boolean checkLeapYear()
	{
		int theYear = Integer.parseInt((String) comboboxYearObject.getSelectedItem());
		if(theYear % 4 == 0)
		{
			if(theYear % 100 != 0 || theYear % 400 == 0)
			{
				return true;

			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	/**
	 * Methode zur Initialisierung des Arrays mit den Jahren die in der Combobox auszuwählen sind.
	 */
	private void initYears()
	{
		List<String> listOfYears = range(0, (int) (Calendar.getInstance().get(Calendar.YEAR) * 1.5));
		possibleyears = listOfYears.stream().toArray(String[]::new);
	}

	private void initMonths()
	{
		List<String> enumNames = Stream.of(Month.values()).map(Month::getName).collect(Collectors.toList());
		possiblemonths = enumNames.stream().toArray(String[]::new);
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

	public void setDate(Calendar pCalendar)
	{
		comboboxDayObject.setSelectedItem(comboDayValue);
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

	@Override
	public void reflectData()
	{

	}

	public enum Month
	{
		JANUARY(31, "Januar"), FEBRUARY(29, "Februar"), MARCH(31, "März"), APRIL(30, "April"), MAY(31, "Mai"), JUNE(30, "Juni"), JULY(31, "Juli"), AUGUST(31, "August"), SEPTEMBER(30, "September"), OCTOBER(31, "Oktober"), NOVEMBER(30, "November"), DECEMBER(31, "Dezember");
		private int days;

		private String name;

		Month(int pDays, String pName)
		{
			days = pDays;
			name = pName;
		}

		public int getNumberOfDays()
		{
			return this.days;
		}

		public String getName()
		{
			return this.name;
		}

		public static Month valueOfIgnoreCase(String name)
		{

			for(Month enumValue : Month.values())
			{
				if(enumValue.getName().equalsIgnoreCase(name))
				{
					return enumValue;
				}
			}

			throw new IllegalArgumentException(String.format("There is no value with name '%s' in Enum Month", name));
		}
	}

	public static List<String> range(int min, int max)
	{
		List<String> list = new LinkedList<>();
		for(int i = min; i <= max; i++)
		{
			list.add(Integer.toString(i));
		}

		return list;
	}
}
