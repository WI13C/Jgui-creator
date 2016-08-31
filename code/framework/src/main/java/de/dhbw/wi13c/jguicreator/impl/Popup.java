package de.dhbw.wi13c.jguicreator.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

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
import de.dhbw.wi13c.jguicreator.elemente.InputGuiKomponente;
import de.dhbw.wi13c.jguicreator.listener.ObjectSavedListener;
import de.dhbw.wi13c.jguicreator.listener.SavedCanceledListener;
import de.dhbw.wi13c.jguicreator.util.WrapLayout;

/**
 * Popup is equal to {@link RootGui} and implements its behavior from {@link IsGui}.
 * There can be multiple Popups, but only one {@link RootGui}.
 * @author Lukas Hessenthaler
 *
 */
public class Popup extends JDialog implements IsGui
{
	private static final long serialVersionUID = 1L;

	private Settings settings;

	private List<ObjectSavedListener> listener;

	private SwingVisitor swingVisitor;

	private List<GUIKomponente> elements;

	private DomainObject domainObject;

	public Popup(String title, JFrame parent, DomainObject domainObject)
	{
		super(parent, title, true);
		this.domainObject = domainObject;
		init();
	}

	/**
	 * Init the gui layout.
	 * @param domainObject
	 */
	private void init()
	{
		settings = new Settings();
		swingVisitor = new SwingVisitor(this);
		elements = new ArrayList<>();
		listener = new ArrayList<>();

		setSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT))));

		JPanel innerScrollPane = new JPanel();
		innerScrollPane.setLayout(new WrapLayout(FlowLayout.LEFT));

		JScrollPane scrollPane = new JScrollPane(innerScrollPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)) - 30, Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT)) - 30));

		setResizable(false);
		setLayout(new FlowLayout());
		for(UiElementData<?> elementData : domainObject.getUiElementContainer().getElements())
		{
			elementData.accept(swingVisitor);
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
				save();
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
		//dumb, but needed for RootGui...
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

	@Override
	public void save()
	{
		boolean valide = true;
		for(GUIKomponente guiKomponente : elements)
		{
			if(guiKomponente instanceof InputGuiKomponente)
			{
				if(!((InputGuiKomponente) guiKomponente).validateContent())
				{
					valide = false;
				}
			}
		}

		if(valide)
		{
			for(GUIKomponente guiKomponente : elements)
			{
				if(guiKomponente instanceof InputGuiKomponente)
				{
					((InputGuiKomponente) guiKomponente).reflectData();
				}
			}
			for(ObjectSavedListener listener : listener)
			{
				listener.saved(domainObject);
			}
		}

	}

	public void addObjectSavedListener(ObjectSavedListener listener)
	{
		this.listener.add(listener);
	}
}
