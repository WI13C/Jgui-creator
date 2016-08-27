package de.dhbw.wi13c.jguicreator.data.validator;

import java.util.regex.Pattern;

import de.dhbw.wi13c.jguicreator.data.annotation.AnnotationMessage;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
/**
 * PatternValidator checks the element against a given pattern (regex)
 * @author Tobias Lang
 *
 */
public class PatternValidator extends Validator<Pattern>
{

	UiElementData<?> element;

	String pattern;

	public PatternValidator(UiElementData<?> element, String comparisonPattern)
	{
		this.element = element;
		this.pattern = comparisonPattern;

	}

	@Override
	public String getMessage()
	{
		return AnnotationMessage.PATTERN_VALIDATOR_MESSAGE;
	}

	@Override
	public boolean validate()
	{
		//System.out.println(element.getDatafield().getValue().toString());
		//System.out.println(pattern);
		return element.getDatafield().getValue().toString().matches("(?i)" + pattern);

	}

	@Override
	public int compareTo(Validator<Pattern> o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
