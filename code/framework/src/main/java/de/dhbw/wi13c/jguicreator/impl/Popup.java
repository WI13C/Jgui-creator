package de.dhbw.wi13c.jguicreator.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.elemente.DoubleButtons;
import de.dhbw.wi13c.jguicreator.listener.SavedCanceledListener;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;
import de.dhbw.wi13c.jguicreator.util.WrapLayout;

public class Popup extends JDialog implements IsGui
{
	private static final long serialVersionUID = 1L;

	private SavedListener<DomainObject> domainObjectSavedListener;

	private DomainObject domainObject;

	private Settings settings;

	private SwingVisitor swingVisitor;

	private List<GUIKomponente> elements;

	public Popup(String title, JFrame parent, DomainObject domainObject, SavedListener<DomainObject> domainObjectSavedListener)
	{
		super(parent, title, true);
		this.domainObject = domainObject;
		this.domainObjectSavedListener = domainObjectSavedListener;
		settings = new Settings();
		init(domainObject);

	}

	private void init(DomainObject domainObject)
	{
		swingVisitor = new SwingVisitor(this);
		elements = new ArrayList<>();

		setSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT))));

		JPanel innerScrollPane = new JPanel();
		innerScrollPane.setLayout(new WrapLayout());

		JScrollPane scrollPane = new JScrollPane(innerScrollPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)) - 30, Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT)) - 30));

		setLayout(new FlowLayout());
		for(UiElementData elementData : domainObject.getUiElementContainer().getElements())
		{
			elementData.accept(swingVisitor);
			//			System.out.println(elementData.getDatafield().getValue());
		}

		// 50, because of save and exit buttons
		int preferedScrollPaneSize = 50;
		for(GUIKomponente elem : elements)
		{
			innerScrollPane.add(elem);
			preferedScrollPaneSize += elem.getKomponentenBounds().getHeight() + 7;
		}

		//Default save and exit buttons
		DoubleButtons saveExit = new DoubleButtons(new SavedCanceledListener()
		{

			@Override
			public void saved()
			{
				System.out.println("saved");
				domainObjectSavedListener.saved(domainObject);
			}

			@Override
			public void canceled()
			{
				dispose();

			}
		}, getSettings());
		add(scrollPane);

		innerScrollPane.add(saveExit);
		innerScrollPane.setPreferredSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), preferedScrollPaneSize));
		innerScrollPane.validate();
		innerScrollPane.repaint();

	}

	@Override
	public JFrame getFrame()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addElement(GUIKomponente elem)
	{
		elements.add(elem);

	}

	@Override
	public Settings getSettings()
	{
		return settings;
	}
}
