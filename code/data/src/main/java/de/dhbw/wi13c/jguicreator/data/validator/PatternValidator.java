package de.dhbw.wi13c.jguicreator.data.validator;

import java.util.regex.Pattern;

import de.dhbw.wi13c.jguicreator.data.annotation.AnnotationMessage;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;

public class PatternValidator extends Validator<Pattern>
{
	
	UiElementData element;

	public PatternValidator(UiElementData element)
	{
		this.element = element;
		
	}

	@Override
	public String getMessage()
	{
		return AnnotationMessage.PATTERN_VALIDATOR_MESSAGE;
	}

	@Override
	public boolean validate(Pattern pattern)
	{
		element.getDatafield().getValue().toString().matches(pattern.pattern());
		return false;
	}

	@Override
	public int compareTo(Validator<Pattern> o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
