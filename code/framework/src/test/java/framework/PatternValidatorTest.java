package framework;

import java.util.regex.Pattern;

import org.junit.*;

import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.validator.PatternValidator;
import de.dhbw.wi13c.jguicreator.data.validator.Validator;

public class PatternValidatorTest
{

	@Test
	public void emailPatternValidTest()
	{
		UiElementData<String> data = new TextfieldData();
		data.setValue("ferdi@busy.com");
		String pattern = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
		Validator<Pattern> patternValidator = new PatternValidator(data, pattern);

		//act, assert
		Assert.assertTrue(patternValidator.validate());
	}

	@Test
	public void emailPatternInvalidTest()
	{
		UiElementData<String> data = new TextfieldData();
		data.setValue("ferdi@busy.comas"); //more than four letters domain
		String pattern = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";
		Validator<Pattern> patternValidator = new PatternValidator(data, pattern);

		//act, assert
		Assert.assertFalse(patternValidator.validate());
	}
}
