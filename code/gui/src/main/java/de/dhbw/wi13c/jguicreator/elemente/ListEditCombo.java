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

public class ListEditCombo extends GUIKomponente
{
	private String labelValue;

	private JComboBox<String> comboListField;

	private JLabel label;

	private JButton btnEdit;

	private JButton btnRemove;

	private JButton btnAdd;

	public ListEditCombo(String pValueLabel, List pListKeys, ActionListener pActionListener, Settings pSettings)
	{
		super();
		this.labelValue = pValueLabel;

		Dimension size = new Dimension(Integer.parseInt(pSettings.getSetting(Setting.WINDOWWIDTH)), Integer.parseInt(pSettings.getSetting(Setting.WINDOWHEIGHT)));
		size.setSize((int) (size.getWidth() * 0.95), (int) (size.getHeight() * 0.08));
		setPanelSize(size);

		initFont(15);

		this.setLayout(new BorderLayout());
		checkLabelValue();
		label = new JLabel(labelValue);
		label.setFont(font);
		label.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));
		this.add(label, BorderLayout.WEST);

		comboListField = new JComboBox<>(pListKeys.getItems());
		comboListField.setFont(font);

		comboListField.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.04), (int) (size.getWidth() * 0.05), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.05)));

		this.add(comboListField, BorderLayout.CENTER);
		
		JPanel pnlAdd = new JPanel();
		btnAdd = new JButton("add");
		btnAdd.addActionListener(pActionListener);
		btnAdd.setFont(font);
		pnlAdd.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001)));
		pnlAdd.add(btnAdd);
		pnlAdd.setOpaque(false);

		JPanel pnlEdit = new JPanel();
		btnEdit = new JButton("edit");
		btnEdit.addActionListener(pActionListener);
		btnEdit.setFont(font);
		pnlEdit.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001)));
		pnlEdit.add(btnEdit);
		pnlEdit.setOpaque(false);

		JPanel pnlRemove = new JPanel();
		btnRemove = new JButton("remove");
		btnRemove.addActionListener(pActionListener);
		btnRemove.setFont(font);
		pnlRemove.setBorder(BorderFactory.createEmptyBorder((int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001), (int) (size.getHeight() * 0.01), (int) (size.getWidth() * 0.0001)));
		pnlRemove.add(btnRemove);
		pnlRemove.setOpaque(false);

		JPanel pnlBtns = new JPanel();
		pnlBtns.add(pnlAdd);
		pnlBtns.add(pnlEdit);
		pnlBtns.add(pnlRemove);
		pnlBtns.setOpaque(true);

		this.add(pnlBtns, BorderLayout.EAST);
	}

	public String getSelectedKey(){
		return (String) comboListField.getSelectedItem();
	}
	
	
	
	public JButton getBtnEdit()
	{
		return btnEdit;
	}

	public JButton getBtnRemove()
	{
		return btnRemove;
	}

	public JButton getBtnAdd()
	{
		return btnAdd;
	}

	private void checkLabelValue()
	{
		if(labelValue != null && !"".equals(labelValue))
		{
			labelValue += ": ";
		}
	}

}
