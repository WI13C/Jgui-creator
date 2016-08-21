package framework;

import org.junit.*;

import de.dhbw.wi13c.jguicreator.data.uielements.TextfieldData;
import de.dhbw.wi13c.jguicreator.data.uielements.UiElementData;
import de.dhbw.wi13c.jguicreator.data.validator.SizeValidator;
import de.dhbw.wi13c.jguicreator.data.validator.Validator;

public class SizeValidatorTest
{

	@Test
	public void sizeIsValidTest()
	{
		//arrange
		UiElementData<String> data = new TextfieldData();
		data.setValue("Length");
		Validator<Object> sizeValidator = new SizeValidator(data, 2, 10);

		//act, assert
		Assert.assertTrue(sizeValidator.validate());
	}

	@Test
	public void sizeToShortTest()
	{
		//arrange
		UiElementData<String> data = new TextfieldData();
		data.setValue("S");
		Validator<Object> sizeValidator = new SizeValidator(data, 2, 10);

		//act, assert
		Assert.assertFalse(sizeValidator.validate());
	}

	@Test
	public void sizeToLongTest()
	{
		//arrange
		UiElementData<String> data = new TextfieldData();
		data.setValue("SuperLongInputBlaBlaBlubb");
		Validator<Object> sizeValidator = new SizeValidator(data, 2, 10);

		//act, assert
		Assert.assertFalse(sizeValidator.validate());
	}
}
