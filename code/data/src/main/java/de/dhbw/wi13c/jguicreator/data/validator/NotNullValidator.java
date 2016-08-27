package de.dhbw.wi13c.jguicreator.data.validator;

import de.dhbw.wi13c.jguicreator.data.annotation.AnnotationMessage;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;

/**
 * Checks if the given element is noll or not
 * @author Tobias Lang
 *
 */
public class NotNullValidator extends Validator<Object>
{

	UiElementData<?> element;

	public NotNullValidator(UiElementData<?> element)
	{

		this.element = element;
	}

	@Override
	public boolean validate()
	{
		Object elementValue = element.getDatafield().getValue();

		if(elementValue == null)
			return false;
		return true;
	}

	@Override
	public String getMessage()
	{
		return AnnotationMessage.NOTNULL_VALIDATOR_MESSAGE;
	}

	@Override
	public int compareTo(Validator<Object> o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
