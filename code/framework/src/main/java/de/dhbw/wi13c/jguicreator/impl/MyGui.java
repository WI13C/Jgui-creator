package de.dhbw.wi13c.jguicreator.impl;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import de.dhbw.wi13c.jguicreator.Gui;
import de.dhbw.wi13c.jguicreator.Settings;
import de.dhbw.wi13c.jguicreator.Settings.Setting;
import de.dhbw.wi13c.jguicreator.data.uielements.DomainObject;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;
import de.dhbw.wi13c.jguicreator.listener.GuiListener;

public class MyGui extends Gui
{

	private JFrame mainFrame;

	private List<JComponent> elements;

	private Settings settings;
	
	private SwingVisitor swingVisitor;

	public MyGui(DomainObject domainObject, GuiListener[] guiListeners)
	{
		super(domainObject, guiListeners);
		settings = new Settings();
		init(domainObject);
	}

	/**
	 * Constructs default GUI-Layout for the given DomainObject
	 * @param domainObject
	 */
	public void init(DomainObject domainObject)
	{
		swingVisitor = new SwingVisitor(this);
		mainFrame = new JFrame();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT))));
		elements = new ArrayList<>();
		mainFrame.setLayout(new FlowLayout());
		for(UiElementData elementData : domainObject.getUiElementContainer().getElements())
		{
			elementData.accept(swingVisitor);
			//			System.out.println(elementData.getDatafield().getValue());
		}

		show();
	}

	@Override
	public void show()
	{

		for(JComponent elem : elements){
			mainFrame.add(elem);
		}

		

		mainFrame.setTitle("Foo");
		mainFrame.setSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT))));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.validate();
		
		mainFrame.setVisible(true);
	}

	public void addElement(JComponent element)
	{
		elements.add(element);
	}

	public Settings getSettings()
	{
		return settings;
	}

	public void setSettings(Settings settings)
	{
		this.settings = settings;
	}
	
	

}
