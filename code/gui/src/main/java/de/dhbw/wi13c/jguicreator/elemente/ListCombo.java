package de.dhbw.wi13c.jguicreator.elemente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

/**
 * Klasse für die GUIKomponente ComboBox zur Darstellung von Listen mit beschreibendem Label und Buttons.
 * 
 * @author Tim Bayer
 *
 */
@SuppressWarnings("serial")
public class ListCombo extends GUIKomponente
{
	private String labelValue;

	private JComboBox<String> comboBoxObject;

	private JLabel labelObject;

	private JButton btnEditObject;

	private JButton btnRemoveObject;

	private JButton btnAddObject;
/**
 * Konstruktor zur Erstellung der ListCombo-GUIKomponente. Größe wird anhand der Settings gesetzt.
 * 
 * @param pValueLabel
 * @param pListKeys
 * @param pActionListener
 * @param pSettings
 */
	public ListCombo(String pValueLabel, List pListKeys, ActionListener pActionListener, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;

		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		this.setLayout(new BorderLayout());
		checkLabelValue();
		labelObject = new JLabel(labelValue);
		labelObject.setFont(textfont);
		labelObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(labelObject, BorderLayout.WEST);

		comboBoxObject = new JComboBox<>(pListKeys.getItems());
		comboBoxObject.setFont(textfont);

		comboBoxObject.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));

		this.add(comboBoxObject, BorderLayout.CENTER);

		JPanel pnlAdd = new JPanel();
		btnAddObject = new JButton("add");
		btnAddObject.addActionListener(pActionListener);
		btnAddObject.setFont(textfont);
		pnlAdd.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001)));
		pnlAdd.add(btnAddObject);
		pnlAdd.setOpaque(false);

		JPanel pnlEdit = new JPanel();
		btnEditObject = new JButton("edit");
		btnEditObject.addActionListener(pActionListener);
		btnEditObject.setFont(textfont);
		pnlEdit.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001)));
		pnlEdit.add(btnEditObject);
		pnlEdit.setOpaque(false);

		JPanel pnlRemove = new JPanel();
		btnRemoveObject = new JButton("remove");
		btnRemoveObject.addActionListener(pActionListener);
		btnRemoveObject.setFont(textfont);
		pnlRemove.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001)));
		pnlRemove.add(btnRemoveObject);
		pnlRemove.setOpaque(false);

		JPanel pnlBtns = new JPanel();
		pnlBtns.add(pnlAdd);
		pnlBtns.add(pnlEdit);
		pnlBtns.add(pnlRemove);
		pnlBtns.setOpaque(true);

		this.add(pnlBtns, BorderLayout.EAST);
	}

	public String getSelectedKey()
	{
		return (String) comboBoxObject.getSelectedItem();
	}

	public JButton getBtnEdit()
	{
		return btnEditObject;
	}

	public JButton getBtnRemove()
	{
		return btnRemoveObject;
	}

	public JButton getBtnAdd()
	{
		return btnAddObject;
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

}