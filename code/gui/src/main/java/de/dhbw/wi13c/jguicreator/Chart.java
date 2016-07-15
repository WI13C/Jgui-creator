package de.dhbw.wi13c.jguicreator;

import javax.swing.border.TitledBorder;

import de.dhbw.wi13c.jguicreator.data.util.GUIKomponente;

public abstract class Chart extends GUIKomponente
{
	private String description;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
		this.setBorder(new TitledBorder(description + ": "));
	}

	protected Chart(String description)
	{	
		super();
		this.setDescription(description);
	}

}
