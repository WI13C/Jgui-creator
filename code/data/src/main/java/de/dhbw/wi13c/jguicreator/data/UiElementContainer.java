package de.dhbw.wi13c.jguicreator.data;

import java.util.ArrayList;
import java.util.List;

import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;

/**
 * Used to be called Dataset.
 * Doesn't add any functionality compared to a conventional list for now.
 * @author Eric Schuh
 *
 */
public class UiElementContainer
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
