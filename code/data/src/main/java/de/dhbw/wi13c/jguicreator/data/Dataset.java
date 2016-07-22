package de.dhbw.wi13c.jguicreator.data;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;

public class Dataset
{
	private List<UiElementData> elements = new ArrayList<>();

	public List<UiElementData> getElements()
	{
		return elements;
	}

	public void setElements(List<UiElementData> elements)
	{
		this.elements = elements;
	}
}
