package de.dhbw.wi13c.jguicreator.data;

import java.util.ArrayList;
import java.util.List;

public class Dataset
{
	private List<UiElement> elements = new ArrayList<>();

	public List<UiElement> getElements()
	{
		return elements;
	}

	public void setElements(List<UiElement> elements)
	{
		this.elements = elements;
	}
}
