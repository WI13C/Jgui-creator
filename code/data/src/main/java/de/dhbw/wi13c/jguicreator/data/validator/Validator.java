package de.dhbw.wi13c.jguicreator.data.validator;

import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;

/**
 * Base class for every validator.
 * @author lukashessenthaler
 *
 * @param <T>
 */
public abstract class Validator<T> implements Comparable<Validator<T>>
{

	UiElementData<?> element;
	public abstract boolean validate();

	public abstract String getMessage();
	
	public void setUiElementData(UiElementData<?> uiElementData){
		this.element = uiElementData;
	}
}
