package de.dhbw.wi13c.jguicreator.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.dhbw.wi13c.jguicreator.Gui;
import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.elemente.DoubleButtons;
import de.dhbw.wi13c.jguicreator.elemente.InputGuiKomponente;
import de.dhbw.wi13c.jguicreator.listener.ObjectSavedListener;
import de.dhbw.wi13c.jguicreator.listener.SavedCanceledListener;
import de.dhbw.wi13c.jguicreator.listener.SavedListener;
import de.dhbw.wi13c.jguicreator.util.WrapLayout;

/**
 * RootGui is the root of all [gui].
 * Dependent on the {@link DomainObject} there will be recursivly called some Popups: {@link Popup}.
 * There can be multiple Popups, but only one {@link RootGui}.
 * 
 * @author Lukas Hessenthaler
 *
 */
public class RootGui extends Gui implements IsGui
{

	private JFrame mainFrame;

	private List<GUIKomponente> elements;

	private Settings settings;

	private SwingVisitor swingVisitor;

	private JScrollPane scrollPane;

	private JPanel innerScrollPane;

	private SavedListener<DomainObject> domainObjectSavedListener;

	private DomainObject domainObject;

	public RootGui(DomainObject domainObject, SavedListener<DomainObject> domainObjectSavedListener)
	{
		super(domainObject);
		this.domainObject = domainObject;
		this.domainObjectSavedListener = domainObjectSavedListener;
		init(domainObject);
	}

	/**
	 * Constructs default GUI-Layout for the given DomainObject
	 * 
	 * @param domainObject
	 */
	public void init(DomainObject domainObject)
	{
		settings = new Settings();
		swingVisitor = new SwingVisitor(this);
		elements = new ArrayList<>();

		innerScrollPane = new JPanel();
		innerScrollPane.setLayout(new WrapLayout(FlowLayout.LEFT));

		scrollPane = new JScrollPane(innerScrollPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)) - 30, Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT)) - 30));
		mainFrame = new JFrame();

		mainFrame.setLayout(new FlowLayout());
		for(UiElementData<?> elementData : domainObject.getUiElementContainer().getElements())
		{
			elementData.accept(swingVisitor);
			// System.out.println(elementData.getDatafield().getValue());
		}

		show();
	}

	@Override
	public void show()
	{

		// 50, because of save and exit buttons
		int preferedScrollPaneSize = 50;
		for(GUIKomponente elem : elements)
		{
			innerScrollPane.add(elem);
			preferedScrollPaneSize += elem.getKomponentenBounds().getHeight() + 7;
		}

		// Default save and exit buttons
		DoubleButtons saveExit = new DoubleButtons(new SavedCanceledListener()
		{

			@Override
			public void saved()
			{
				if(validate())
				{
					save();
					domainObjectSavedListener.saved(domainObject);
				}

			}

			@Override
			public void canceled()
			{
				if(JOptionPane.showConfirmDialog(mainFrame, "Das Schließen des Fensters führt zum Datenverlust!\nTrotzdem schließen?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}

			}
		}, getSettings());

		innerScrollPane.add(saveExit);

		innerScrollPane.setPreferredSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), preferedScrollPaneSize));
		innerScrollPane.validate();
		innerScrollPane.repaint();
		mainFrame.add(scrollPane);

		// Override default windows closing behavior
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter()
		{
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent)
			{
				if(JOptionPane.showConfirmDialog(mainFrame, "Das Schließen des Fensters führt zum Datenverlust!\nTrotzdem schließen?", "Achtung!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
			}
		});

		mainFrame.setTitle("Foo");
		mainFrame.setSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT))));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.validate();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}

	public void addElement(GUIKomponente element)
	{
		elements.add(element);
	}

	@Override
	public Settings getSettings()
	{
		return settings;
	}

	public void setSettings(Settings settings)
	{
		this.settings = settings;
	}

	public JFrame getFrame()
	{
		return mainFrame;
	}

	@Override
	public void save()
	{

		for(GUIKomponente guiKomponente : elements)
		{
			if(guiKomponente instanceof InputGuiKomponente)
			{
				((InputGuiKomponente) guiKomponente).reflectData();
			}
		}

		System.out.println("saved");
	}

	private boolean validate()
	{
		boolean valide = true;
		for(GUIKomponente guiKomponente : elements)
		{
			if(guiKomponente instanceof InputGuiKomponente)
			{
				if(!((InputGuiKomponente) guiKomponente).validateContent())
				{
					System.out.println("not valide: " + guiKomponente.getClass());
					valide = false;
				}
			}
		}
		return valide;
	}

}
