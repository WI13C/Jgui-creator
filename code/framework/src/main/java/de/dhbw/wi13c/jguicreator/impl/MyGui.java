package de.dhbw.wi13c.jguicreator.impl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

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
		init(domainObject);
	}

	public void init(DomainObject domainObject)
	{
		swingVisitor = new SwingVisitor(this);
		mainFrame = new JFrame();
		elements = new ArrayList<>();
		mainFrame.setLayout(new GridLayout(domainObject.getUiElementContainer().getElements().size(), 1));
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

		
		settings = new Settings();
		settings.setSetting(Setting.WINDOWHEIGHT, ""+(elements.size()+1)*50);
		mainFrame.setTitle("Foo");
		mainFrame.setSize(new Dimension(Integer.valueOf(settings.getSetting(Setting.WINDOWWIDTH)), Integer.valueOf(settings.getSetting(Setting.WINDOWHEIGHT))));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.validate();
		mainFrame.repaint();
		
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
